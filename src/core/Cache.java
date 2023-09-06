package core;

import utils.Parser;
import utils.Reader;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

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
    private Integer[][] cacheValid;//x da matriz = nsets // y da matriz = assoc;
    private Integer[][] cacheTag;//x da matriz = nsets // y da matriz = assoc;

    public static Cache build(String nSets, String bSize, String assoc, String substitution, String flag_saida,
                              String file) {
        Cache newCache = new Cache();
        newCache.setNsets(Integer.parseInt(nSets));
        newCache.setBsize(Integer.parseInt(bSize));
        newCache.setAssoc(Integer.parseInt(assoc));
        newCache.setSubstitution(substitution);
        newCache.setFlag_saida(Parser.stringToBoolean(flag_saida));
        newCache.setFile(file);
        newCache.setCacheTag(new Integer[newCache.nSets][newCache.assoc]);
        newCache.setCacheValid(new Integer[newCache.nSets][newCache.assoc]);
        return newCache;
    }

    public void readAddresses() throws IOException {
        Reader r = new Reader();
        r.readFile("resources\\addresses\\" + this.file);
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

        Boolean hit = false;
        Boolean missCompulsorio = false;

        for (int i = 0; i < this.assoc; i++) {
            if (this.cacheValid[indice][i] != 1) {
                missCompulsorio = true;
                this.missCompulsorio++;
                this.cacheValid[indice][i] = 1;
                this.cacheTag[indice][i] = tag;
                break;
            } else if (Objects.equals(cacheTag[indice][i], tag)) {
                hit = true;
                this.hits++;
                break;
            }
        }
        if (!hit && !missCompulsorio) {
            this.verifyMiss();
            int assocReplace = this.trataFalta();
            this.cacheTag[indice][assocReplace] = tag;
            this.cacheValid[indice][assocReplace] = 1;
        }
    }


    private void verifyMiss() {
        for (int i = 0; i < this.nSets; i++) {
            for (int j = 0; j < this.assoc; j++) {
                if (this.cacheValid[i][j] == -1) {
                    this.missConflito++;
                    return;
                }
            }
        }
        this.missCapacidade++;
    }

    private int trataFalta() {
        Random random = new Random();
        int replaceIndex = random.nextInt(this.assoc);
        return replaceIndex;
    }

    private void printResults() {
        Double[] results = calculateRatios();
        if (this.flag_saida) {
            System.out.print(this.instructionsExecuted + " ");
            for (Double result : results) {
                System.out.print(result + " ");
            }
            System.out.println();
        } else {
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("Instruções Executadas: " + this.instructionsExecuted);
            System.out.println("Hit Ratio: " + results[0] * 100 + "%");
            System.out.println("Miss Ratio: " + results[1] * 100 + "%");
            System.out.println("Misses Compulsórios: " + results[2] * 100 + "%");
            System.out.println("Misses de Conflito: " + results[3] * 100 + "%");
            System.out.println("Misses de Capacidade: " + results[4] * 100 + "%");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        }
    }

    private Double[] calculateRatios() {
        Double somaMisses = (double) (missCapacidade + missConflito + missCompulsorio);
        Double IE = (double) instructionsExecuted;
        Double hitRatio = ((double) hits / IE);
        Double missRatio = (somaMisses / IE);
        Double missCompulsorioRatio = ((double) missCompulsorio / somaMisses);
        Double missCapacidadeRatio = ((double) missCapacidade / somaMisses);
        Double missConflitoRatio = ((double) missConflito / somaMisses);
        return new Double[]{hitRatio, missRatio, missCompulsorioRatio, missCapacidadeRatio, missConflitoRatio};
    }

    public void initializeValidAndTag() {
        for (int i = 0; i < this.nSets; i++) {
            for (int j = 0; j < this.assoc; j++) {
                this.cacheTag[i][j] = -1;
                this.cacheValid[i][j] = -1;
            }
        }
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

    public Integer getnSets() {
        return nSets;
    }

    public void setnSets(Integer nSets) {
        this.nSets = nSets;
    }

    public Integer getbSize() {
        return bSize;
    }

    public void setbSize(Integer bSize) {
        this.bSize = bSize;
    }

    public Integer getNumOffset() {
        return numOffset;
    }

    public void setNumOffset(Integer numOffset) {
        this.numOffset = numOffset;
    }

    public Integer getNumIndex() {
        return numIndex;
    }

    public void setNumIndex(Integer numIndex) {
        this.numIndex = numIndex;
    }

    public Integer getNumTag() {
        return numTag;
    }

    public void setNumTag(Integer numTag) {
        this.numTag = numTag;
    }

    public Integer getInstructionsExecuted() {
        return instructionsExecuted;
    }

    public void setInstructionsExecuted(Integer instructionsExecuted) {
        this.instructionsExecuted = instructionsExecuted;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getMissCompulsorio() {
        return missCompulsorio;
    }

    public void setMissCompulsorio(Integer missCompulsorio) {
        this.missCompulsorio = missCompulsorio;
    }

    public Integer getMissConflito() {
        return missConflito;
    }

    public void setMissConflito(Integer missConflito) {
        this.missConflito = missConflito;
    }

    public Integer getMissCapacidade() {
        return missCapacidade;
    }

    public void setMissCapacidade(Integer missCapacidade) {
        this.missCapacidade = missCapacidade;
    }

    public Integer[][] getCacheValid() {
        return cacheValid;
    }

    public void setCacheValid(Integer[][] cacheValid) {
        this.cacheValid = cacheValid;
    }

    public Integer[][] getCacheTag() {
        return cacheTag;
    }

    public void setCacheTag(Integer[][] cacheTag) {
        this.cacheTag = cacheTag;
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
