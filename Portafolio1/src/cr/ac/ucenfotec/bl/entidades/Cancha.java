package cr.ac.ucenfotec.bl.entidades;


import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Cancha {
    private String numero;
    private double precioHora;
    private String tipo;
    private int horaApertura;
    private int horaCierre;

    //constructores
    private static int numeroUltimoID() throws SQLException, IOException, ClassNotFoundException {
        String query = "SELECT * FROM t_cancha ORDER BY id DESC LIMIT 1;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) return 0;
        String id = resultado.getString("id");
        return Integer.parseInt(id.substring(0));
    }

    public Cancha(int horaCierre, int horaApertura, String tipo, double precioHora) throws SQLException, IOException, ClassNotFoundException {
        int idnumero = numeroUltimoID() + 1;
        this.numero = String.valueOf(idnumero);
        this.horaCierre = horaCierre;
        this.horaApertura = horaApertura;
        this.tipo = tipo;
        this.precioHora = precioHora;
    }

    public Cancha(String numero, double precioHora, String tipo, int horaApertura, int horaCierre) {
        this.numero = numero;
        this.precioHora = precioHora;
        this.tipo = tipo;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    //getters

    public String getNumero() {
        return numero;
    }

    public double getPrecioHora() {
        return precioHora;
    }

    public String getTipo() {
        return tipo;
    }

    public int getHoraApertura() {
        return horaApertura;
    }

    public int getHoraCierre() {
        return horaCierre;
    }

    //setters

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setPrecioHora(double precioHora) {
        this.precioHora = precioHora;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setHoraApertura(int horaApertura) {
        this.horaApertura = horaApertura;
    }

    public void setHoraCierre(int horaCierre) {
        this.horaCierre = horaCierre;
    }

    //equals

    public boolean equals(Cancha cancha) {
        return this.numero == cancha.numero;
    }

    //toString
    public String toString() {
        return "numero: " + numero;
    }

}
