package Models;

public class Recital extends Evento {
    private String tipoMusica;

    public Recital(String nombre, String fecha, String ubicacion, int precio,
                   String tipoMusica) {
        super(nombre, fecha, ubicacion, precio);
        this.tipoMusica = tipoMusica;
    }

    public String getTipoMusica() { return tipoMusica; }

    @Override
    public String toString() {
        return "Recital [" + super.toString() + ", tipoMusica=" + tipoMusica + "]";
    }
}
