package Intefaces;

import Models.Empleados;
import Models.Departamentos;
import Exceptions.DepartmentNotFoundException;

import java.util.LinkedList;

public interface ICompany {
    LinkedList<Empleados> findDepartmentEmployees(String department)
            throws DepartmentNotFoundException;

    Departamentos findDepartmentByName(String department)
            throws DepartmentNotFoundException;
}
