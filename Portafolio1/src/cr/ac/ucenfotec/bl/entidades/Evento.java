package cr.ac.ucenfotec.bl.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Evento {
    private String id;
    private static int contador = 0;
    private String nombre;
    private ArrayList<LocalDate> fechas = new ArrayList<>();
    private ArrayList<Integer> horas = new ArrayList<>();
    private ArrayList<Integer> canchas = new ArrayList<>();

    public Evento(String nombre, LocalDate[] fechas, Integer[] horas, Integer[] canchas) {
        contador++;
        this.id = "E-" + contador;
        this.nombre = nombre;
        Collections.addAll(this.fechas, fechas);
        Collections.addAll(this.horas, horas);
        Collections.addAll(this.canchas, canchas);
    }

    //constructor
    public Evento(String nombre){
        this.nombre = nombre;
        contador++;
        id = "E-" + contador;
        fechas = new ArrayList<>();
        horas = new ArrayList<>();
        canchas = new ArrayList<>();
    }

    //getters


    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public static int getContador() {
        return contador;
    }

    public ArrayList<LocalDate> getFechas() {
        return fechas;
    }

    public ArrayList<Integer> getHoras() {
        return horas;
    }

    public ArrayList<Integer> getCanchas() {
        return canchas;
    }

    //setters


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static void setContador(int contador) {
        Evento.contador = contador;
    }

    public void setFechas(ArrayList<LocalDate> fechas) {
        this.fechas = fechas;
    }

    public void setHoras(ArrayList<Integer> horas) {
        this.horas = horas;
    }

    public void setCanchas(ArrayList<Integer> canchas) {
        this.canchas = canchas;
    }

    //equals
    public boolean equals(Evento evento){
        return this.id.equals(evento.getId());
    }

    //toString
    public String toString(){
        return "Evento " + nombre +
                "\n ID: " + id +
                "\n Fechas: " + fechas +
                "\n Horas: " + horas +
                "\n Canchas " + canchas;
    }
}

