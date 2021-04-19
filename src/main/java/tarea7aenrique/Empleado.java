/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea7aenrique;

import java.time.LocalDate;

/**
 *
 * @author enrique
 */
public class Empleado {

    private String apellidos;
    private String nombre;
    private String DNI;
    private String puesto;
    private LocalDate fechaIni;
    private LocalDate fechaFin;
    private String tlf;
    private Boolean evaluador;
    private Boolean coord;

    public Empleado() {
    }

    public Empleado(String apellidos, String nombre, String DNI, String puesto, LocalDate fechaIni, LocalDate fechaFin, String tlf, Boolean evaluador, Boolean coord) {
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.DNI = DNI;
        this.puesto = puesto;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.tlf = tlf;
        this.evaluador = evaluador;
        this.coord = coord;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public LocalDate getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(LocalDate fechaIni) {
        this.fechaIni = fechaIni;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public Boolean getEvaluador() {
        return evaluador;
    }

    public void setEvaluador(Boolean evaluador) {
        this.evaluador = evaluador;
    }

    public Boolean getCoord() {
        return coord;
    }

    public void setCoord(Boolean coord) {
        this.coord = coord;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {

        if (evaluador = true && coord == true) {
            return apellidos + "," + nombre + "," + DNI + "," + puesto + "," + fechaIni + "," + fechaFin + "," + tlf + "," + "Sí" + "," + "Sí";
        } else if (evaluador == true && coord == false) {
            return apellidos + "," + nombre + "," + DNI + "," + puesto + "," + fechaIni + "," + fechaFin + "," + tlf + "," + "Sí" + "," + "No";
        } else if (evaluador == false && coord == true) {
            return apellidos + "," + nombre + "," + DNI + "," + puesto + "," + fechaIni + "," + fechaFin + "," + tlf + "," + "No" + "," + "Sí";
        } else {
            return apellidos + "," + nombre + "," + DNI + "," + puesto + "," + fechaIni + "," + fechaFin + "," + tlf + "," + "No" + "," + "No";
        }
    }
}
