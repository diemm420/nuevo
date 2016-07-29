package ec.com.crimen.crimendroid_dialog.dominios;

import java.util.Date;
import java.util.UUID;

/**
 * Created by cesaralcivar on 2/7/16.
 */
public class Crimen {

    private int id;
    private String titulo;
    private Date fecha;
    private boolean resuelto;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isResuelto() {
        return resuelto;
    }

    public void setResuelto(boolean resuelto) {
        this.resuelto = resuelto;
    }

    @Override
    public String toString() {
        return "Crimen{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", fecha=" + fecha +
                ", resuelto=" + resuelto +
                '}';
    }
}
