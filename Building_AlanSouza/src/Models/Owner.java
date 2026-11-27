package Models;
/**
 * Representa a un propietario de un apartamento.
 */
public class Owner {

        private String name;
        private String surname;

        public Owner(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        /**
         * Muestra la información del propietario.
         */
        public void printInfo() {
            System.out.println("Nombre: " + name);
            System.out.println("Apellidos: " + surname);
        }

        @Override
        public String toString() {
            return name + " " + surname;
        }
}