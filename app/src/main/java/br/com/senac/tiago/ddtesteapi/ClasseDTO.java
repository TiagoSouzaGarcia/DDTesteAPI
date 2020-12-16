package br.com.senac.tiago.ddtesteapi;

public class ClasseDTO {

    private String name;


    public ClasseDTO() {
    }

    public ClasseDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Classe getClasse() {
        Classe classe = new Classe();
        classe.setNomeClasse(this.name);
        return classe;
    }
}

