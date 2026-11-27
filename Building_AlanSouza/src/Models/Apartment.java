package Models;

import java.util.LinkedList;

/**
 * Representa un apartamento de un edificio.
 */
public class Apartment implements Comparable<Apartment> {
    private int floor;
    private int door;
    private LinkedList<Owner> owners;

    public Apartment(int floor, int door) {
        this.floor = floor;
        this.door = door;
        this.owners = new LinkedList<>();
    }

    public int getFloor() {
        return floor;
    }

    public int getDoor() {
        return door;
    }

    public LinkedList<Owner> getOwners() {
        return owners;
    }

    public void addOwner(Owner owner) {
        owners.add(owner);
    }

    /**
     * Muestra la información del apartamento y sus propietarios.
     */
    public void printInfo() {
        System.out.println("Planta: " + floor);
        System.out.println("Puerta: " + door);
        System.out.println("Propietarios:");

        for (Owner owner : owners) {
            System.out.println("- " + owner);
        }
    }

    /**
     * Compara dos apartamentos por planta y después por puerta
     * @param other apartamento con el que se compara
     * @return un valor negativo, cero o positivo según el orden
     */
    @Override
    public int compareTo(Apartment other) {
        int result = Integer.compare(this.floor, other.floor);

        if (result == 0) {
            result = Integer.compare(this.door, other.door);
        }

        return result;
    }
}
