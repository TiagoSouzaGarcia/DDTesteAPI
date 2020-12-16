package br.com.senac.tiago.ddtesteapi;

public class Classe {

    private String nomeClasse;
    private String dadosVida;

    public Classe(String nomeClasse, String dadosVida) {
        this.nomeClasse = nomeClasse;
        this.dadosVida = dadosVida;
    }

    public Classe() {
    }

    public String getNomeClasse() {
        return nomeClasse;
    }

    public void setNomeClasse(String nomeClasse) {
        this.nomeClasse = nomeClasse;
    }

    public String getDadosVida() {
        return dadosVida;
    }

    public void setDadosVida(String dadosVida) {
        this.dadosVida = dadosVida;
    }

   /* @Override
    public String toString() {
        return "Nome da Classe" + nomeClasse +"/n" + "Dados de vida" +  dadosVida;
    }*/
}
