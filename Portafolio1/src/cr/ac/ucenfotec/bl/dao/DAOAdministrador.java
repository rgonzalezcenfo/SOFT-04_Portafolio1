package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entidades.Administrador;
import cr.ac.ucenfotec.bl.exceptions.PasswordIncorrectaException;
import cr.ac.ucenfotec.bl.exceptions.TelefonoNoDisponibleException;
import cr.ac.ucenfotec.bl.exceptions.UsuarioNoExisteException;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOAdministrador {
    private static String statement;
    private static String query;
    public static String insertarAdministrador(Administrador administrador) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_administrador WHERE telefono = ?";
        ResultSet resultado =  Conector.getConexion().ejecutarQuery(query, administrador.getTelefono());
        if (resultado.next()) throw new TelefonoNoDisponibleException("El teléfono digitado ya tiene una cuenta asociada.\nDebe usar otro o iniciar sesión con ese número");

        statement = "INSERT INTO t_administrador VALUES (?, ?, ?)";
        Conector.getConexion().ejecutarStatement(statement, administrador.getTelefono(), administrador.getNombreCompleto() , administrador.getPassword());
        return "\nSe registro su cuenta de Administrador exitosamente";
    }

    public static Administrador seleccionarAdministrador(String telefono, String password) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_administrador WHERE telefono = ?";
        ResultSet resultado =  Conector.getConexion().ejecutarQuery(query, telefono);
        if (!resultado.next()) throw new UsuarioNoExisteException("No existe un usuario de administrador registrado con ese teléfono");

        query = "SELECT * FROM t_administrador WHERE telefono = ? AND password = ?";
        resultado =  Conector.getConexion().ejecutarQuery(query, telefono, password);
        if (!resultado.next()) throw new PasswordIncorrectaException("La contraseña es incorrecta");

        return new Administrador(
                resultado.getString("nombre_completo"),
                resultado.getString("telefono"),
                resultado.getString("password")
        );
    }
}
