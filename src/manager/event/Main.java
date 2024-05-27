package manager.event;

import com.sun.security.jgss.GSSUtil;
import manager.event.exceptions.PastDateException;
import manager.event.exceptions.ValueToSmallException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args){

        Show show = null;

        try {
            show = new Show("Prima", LocalDateTime.of(2024, 06, 24,18,0), 50 );
            System.out.println(show.toString());
        }catch(PastDateException e){
            System.out.println(e.getMessage());
        }catch(ValueToSmallException e){
            System.out.println(e.getMessage());
        }


    }
}
