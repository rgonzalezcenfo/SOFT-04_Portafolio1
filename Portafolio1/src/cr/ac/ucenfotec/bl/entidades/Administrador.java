package cr.ac.ucenfotec.bl.entidades;

import java.time.LocalDate;

public class Administrador extends Usuario{

    private static int contador;

    //constructores
    public Administrador(){}

    public Administrador(String nombre, String apellidos) {
        contador++;
        super(nombre, apellidos);
        this.id = "Admin-" + contador;
    }

    //g y s

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Administrador.contador = contador;
    }

    //toString
    @Override
    public String toString() {
        return "Administrador:" + super.toString();
    }

    //crearEvento
    public void crearEvento(String nombre, LocalDate[] fechas, Integer[] horas, Integer[] canchas, Club club){
        Evento evento = new Evento(nombre, fechas, horas, canchas);
        club.agragarEvento(evento);
    }


}
