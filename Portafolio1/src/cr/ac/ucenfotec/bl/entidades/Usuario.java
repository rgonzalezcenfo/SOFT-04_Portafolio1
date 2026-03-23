package cr.ac.ucenfotec.bl.entidades;

public abstract class Usuario {
    //atributos
    protected String id;
    protected String nombre;
    protected String apellidos;

    //constructores
    public Usuario(){}

    public Usuario(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    //getters

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    //setters

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    //equals
    public boolean equals(Usuario u) {
        return this.id.equals(u.id);
    }

    //toString
    public String toString(){
        return "\nNombre: " + nombre +" "+ apellidos +
                "\nID: " + id;
    }
}
