package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entidades.Administrador;
import cr.ac.ucenfotec.bl.entidades.Evento;
import cr.ac.ucenfotec.bl.entidades.HorarioEvento;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOEvento {
    private static String statement;
    private static String query;

    public static String insertarEvento(Evento evento, String telAdmin) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_evento VALUES(?, ?, ?, ?)";
        Conector.getConexion().ejecutarStatement(statement, evento.getId(), evento.getNombre(), evento.getDescripcion(), telAdmin);
        int horariosSize = evento.getHorarios().size();

        for (int i = 0; i < horariosSize; i++) {
            HorarioEvento horario = evento.getHorarios().get(i);
            statement = "INSERT INTO t_evento_horario VALUES(?, ?, ?, ?, ?, ?)";
            Conector.getConexion().ejecutarStatement(statement, horario.getId(), evento.getId(), horario.getNumeroCancha(), horario.getFecha(), horario.getHoraInicio(), horario.getHoraFinal());
        }

        return "El evento se ha creado con éxito";
    }

    public static String seleccionarEventos(Administrador admin) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_evento WHERE tel_admin = ?";
        ResultSet resultadoEvento = Conector.getConexion().ejecutarQuery(query, admin.getTelefono());
        ArrayList<Evento> eventos = new ArrayList<>();
        if (!resultadoEvento.next()) return "\nNo se ha creado ningún evento";
        do {
            ArrayList<HorarioEvento> horarios = new ArrayList<>();

            query = "SELECT * FROM t_evento_horario WHERE id_evento = ?";
            ResultSet resultadoHorario = Conector.getConexion().ejecutarQuery(query, resultadoEvento.getString("id"));
            while (resultadoHorario.next()) {
                horarios.add(new HorarioEvento(resultadoHorario.getString("id"), resultadoHorario.getString("numero_cancha"), resultadoHorario.getDate("fecha").toLocalDate(), resultadoHorario.getInt("hora_inicio"), resultadoHorario.getInt("hora_final")));
            }

            eventos.add(new Evento(resultadoEvento.getString("id"), resultadoEvento.getString("nombre"), resultadoEvento.getString("descripcion"), horarios));

        } while (resultadoEvento.next());

        return eventos.toString();
    }
}
