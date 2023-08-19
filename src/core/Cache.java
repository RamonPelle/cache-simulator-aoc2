package core;


import java.io.IOException;
import java.util.List;

import utils.Parser;
import utils.Reader;


public class Cache {

    private Integer nSets;
    private Integer bSize;
    private Integer assoc;
    private String substitution;
    private Boolean flag_saida;
    private String file;
    private Integer numOffset;
    private Integer numIndex;
    private Integer numTag;
    private Integer instructionsExecuted;
    private Integer hits = 0;
    private Integer missCompulsorio = 0;
    private Integer missConflito = 0;
    private Integer missCapacidade = 0;
    private Integer[][] infoCache;

    public static Cache build(String nSets, String bSize, String assoc, String substitution, String flag_saida,
            String file) {
        Cache newCache = new Cache();
        newCache.setNsets(Integer.parseInt(nSets));
        newCache.setBsize(Integer.parseInt(bSize));
        newCache.setAssoc(Integer.parseInt(assoc));
        newCache.setSubstitution(substitution);
        newCache.setFlag_saida(Parser.stringToBoolean(flag_saida));
        newCache.setFile(file);
        newCache.setInfoCache(buildInfoCache(Integer.parseInt(nSets) * Integer.parseInt(assoc), 2));
        return newCache;
    }

    public void readAddresses() throws IOException {
        Reader r = new Reader();
        r.readFile("resources/addresses/" + this.file);
        List<String> addresses = r.getAddresses();
        this.instructionsExecuted = addresses.size();
        divideAddress();
        for (String address : addresses) {
            verifyCache(address);
        }
        this.printResults();
    }

    private void divideAddress() {
        this.numOffset = (int) (Math.log10(this.bSize) / Math.log10(2));
        this.numIndex = (int) (Math.log10(this.nSets) / Math.log10(2));
        this.numTag = 32 - numOffset - numIndex;
    }

    private void verifyCache(String address) {
        Integer value = Integer.parseInt(address, 2);
        Integer indice = ((value >> this.numOffset) & ((int) (Math.pow(2, this.numIndex) - 1)));
        Integer tag = value >> (this.numOffset + this.numIndex);
        if (this.infoCache[indice][0] == 0) {
            this.missCompulsorio++;
            this.infoCache[indice][0] = 1;
            this.infoCache[indice][1] = tag;
        } else {
            if (this.infoCache[indice][1] == tag) {
                this.hits++;
            } else {
                verifyMiss();
                this.infoCache[indice][0] = 1;
                this.infoCache[indice][1] = tag;
            }
        }
    }

    private void verifyMiss() {
        boolean missChanged = false;
        for (int i = 0; i < this.nSets * this.assoc; i++) {
            if (this.infoCache[i][0] == 0) {
                this.missConflito++;
                missChanged = true;
                break;
            }
        }
        if (!missChanged)
            missCapacidade++;
    }

    private void printResults() {
        Double[] results = calculateRatios();
        if(this.flag_saida){
            System.out.print(this.instructionsExecuted + " ");
            for(Double result: results){
                System.out.print(result + " ");
            }
            System.out.println();
        }else{
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("Instruções Executadas: " + this.instructionsExecuted);
            System.out.println("Hit Ratio: " + results[0]*100 + "%");
            System.out.println("Miss Ratio: " + results[1]*100 + "%");
            System.out.println("Misses Compulsórios: " + results[2]*100 + "%");
            System.out.println("Misses de Conflito: " + results[3]*100 + "%");
            System.out.println("Misses de Capacidade: " + results[4]*100 + "%");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        }
    }

    private Double[] calculateRatios() {
        Double somaMisses = (double) (missCapacidade + missConflito + missCompulsorio);
        Double IE = (double) instructionsExecuted;
        Double hitRatio = ((double) hits / IE);
        Double missRatio = (somaMisses/ IE);
        Double missCompulsorioRatio = ((double) missCompulsorio / somaMisses);
        Double missCapacidadeRatio = ((double) missCapacidade / somaMisses);
        Double missConflitoRatio = ((double) missConflito / somaMisses);
        return new Double[] {hitRatio, missRatio, missCompulsorioRatio, missCapacidadeRatio, missConflitoRatio};
    }

    private static Integer[][] buildInfoCache(Integer x, Integer y) {
        Integer matrixInfoCache[][] = new Integer[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                matrixInfoCache[i][j] = 0;
            }
        }
        return matrixInfoCache;
    }

    public Integer getNsets() {
        return nSets;
    }

    public void setNsets(Integer nsets) {
        this.nSets = nsets;
    }

    public Integer getBsize() {
        return bSize;
    }

    public void setBsize(Integer bsize) {
        this.bSize = bsize;
    }

    public Integer getAssoc() {
        return assoc;
    }

    public void setAssoc(Integer assoc) {
        this.assoc = assoc;
    }

    public String getSubstitution() {
        return substitution;
    }

    public void setSubstitution(String substitution) {
        this.substitution = substitution;
    }

    public Boolean getFlag_saida() {
        return flag_saida;
    }

    public void setFlag_saida(Boolean flag_saida) {
        this.flag_saida = flag_saida;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Integer[][] getInfoCache() {
        return infoCache;
    }

    public void setInfoCache(Integer[][] infoCache) {
        this.infoCache = infoCache;
    }

    @Override
    public String toString() {
        return "Cache{" +
                "nsets=" + nSets +
                ", bsize=" + bSize +
                ", assoc=" + assoc +
                ", substitution='" + substitution + '\'' +
                ", flag_saida=" + flag_saida +
                '}';
    }
}
