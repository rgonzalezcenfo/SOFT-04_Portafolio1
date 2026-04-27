package cr.ac.ucenfotec.bl.gestores;

import cr.ac.ucenfotec.bl.dao.DAOReserva;
import cr.ac.ucenfotec.bl.entidades.Cliente;
import cr.ac.ucenfotec.bl.entidades.Reserva;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class GestorReserva {

    public static String mostrarHorario(LocalDate fecha) throws SQLException, IOException, ClassNotFoundException {
        return DAOReserva.seleccionarHorario(fecha);
    }

    public static String reservar(Cliente cliente, LocalDate fecha, String numCancha, int hora) throws SQLException, IOException, ClassNotFoundException {
        return DAOReserva.insertarReserva(new Reserva(fecha, hora, numCancha, cliente.getTelefono()));
    }

    public static String mostrarReservas(Cliente cliente) throws SQLException, IOException, ClassNotFoundException {
        return DAOReserva.seleccionarReservas(cliente);
    }

    public static String mostrarReservas(Cliente cliente, LocalDateTime fechaHora) throws SQLException, IOException, ClassNotFoundException {
        return DAOReserva.seleccionarReservas(cliente, fechaHora);
    }
}
