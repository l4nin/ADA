import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // creamos el archivo CSV con la cabecera
        String ruta = "alumnos.csv";
        crearCSV(ruta);

        // anadimos un alumno al archivo CSV
        ayadirAlumno(ruta);

        // leemos el archivo CSV y mostramos su contenido
        ArrayList<String> contenido = new ArrayList<String>();

        //Leer el contenido del CSV a un ArrayList
        leerCSV(ruta, contenido);

        // 4. Imprimir el ArrayList por pantalla
        imprimirLista(contenido);

    }

    /**
     * Apartado d: escribe la cabecera "NIA;NOMBRE;DIRECCIÓN;" en un archivo de texto.
     * Si el archivo ya existía, se sobrescribe.
     * @param ruta
     */
    public static void crearCSV(String ruta) {
        File f = new File(ruta);

        // Si el fichero ya existe, no hacemos nada para no borrar los alumnos anteriores
        if (f.exists()) {
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            bw.write("NIA;NOMBRE;DIRECCIÓN;");
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al crear el fichero: " + e.getMessage());
        }
    }

    public static void ayadirAlumno(String ruta){
        // Creamos un objeto Scanner para leer la entrada del usuario
        Scanner cs = new Scanner(System.in);

        // Pedimos al usuario que introduzca los datos del alumno
        System.out.println("Introduce el NIA del alumno:");
        String nia = cs.nextLine().trim();
        System.out.println("Introduce el nombre del alumno:");
        String nombre = cs.nextLine().trim();
        System.out.println("Introduce la dirección del alumno:");
        String direccion = cs.nextLine().trim();

        // Juntamos los datos en un registro separado por punto y coma
        String registro = nia + ";" + nombre + ";" + direccion + ";";

        // Escribimos el registro en el archivo
        File archivo = new File(ruta);

        try (FileWriter escritor = new FileWriter(archivo, true)) {

            // "true" para añadir al final del archivo
            escritor.write(registro);
            // Salto de línea para el siguiente registro
            escritor.write(System.lineSeparator());

            System.out.println("Alumno añadido correctamente en: " + archivo.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero: " + e.getMessage());
        }

    }

    public static ArrayList<String> leerCSV(String ruta, ArrayList<String> lista) {

        //try-with-resources: el Scanner se cierra solo al terminar
        try (Scanner sc = new Scanner(new File(ruta))) {

            //Mientras queden líneas por leer...
            while (sc.hasNextLine()) {

                //Leemos la línea y la añadimos a la lista
                String linea = sc.nextLine();
                lista.add(linea);
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el fichero: " + ruta);
        }

        //Devolvemos la lista (vacía si hubo error)
        return lista;
    }

    public static void imprimirLista(ArrayList<String> lista) {
        // Recorremos la lista y mostramos cada elemento en una línea
        for (String elemento : lista) {
            System.out.println(elemento);
        }
    }


}