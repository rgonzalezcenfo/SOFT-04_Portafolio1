package cr.ac.ucenfotec.bl.entidades;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Club {
    private String nombre;
    private ArrayList<Cancha> canchas;
    private ArrayList<Cliente> clientes;
    private ArrayList<Administrador> admins;
    private ArrayList<Evento> eventos;

    //constructores
    public Club(){}

    public Club(String nombre) {
        this.nombre = nombre;
        canchas = new ArrayList<>();
        clientes = new ArrayList<>();
        admins = new ArrayList<>();
        eventos = new ArrayList<>();
    }

    //getters

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Cancha> getCanchas() {
        return canchas;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Administrador> getAdmins() {
        return admins;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    //setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCanchas(ArrayList<Cancha> canchas) {
        this.canchas = canchas;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setAdmins(ArrayList<Administrador> admins) {
        this.admins = admins;
    }

    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }

    //equals
    public boolean equals(Club club){
        return this.nombre.equals(club.nombre);
    }

    //toString
    public String toString(){
        return "Club: " + nombre;
    }

    //agregar Cancha
    public void agregarCanchaAireLibre(){
        CanchaAireLibre cancha = new CanchaAireLibre();
        canchas.add(cancha);
    }

    public void agregarCanchaTechada(){
        CanchaTechada cancha = new CanchaTechada();
        canchas.add(cancha);
    }


    //agregar Usuarios
    public void registrarCliente(String nombre, String apellidos, String telefono){
        Cliente cliente = new Cliente(nombre, apellidos, telefono);
        clientes.add(cliente);
    }

    public void registrarAdmin(String nombre, String apellidos){
        Administrador admin = new Administrador(nombre, apellidos);
        admins.add(admin);
    }

    //agregar evento
    public void agragarEvento(Evento evento){
        eventos.add(evento);
    }

    //usuarioXid

    public Cliente clienteXId(String id){
        Cliente clienteEncontrado = null;
        boolean encontrado = false;

        for (Cliente cliente : clientes){
            if(cliente.getId().equals(id)){
                clienteEncontrado = cliente;
                encontrado = true;
            }
        }

        if(!encontrado){
            System.out.println("Usuario no exite");
        }
        return clienteEncontrado;
    }

    public Administrador adminXId(String id){
        Administrador adminEncontrado = null;
        boolean encontrado = false;

        for (Administrador admin : admins){
            if(admin.getId().equals(id)){
                adminEncontrado = admin;
                encontrado = true;
            }
        }

        if(!encontrado){
            System.out.println("Usuario no exite");
        }
        return adminEncontrado;
    }

    //canchaXNum

    public Cancha canchaXId(int numero){
        Cancha canchaEncontrada = null;
        boolean encontrado = false;

        for (Cancha cancha : canchas){
            if(cancha.getNumero() == numero){
                canchaEncontrada = cancha;
                encontrado = true;
            }
        }

        if(!encontrado){
            System.out.println("Cancha no exite");
        }
        return canchaEncontrada;
    }


    //EventoXId
    public Evento eventoXId(String id){
        Evento eventoEncontrada = null;
        boolean encontrado = false;

        for (Evento evento : eventos){
            if(evento.getId() == id){
                eventoEncontrada = evento;
                encontrado = true;
            }
        }

        if(!encontrado){
            System.out.println("Evento no exite");
        }
        return eventoEncontrada;
    }
    //nuevoDia
    public void nuevoDia(){
        for (Cancha cancha : canchas){
            for (TimeSlot slotHoy : cancha.getHoy()){
                for (TimeSlot slotManana : cancha.getManana()){
                    if(slotHoy.getHora() == slotManana.getHora()){
                        slotHoy.setEstaReservado(slotManana.isEstaReservado());
                        slotHoy.setUsuario(slotManana.getUsuario());
                        break;
                    }
                }
            }
        }
    }

    //actualizarHoy
    public void actualizar(Administrador admin){
        for(Evento evento : eventos){
            System.out.println("Evento");
            System.out.println(LocalDate.now());

            for(LocalDate fecha : evento.getFechas()){
//                System.out.println("fecha: " + fecha);
                if(fecha.equals(LocalDate.now())){
//                    System.out.println("Fecha de evento es hoy");
                    for (int numCancha : evento.getCanchas()){
//                        System.out.println("Cancha" + numCancha);
                        for( int hora : evento.getHoras()){
                            for (TimeSlot slot : canchaXId(numCancha).getHoy()){
                                if (slot.getHora() == hora){
//                                    System.out.println("Llegamos al ultimo chequeo");
                                    slot.setEstaReservado(true);
                                    slot.setUsuario(admin);
                                }
                            }
                        }
                    }
                } else if (fecha.equals(LocalDate.now().plusDays(1))){
                    for (int numCancha : evento.getCanchas()){
                        for( int hora : evento.getHoras()){
                            for (TimeSlot slot : canchaXId(numCancha).getManana()){
                                if (slot.getHora() == hora){
                                    slot.setEstaReservado(true);
                                    slot.setUsuario(admin);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
