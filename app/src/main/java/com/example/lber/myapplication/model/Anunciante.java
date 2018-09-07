package com.example.lber.myapplication.model;

import android.text.Editable;

import java.util.Objects;

public class Anunciante {

    private String nome;
    private String cpf_cnpj;
    private String contato;
    private String email;

    public Anunciante() {
    }

    public Anunciante(String nome, String cpf_cnpj, String contato, String email) {
        this.nome = nome;
        this.cpf_cnpj = cpf_cnpj;
        this.contato = contato;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Anunciante{" +
                "nome='" + nome + '\'' +
                ", cpf_cnpj='" + cpf_cnpj + '\'' +
                ", contato='" + contato + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Anunciante)) return false;
        Anunciante that = (Anunciante) o;
        return Objects.equals(getNome(), that.getNome()) &&
                Objects.equals(getCpf_cnpj(), that.getCpf_cnpj()) &&
                Objects.equals(getContato(), that.getContato()) &&
                Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getNome(), getCpf_cnpj(), getContato(), getEmail());
    }
}
