package Interfaces;

import Models.Apartment;
import Models.Owner;
import Exceptions.ApartmentNotFoundException;
import Exceptions.OwnerNotFoundException;
import java.util.LinkedList;

public interface IBuilding {

    /**
     * Muestra la información de todos los apartamentos del edificio.
     */
    void showFirstFloorApartments();

    /**
     * Función que devuelve el apartamento correspondiente a la planta y puerta indicadas.
     * @param floor numero de planta del apartamento
     * @param door numero de puerta del apartamento
     * @return el apartamento correspondiente a la planta y puerta indicadas
     * @throws ApartmentNotFoundException controla el caso de que no exista el apartamento
     */
    Apartment getApartment(int floor, int door)
            throws ApartmentNotFoundException;

    /**
     * Función que devuelve la lista de propietarios del apartamento correspondiente a la planta y puerta indicadas.
     * @param floor numero de planta del apartamento
     * @param door numero de puerta del apartamento
     * @return la lista de propietarios del apartamento correspondiente a la planta y puerta indicadas
     * @throws ApartmentNotFoundException controla el caso de que no exista el apartamento
     */
    LinkedList<Owner> getApartmentOwners(int floor, int door)
            throws ApartmentNotFoundException;

    /**
     * Función que devuelve la lista de apartamentos de una planta específica.
     * @param floor numero de planta del apartamento
     * @return la lista de apartamentos de una planta específica
     */
    LinkedList<Apartment> getApartmentsByFloor(int floor);

    /**
     * Función que devuelve el apartamento con más propietarios.
     * @return el apartamento con más propietarios
     */
    Apartment getApartmentWithMostOwners();

    /**
     * Función que devuelve el apartamento correspondiente al propietario indicado.
     * @param name nombre del propietario
     * @param surname apellidos del propietario
     * @return el apartamento correspondiente al propietario indicado
     * @throws OwnerNotFoundException controla el caso de que no exista el propietario
     */
    Apartment getApartmentByOwner(String name, String surname)
            throws OwnerNotFoundException;
}