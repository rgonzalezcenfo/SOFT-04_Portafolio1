package cr.ac.ucenfotec.dl;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AccesoBD {
    private Connection conexion;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public AccesoBD(String direccion, String usuario, String contrasenia) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection(direccion, usuario, contrasenia);
    }

    public void ejecutarStatement(String statement, String s1, String s2, String s3) throws SQLException {
        preparedStatement = conexion.prepareStatement(statement);
        preparedStatement.setString(1, s1);
        preparedStatement.setString(2, s2);
        preparedStatement.setString(3, s3);
        preparedStatement.executeUpdate();
    }

    public void ejecutarStatement(String statement, String s1, String s2, String s3, String s4) throws SQLException {
        preparedStatement = conexion.prepareStatement(statement);
        preparedStatement.setString(1, s1);
        preparedStatement.setString(2, s2);
        preparedStatement.setString(3, s3);
        preparedStatement.setString(4, s4);
        preparedStatement.executeUpdate();
    }

    public void ejecutarStatement(String statement, String s1, LocalDate d, int i, String s2, String s3) throws SQLException {
        preparedStatement = conexion.prepareStatement(statement);
        preparedStatement.setString(1, s1);
        preparedStatement.setDate(2, Date.valueOf(d));
        preparedStatement.setInt(3, i);
        preparedStatement.setString(4, s2);
        preparedStatement.setString(5, s3);
        preparedStatement.executeUpdate();
    }

    public void ejecutarStatement(String statement, String s1, String s2, String s3, LocalDate d, int i1, int i2) throws SQLException {
        preparedStatement = conexion.prepareStatement(statement);
        preparedStatement.setString(1, s1);
        preparedStatement.setString(2, s2);
        preparedStatement.setString(3, s3);
        preparedStatement.setDate(4, Date.valueOf(d));
        preparedStatement.setInt(5, i1);
        preparedStatement.setInt(6, i2);
        preparedStatement.executeUpdate();
    }

    public ResultSet ejecutarQuery(String query) throws SQLException {
        preparedStatement = conexion.prepareStatement(query);
        return preparedStatement.executeQuery();
    }

    public ResultSet ejecutarQuery(String query, String s1) throws SQLException {
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setString(1, s1);
        return preparedStatement.executeQuery();
    }

    public ResultSet ejecutarQuery(String query, String s1, LocalDate f) throws SQLException {
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setString(1, s1);
        preparedStatement.setDate(2, Date.valueOf(f));
        return preparedStatement.executeQuery();
    }

    public ResultSet ejecutarQuery(String query, String s1, String s2) throws SQLException {
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setString(1, s1);
        preparedStatement.setString(2, s2);
        return preparedStatement.executeQuery();
    }
}