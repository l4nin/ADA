import Exceptions.DepartmentNotFoundException;
import Exceptions.EmployeeNotFoundException;
import Models.Departamentos;
import Models.Empresa;
import Models.Empleados;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Departamentos> departamentos = new LinkedList<>();
        departamentos.add(new Departamentos("Administracion", 3, empleados(
                new Empleados("Aitana", "Garcia Lopez", "12345678A", "Administrativa"),
                new Empleados("Aitor", "Martin Perez", "23456789B", "Contable"),
                new Empleados("Adria", "Ruiz Soto", "78901234G", "Directora"))));
        departamentos.add(new Departamentos("Tecnologia", 2, empleados(
                new Empleados("Clara", "Sanchez Ruiz", "34567890C", "Desarrolladora"),
                new Empleados("Pablo", "Lopez Diaz", "45678901D", "Analista"))));
        departamentos.add(new Departamentos("Ventas", 1, empleados(
                new Empleados("Anna", "Perez Gomez", "56789012E", "Comercial"))));

        Empresa empresa = new Empresa("Company Alan Souza", "B12345678", departamentos);

        empresa.printInfo();

        
        try {
            System.out.println("Empleados de Tecnologia:");
            empresa.findDepartmentEmployees("Tecnologia").forEach(Empleados::printInfo);
            System.out.println("Departamento encontrado:");
            empresa.findDepartmentByName("Ventas").printInfo();
            System.out.println("Empleado encontrado:");
            empresa.findEmployeeByNif("12345678A").printInfo();
        } catch (DepartmentNotFoundException | EmployeeNotFoundException exception) {
            System.out.println(exception.getMessage());
        }

        try {
            empresa.findDepartmentEmployees("Inexistente");
        } catch (DepartmentNotFoundException exception) {
            System.out.println(exception.getMessage());
        }

        try {
            empresa.findDepartmentByName("Inexistente");
        } catch (DepartmentNotFoundException exception) {
            System.out.println(exception.getMessage());
        }

        try {
            empresa.findEmployeeByNif("00000000Z");
        } catch (EmployeeNotFoundException exception) {
            System.out.println(exception.getMessage());
        }

        System.out.println("Departamentos con mas de un empleado: "
                + empresa.findDepartmentsWithMoreThanOneEmployee());
        System.out.println("Departamento(s) con menos empleados: "
                + empresa.findDepartmentsWithFewestEmployees());
    }

    private static LinkedList<Empleados> empleados(Empleados... empleados) {
        LinkedList<Empleados> resultado = new LinkedList<>();
        for (Empleados empleado : empleados) {
            resultado.add(empleado);
        }
        return resultado;
    }
}
