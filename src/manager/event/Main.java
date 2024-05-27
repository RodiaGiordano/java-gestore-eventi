package manager.event;

import manager.event.exceptions.PastDateException;
import manager.event.exceptions.ValueToSmallException;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Show show = null;

        try( Scanner scanner = new Scanner(System.in)){

            System.out.println("crea un evento!");

            System.out.println("Inserisci il titolo");
            String title = scanner.nextLine();


            // la data è statica, troppi parse da fare, caso mai tornarci

            System.out.println("quanti posti avrà l'evento?");

            int placesAvailable = Integer.parseInt(scanner.nextLine());

            show = new Show(title, LocalDate.of(2024, 06, 24), placesAvailable );

            System.out.println(show);

            System.out.println("vuoi prenotare dei posti o disdire? prenotare/disdire");
            String choiceInput = scanner.nextLine();

            ChoiceSeats choice;
            if (choiceInput.equals("prenotare")) {
                choice = ChoiceSeats.RESERVED_YES;
            } else {
                choice = ChoiceSeats.RESERVED_NO;
            }


            switch (choice) {
                case RESERVED_YES:
                    System.out.println("Quanti posti vuoi prenotare?");

                    int setsReserved = Integer.parseInt(scanner.nextLine());
                    show.bookSeat(setsReserved);

                    System.out.println("Hai prenotato " + setsReserved + " " + show.getSetsRemain());
                    break;

                case RESERVED_NO:
                    System.out.println("Quanti posti vuoi disdire?");
                    int setsCancelled = Integer.parseInt(scanner.nextLine());
                    show.cancellation(setsCancelled);

                    System.out.println("Hai disdetto " + setsCancelled + " " + show.getSetsRemain());
                    break;
                }
            System.out.println("Sono stati prenotati: " + show.getReservedSeats() + " " + show.getSetsRemain());

        }catch(PastDateException e){
            System.out.println(e.getMessage());
        }catch(ValueToSmallException e){
            System.out.println(e.getMessage());
        }catch(NumberFormatException e){
            System.out.println("numero non valido");
        }

    }
}
