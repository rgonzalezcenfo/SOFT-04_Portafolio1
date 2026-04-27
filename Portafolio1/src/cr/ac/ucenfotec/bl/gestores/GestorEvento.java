package cr.ac.ucenfotec.bl.gestores;

import cr.ac.ucenfotec.bl.dao.DAOEvento;
import cr.ac.ucenfotec.bl.entidades.Administrador;
import cr.ac.ucenfotec.bl.entidades.Evento;
import cr.ac.ucenfotec.bl.entidades.HorarioEvento;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GestorEvento {

    public static String crearEvento(String nombre, String descripcion, ArrayList<HorarioEvento> horarios, String telAdmin) throws SQLException, IOException, ClassNotFoundException {
        return DAOEvento.insertarEvento(new Evento(nombre, descripcion, horarios), telAdmin);
    }

    public static String mostrarEventos(Administrador admin) throws SQLException, IOException, ClassNotFoundException {
        return DAOEvento.seleccionarEventos(admin);
    }
}
