package cr.ac.ucenfotec.bl.entidades;

public class TimeSlot {
    private int hora;
    private boolean estaReservado;
    private Usuario usuario;

    //constructor
    public TimeSlot(){};

    public TimeSlot(int hora) {
        this.hora = hora;
        this.estaReservado = false;
    }

    //getter

    public int getHora() {
        return hora;
    }

    public boolean isEstaReservado() {
        return estaReservado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    //setter

    public void setHora(int hora) {
        this.hora = hora;
    }

    public void setEstaReservado(boolean estaReservado) {
        this.estaReservado = estaReservado;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    //equals
    public boolean equals(TimeSlot slot){
        return this.hora == slot.getHora();
    }

    //toString
    public String toString(){
        return "TimeSlot para las " + hora;
    }
}
