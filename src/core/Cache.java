package core;


import utils.Parser;
import utils.Reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
    private List<List<String>> infoCache = new ArrayList(); //TODO verificar se faz sentido uma logica de matriz assim

    public static Cache build(String nSets, String bSize, String assoc, String substitution, String flag_saida, String file){
        Cache newCache = new Cache();
        newCache.setNsets(Integer.parseInt(nSets));
        newCache.setBsize(Integer.parseInt(bSize));
        newCache.setAssoc(Integer.parseInt(assoc));
        newCache.setSubstitution(substitution);
        newCache.setFlag_saida(Parser.stringToBoolean(flag_saida));
        newCache.setFile(file);
        return newCache;
    }

    public void readAddresses() throws IOException {
        Reader r = new Reader();
        r.readFile("resources/addresses/" + this.file);
        List<String> addresses = r.getAddresses();
        divideAddress();
        for(String address : addresses){
            verifyCache(address);
        }
    }

    private void divideAddress(){
        this.numOffset = (int) (Math.log(this.bSize) / Math.log(2));
        this.numIndex = (int) (Math.log(this.nSets) / Math.log(2));
        this.numTag = 32 - numOffset - numIndex;
    }

    private void verifyCache(String address){
        Integer value = Integer.parseInt(address, 2);
        Integer tag = value >> (this.numOffset +  this.numIndex);
        Integer indice = (value >> this.numOffset) & (int)Math.pow(2,this.numIndex-1);
        //TODO nao sei trabalhar com matriz sou fofo burrinho

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
