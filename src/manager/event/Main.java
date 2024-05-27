package manager.event;

import manager.event.exceptions.PastDateException;
import manager.event.exceptions.ValueToSmallException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Show show = null;


       // il processo di inserimento è lungo, nel makeShows metto dati statici

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

            // bonus - non ho implementato il compare anche per il time

            BigDecimal big = new BigDecimal(10.00);
            MusicalConcert prova = new MusicalConcert("Concerto Pop", LocalDate.of(2024, 06, 24), 10, LocalTime.of(18,00),big );
            MusicalConcert prova2 = new MusicalConcert("Concerto Rap", LocalDate.of(2025, 06, 24), 10, LocalTime.of(18,00),big );
            MusicalConcert prova3 = new MusicalConcert("Concerto Metal", LocalDate.of(2025, 02, 21), 10, LocalTime.of(18,00),big );
            MusicalConcert prova4 =  new MusicalConcert("Concerto Metal", LocalDate.of(2025, 02, 21), 10, LocalTime.of(18,00),big );



            MakeShow makeShow = new MakeShow("prima lista");
            makeShow.addShow(prova);
            makeShow.addShow(prova2);

            System.out.println(makeShow.getSHOWS().size());

            System.out.println(makeShow.getShowsForDate(LocalDate.of(2025,06,24)).length);
            System.out.println(makeShow.getHowManyShows());

            // makeShow.setToEmpty();
            System.out.println(makeShow.getSHOWS().size());

            System.out.println(makeShow.getAllShowsForDate());

        }catch(PastDateException e){
            System.out.println(e.getMessage());
        }catch(ValueToSmallException e){
            System.out.println(e.getMessage());
        }catch(NumberFormatException e){
            System.out.println("numero non valido");
        }

    }
}
