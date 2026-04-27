package cr.ac.ucenfotec.bl.gestores;

import cr.ac.ucenfotec.bl.dao.DAOAdministrador;
import cr.ac.ucenfotec.bl.dao.DAOCliente;
import cr.ac.ucenfotec.bl.entidades.Administrador;
import cr.ac.ucenfotec.bl.entidades.Cliente;

import java.io.IOException;
import java.sql.SQLException;

public class GestorAdministrador {

    public static String registrarAdministrador(String nombre, String telefono, String password) throws SQLException, IOException, ClassNotFoundException {
        return DAOAdministrador.insertarAdministrador(new Administrador(nombre, telefono, password));
    }

    public static Administrador ingresarAdministrador(String telefono, String password) throws SQLException, IOException, ClassNotFoundException {
        return DAOAdministrador.seleccionarAdministrador(telefono, password);
    }
}
