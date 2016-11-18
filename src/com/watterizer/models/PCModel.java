/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.watterizer.models;

/**
 *
 * @author 15153769
 */
public class PCModel {
    
    private String nome = "";
    private String mac = "";
    private String setor = "";
    private int arduinoId;
    private int setorId = 0;
    private int type = 0;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public int getSetorId() {
        return setorId;
    }

    public void setSetorId(int setorId) {
        this.setorId = setorId;
    }
    
    public int getArduinoId() {
        return arduinoId;
    }
    
    public void setArduinoId(int arduinoId) {
        this.arduinoId = arduinoId;
    }
    
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
