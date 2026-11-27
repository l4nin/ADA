import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        // Cambia esta ruta por la de un archivo real de tu equipo
        String ruta = "datos.dat";

        // Llamadas a los métodos para guardar y mostrar números en el archivo binario
        System.out.println("===== Guardando números en el archivo binario =====");
        guardarNumeros(ruta);

        System.out.println("");
        System.out.println("===== Leyendo números del archivo binario =====");
        leerNumeros();

        // Actividad 2: Guardar enteros desde la entrada del usuario
        System.out.println("");
        System.out.println("===== Guardando enteros desde la entrada =====");
        guardarEnteros();

        // actividad 3: Guardar y leer personas
        System.out.println("");
        System.out.println("===== Guardando y leyendo personas =====");
        Persona persona = pedirPersona();

        // Guardar la persona en el archivo binario
        guardarPersona(persona);

        // Leer y mostrar las personas guardadas en el archivo binario
        System.out.println("");
        leerPersonas();

    }

    /**
     * Apartado a: lee los números guardados en el fichero "datos.dat" y los muestra por pantalla.
     */
    public static void guardarNumeros(String ruta) {
        Random random = new Random();

        try (DataOutputStream salida = new DataOutputStream(new FileOutputStream(ruta,true))) {
            for (int i = 0; i < 5; i++) {
                int numero = random.nextInt(11); // número entre 0 y 10
                salida.writeInt(numero);
                System.out.println("Guardado: " + numero);
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero: " + e.getMessage());
        }
    }

    /**
     * Apartado b: lee los números guardados en el fichero "datos.dat" y los muestra por pantalla.
     */

    public static void leerNumeros() {
        ArrayList<Integer> numeros = new ArrayList<>();

        try (DataInputStream entrada = new DataInputStream(new FileInputStream("datos.dat"))) {
            while (true) {
                numeros.add(entrada.readInt());
            }
        } catch (EOFException e) {
            // fin del fichero: es normal, no es un error
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
            return;
        }

        System.out.println("Números leídos del fichero:");
        for (int i = 0; i < numeros.size(); i++) {
            System.out.println("Posición " + i + ": " + numeros.get(i));
        }
    }


    // act 2
    /**
     * lee números enteros desde la entrada (Scanneer) y los guarda en un archivo binario.
     * La lectura termina cuando el usuario introduce -1.
     */

    public static void guardarEnteros() {
        Scanner sc = new Scanner(System.in);

        try (DataOutputStream salida = new DataOutputStream(new FileOutputStream("numerosGuardados.dat", true))) {
            int numero;

            do {
                System.out.print("Introduce un entero (-1 para terminar): ");

                while (!sc.hasNextInt()) {
                    System.out.println("Error: introduce solo números enteros.");
                    sc.next();
                    System.out.print("Introduce un entero (-1 para terminar): ");
                }
                numero = sc.nextInt();

                if (numero != -1) {
                    salida.writeInt(numero);
                }
            } while (numero != -1);

            System.out.println("Fin de la lectura. Datos guardados en datos.dat");
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero: " + e.getMessage());
        }
    }


    // act 3

    /**
     * Pide al usuario los datos de una persona (DNI, nombre y edad) y devuelve un objeto Persona con esos datos.
     * @return
     */
    public static Persona pedirPersona() {
        Scanner sc = new Scanner(System.in);

        System.out.print("DNI: ");
        String dni = sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        int edad;
        System.out.print("Edad: ");
        while (!sc.hasNextInt()) {
            System.out.println("Error: la edad debe ser un número entero.");
            sc.next();
            System.out.print("Edad: ");
        }
        edad = sc.nextInt();

        return new Persona(dni, nombre, edad);
    }

    /**
     * Guarda los datos de una persona en un archivo binario.
     * @param p
     */
    public static void guardarPersona(Persona p) {
        try (DataOutputStream salida = new DataOutputStream(new FileOutputStream("persona.dat", true))) {
            salida.writeUTF(p.getDni());
            salida.writeUTF(p.getNombre());
            salida.writeInt(p.getEdad());
            System.out.println("Persona guardada en persona.dat");
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero: " + e.getMessage());
        }
    }

    /**
     * Lee los datos de una persona desde un archivo binario y los muestra por pantalla.
     */
    public static void leerPersonas() {
        try (DataInputStream entrada = new DataInputStream(new FileInputStream("persona.dat"))) {
            System.out.println("Contenido de persona.dat:");

            while (true) {
                String dni = entrada.readUTF();
                String nombre = entrada.readUTF();
                int edad = entrada.readInt();

                Persona p = new Persona(dni, nombre, edad);
                System.out.println(p);
            }
        } catch (EOFException e) {
            // fin del fichero: es normal, no es un error
        } catch (FileNotFoundException e) {
            System.out.println("El fichero persona.dat no existe.");
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }



}