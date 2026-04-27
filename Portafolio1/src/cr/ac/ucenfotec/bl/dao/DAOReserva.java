package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entidades.Cancha;
import cr.ac.ucenfotec.bl.entidades.Cliente;
import cr.ac.ucenfotec.bl.entidades.Reserva;
import cr.ac.ucenfotec.dl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

public class DAOReserva {
    private static String query;
    private static String statement;

    public static String seleccionarHorario(LocalDate fecha) throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Cancha> canchas = DAOCancha.seleccionarChanchas();
        String headerCanchas = "     ";
        String tipos = "     ";
        String seis = "   6 ";
        String siete = "   7 ";
        String ocho = "   8 ";
        String nueve = "   9 ";
        String dies = "  10 ";
        String once = "  11 ";
        String doce = "  12 ";
        String trece = "  13 ";
        String catorce = "  14 ";
        String quince = "  15 ";
        String dieciseis = "  16 ";
        String diecisiete = "  17 ";
        String dieciocho = "  18 ";
        String diecinueve = "  19 ";
        String veinte = "  20 ";
        String veintiuno = "  21 ";
        String veintidos = "  22 ";
        String[] horas = {seis, siete, ocho, nueve, dies, once, doce, trece, catorce, quince, dieciseis, diecisiete, dieciocho, diecinueve, veinte, veintiuno, veintidos};

        int largoStringxCancha = 0;

        for (Cancha cancha : canchas){
            largoStringxCancha = 5 + (13*Integer.parseInt(cancha.getNumero()));
            headerCanchas += "| Cancha "+ cancha.getNumero();

            while(headerCanchas.length() < largoStringxCancha){
                headerCanchas += " ";
            }
//            System.out.println(headerCanchas);

            tipos += "| " + cancha.getTipo();
            while(tipos.length() < largoStringxCancha){
                tipos += " ";
            }
//            System.out.println(tipos);


            query = "Select * FROM t_reserva WHERE numero_cancha = ?  AND fecha = ?";
            ResultSet resultado = Conector.getConexion().ejecutarQuery(query, cancha.getNumero(), fecha);
            if(resultado.next()){
                do {
                    int hora = resultado.getInt("hora");
//                    System.out.println(hora);
                    horas[hora - 6] += "| RESERVADO  ";
                }while (resultado.next());
            }

            query = "Select * FROM t_evento_horario WHERE numero_cancha = ?  AND fecha = ?";
            resultado = Conector.getConexion().ejecutarQuery(query, cancha.getNumero(), fecha);
            if(resultado.next()){
                do {
                    int horaInicio = resultado.getInt("hora_inicio");
                    int horaFinal = resultado.getInt("hora_final");

                    for (int i = horaInicio; i <= horaFinal; i++){
                        horas[i - 6] += "| EVENTO     ";
                    }


                }while (resultado.next());
            }

            int ultimaHora = cancha.getHoraCierre();
            if(ultimaHora != 22) {
                for (int i = ultimaHora - 5; i <= 16; i++) {
                    if(horas[i].length() < largoStringxCancha)
                        horas[i] += "| CERRADO    ";
//                    System.out.println(horas[i]);
                }
            }

            for(int i = 0; i <= horas.length-1; i++){
//                System.out.println("hora.length = " + horas[i].length() + " | largoStringXCancha = " + largoStringxCancha);
                if(horas[i].length() < largoStringxCancha && i+6 <= LocalDateTime.now().getHour() && Objects.equals(fecha, LocalDate.now())){
                    horas[i] += "|            ";
                }

                if(horas[i].length() < largoStringxCancha){
                    horas[i] += "| DISPONIBLE ";
                }

            }
        }

        String horario = "-".repeat(largoStringxCancha)+"\n"+headerCanchas +"\n"+"-".repeat(largoStringxCancha)+"\n"+tipos+"\n"+"-".repeat(largoStringxCancha);
        for (String hora : horas){
            horario += "\n"+hora;
        }

        return horario;
    }

    public static String insertarReserva(Reserva reserva) throws SQLException, IOException, ClassNotFoundException {
        statement = "INSERT INTO t_reserva VALUES(?, ?, ?, ?, ?)";
        Conector.getConexion().ejecutarStatement(statement, reserva.getId(), reserva.getFecha(), reserva.getHora(), reserva.getNumeroCancha(), reserva.getTelCliente());
        double precio = DAOCancha.seleccionarChanchaXNumero(reserva.getNumeroCancha()).getPrecioHora();
        return "\nSe ha hecho la reserva correctamente. Favor de hacer SINPE por " + precio + " al 88888888";
    }

    public static String seleccionarReservas(Cliente cliente) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_reserva WHERE tel_cliente = ? ORDER BY fecha ASC";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, cliente.getTelefono());
        if (!resultado.next()) return "\nSu cuenta no tiene ninguna Reservación registrada";
        ArrayList<Reserva> reservas = new ArrayList<>();
        do {
            Reserva reserva =  new Reserva(
                    resultado.getString("id"),
                    resultado.getDate("fecha").toLocalDate(),
                    resultado.getInt("hora"),
                    resultado.getString("numero_cancha"),
                    cliente.getTelefono()
            );

            reservas.add(reserva);
        } while(resultado.next());

        return "Reservaciones" + reservas;
    }

    public static String seleccionarReservas(Cliente cliente, LocalDateTime fechaHora) throws SQLException, IOException, ClassNotFoundException {
        query = "SELECT * FROM t_reserva WHERE tel_cliente = ? AND fecha >= ? ORDER BY fecha ASC";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, cliente.getTelefono(), fechaHora.toLocalDate());
        if (!resultado.next()) return "\nSu cuenta no tiene ninguna Reservación registrada";
        ArrayList<Reserva> reservas = new ArrayList<>();
        do {
            if(!(resultado.getInt("hora") < fechaHora.getHour() && fechaHora.toLocalDate().equals(resultado.getDate("fecha").toLocalDate()))) {
                Reserva reserva = new Reserva(
                        resultado.getString("id"),
                        resultado.getDate("fecha").toLocalDate(),
                        resultado.getInt("hora"),
                        resultado.getString("numero_cancha"),
                        cliente.getTelefono()
                );

                reservas.add(reserva);
            }
        } while(resultado.next());

        return "Reservaciones" + reservas;
    }
}
