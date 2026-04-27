package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entidades.Cliente;
import cr.ac.ucenfotec.bl.exceptions.PasswordIncorrectaException;
import cr.ac.ucenfotec.bl.exceptions.TelefonoNoDisponibleException;
import cr.ac.ucenfotec.bl.exceptions.UsuarioNoExisteException;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOCliente {
    private static String statement;
    private static String query;

    public static String insertarCliente(Cliente cliente) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_cliente WHERE telefono = ?";
        ResultSet resultado =  Conector.getConexion().ejecutarQuery(query, cliente.getTelefono());
        if (resultado.next()) throw new TelefonoNoDisponibleException("El teléfono digitado ya tiene una cuenta asociada.\nDebe usar otro o iniciar sesión con ese número");

        statement = "INSERT INTO t_cliente VALUES (?, ?, ?)";
        Conector.getConexion().ejecutarStatement(statement, cliente.getTelefono(), cliente.getNombreCompleto(), cliente.getPassword());
        return "\nSe registro su cuenta de Cliente exitosamente";
    }

    public static Cliente seleccionarCliente(String telefono, String password) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_cliente WHERE telefono = ?";
        ResultSet resultado =  Conector.getConexion().ejecutarQuery(query, telefono);
        if (!resultado.next()) throw new UsuarioNoExisteException("No existe un usuario de cliente registrado con ese teléfono");

        query = "SELECT * FROM t_cliente WHERE telefono = ? AND password = ?";
        resultado =  Conector.getConexion().ejecutarQuery(query, telefono, password);
        if (!resultado.next()) throw new PasswordIncorrectaException("La contraseña es incorrecta");

        return new Cliente(
                resultado.getString("nombre_completo"),
                resultado.getString("telefono"),
                resultado.getString("password")
        );
    }
}
