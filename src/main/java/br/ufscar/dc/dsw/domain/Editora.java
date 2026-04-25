package br.ufscar.dc.dsw.domain;

public class Editora {
    private Long id;
    private String cnpj;
    private String nome;

    public Editora(Long id) {
        this.id = id;
    }

    public Editora(String cnpj, String nome) {
        this.cnpj = cnpj;
        this.nome = nome;
    }

    public Editora(Long id, String cnpj, String nome) {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}