package com.watterizer.util;

import java.sql.Date;
import java.sql.Time;

public class UserModel {
    
    private int id;
    private String nome;
    private String username;
    private String email;
    private String senha;
    private String telefone;
    private Date dataCadastro;
    private Time horaInicioExpediente;
    private Time horaFimExpediente;
    private Time horaAlmoco;
    private int idPergunta;
    private String respostaPergunta;
    private int idSetor;
    private int idPerfil;

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

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Time getHoraInicioExpediente() {
        return horaInicioExpediente;
    }

    public void setHoraInicioExpediente(Time horaInicioExpediente) {
        this.horaInicioExpediente = horaInicioExpediente;
    }

    public Time getHoraFimExpediente() {
        return horaFimExpediente;
    }

    public void setHoraFimExpediente(Time horaFimExpediente) {
        this.horaFimExpediente = horaFimExpediente;
    }

    public Time getHoraAlmoco() {
        return horaAlmoco;
    }

    public void setHoraAlmoco(Time horaAlmoco) {
        this.horaAlmoco = horaAlmoco;
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

    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }
    
}
