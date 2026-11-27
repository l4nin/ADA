import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;


public class Main {
    public static void main(String[] args) {

        // Cambia esta ruta por la de un archivo real de tu equipo
        String ruta = "C:\\Users\\alan\\Documents\\prueba.txt";

        // Llamadas a los métodos para mostrar el contenido del archivo de diferentes maneras
        System.out.println("===== a) Contenido con FileReader =====");
        mostrarConFileReader(ruta);

        // Llamada al método para guardar en LinkedList y mostrar el contenido
        System.out.println("\n===== b) LinkedList en minúsculas =====");
        LinkedList<String> lista = guardarEnLinkedList(ruta);
        for (String linea : lista) {
            System.out.println(linea);
        }

        // Llamada al método para mostrar el contenido con Files.readAllLines
        System.out.println("\n===== c) Contenido con Files.readAllLines =====");
        mostrarConReadAllLines(ruta);

    }

    /**
     * Apartado a: comprueba que la ruta sea un archivo con extensión .txt, .odt o .doc
     * y muestra su contenido carácter a carácter usando FileReader.
     *
     * @param ruta ruta completa del archivo
     */
    public static void mostrarConFileReader(String ruta) {
        File archivo = new File(ruta);

        // 1. Comprobamos que sea un archivo (no una carpeta ni algo inexistente)
        if (!archivo.isFile()) {
            System.out.println("La ruta no corresponde a un archivo: " + ruta);
            return;
        }
        // 2. Comprobamos la extensión (en minúsculas para no distinguir mayúsculas)
        String nombre = archivo.getName().toLowerCase();
        if (!(nombre.endsWith(".txt") || nombre.endsWith(".odt") || nombre.endsWith(".doc"))) {
            System.out.println("El archivo no es un archivo de texto (.txt, .odt o .doc): " + archivo.getName());
            return;
        }

        // 3. Leemos carácter a carácter con FileReader (try-with-resources lo cierra solo)
        try (FileReader lector = new FileReader(archivo)) {
            int caracter;
            // read() devuelve -1 cuando se llega al final del archivo
            while ((caracter = lector.read()) != -1) {
                System.out.print((char) caracter);
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }


    /**
     * Apartado b: lee un archivo línea a línea con BufferedReader y guarda
     * cada línea en minúsculas en una LinkedList.
     *
     * @param ruta ruta completa del archivo
     * @return LinkedList con las líneas en minúsculas (vacía si hubo algún problema)
     */
    public static LinkedList<String> guardarEnLinkedList(String ruta) {
        LinkedList<String> lista = new LinkedList<>();
        File archivo = new File(ruta);

        // Comprobamos que sea un archivo
        if (!archivo.isFile()) {
            System.out.println("La ruta no corresponde a un archivo: " + ruta);
            return lista; // devolvemos la lista vacía
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            // readLine() devuelve null cuando no quedan más líneas
            while ((linea = br.readLine()) != null) {
                lista.add(linea.toLowerCase()); // guardamos cada línea en minúsculas
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Apartado c: lee un archivo línea a línea con Files.readAllLines() y muestra
     * su contenido en la consola.
     *
     * @param ruta ruta completa del archivo
     */
    public static void mostrarConReadAllLines(String ruta) {
        File archivo = new File(ruta);

        // Comprobamos que sea un archivo
        if (!archivo.isFile()) {
            System.out.println("La ruta no corresponde a un archivo: " + ruta);
            return;
        }

        try {
            Path path = Paths.get(ruta);
            // readAllLines carga todas las líneas del archivo en una List
            LinkedList<String> lineas = new LinkedList<>(Files.readAllLines(path));
            for (String linea : lineas) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }





}
