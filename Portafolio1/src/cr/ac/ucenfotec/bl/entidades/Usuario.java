package cr.ac.ucenfotec.bl.entidades;

import java.util.Objects;

public abstract class Usuario {
    //atributos
    protected String nombreCompleto;
    protected String telefono;
    protected String password;


    //constructores
    public Usuario(){}

    public Usuario(String nombreCompleto, String telefono, String password) {
        this.nombreCompleto = nombreCompleto;
        this.password = password;
        this.telefono = telefono;
    }


    //getters

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getPassword() {
        return password;
    }

    public String getTelefono() {
        return telefono;
    }

    //setters


    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //toString
    public String toString(){
        return "\nNombre: " + nombreCompleto +
                "\nNumero de Teléfono: " + telefono;
    }
}
