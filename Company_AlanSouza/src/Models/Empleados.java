package Models;

import java.util.Comparator;

public class Empleados implements Comparable<Empleados> {
    private String nombre;
    private String apellidos;
    private String nif;
    private String cargo;

    // Constructor
    public Empleados(String nombre, String apellidos, String nif, String cargo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.cargo = cargo;
    }

    // Getters and setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellidos;
    }

    public void setApellido(String apellido) {
        this.apellidos = apellido;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Empleados{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", nif='" + nif + '\'' +
                ", cargo='" + cargo + '\'' +
                '}';
    }

    @Override
    public int compareTo(Empleados otro) {
        return Comparator.comparing(Empleados::getCargo, String.CASE_INSENSITIVE_ORDER)
                .thenComparing(Empleados::getApellidos, String.CASE_INSENSITIVE_ORDER)
                .thenComparing(Empleados::getNif, String.CASE_INSENSITIVE_ORDER)
                .compare(this, otro);
    }

    public void printInfo() {
        System.out.println(this);
    }
}
