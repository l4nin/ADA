import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        guardarCabecera();
        guardarDatos();
        leerCSV();

    }


    public static void guardarCabecera() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("productos.csv"))) {
            pw.println("ID;DESCRIPCION;PRECIO;UNIDAD;");
        } catch (IOException e) {
            System.out.println("Error al escribir la cabecera: " + e.getMessage());
        }
    }

    public static void guardarDatos() {
        int[] idProducto = {1, 2, 3, 4, 5};
        String[] descripciones = {"paquetes de papel", "lápices", "bolígrafos", "carteras", "mesas"};
        double[] precios = {1350, 400, 890, 6200, 8730};
        int[] unidades = {5, 7, 12, 8, 30};

        try (PrintWriter pw = new PrintWriter(new FileWriter("productos.csv", true))) {
            for (int i = 0; i < idProducto.length; i++) {
                pw.println(idProducto[i] + ";" + descripciones[i] + ";" + precios[i] + ";" + unidades[i] + ";");
            }
        } catch (IOException e) {
            System.out.println("Error al escribir los datos: " + e.getMessage());
        }
    }


    public static void leerCSV() {
        try (Scanner sc = new Scanner(new File("productos.csv"))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] campos = linea.split(";");
                System.out.printf("%-5s %-20s %-10s %-8s%n",
                        campos[0], campos[1], campos[2], campos[3]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el fichero: " + e.getMessage());
        }
    }


}