import Models.Concierto;
import Models.Evento;
import Models.Festival;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // Crear la carpeta "datos" si no existe
        File carpetaDatos = crearCarpetaDatos();

        // Crear algunos conciertos y guardarlos en CSV
        ArrayList<Concierto> conciertos = new ArrayList<>();
        conciertos.add(new Concierto("Concierto A", "01/01/2024", "Madrid", 50, "Artista A"));
        conciertos.add(new Concierto("Concierto B", "15/02/2024", "Barcelona", 70, "Artista B"));
        conciertos.add(new Concierto("Concierto C", "10/03/2024", "Valencia", 30, "Artista C"));

        File ficheroConciertos = new File(carpetaDatos, "conciertos.csv");
        guardarConciertosCsv(conciertos, ficheroConciertos);

        System.out.println("Conciertos guardados en CSV: " + ficheroConciertos.getAbsolutePath());

        // Leer y mostrar el contenido del CSV
        System.out.println("\nContenido del CSV de conciertos:");
        leerFicheroTexto(ficheroConciertos);

        // Crear algunos festivales y guardarlos en binario
        LinkedList<Festival> festivales = new LinkedList<>();
        festivales.add(new Festival("Festival X", "05/05/2024", "Sevilla", 100, 3));
        festivales.add(new Festival("Festival Y", "20/06/2024", "Bilbao", 150, 2));

        File ficheroFestivales = new File(carpetaDatos, "festivales.bin");
        guardarFestivalesBinario(festivales, ficheroFestivales);

        System.out.println("\nFestivales guardados en binario: " + ficheroFestivales.getAbsolutePath());

        // Leer y mostrar el contenido del fichero binario
        System.out.println("\nContenido del fichero binario de festivales:");
        leerFicheroBinario(ficheroFestivales);

        // Filtrar eventos por precio
        ArrayList<Evento> eventos = new ArrayList<>();
        eventos.addAll(conciertos);
        eventos.addAll(festivales);

        ArrayList<Evento> eventosFiltrados = filtrarPorPrecio(eventos);
        System.out.println("\nEventos filtrados por precio:");
        for (Evento e : eventosFiltrados) {
            System.out.println(e);
        }

        // Añadir un nuevo concierto al CSV
        Concierto nuevoConcierto = new Concierto("Concierto D", "25/04/2024", "Granada", 60, "Artista D");
        anadirConcierto(nuevoConcierto, ficheroConciertos);
        System.out.println("\nNuevo concierto añadido al CSV.");

        // Leer y mostrar el contenido actualizado del CSV
        System.out.println("\nContenido actualizado del CSV de conciertos:");
        leerFicheroTexto(ficheroConciertos);

        // Cerrar el Scanner antes de salir
        teclado.close();

        // Fin del programa
        System.out.println("\nPrograma finalizado.");

    }



    //-------------------------------- Funciones ---------------------------------

    // Un único Scanner para todo el programa (no se cierra para no cerrar System.in)
    private static final Scanner teclado = new Scanner(System.in);

    // Separador de campos del CSV
    private static final String SEP = "|";

    /** Crea la carpeta "datos" si no existe y devuelve el File de la carpeta.*/
    public static File crearCarpetaDatos() {
        File carpeta = new File("datos");
        if (!carpeta.exists()) {
            if (carpeta.mkdir()) {
                System.out.println("Carpeta creada.");
            } else {
                System.out.println("No se pudo crear la carpeta.");
            }
        }
        System.out.println("Ruta de la carpeta: " + carpeta.getAbsolutePath());
        return carpeta;
    }

    /** Pide un entero por teclado; si no lo es, lo vuelve a pedir. */
    public static int leerEntero() {
        int numero = 0;
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print("Introduce un numero entero: ");
                numero = teclado.nextInt();
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("Eso no es un entero. Intentalo de nuevo.");
                teclado.nextLine(); // descarta la entrada incorrecta
            }
        }
        teclado.nextLine(); // consume el salto de linea pendiente
        return numero;
    }

    /** Devuelve los eventos con precio estrictamente superior al introducido. */
    public static ArrayList<Evento> filtrarPorPrecio(ArrayList<Evento> eventos) {
        System.out.println("Precio minimo (se mostraran los que cuesten MAS que este valor):");
        int precioMinimo = leerEntero();

        ArrayList<Evento> resultado = new ArrayList<>();
        for (Evento e : eventos) {
            if (e.getPrecio() > precioMinimo) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    /** Linea CSV de un concierto (mismo formato en guardar y anadir). */
    private static String lineaCsv(Concierto c) {
        return c.getNombre() + SEP + c.getFecha() + SEP + c.getUbicacion()
                + SEP + c.getPrecio() + SEP + c.getArtistaPrincipal();
    }

    /** Cierra un flujo ignorando el error de cierre (si el flujo no es null). */
    private static void cerrar(Closeable flujo) {
        if (flujo != null) {
            try {
                flujo.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero: " + e.getMessage());
            }
        }
    }

    /** Guarda los conciertos en CSV (sobrescribe si ya existe). */
    public static void guardarConciertosCsv(ArrayList<Concierto> conciertos, File fichero) {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(fichero); // sin append: sobrescribe
            pw = new PrintWriter(fw);
            // Cabecera con los nombres de los campos
            pw.println("nombre" + SEP + "fecha" + SEP + "ubicacion" + SEP
                    + "precio" + SEP + "artistaPrincipal");
            for (Concierto c : conciertos) {
                pw.println(lineaCsv(c));
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el CSV: " + e.getMessage());
        } finally {
            cerrar(pw); // cierra tambien el FileWriter que envuelve
            cerrar(fw);
        }
    }

    /** Añade un concierto al final del CSV sin borrar lo anterior. */
    public static void anadirConcierto(Concierto c, File fichero) {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(fichero, true); // true = modo append
            pw = new PrintWriter(fw);
            pw.println(lineaCsv(c));
        } catch (IOException e) {
            System.out.println("Error al anadir el concierto: " + e.getMessage());
        } finally {
            cerrar(pw);
            cerrar(fw);
        }
    }

    /** Lee un fichero de texto linea a linea y lo imprime. */
    public static void leerFicheroTexto(File fichero) {
        if (fichero.exists() && fichero.isFile()) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(fichero));
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Fichero no encontrado: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error de lectura: " + e.getMessage());
            } finally {
                cerrar(br);
            }
        } else {
            System.out.println("Error: " + fichero.getName()
                    + " no existe o no es un fichero.");
        }
    }

    /** Guarda los festivales atributo a atributo en un fichero binario. */
    public static void guardarFestivalesBinario(LinkedList<Festival> festivales, File fichero) {
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try {
            fos = new FileOutputStream(fichero);
            dos = new DataOutputStream(fos);
            for (Festival f : festivales) {
                // Orden de escritura: nombre, fecha, ubicacion, precio, duracion
                dos.writeUTF(f.getNombre());
                dos.writeUTF(f.getFecha());
                dos.writeUTF(f.getUbicacion());
                dos.writeInt(f.getPrecio());
                dos.writeInt(f.getDuracion());
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo abrir el fichero: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero binario: " + e.getMessage());
        } finally {
            cerrar(dos);
            cerrar(fos);
        }
    }

    /** Lee los festivales del fichero binario y los muestra. */
    public static void leerFicheroBinario(File fichero) {
        if (fichero.exists() && fichero.isFile()) {
            FileInputStream fis = null;
            DataInputStream dis = null;
            try {
                fis = new FileInputStream(fichero);
                dis = new DataInputStream(fis);
                // available() > 0 mientras queden bytes por leer
                while (dis.available() > 0) {
                    // Mismo orden y tipos que al escribir
                    String nombre = dis.readUTF();
                    String fecha = dis.readUTF();
                    String ubicacion = dis.readUTF();
                    int precio = dis.readInt();
                    int duracion = dis.readInt();
                    Festival f = new Festival(nombre, fecha, ubicacion, precio, duracion);
                    System.out.println(f);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Fichero no encontrado: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error de lectura: " + e.getMessage());
            } finally {
                cerrar(dis);
                cerrar(fis);
            }
        } else {
            System.out.println("Error: " + fichero.getName()
                    + " no existe o no es un fichero.");
        }
    }



}
