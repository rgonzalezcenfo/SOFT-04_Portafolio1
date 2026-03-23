package cr.ac.ucenfotec.bl.entidades;

import cr.ac.ucenfotec.bl.exceptions.CanchaNoDisponibleException;
import cr.ac.ucenfotec.bl.exceptions.FueraDeHoraioException;

import java.util.ArrayList;

public class Cliente extends Usuario{

    private static int contador =0;
    private String telefono;
    private ArrayList<Reserva> reservas;

    //constructores
    public Cliente(){}

    public Cliente(String nombre, String apellidos, String telefono) {
        contador++;
        super(nombre, apellidos);
        this.id = "C-" + contador;
        this.telefono = telefono;
        reservas = new ArrayList<>();
    }

    //getter

    public static int getContador() {
        return contador;
    }

    public String getTelefono() {
        return telefono;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    //setter


    public static void setContador(int contador) {
        Cliente.contador = contador;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    //toString
    @Override
    public String toString() {
        return "Cliente:" + super.toString();
    }

    //reservar

    public void reservar(Club club, int numeroCancha, Dia dia, int hora) {
        Cancha cancha = club.canchaXId(numeroCancha);

        Reserva reserva = new Reserva(hora, cancha, dia);
        reservas.add(reserva);
        reserva.reservar(this);
    }
}
