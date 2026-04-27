package cr.ac.ucenfotec.tl;

import cr.ac.ucenfotec.bl.dao.DAOCancha;
import cr.ac.ucenfotec.bl.entidades.*;
import cr.ac.ucenfotec.bl.gestores.GestorAdministrador;
import cr.ac.ucenfotec.bl.gestores.GestorCliente;
import cr.ac.ucenfotec.bl.gestores.GestorEvento;
import cr.ac.ucenfotec.bl.gestores.GestorReserva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Controlador {
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void registrarCliente() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("\nNombre Completo: ");
        String nombre = in.readLine();
        System.out.println("\nTeléfono: ");
        String telefono = in.readLine();
        System.out.println("\nContraseña: ");
        String password = in.readLine();

        System.out.println(GestorCliente.registrarCliente(nombre, telefono, password));
    }

    public static void registrarAdministrador() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("\nNombre Completo: ");
        String nombre = in.readLine();
        System.out.println("\nTeléfono: ");
        String telefono = in.readLine();
        System.out.println("\nContraseña: ");
        String password = in.readLine();

        System.out.println(GestorAdministrador.registrarAdministrador(nombre, telefono, password));
    }

    public static Cliente ingresarCliente() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("---Inicio de Sesión---");
        System.out.println("\nNúmero de Teléfono: ");
        String telefono = in.readLine();
        System.out.println("\nContraseña: ");
        String password = in.readLine();

        return GestorCliente.ingresarCliente(telefono, password);
    }

    public static Administrador ingresarAdministrador() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("---Inicio de Sesión---");
        System.out.println("\nNúmero de Teléfono: ");
        String telefono = in.readLine();
        System.out.println("\nContraseña: ");
        String password = in.readLine();

        return GestorAdministrador.ingresarAdministrador(telefono, password);
    }

    public static void mostrarHorarios() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("---Horarios---");
        System.out.println("\n¿Desea ver el horario para Hoy (H) o Mañana (M)?");
        String dia = in.readLine().toLowerCase();
        switch (dia) {
            case "h":
                System.out.println("----" + LocalDate.now().getDayOfMonth() + "-" + LocalDate.now().getMonth() + -+LocalDate.now().getYear() + "----");
                System.out.println(GestorReserva.mostrarHorario(LocalDate.now()));
                break;

            case "m":
                System.out.println("----" + LocalDate.now().plusDays(1).getDayOfMonth() + "-" + LocalDate.now().plusDays(1).getMonth() + -+LocalDate.now().plusDays(1).getYear() + "----");
                System.out.println(GestorReserva.mostrarHorario(LocalDate.now().plusDays(1)));
                break;

            default:
                System.out.println("Opción invalida");
        }

    }

    public static void reservar(Cliente cliente) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("---Reservación---");
        System.out.println("\n¿Desea reservar para Hoy (H) o Mañana (M)?");
        String dia = in.readLine().toLowerCase();
        System.out.println("¿Cuál cancha desea reservar?");
        String numCancha = in.readLine();
        System.out.println("¿A qué hora desea reservar?");
        int hora = Integer.parseInt(in.readLine());
        if (hora <= LocalDateTime.now().getHour() && dia.equals("h")) {
            System.out.println("No puede reservar en el pasado");
            return;
        }

        switch (dia) {
            case "h":
                System.out.println("----" + LocalDate.now().getDayOfMonth() + "-" + LocalDate.now().getMonth() + -+LocalDate.now().getYear() + "----");
                System.out.println(GestorReserva.reservar(cliente, LocalDate.now(), numCancha, hora));
                break;

            case "m":
                System.out.println("----" + LocalDate.now().plusDays(1).getDayOfMonth() + "-" + LocalDate.now().plusDays(1).getMonth() + -+LocalDate.now().plusDays(1).getYear() + "----");
                System.out.println(GestorReserva.reservar(cliente, LocalDate.now().plusDays(1), numCancha, hora));
                break;

            default:
                System.out.println("Opción invalida");
        }
    }

    public static void verReservas(Cliente cliente) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("---Mis Reservaciones---");
        System.out.println("- [1] Ver Mis Reservas Futuras");
        System.out.println("- [2] Ver Todas Mis Reservas");
        System.out.println("- [3] Salir");
        String opcion = in.readLine();
        switch (opcion) {
            case "1":
                System.out.println(GestorReserva.mostrarReservas(cliente, LocalDateTime.now()));

                break;

            case "2":
                System.out.println(GestorReserva.mostrarReservas(cliente));
                break;


            case "3":
                return;

            default:
                System.out.println("Opción inválida");
                break;
        }
    }

    public static void crearEvento(Administrador admin) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("---Eventos---");
        System.out.println("Ingrese el nombre del evento");
        String nombre = in.readLine();
        System.out.println("Ingrese una breve descripción del evento");
        String descripcion = in.readLine();

        System.out.println("¿Cuántos días necesita reservar para su evento?");
        int dias = Integer.parseInt(in.readLine());

        ArrayList<HorarioEvento> horarios = new ArrayList<>();
        String ultimoid = "1";
        for (int i = 1; i <= dias; i++) {
            System.out.println("Dia " + i);
            System.out.println("Ingrese la fecha del dia " + i);
            System.out.println("Año: ");
            int annio = Integer.parseInt(in.readLine());
            System.out.println("Mes: ");
            int mes = Integer.parseInt(in.readLine());
            System.out.println("Día del mes: ");
            int dia = Integer.parseInt(in.readLine());
            LocalDate fecha = LocalDate.of(annio, mes, dia);

            System.out.println("¿Cuántas canchas necesita este día?");
            int cantidadCanchas = Integer.parseInt(in.readLine());
            ArrayList<Cancha> canchas = DAOCancha.seleccionarChanchas();
            if (cantidadCanchas > Integer.parseInt(canchas.getLast().getNumero())) {
                System.out.println("No existen tantas canchas. Solo contamos con " + canchas.getLast().getNumero() + " canchas");
                i--;
                continue;
            }
            ArrayList<String> numCanchas = new ArrayList<>();

            for (int j = 1; j <= cantidadCanchas; j++) {
                System.out.println("¿Cuál cancha desea apartar?");
                String numCancha = in.readLine();
                if (numCanchas.contains(numCancha)) {
                    System.out.println("Ya se reservo esta cancha para este evento en esta fecha");
                    j--;
                    continue;
                }
                numCanchas.add(numCancha);
                System.out.println("¿A qué hora inicia el torneo en cancha " + numCancha + " el dia " + fecha.getDayOfMonth() + "-" + fecha.getMonth() + "-" + fecha.getYear() + "?");
                int horaInicio = Integer.parseInt(in.readLine());
                System.out.println("¿A qué hora termina el torneo en cancha " + numCancha + " el dia " + fecha.getDayOfMonth() + "-" + fecha.getMonth() + "-" + fecha.getYear() + "?");
                int horaFinal = Integer.parseInt(in.readLine());
                HorarioEvento horario;
                if (j == 1 && i==1) {
                    horario = new HorarioEvento(numCancha, fecha, horaInicio, horaFinal);
                    ultimoid = horario.getId();
//                    System.out.println("Ruta 1 Id: " +horario.getId());
                    horarios.add(horario);
                } else {
                    horario = new HorarioEvento(ultimoid, numCancha, fecha, horaInicio, horaFinal);
//                    System.out.println("Ruta 2 Id: " +horario.getId());
                    horarios.add(horario);
                }
                ultimoid = String.valueOf(Integer.parseInt(ultimoid) +1);
//                System.out.println("Ultimo ID: " +ultimoid);
            }
        }

        System.out.println(GestorEvento.crearEvento(nombre, descripcion, horarios, admin.getTelefono()));
    }

    public static void verEventos(Administrador admin) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("---Mis Eventos---");
        System.out.println(GestorEvento.mostrarEventos(admin));
    }
}
