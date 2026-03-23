package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.entidades.Club;
import cr.ac.ucenfotec.bl.entidades.Dia;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args){
        //crear entidades
        Club club = new Club("Club Top Spin");
        club.agregarCanchaAireLibre();
        club.agregarCanchaTechada();

        //prueba de eventos
        club.registrarAdmin("Roberto", "Gonzalez Castillo");
        club.adminXId("Admin-1").crearEvento("Torneo 1", new LocalDate[]{LocalDate.of(2026, 3, 22)}, new Integer[]{8,9,10}, new Integer[]{1}, club);
        club.adminXId("Admin-1").crearEvento("Clase con Alex", new LocalDate[]{LocalDate.of(2026, 3, 23)}, new Integer[]{15,16}, new Integer[]{1}, club);
        club.actualizar(club.adminXId("Admin-1"));

        System.out.println("---------\nHoy \n" + club.canchaXId(1).getDisponibilidad(club.canchaXId(1).getHoy()));
        System.out.println("---------\nMañana\n" + club.canchaXId(1).getDisponibilidad(club.canchaXId(1).getManana()));


//        System.out.println("Eventos: " + club.getEventos());

        //pruebas de reservas
        club.registrarCliente("Roberto", "Gonzalez Castillo", "64342222");
//        club.clienteXId("C-1").reservar(club, 1, Dia.HOY, 8);
//        club.clienteXId("C-1").reservar(club, 1, Dia.HOY, 24);
//        club.clienteXId("C-1").reservar(club, 1, Dia.HOY, 24);

        club.clienteXId("C-1").reservar(club, 1, Dia.MANANA, 12);

        System.out.println("---------\nHoy \n" + club.canchaXId(1).getDisponibilidad(club.canchaXId(1).getHoy()));


        club.nuevoDia();
        System.out.println("---------\nHoy \n" + club.canchaXId(1).getDisponibilidad(club.canchaXId(1).getHoy()));



    }
}
