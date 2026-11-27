import java.io.*;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        guardarPalabras();
        ArrayList<String> palabras = leerPalabras();
        imprimirLista(palabras);

        // Guardar y leer productos
        guardarProductos();
        leerProductos();

    }

    public static void guardarPalabras() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("info.dat"))) {
            dos.writeUTF("Say");
            dos.writeUTF("My");
            dos.writeUTF("Name");
        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }
    }

    public static ArrayList<String> leerPalabras() {
        ArrayList<String> palabras = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(new FileInputStream("info.dat"))) {
            for (int i = 0; i < 3; i++) {
                palabras.add(dis.readUTF());
            }
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
        return palabras;
    }

    public static void imprimirLista(ArrayList<String> lista) {
        for (String palabra : lista) {
            System.out.println(palabra);
        }
    }


    public static void guardarProductos() {
        double[] precios = {1350, 400, 890, 6200, 8730};
        int[] unidades = {5, 7, 12, 8, 30};
        String[] descripciones = {"paquetes de papel", "lápices", "bolígrafos", "carteras", "mesas"};

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("productos.dat"))) {
            for (int i = 0; i < precios.length; i++) {
                dos.writeDouble(precios[i]);
                dos.writeInt(unidades[i]);
                dos.writeUTF(descripciones[i]);
            }
        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }
    }


    public static void leerProductos() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("productos.dat"))) {
            while (true) {
                double precio = dis.readDouble();
                int unidades = dis.readInt();
                String descripcion = dis.readUTF();
                System.out.println("Hay " + unidades + " " + descripcion + " a " + precio + " euros");
            }
        } catch (EOFException e) {
            // Fin del fichero: es la forma normal de terminar la lectura
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
    }




}
