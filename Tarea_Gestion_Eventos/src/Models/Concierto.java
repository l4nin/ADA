package Models;

public class Concierto extends Evento {
    private String artistaPrincipal;

    public Concierto(String nombre, String fecha, String ubicacion, int precio,
                     String artistaPrincipal) {
        super(nombre, fecha, ubicacion, precio);
        this.artistaPrincipal = artistaPrincipal;
    }

    public String getArtistaPrincipal() { return artistaPrincipal; }

    @Override
    public String toString() {
        // Reutiliza el toString() de Evento
        return "Concierto [" + super.toString()
                + ", artistaPrincipal=" + artistaPrincipal + "]";
    }
}