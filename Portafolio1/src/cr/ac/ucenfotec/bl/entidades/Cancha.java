package cr.ac.ucenfotec.bl.entidades;

import cr.ac.ucenfotec.bl.exceptions.FueraDeHoraioException;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Cancha {
    protected int numero;
    protected static int contador = 0;
    protected ArrayList<TimeSlot> horasHoy;
    protected ArrayList<TimeSlot> horasManana;
    protected double precioHora;

    //constructores
    public Cancha() {
        contador++;
        numero = contador;
    }

    //getters

    public int getNumero() {
        return numero;
    }

    public ArrayList<TimeSlot> getHoy() {
        return horasHoy;
    }

    public ArrayList<TimeSlot> getManana() {
        return horasManana;
    }

    //setters

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setHorasHoy(ArrayList<TimeSlot> horas) {
        this.horasHoy = horas;
    }

    public void setHorasManana(ArrayList<TimeSlot> horas) {
        this.horasManana = horas;
    }

    //equals

    public boolean equals(Cancha cancha) {
        return this.numero == cancha.numero;
    }

    //toString
    public String toString(){
        return "numero: " + numero;
    }


    //mostrarDisponibilidad
    public boolean estaDisponible(ArrayList<TimeSlot> dia, int hora){
        boolean estaDisponible = false;

        for(TimeSlot slot : dia){
            if (hora == slot.getHora()){
                return slot.isEstaReservado();
            }
        }

        return estaDisponible;
    }

    public String getDisponibilidad(ArrayList<TimeSlot> dia){
        String disponibilidad = "Cancha : " +numero + "\nDisponibilidad por hora:";

        for(TimeSlot slot : dia){
            if(!slot.isEstaReservado()) {
                disponibilidad += "\n" + slot.getHora() + ":00" + "-> Disponible";
            } else {
                disponibilidad += "\n" + slot.getHora() + ":00" + "-> Reservado";
            }
        }

        return disponibilidad;
    }
}
