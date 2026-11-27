package Exceptions;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(String nif) {
        super("No se ha encontrado el empleado con NIF: " + nif);
    }
}
