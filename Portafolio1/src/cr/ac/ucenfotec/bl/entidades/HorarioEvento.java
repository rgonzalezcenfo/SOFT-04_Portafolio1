package cr.ac.ucenfotec.bl.entidades;

import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class HorarioEvento {
    private String id;
    private String numeroCancha;
    private LocalDate fecha;
    private int horaInicio;
    private int horaFinal;

    private static int numeroUltimoID() throws SQLException, IOException, ClassNotFoundException {
        String query = "SELECT * FROM t_evento_horario ORDER BY id DESC LIMIT 1;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) return 0;
        String id = resultado.getString("id");
        return Integer.parseInt(id.substring(0));
    }

    //constructor
    public HorarioEvento(String numeroCancha, LocalDate fecha, int horaInicio, int horaFinal) throws SQLException, IOException, ClassNotFoundException {
        int numeroId = numeroUltimoID() + 1;
        this.id = String.valueOf(numeroId);
        this.numeroCancha = numeroCancha;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }

    public HorarioEvento(String id, String numeroCancha, LocalDate fecha, int horaInicio, int horaFinal) {
        this.id = id;
        this.numeroCancha = numeroCancha;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }

    //getters
    public String getNumeroCancha() {
        return numeroCancha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public int getHoraFinal() {
        return horaFinal;
    }

    public String getId() {
        return id;
    }

    //setters

    public void setNumeroCancha(String numeroCancha) {
        this.numeroCancha = numeroCancha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFinal(int horaFinal) {
        this.horaFinal = horaFinal;
    }

    public void setId(String id) {
        this.id = id;
    }

    //equals
    public boolean equals(HorarioEvento h) {
        return this.horaInicio == h.horaInicio && this.horaFinal == h.horaFinal && Objects.equals(this.numeroCancha, h.numeroCancha) && Objects.equals(this.fecha, h.fecha);
    }

    //toString
    public String toString(){
        return "\nFecha: " + fecha + " | Cancha: " + numeroCancha + " | De: " + horaInicio + " Hasta: " + horaFinal;
    }

}
