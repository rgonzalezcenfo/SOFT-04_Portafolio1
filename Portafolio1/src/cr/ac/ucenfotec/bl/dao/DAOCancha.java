package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entidades.Cancha;
import cr.ac.ucenfotec.bl.exceptions.CanchaNoExisteException;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOCancha {
    private static String query;
    private static String statement;

    public static ArrayList<Cancha> seleccionarChanchas() throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_cancha ORDER BY numero ASC";
        ResultSet resultado =  Conector.getConexion().ejecutarQuery(query);
        ArrayList<Cancha> canchas = new ArrayList<>();
        if(resultado.next()){
            do {
                Cancha cancha = new Cancha(
                        resultado.getString("numero"),
                        resultado.getDouble("precio_hora"),
                        resultado.getString("tipo"),
                        resultado.getInt("hora_apertura"),
                        resultado.getInt("hora_cierre")
                );

                canchas.add(cancha);

            } while(resultado.next());
        }
        return canchas;
    }

    public static Cancha seleccionarChanchaXNumero(String numero) throws SQLException, IOException, ClassNotFoundException, CanchaNoExisteException {
        query = "SELECT * FROM t_cancha WHERE numero = ?";
        ResultSet resultado =  Conector.getConexion().ejecutarQuery(query, numero);
        Cancha cancha = null;
        if(resultado.next()){
                 cancha = new Cancha(
                        resultado.getString("numero"),
                        resultado.getDouble("precio_hora"),
                        resultado.getString("tipo"),
                        resultado.getInt("hora_apertura"),
                        resultado.getInt("hora_cierre")
                );

        } else {
            throw new CanchaNoExisteException("No existe ninguna cancha con ese número");
        }
        return cancha;
    }
}
