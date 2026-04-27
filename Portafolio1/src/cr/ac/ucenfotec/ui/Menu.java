package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.entidades.Administrador;
import cr.ac.ucenfotec.bl.entidades.Cliente;
import cr.ac.ucenfotec.bl.exceptions.PasswordIncorrectaException;
import cr.ac.ucenfotec.bl.exceptions.UsuarioNoExisteException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import static cr.ac.ucenfotec.tl.Controlador.*;

public class Menu {
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void iniciarMenu() throws IOException, SQLException, ClassNotFoundException {
        boolean ejecutando = true;


        while (ejecutando) {
            System.out.println("\nBienvenido al servicio de reservas del Club TopSpin" +
                    "\n Ingresar como:" +
                    "\n - [1]Cliente" +
                    "\n - [2]Administrador" +
                    "\n - [3]Salir");

            String opcion = in.readLine();

            switch (opcion) {
                case "1":
                    inicioCliente();
                    break;

                case "2":
                    inicioAdministrador();
                    break;

                case "3":
                    ejecutando = false;
                    break;

                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }

    public static void inicioCliente() throws IOException, SQLException, ClassNotFoundException {
        boolean ejecutando = true;
        while (ejecutando) {
            System.out.println("\n-----Clientes-----");
            System.out.println("- [1] Iniciar Sesión");
            System.out.println("- [2] Registrarse");
            System.out.println("- [3] Salir");

            String opcion = in.readLine();

            switch (opcion) {
                case "1":
                    Cliente cliente;
                    try {
                        cliente = ingresarCliente();
                    } catch(UsuarioNoExisteException | PasswordIncorrectaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    menuCliente(cliente);
                    break;

                case "2":
                    registrarCliente();
                    break;

                case "3":
                    ejecutando = false;
                    break;

                default:
                    System.out.println("\nOpción inválida");
                    break;
            }
        }
    }

    public static void inicioAdministrador() throws IOException, SQLException, ClassNotFoundException {
        boolean ejecutando = true;
        while (ejecutando) {
            System.out.println("\n-----Administradores-----");
            System.out.println("- [1] Iniciar Sesión");
            System.out.println("- [2] Registrarse");
            System.out.println("- [3] Salir");

            String opcion = in.readLine();

            switch (opcion) {
                case "1":
                    Administrador administrador;
                    try {
                        administrador = ingresarAdministrador();
                    } catch(UsuarioNoExisteException | PasswordIncorrectaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    menuAdministrador(administrador);
                    break;

                case "2":
                    registrarAdministrador();
                    break;

                case "3":
                    ejecutando = false;
                    break;

                default:
                    System.out.println("\nOpción inválida");
                    break;
            }
        }
    }

    public static void menuCliente(Cliente cliente) throws IOException, SQLException, ClassNotFoundException {
        boolean ejecutando = true;

        while (ejecutando){
            System.out.println("\n-----Cliente " + cliente.getNombreCompleto() + "-----");
            System.out.println("- [1] Ver Horarios Disponibles");
            System.out.println("- [2] Reservar");
            System.out.println("- [3] Ver Mis Reservas");
            System.out.println("- [4] Salir");

            String opcion = in.readLine();

            switch(opcion){
                case "1":
                    mostrarHorarios();
                    break;

                case "2":
                    reservar(cliente);
                    break;

                case "3":
                    verReservas(cliente);
                    break;

                case "4":
                    ejecutando = false;
                    break;
            }
        }

    }

    public static void menuAdministrador(Administrador administrador) throws IOException, SQLException, ClassNotFoundException {
        boolean ejecutando = true;

        while (ejecutando) {
            System.out.println("\n-----Administrador " + administrador.getNombreCompleto() +"-----");
            System.out.println("- [1] Ver Horarios Disponibles");
            System.out.println("- [2] Crear un Evento");
            System.out.println("- [3] Ver Eventos");
            System.out.println("- [4] Salir");

            String opcion = in.readLine();

            switch(opcion){

                case "1":
                    mostrarHorarios();
                    break;

                case "2":
                    crearEvento(administrador);
                    break;

                case "3":
                    verEventos(administrador);
                    break;

                case "4":
                    ejecutando = false;
                    break;
            }
        }

    }
}
