package Intefaces;

import Exceptions.EmployeeNotFoundException;
import Models.Departamentos;
import Models.Empleados;

import java.util.LinkedList;

public interface IWorkers {
    Empleados findEmployeeByNif(String nif) throws EmployeeNotFoundException;

    LinkedList<Departamentos> findDepartmentsWithMoreThanOneEmployee();

    LinkedList<Departamentos> findDepartmentsWithFewestEmployees();
}
