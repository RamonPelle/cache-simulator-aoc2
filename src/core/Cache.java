package core;


import utils.Parser;


public class Cache {
    private Integer nsets;
    private Integer bsize;
    private Integer assoc;
    private String substitution;
    private Boolean flag_saida;

    public static Cache build(String nsets, String bsize, String assoc, String substitution, String flag_saida){
        Cache newCache = new Cache();
        newCache.setNsets(Integer.parseInt(nsets));
        newCache.setBsize(Integer.parseInt(bsize));
        newCache.setAssoc(Integer.parseInt(assoc));
        newCache.setSubstitution(substitution);
        newCache.setFlag_saida(Parser.stringToBoolean(flag_saida));
        return newCache;
    }

    public Integer getNsets() {
        return nsets;
    }

    public void setNsets(Integer nsets) {
        this.nsets = nsets;
    }

    public Integer getBsize() {
        return bsize;
    }

    public void setBsize(Integer bsize) {
        this.bsize = bsize;
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

    @Override
    public String toString() {
        return "Cache{" +
                "nsets=" + nsets +
                ", bsize=" + bsize +
                ", assoc=" + assoc +
                ", substitution='" + substitution + '\'' +
                ", flag_saida=" + flag_saida +
                '}';
    }
}
