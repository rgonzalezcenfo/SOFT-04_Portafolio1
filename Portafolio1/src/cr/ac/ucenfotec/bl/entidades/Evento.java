package cr.ac.ucenfotec.bl.entidades;

import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Evento {
    private String id;
    private String nombre;
    private String descripcion;
    private ArrayList<HorarioEvento> horarios;


    private static int numeroUltimoID() throws SQLException, IOException, ClassNotFoundException {
        String query = "SELECT * FROM t_evento ORDER BY id DESC LIMIT 1;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);
        if (!resultado.next()) return 0;
        String id = resultado.getString("id");
        return Integer.parseInt(id.substring(2));
    }

    //constructor
    public Evento(String nombre, String descripcion, ArrayList<HorarioEvento> horarios) throws SQLException, IOException, ClassNotFoundException {
        int numeroID = numeroUltimoID() + 1;
        this.id = "E-" + numeroID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horarios = horarios;
    }

    public Evento(String id, String nombre, String descripcion, ArrayList<HorarioEvento> horarios) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horarios = horarios;
    }

    //getters


    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ArrayList<HorarioEvento> getHorarios() {
        return horarios;
    }

//setters


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setHorarios(ArrayList<HorarioEvento> horarios) {
        this.horarios = horarios;
    }

    //equals
    public boolean equals(Evento evento){
        return this.id.equals(evento.getId());
    }

    //toString
    public String toString(){
        return "Evento " + nombre +
                " Descripción: " + descripcion +
                "\n Horarios:\n" + horarios;
    }
}

