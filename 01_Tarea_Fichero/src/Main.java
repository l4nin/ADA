import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

// Programa que reúne los ejercicios de manejo de ficheros y directorios en Linux.
public class Main {

    // Carpeta base para las estructuras de los ejercicios 5, 6, 10 y 11.
    // Se crean dentro de una subcarpeta propia para no tocar nada importante.
    private static final String BASE_TMP = "/tmp/ejercicio_ficheros";

    public static void main(String[] args) {
        // Scanner lee las opciones que escribe la persona por teclado.
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            // El menú se repite hasta que se elige la opción 0.
            while (running) {
                printMenu();
                System.out.print("Elige un ejercicio (0 para salir): ");
                String option = scanner.nextLine().trim();

                // Cada opción llama al método que realiza el ejercicio correspondiente.
                switch (option) {
                    case "1":
                        // Ejercicio 1 adaptado: se lista /etc, un directorio habitual de Linux.
                        listarDirectorio("/etc");
                        break;
                    case "2":
                        // user.home obtiene la carpeta personal del usuario actual.
                        listarDirectorio(System.getProperty("user.home"));
                        break;
                    case "3":
                        // La ruta se solicita al usuario.
                        System.out.print("Introduce la ruta completa del directorio: ");
                        listarDirectorio(scanner.nextLine().trim());
                        break;
                    case "4":
                    case "7":
                        // Lista el directorio desde el que se ejecuta el programa y un nivel más.
                        listarActualYSubdirectorios();
                        break;
                    case "5":
                        // Adaptación a Linux del ejercicio de Windows (C:\tmp -> /tmp).
                        crearEstructuraDosDirectorios(new File(BASE_TMP));
                        break;
                    case "6":
                        // Ambos enunciados crean la misma estructura en una ruta segura de /tmp.
                        crearEstructuraDosDirectorios(new File(BASE_TMP));
                        break;
                    case "8":
                        // Ejercicio 7: muestra los datos completos de cada entrada.
                        listarActualConInfo();
                        break;
                    case "9":
                        // Este ejercicio muestra el listado solo cuando el sistema es Windows.
                        System.out.print("Introduce la ruta completa del directorio: ");
                        listarSoloEnWindows(scanner.nextLine().trim());
                        break;
                    case "10":
                        // Crea usr1 y usr2 dentro de la carpeta personal del usuario.
                        crearEstructuraUsr(carpetaBaseUsuario());
                        break;
                    case "11":
                        // Pide confirmación antes de borrar ficheros y directorios concretos.
                        System.out.print("Se borrarán f1, f2, f3, d1 y d2. ¿Continuar? (s/n): ");
                        String respuesta = scanner.nextLine().trim().toLowerCase();
                        if (respuesta.equals("s")) {
                            borrarEstructuraUsr(carpetaBaseUsuario());
                        } else {
                            System.out.println("Operación cancelada.");
                        }
                        break;
                    case "0":
                        running = false;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Función Menu
     */
    private static void printMenu() {
        System.out.println("\n=== Ejercicios de ficheros (Linux) ===");
        System.out.println("1. Listar /etc (sin entrar en subdirectorios)");
        System.out.println("2. Listar mi directorio personal (sin entrar en subdirectorios)");
        System.out.println("3. Listar un directorio indicado por ruta");
        System.out.println("4. Listar el directorio actual y el primer nivel de sus subdirectorios");
        System.out.println("5. Crear d1/f11.txt y d2/f21.txt en /tmp (adaptación del ejercicio Windows)");
        System.out.println("6. Crear la misma estructura en /tmp (enunciado de Lliurex)");
        System.out.println("7. (Ejercicios I, 6) Listar el directorio actual y el primer nivel");
        System.out.println("8. Mostrar información detallada del directorio actual y su primer nivel");
        System.out.println("9. Comprobar el sistema operativo y listar una ruta (solo Windows)");
        System.out.println("10. Crear la estructura usr1/usr2 en un directorio de mi usuario");
        System.out.println("11. Borrar la estructura creada en el ejercicio 10");
        System.out.println("0. Salir");
        System.out.println("");
    }

    /**
     * Devuelve "Directorio" o "Fichero" según el tipo del objeto File.
     * Evita repetir el mismo if/else en varios ejercicios.
     */
    private static String tipo(File f) {
        // Operador ternario: condicion ? valorSiTrue : valorSiFalse
        return f.isDirectory() ? "Directorio" : "Fichero";
    }

    /**
     * Carpeta base de los ejercicios 10 y 11: /home/<usuario>/ejercicio_ficheros
     * (user.home da la carpeta personal, así no hay que escribir el usuario a mano).
     */
    private static File carpetaBaseUsuario() {
        return new File(System.getProperty("user.home"), "ejercicio_ficheros");
    }

    /**
     * Muestra el contenido de un directorio (solo el primer nivel,
     * sin entrar en los subdirectorios).
     *
     * @param ruta ruta del directorio a listar
     * @return true si se pudo listar, false si hubo algún problema
     */
    public static boolean listarDirectorio(String ruta) {
        File dir = new File(ruta);

        if (!dir.exists()) {
            System.out.println("El directorio no existe: " + ruta);
            return false;
        }
        if (!dir.isDirectory()) {
            System.out.println("La ruta no es un directorio: " + ruta);
            return false;
        }

        File[] contenido = dir.listFiles();
        if (contenido == null) {
            System.out.println("No se puede leer el directorio (¿permisos?): " + ruta);
            return false;
        }

        for (File f : contenido) {
            String tipo = f.isDirectory() ? "[DIR]  " : "[FILE] ";
            System.out.println(tipo + f.getName());
        }
        return true;
    }

    /**
     * Opciones 4 y 7: lista el directorio actual (user.dir) mostrando nombre y tipo
     * y, de cada subdirectorio, solo su primer nivel (no es recursivo).
     */
    public static void listarActualYSubdirectorios() {
        // user.dir es el directorio desde el que se ejecuta el programa
        File actual = new File(System.getProperty("user.dir"));
        File[] contenido = actual.listFiles();

        if (contenido == null) {
            System.out.println("No se puede leer el directorio actual.");
            return;
        }

        System.out.println("Contenido de " + actual.getAbsolutePath() + ":");
        for (File f : contenido) {
            System.out.println("  " + f.getName() + " -> " + tipo(f));
        }

        // Por cada subdirectorio, listamos solo su primer nivel
        for (File f : contenido) {
            if (f.isDirectory()) {
                System.out.println("\nContenido de " + f.getName() + ":");
                File[] sub = f.listFiles();
                if (sub != null) { // puede ser null si no hay permisos
                    for (File s : sub) {
                        System.out.println("  " + s.getName() + " -> " + tipo(s));
                    }
                }
            }
        }
    }

    /**
     * Opciones 5 y 6: crea la estructura
     *   base/d1/f11.txt
     *   base/d2/f21.txt
     *
     * @param base directorio donde se crea la estructura
     */
    public static void crearEstructuraDosDirectorios(File base) {
        // new File(padre, "hijo") construye la ruta sin concatenar a mano
        File d1 = new File(base, "d1");
        File d2 = new File(base, "d2");

        // mkdirs() crea también los directorios intermedios que falten (incluida "base")
        d1.mkdirs();
        d2.mkdirs();

        // createNewFile() lanza IOException (checked): hay que capturarla
        try {
            new File(d1, "f11.txt").createNewFile();
            new File(d2, "f21.txt").createNewFile();
            System.out.println("Estructura creada en " + base.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al crear los ficheros: " + e.getMessage());
        }
    }

    /**
     * Muestra la información detallada de un fichero o directorio.
     *
     * @param f objeto File del que se quiere la información
     */
    public static void infoFichero(File f) {
        System.out.println("Nombre: " + f.getName());
        System.out.println("Camino absoluto: " + f.getAbsolutePath());
        System.out.println("Tamaño: " + f.length() + " bytes");
        System.out.println("Es fichero: " + f.isFile());
        System.out.println("Es directorio: " + f.isDirectory());
        // lastModified() devuelve milisegundos desde 1970; Date lo hace legible
        System.out.println("Última modificación: " + new Date(f.lastModified()));
        System.out.println("Permiso de escritura: " + f.canWrite());
        System.out.println("Permiso de lectura: " + f.canRead());
        // En un directorio, canExecute() indica si se puede entrar en él
        System.out.println("Permiso de ejecución: " + f.canExecute());
        System.out.println("----------------------------");
    }

    /**
     * Opción 8: muestra la información detallada del directorio actual
     * y del primer nivel de sus subdirectorios, usando infoFichero().
     */
    public static void listarActualConInfo() {
        File actual = new File(System.getProperty("user.dir"));
        File[] contenido = actual.listFiles();

        if (contenido == null) {
            System.out.println("No se puede leer el directorio actual.");
            return;
        }

        for (File f : contenido) {
            infoFichero(f);
        }

        for (File f : contenido) {
            if (f.isDirectory()) {
                System.out.println("\n=== Contenido de " + f.getName() + " ===");
                File[] sub = f.listFiles();
                if (sub != null) {
                    for (File s : sub) {
                        infoFichero(s);
                    }
                }
            }
        }
    }

    /**
     * Opción 9: si el sistema es Windows muestra el contenido de la ruta con
     * infoFichero(); si no, muestra el sistema operativo y un aviso.
     *
     * @param ruta ruta completa del directorio
     */
    public static void listarSoloEnWindows(String ruta) {
        // os.name devuelve, por ejemplo, "Windows 11" o "Linux"
        String so = System.getProperty("os.name");

        // startsWith porque el nombre lleva la versión detrás
        if (so.toLowerCase().startsWith("windows")) {
            File dir = new File(ruta);
            if (dir.isDirectory()) {
                File[] contenido = dir.listFiles();
                if (contenido != null) {
                    for (File f : contenido) {
                        infoFichero(f);
                    }
                }
            } else {
                System.out.println("La ruta no es un directorio válido.");
            }
        } else {
            System.out.println("Sistema operativo: " + so);
            System.out.println("Solo se puede mostrar el contenido de los directorios de los sistemas operativos de Windows");
        }
    }

    /**
     * Opción 10: crea la estructura
     *   base/usr1/f1
     *   base/usr2/d1/f2
     *   base/usr2/d2/f3
     *
     * @param base directorio donde se crea la estructura
     */
    public static void crearEstructuraUsr(File base) {
        File usr1 = new File(base, "usr1");
        // Al crear usr2/d1 con mkdirs(), usr2 se crea automáticamente
        File d1 = new File(base, "usr2/d1");
        File d2 = new File(base, "usr2/d2");

        try {
            usr1.mkdirs();
            d1.mkdirs();
            d2.mkdirs();

            // Ficheros sin extensión
            new File(usr1, "f1").createNewFile();
            new File(d1, "f2").createNewFile();
            new File(d2, "f3").createNewFile();
            System.out.println("Estructura creada en " + base.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al crear los ficheros: " + e.getMessage());
        }
    }

    /**
     * Opción 11: borra f1, f2, f3, d1 y d2 de la estructura anterior.
     * IMPORTANTE: delete() solo borra directorios VACÍOS, así que primero
     * van los ficheros y después los directorios.
     *
     * @param base directorio donde se creó la estructura
     */
    public static void borrarEstructuraUsr(File base) {
        File usr1 = new File(base, "usr1");
        File d1 = new File(base, "usr2/d1");
        File d2 = new File(base, "usr2/d2");

        // El orden del array es el orden de borrado
        File[] aBorrar = {
                new File(usr1, "f1"),
                new File(d1, "f2"),
                new File(d2, "f3"),
                d1,
                d2
        };

        for (File f : aBorrar) {
            // delete() devuelve true si borró y false si falló (no lanza excepción):
            // falla si no existe, no hay permisos o el directorio no está vacío
            if (f.delete()) {
                System.out.println("Borrado: " + f.getAbsolutePath());
            } else {
                System.out.println("No se pudo borrar: " + f.getAbsolutePath());
            }
        }
        // usr1 y usr2 se quedan
    }
}