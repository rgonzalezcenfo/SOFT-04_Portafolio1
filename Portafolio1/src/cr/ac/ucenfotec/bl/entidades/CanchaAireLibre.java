package cr.ac.ucenfotec.bl.entidades;

import java.util.ArrayList;

public class CanchaAireLibre extends Cancha{
    //constructor
    public CanchaAireLibre(){
        super();
        horasHoy = new ArrayList<>();
        for (int i = 7; i <= 16; i++){
            horasHoy.add(new TimeSlot(i));
        }

        horasManana = new ArrayList<>();
        for (int i = 7; i <= 16; i++){
            horasManana.add(new TimeSlot(i));
        }
    }

    //toString
    public String toString(){
        return "Cancha al aire libre " + super.toString();
    }




}
