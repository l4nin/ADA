package Models;

public class Evento {
    protected String nombre;
    protected String fecha;      // formato dd/MM/yyyy
    protected String ubicacion;
    protected int precio;

    public Evento(String nombre, String fecha, String ubicacion, int precio) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.precio = precio;
    }

    public String getNombre() { return nombre; }
    public String getFecha() { return fecha; }
    public String getUbicacion() { return ubicacion; }
    public int getPrecio() { return precio; }

    @Override
    public String toString() {
        return "nombre=" + nombre + ", fecha=" + fecha
                + ", ubicacion=" + ubicacion + ", precio=" + precio;
    }
}

