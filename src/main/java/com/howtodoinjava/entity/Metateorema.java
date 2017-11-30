package com.howtodoinjava.entity;
// Generated Mar 20, 2017 12:50:11 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * Metateorema generated by hbm2java
 */
public class Metateorema  implements java.io.Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue( strategy= GenerationType.SEQUENCE, generator="metateorema_id_seq")
    @SequenceGenerator(name="metateorema_id_seq", sequenceName="metateorema_id_seq")
     private int id;
     private Categoria categoria;
     private String enunciado;
     private byte[] metateoserializado;
     private Set dispones = new HashSet(0);

    public Metateorema() {
    }

    public Metateorema(int id, Categoria categoria, String enunciado, byte[] metateoserializado) {
        this.id = id;
        this.categoria = categoria;
        this.enunciado = enunciado;
        this.metateoserializado = metateoserializado;
    }
    
    public Metateorema(Categoria categoria, String enunciado, byte[] metateoserializado) {
        this.categoria = categoria;
        this.enunciado = enunciado;
        this.metateoserializado = metateoserializado;
    }


    public byte[] getMetateoserializado() {
        return metateoserializado;
    }

    public void setMetateoserializado(byte[] metateoserializado) {
        this.metateoserializado = metateoserializado;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Categoria getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public String getEnunciado() {
        return this.enunciado;
    }
    
    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public Set getDispones() {
        return this.dispones;
    }
    
    public void setDispones(Set dispones) {
        this.dispones = dispones;
    }
}

