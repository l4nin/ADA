package Exceptions;

public class DepartmentNotFoundException extends Exception {
    public DepartmentNotFoundException(String department) {
        super("No se ha encontrado el departamento: " + department);
    }
}
