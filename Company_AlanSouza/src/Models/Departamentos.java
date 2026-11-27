package Models;

import java.util.LinkedList;

public class Departamentos {
    private String nombre;
    private int tamanyo;
    private LinkedList<Empleados> empleados;

    // Constructor
    public Departamentos(String nombre, int tamanyo, LinkedList<Empleados> empleados) {
        this.nombre = nombre;
        this.tamanyo = tamanyo;
        this.empleados = empleados;
    }

    // Getters and setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTamanyo() {
        return tamanyo;
    }

    public void setTamanyo(int tamanyo) {
        this.tamanyo = tamanyo;
    }

    public LinkedList<Empleados> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(LinkedList<Empleados> empleados) {
        this.empleados = empleados;
    }


    //to string
    @Override
    public String toString() {
        return "Departamentos{" +
                "nombre='" + nombre + '\'' +
                ", tamanyo=" + tamanyo +
                ", empleados=" + empleados +
                '}';
    }

    public void printInfo() {
        System.out.println(this);
    }
}
