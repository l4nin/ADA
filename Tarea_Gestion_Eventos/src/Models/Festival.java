package Models;

public class Festival extends Evento {
    private int duracion; // número de días

    public Festival(String nombre, String fecha, String ubicacion, int precio,int duracion) {
        super(nombre, fecha, ubicacion, precio);
        this.duracion = duracion;
    }

    public int getDuracion() { return duracion; }

    @Override
    public String toString() {
        return "Festival [" + super.toString() + ", duracion=" + duracion + " dias]";
    }
}
