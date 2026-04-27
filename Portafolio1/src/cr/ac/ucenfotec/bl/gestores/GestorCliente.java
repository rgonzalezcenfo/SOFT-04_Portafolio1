package cr.ac.ucenfotec.bl.gestores;

import cr.ac.ucenfotec.bl.dao.DAOCliente;
import cr.ac.ucenfotec.bl.entidades.Cliente;

import java.io.IOException;
import java.sql.SQLException;

public class GestorCliente {
    public static String registrarCliente(String nombre, String telefono, String password) throws SQLException, IOException, ClassNotFoundException {
        return DAOCliente.insertarCliente(new Cliente(nombre, telefono, password));
    }

    public static Cliente ingresarCliente(String telefono, String password) throws SQLException, IOException, ClassNotFoundException {
        return DAOCliente.seleccionarCliente(telefono, password);
    }
}
