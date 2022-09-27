package br.ufpb.dcx.tdm.facade;

public class File {
    private String name;
    private Integer classification;

    public File(String name, Integer classification) {
        this.name = name;
        this.classification = classification;
    }
    public File() {
        this("", 0);
    }

    public Integer getClassification() {
        return classification;
    }

}
