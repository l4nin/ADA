package Models;

import Intefaces.ICompany;
import Intefaces.IWorkers;
import Exceptions.DepartmentNotFoundException;
import Exceptions.EmployeeNotFoundException;
import java.util.LinkedList;

public class Empresa implements ICompany, IWorkers {

    private String nombre;
    private String cif;
    private LinkedList<Departamentos> departamentos;

    public Empresa(String nombre, String cif, LinkedList<Departamentos> departamentos) {
        this.nombre = nombre;
        this.cif = cif;
        this.departamentos = departamentos;
    }

    // Getters and setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public LinkedList<Departamentos> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(LinkedList<Departamentos> departamentos) {
        this.departamentos = departamentos;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "nombre='" + nombre + '\'' +
                ", cif='" + cif + '\'' +
                ", departamentos=" + departamentos +
                '}';
    }

    @Override
    public LinkedList<Empleados> findDepartmentEmployees(String department)
            throws DepartmentNotFoundException {
        for (Departamentos dep : departamentos) {
            if (dep.getNombre().equalsIgnoreCase(department)) {
                return new LinkedList<>(dep.getEmpleados());
            }
        }
        throw new DepartmentNotFoundException(department);
    }

    @Override
    public Departamentos findDepartmentByName(String department)
            throws DepartmentNotFoundException {
        for (Departamentos dep : departamentos) {
            if (dep.getNombre().equalsIgnoreCase(department)) {
                return dep;
            }
        }
        throw new DepartmentNotFoundException(department);
    }

    @Override
    public Empleados findEmployeeByNif(String nif) throws EmployeeNotFoundException {
        for (Departamentos departamento : departamentos) {
            for (Empleados empleado : departamento.getEmpleados()) {
                if (empleado.getNif().equalsIgnoreCase(nif)) {
                    return empleado;
                }
            }
        }
        throw new EmployeeNotFoundException(nif);
    }

    @Override
    public LinkedList<Departamentos> findDepartmentsWithMoreThanOneEmployee() {
        LinkedList<Departamentos> resultado = new LinkedList<>();
        for (Departamentos departamento : departamentos) {
            if (departamento.getEmpleados().size() > 1) {
                resultado.add(departamento);
            }
        }
        return resultado;
    }

    @Override
    public LinkedList<Departamentos> findDepartmentsWithFewestEmployees() {
        if (departamentos.isEmpty()) {
            return new LinkedList<>();
        }

        int minimo = Integer.MAX_VALUE;
        for (Departamentos departamento : departamentos) {
            minimo = Math.min(minimo, departamento.getEmpleados().size());
        }

        LinkedList<Departamentos> resultado = new LinkedList<>();
        for (Departamentos departamento : departamentos) {
            if (departamento.getEmpleados().size() == minimo) {
                resultado.add(departamento);
            }
        }
        return resultado;
    }

    public void printInfo() {
        System.out.println(this);
    }

}
