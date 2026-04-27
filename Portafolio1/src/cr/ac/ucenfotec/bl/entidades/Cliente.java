package cr.ac.ucenfotec.bl.entidades;

import cr.ac.ucenfotec.bl.exceptions.CanchaNoDisponibleException;
import cr.ac.ucenfotec.bl.exceptions.FueraDeHoraioException;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Cliente extends Usuario{


    //constructores

    public Cliente(String nombre, String telefono, String password) throws SQLException, IOException, ClassNotFoundException {
        super(nombre, telefono, password);
    }

    //toString
    @Override
    public String toString() {
        return "Cliente:" + super.toString();
    }

    //equals
    public boolean equals(Cliente cliente) {
        return Objects.equals(telefono, cliente.telefono);
    }
}
