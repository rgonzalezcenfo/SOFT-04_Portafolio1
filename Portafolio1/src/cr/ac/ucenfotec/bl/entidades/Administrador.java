package cr.ac.ucenfotec.bl.entidades;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class Administrador extends Usuario{


    //constructores
    public Administrador(String nombre, String telefono, String password) throws SQLException, IOException, ClassNotFoundException {
        super(nombre, telefono, password);
    }


    //toString
    @Override
    public String toString() {
        return "Administrador:" + super.toString();
    }

    //equals
    public boolean equals(Administrador admin) {
        return Objects.equals(telefono, admin.telefono);
    }
}
