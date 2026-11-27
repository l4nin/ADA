import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        File fichero = crearFichero();

        if (!esFichero(fichero)) {
            System.out.println("El fichero no existe o no es un fichero.");
            return;
        }
        System.out.println("Fichero correcto: " + fichero.getAbsolutePath());

        agregarPersonas(fichero);

        ArrayList<String> contenido = leerFichero(fichero.getAbsolutePath());

        System.out.println("Campos de la tabla: " + contarCampos(contenido));
        System.out.println("Líneas (sin cabecera): " + contarLineas(contenido));

        mostrarTabla(contenido);
    }


    private static final String NOMBRE_FICHERO = "alan.csv";
    public static File crearFichero() {
        File carpeta = new File("bd");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        File fichero = new File(carpeta, NOMBRE_FICHERO);

        try {
            if (fichero.createNewFile()) {
                try (PrintWriter pw = new PrintWriter(new FileWriter(fichero))) {
                    pw.println("NOMBRE;APELLIDOS;FECHA_NACIMIENTO");
                }
            }
        } catch (IOException e) {
            System.out.println("Error al crear el fichero: " + e.getMessage());
        }

        return fichero;
    }

    public static ArrayList<String> leerFichero(String ruta) {
        ArrayList<String> lineas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }

        return lineas;
    }

    public static boolean esFichero(File f) {
        return f.exists() && f.isFile();
    }

    public static int contarCampos(ArrayList<String> contenido) {
        if (contenido.isEmpty()) {
            return 0;
        }
        return contenido.get(0).split(";").length;
    }

    public static int contarLineas(ArrayList<String> contenido) {
        if (contenido.isEmpty()) {
            return 0;
        }
        return contenido.size() - 1;
    }

    public static void agregarPersonas(File fichero) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine();
        System.out.print("Fecha de nacimiento (dd/mm/aaaa): ");
        String fecha = sc.nextLine();

        try (PrintWriter pw = new PrintWriter(new FileWriter(fichero, true))) {
            pw.println(nombre + ";" + apellidos + ";" + fecha);
            pw.println("TuNombre;TusApellidos;01/01/2000");
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
        }
    }

    public static void mostrarTabla(ArrayList<String> contenido) {
        if (contenido.isEmpty()) {
            System.out.println("El fichero está vacío.");
            return;
        }

        int columnas = contenido.get(0).split(";").length;
        String separador = "-".repeat(columnas * 22);

        System.out.println(separador);
        for (int i = 0; i < contenido.size(); i++) {
            String[] campos = contenido.get(i).split(";");
            for (String campo : campos) {
                System.out.printf("%-22s", campo);
            }
            System.out.println();

            if (i == 0) {
                System.out.println(separador);
            }
        }
        System.out.println(separador);
    }
}