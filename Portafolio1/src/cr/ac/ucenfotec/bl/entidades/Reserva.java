package cr.ac.ucenfotec.bl.entidades;

import cr.ac.ucenfotec.bl.exceptions.CanchaNoDisponibleException;
import cr.ac.ucenfotec.bl.exceptions.FueraDeHoraioException;

import java.util.ArrayList;
import java.util.Objects;

public class Reserva {
    private int hora;
    private Cancha cancha;
    private Dia dia;

    //constructor

    public Reserva(int hora, Cancha cancha, Dia dia) {
        this.hora = hora;
        this.cancha = cancha;
        this.dia = dia;
    }


    //getter

    public int getHora() {
        return hora;
    }

    public Cancha getCancha() {
        return cancha;
    }

    public Dia getDia() {
        return dia;
    }

    //setter

    public void setHora(int hora) {
        this.hora = hora;
    }

    public void setCancha(Cancha cancha) {
        this.cancha = cancha;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    //equals
    public boolean equals(Reserva reserva) {
        return hora == reserva.hora && Objects.equals(cancha, reserva.cancha) && dia == reserva.dia;
    }

    //toString
    public String toString(){
        return "Reserva Cancha" + cancha.getNumero()  + " a las " + hora;
    }

    //reservar
    public void reservar(Cliente cliente) throws CanchaNoDisponibleException,FueraDeHoraioException{
        ArrayList<TimeSlot> horariosDia;

        if(dia.equals(Dia.HOY)) {
            horariosDia =cancha.getHoy();
        } else {
            horariosDia =  cancha.getManana();
        }

        boolean encontrado = false;

        for(TimeSlot slot : horariosDia){
            if (slot.getHora() ==  hora ){
                if (!slot.isEstaReservado()) {
                    slot.setEstaReservado(true);
                    slot.setUsuario(cliente);
                    encontrado = true;
                } else {
                    throw new CanchaNoDisponibleException("Cancha ya esta reservada a esa hora");
                }
            }
        }
        if(!encontrado){
            throw new FueraDeHoraioException("La cancha no opera en el horario consultado");
        }
    }
}
