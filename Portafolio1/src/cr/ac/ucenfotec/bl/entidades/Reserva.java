package cr.ac.ucenfotec.bl.entidades;

import cr.ac.ucenfotec.bl.exceptions.CanchaNoDisponibleException;
import cr.ac.ucenfotec.bl.exceptions.FueraDeHoraioException;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Reserva {
    private String id;
    private LocalDate fecha;
    private int hora;
    private String numeroCancha;
    private String telCliente;


    //constructor
    private static int numeroUltimoID() throws SQLException, IOException, ClassNotFoundException {
        String query = "SELECT * FROM t_evento ORDER BY id DESC LIMIT 1;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) return 0;
        String id = resultado.getString("id");
        return Integer.parseInt(id.substring(2));
    }

    public Reserva(LocalDate fecha, int hora, String numeroCancha, String telCliente) throws SQLException, IOException, ClassNotFoundException {
        int numeroId = numeroUltimoID() + 1;
        this.id = "R-" + numeroId;
        this.fecha = fecha;
        this.hora = hora;
        this.numeroCancha = numeroCancha;
        this.telCliente = telCliente;
    }

    public Reserva(String id, LocalDate fecha, int hora, String numeroCancha, String telCliente) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.numeroCancha = numeroCancha;
        this.telCliente = telCliente;
    }

    //getter

    public int getHora() {
        return hora;
    }

    public String getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getNumeroCancha() {
        return numeroCancha;
    }

    public String getTelCliente() {
        return telCliente;
    }

    //setter

    public void setHora(int hora) {
        this.hora = hora;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setNumeroCancha(String numeroCancha) {
        this.numeroCancha = numeroCancha;
    }

    public void setTelCliente(String telCliente) {
        this.telCliente = telCliente;
    }

    //equals
    public boolean equals(Reserva reserva) {
        return hora == reserva.hora && Objects.equals(numeroCancha, reserva.numeroCancha) && fecha == reserva.fecha;
    }

    //toString
    public String toString(){
        return "\nReserva Cancha " + numeroCancha  + " el "+fecha.getDayOfMonth()+"-"+fecha.getMonth()+"-"+fecha.getYear()+ " a las " + hora+":00";
    }
}
