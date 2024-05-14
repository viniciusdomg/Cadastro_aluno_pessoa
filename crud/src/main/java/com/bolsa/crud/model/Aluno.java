package com.bolsa.crud.model;

import javax.persistence.*;

@Entity
@Table(name="aluno", schema="graduacao")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String matricula;

    @Column(nullable = false)
    private int ano_entrada;

    @Column(name = "id_pessoa", nullable = false)
    private int id_pessoa;

    private String cpfPessoa;

    public void setId(Integer id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public int getAno_entrada() {
        return ano_entrada;
    }
    public void setAno_entrada(int ano_entrada) {
        this.ano_entrada = ano_entrada;
    }
    public int getId_pessoa() {
        return id_pessoa;
    }
    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }
    @Transient
    public String getCpfPessoa(){
        return this.cpfPessoa;
    }
    public void setCpfPessoa(String cpfPessoa) {
        this.cpfPessoa = cpfPessoa;
    }
}
