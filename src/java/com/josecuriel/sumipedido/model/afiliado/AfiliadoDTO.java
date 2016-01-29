

package com.josecuriel.sumipedido.model.afiliado;

import java.io.Serializable;
import java.util.Date;


public class AfiliadoDTO implements Serializable{
    
    private String tipodocutmento;
    private String numeroidentidad;
    private String primer_apellido;
    private String seg_apellido;
    private String primer_nombre;
    private String seg_nombre;
    private Date fecha_nacimiento;
    private int edad;
    private String departamento;
    private String municipio;
    private String zona;
    private String regimen;
    private String tipo_afiliado;
    private int nivel;
    private String sexo;
    private String telefono;

    public AfiliadoDTO() {
    }

    public AfiliadoDTO(String tipodocutmento, String numeroidentidad, String primer_apellido, String seg_apellido, String primer_nombre, String seg_nombre, Date fecha_nacimiento, int edad, String departamento, String municipio, String zona, String regimen, String tipo_afiliado, int nivel, String sexo, String telefono) {
        this.tipodocutmento = tipodocutmento;
        this.numeroidentidad = numeroidentidad;
        this.primer_apellido = primer_apellido;
        this.seg_apellido = seg_apellido;
        this.primer_nombre = primer_nombre;
        this.seg_nombre = seg_nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.edad = edad;
        this.departamento = departamento;
        this.municipio = municipio;
        this.zona = zona;
        this.regimen = regimen;
        this.tipo_afiliado = tipo_afiliado;
        this.nivel = nivel;
        this.sexo = sexo;
        this.telefono = telefono;
    }

    
    

    public String getTipodocutmento() {
        return tipodocutmento;
    }

    public void setTipodocutmento(String tipodocutmento) {
        this.tipodocutmento = tipodocutmento;
    }

    public String getNumeroidentidad() {
        return numeroidentidad;
    }

    public void setNumeroidentidad(String numeroidentidad) {
        this.numeroidentidad = numeroidentidad;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSeg_apellido() {
        return seg_apellido;
    }

    public void setSeg_apellido(String seg_apellido) {
        this.seg_apellido = seg_apellido;
    }

    public String getPrimer_nombre() {
        return primer_nombre;
    }

    public void setPrimer_nombre(String primer_nombre) {
        this.primer_nombre = primer_nombre;
    }

    public String getSeg_nombre() {
        return seg_nombre;
    }

    public void setSeg_nombre(String seg_nombre) {
        this.seg_nombre = seg_nombre;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getTipo_afiliado() {
        return tipo_afiliado;
    }

    public void setTipo_afiliado(String tipo_afiliado) {
        this.tipo_afiliado = tipo_afiliado;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
            
    
}
