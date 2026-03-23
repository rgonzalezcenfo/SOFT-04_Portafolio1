package cr.ac.ucenfotec.bl.entidades;

import java.util.ArrayList;

public class CanchaTechada extends Cancha{
    //constructor
    public CanchaTechada(){
        super();

        horasHoy = new ArrayList<>();
        for (int i = 7; i <= 21; i++){
            horasHoy.add(new TimeSlot(i));
        }

        horasManana = new ArrayList<>();
        for (int i = 7; i <= 21; i++){
            horasManana.add(new TimeSlot(i));
        }
    }

    //toString
    public String toString(){
        return "Cancha techada " + super.toString();
    }
}
