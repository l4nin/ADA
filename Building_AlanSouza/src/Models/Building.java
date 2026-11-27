package Models;

import Exceptions.ApartmentNotFoundException;
import Exceptions.OwnerNotFoundException;
import Interfaces.IBuilding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

    /**
     * Representa un edificio con sus apartamentos y propietarios.
     */
    public class Building implements IBuilding {

        private String address;
        private String municipality;
        private int floors;
        private List<Apartment> apartments;

        public Building(String address, String municipality, int floors) {
            this.address = address;
            this.municipality = municipality;
            this.floors = floors;
            this.apartments = new ArrayList<>();
        }

        public String getAddress() {
            return address;
        }

        public String getMunicipality() {
            return municipality;
        }

        public int getFloors() {
            return floors;
        }

        public List<Apartment> getApartments() {
            return apartments;
        }

        public void addApartment(Apartment apartment) {
            apartments.add(apartment);
        }

        /**
         * Muestra la información general del edificio y sus apartamentos.
         */
        public void printInfo() {
            System.out.println("===== EDIFICIO =====");
            System.out.println("Dirección: " + address);
            System.out.println("Municipio: " + municipality);
            System.out.println("Número de plantas: " + floors);

            System.out.println("\n===== APARTAMENTOS =====");

            for (Apartment apartment : apartments) {
                apartment.printInfo();
                System.out.println("--------------------");
            }
        }

        /**
         * Muestra los apartamentos situados en la primera planta.
         */
        @Override
        public void showFirstFloorApartments() {
            List<Apartment> firstFloor = getApartmentsByFloor(1);

            if (firstFloor.isEmpty()) {
                System.out.println("No hay apartamentos en la primera planta.");
                return;
            }

            for (Apartment apartment : firstFloor) {
                apartment.printInfo();
                System.out.println("--------------------");
            }
        }

        /**
         * Busca un apartamento por planta y puerta.
         *
         * @param floor número de planta
         * @param door número de puerta
         * @return apartamento encontrado
         * @throws ApartmentNotFoundException si no existe el apartamento
         */
        @Override
        public Apartment getApartment(int floor, int door)
                throws ApartmentNotFoundException {

            for (Apartment apartment : apartments) {
                if (apartment.getFloor() == floor
                        && apartment.getDoor() == door) {
                    return apartment;
                }
            }

            throw new ApartmentNotFoundException(
                    "No existe el apartamento de la planta "
                            + floor + ", puerta " + door);
        }

        /**
         * Devuelve los propietarios de un apartamento.
         *
         * @param floor número de planta
         * @param door número de puerta
         * @return lista de propietarios del apartamento
         * @throws ApartmentNotFoundException si no existe el apartamento
         */
        @Override
        public LinkedList<Owner> getApartmentOwners(int floor, int door)
                throws ApartmentNotFoundException {

            Apartment apartment = getApartment(floor, door);
            return new LinkedList<>(apartment.getOwners());
        }

        /**
         * Devuelve todos los apartamentos de una planta.
         *
         * @param floor número de planta
         * @return lista de apartamentos de esa planta
         */
        @Override
        public LinkedList<Apartment> getApartmentsByFloor(int floor) {
            LinkedList<Apartment> result = new LinkedList<>();

            for (Apartment apartment : apartments) {
                if (apartment.getFloor() == floor) {
                    result.add(apartment);
                }
            }

            return result;
        }

        /**
         * Busca el apartamento que tiene más propietarios.
         * @return apartamento con más propietarios o null si no hay apartamentos
         */
        @Override
        public Apartment getApartmentWithMostOwners() {
            if (apartments.isEmpty()) {
                return null;
            }

            Apartment result = apartments.get(0);

            for (Apartment apartment : apartments) {
                if (apartment.getOwners().size()
                        > result.getOwners().size()) {
                    result = apartment;
                }
            }

            return result;
        }

        /**
         * Busca el apartamento de un propietario por nombre y apellidos.
         *
         * @param name nombre del propietario
         * @param surname apellidos del propietario
         * @return apartamento del propietario encontrado
         * @throws OwnerNotFoundException si el propietario no existe
         */
        @Override
        public Apartment getApartmentByOwner(String name, String surname)
                throws OwnerNotFoundException {

            for (Apartment apartment : apartments) {
                for (Owner owner : apartment.getOwners()) {
                    if (owner.getName().equalsIgnoreCase(name)
                            && owner.getSurname().equalsIgnoreCase(surname)) {
                        return apartment;
                    }
                }
            }

            throw new OwnerNotFoundException(
                    "No se encuentra al propietario: " + name + " " + surname);
        }
    }
