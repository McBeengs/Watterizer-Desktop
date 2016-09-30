package com.watterizer.models;

import java.sql.Time;

public class UserModel {

    private int id;
    private String username;
    private String senha;
    private String email;
    private String nome;
    private String telefone;
    private Time horaEntrada;
    private Time horaIntervalo;
    private Time expediente;
    private int idPerfil;
    private int idPergunta;
    private String respostaPergunta;
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(int idPergunta) {
        this.idPergunta = idPergunta;
    }

    public String getRespostaPergunta() {
        return respostaPergunta;
    }

    public void setRespostaPergunta(String respostaPergunta) {
        this.respostaPergunta = respostaPergunta;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Time getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Time horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Time getHoraIntervalo() {
        return horaIntervalo;
    }

    public void setHoraIntervalo(Time horaIntervalo) {
        this.horaIntervalo = horaIntervalo;
    }

    public Time getExpediente() {
        return expediente;
    }

    public void setExpediente(Time expediente) {
        this.expediente = expediente;
    }
}
