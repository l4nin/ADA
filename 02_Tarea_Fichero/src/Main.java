import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

                try (Scanner scanner = new Scanner(System.in)) {
                    boolean running = true;

                    while (running) {
                        System.out.println("\n=== Lectura de ficheros ===");
                        System.out.println("1. Mostrar contenido de un archivo (.txt, .odt, .doc) con FileReader");
                        System.out.println("2. Guardar contenido en minúsculas en una LinkedList (BufferedReader)");
                        System.out.println("0. Salir");
                        System.out.print("Elige una opción: ");
                        String option = scanner.nextLine().trim();

                        switch (option) {
                            case "1":
                                System.out.print("Introduce la ruta completa del archivo: ");
                                mostrarContenido(scanner.nextLine().trim());
                                break;
                            case "2":
                                System.out.print("Introduce la ruta completa del archivo: ");
                                LinkedList<String> lineas = leerEnMinusculas(scanner.nextLine().trim());

                                // El resultado se muestra aquí, en el main, como pide el enunciado
                                if (lineas.isEmpty()) {
                                    System.out.println("La lista está vacía (archivo vacío o ruta no válida).");
                                } else {
                                    System.out.println("Contenido de la LinkedList (" + lineas.size() + " líneas):");
                                    for (String linea : lineas) {
                                        System.out.println(linea);
                                    }
                                }
                                break;
                            case "0":
                                running = false;
                                break;
                            default:
                                System.out.println("Opción no válida.");
                        }
                    }
                }
            }

            /**
             * Apartado a: comprueba que la ruta sea un archivo con extensión .txt, .odt o .doc
             * y muestra su contenido carácter a carácter usando FileReader.
             *
             * @param ruta ruta completa del archivo
             */
            public static void mostrarContenido(String ruta) {
                File archivo = new File(ruta);

                // 1) Comprobamos que sea un archivo (isFile() es false si no existe o es un directorio)
                if (!archivo.isFile()) {
                    System.out.println("La ruta no corresponde a un archivo: " + ruta);
                    return;
                }

                // 2) Comprobamos la extensión. toLowerCase() para aceptar también ".TXT", ".Doc"...
                String nombre = archivo.getName().toLowerCase();
                if (!(nombre.endsWith(".txt") || nombre.endsWith(".odt") || nombre.endsWith(".doc"))) {
                    System.out.println("No es un archivo de texto (.txt, .odt o .doc): " + archivo.getName());
                    return;
                }

                // 3) Leemos con FileReader. try-with-resources cierra el flujo automáticamente.
                try (FileReader lector = new FileReader(archivo)) {
                    int caracter;
                    // read() devuelve el carácter como int, o -1 cuando se llega al final del fichero
                    while ((caracter = lector.read()) != -1) {
                        System.out.print((char) caracter);   // convertimos el int a char
                    }
                    System.out.println();
                } catch (IOException e) {
                    // FileNotFoundException es subclase de IOException, así que se captura aquí
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
            public static LinkedList<String> leerEnMinusculas(String ruta) {
                LinkedList<String> lista = new LinkedList<>();
                File archivo = new File(ruta);

                // Comprobamos que sea un archivo. Devolvemos la lista vacía (mejor que null,
                // así quien llame no tiene que comprobar null antes de recorrerla).
                if (!archivo.isFile()) {
                    System.out.println("La ruta no corresponde a un archivo: " + ruta);
                    return lista;
                }

                // BufferedReader envuelve a FileReader y permite leer línea a línea con readLine()
                try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                    String linea;
                    // readLine() devuelve null cuando se llega al final del fichero
                    while ((linea = br.readLine()) != null) {
                        lista.add(linea.toLowerCase());   // guardamos la línea en minúsculas
                    }
                } catch (IOException e) {
                    System.out.println("Error al leer el archivo: " + e.getMessage());
                }

                return lista;
            }
}
