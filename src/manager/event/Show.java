package manager.event;

import manager.event.exceptions.PastDateException;
import manager.event.exceptions.ValueToSmallException;

import java.time.LocalDateTime;

public class Show {

    private String title;
    private LocalDateTime date;
    private final int PLACES_AVAILABLE;
    private int reservedSeats;

    public Show(String title, LocalDateTime date, int placesAvailable)throws PastDateException, ValueToSmallException  {
        this.title = checkTitle(title);
        this.date = checkDate(date);
        this.PLACES_AVAILABLE = checkPlaces(placesAvailable);
        this.reservedSeats = 0;
    }

    private String checkTitle(String title)throws ValueToSmallException{
        if(title == null || title.length() <3 ){
            throw new ValueToSmallException("Il titolo deve avere almeno 3 caratteri");
        }
        return title;
    }

    private LocalDateTime checkDate(LocalDateTime date)throws PastDateException{
        if(date.isBefore(LocalDateTime.now())){
            throw new PastDateException("La data è nel passato..");
        }
        return date;
    }

    private int checkPlaces(int placesAvailable)throws ValueToSmallException{
        if(placesAvailable < 1){
            throw new ValueToSmallException("Deve esserci almeno un posto");
        }
        return placesAvailable;
    }

    public String getTitle(){
        return title;
    }

    public LocalDateTime getDate(){
        return date;
    }

    public int getPlacesAvailable(){
        return PLACES_AVAILABLE;
    }

    public int getReservedSeats(){
        return reservedSeats;
    }

    public void setTitle(String newTitle)throws PastDateException{

        title = checkTitle(newTitle);
    }


    public void setDate(LocalDateTime newDate)throws PastDateException{
        date = checkDate(newDate);
    }

    private boolean isValidDate(){
        boolean res = date.isBefore(LocalDateTime.now()) ? false : true;
        return res;
    }

    public String getSetsRemain(){
       return "Sono rimasti " + (getPlacesAvailable() - getReservedSeats());
    }

    public String bookSeat(int placesToReserve)throws ValueToSmallException, PastDateException{
        if(!isValidDate()){
            throw new PastDateException("Lo spettacolo è nel passato..");
        } else if(getPlacesAvailable() - (getReservedSeats() + placesToReserve) < 0){
            throw new ValueToSmallException("Hai prenotato piu posti di quanti siano disponibili");
        }

         checkPlaces(placesToReserve);
        reservedSeats = placesToReserve;
        return "Prenotazione di " + placesToReserve + " posti confermata!";
    }

    // attualmente codice simile, tenuti separati perchè in futuro potrebbero cambiare le specifiche dei due metodi
    public String cancellation(int cancellationResevation)throws ValueToSmallException, PastDateException{
        if(!isValidDate()){
            throw new PastDateException("lo spettacolo è nel passato..");
        } else if(getReservedSeats() < cancellationResevation){
            throw new ValueToSmallException("cancelli più posti di quanti siano prenotati");
        }

        checkPlaces(cancellationResevation);
        reservedSeats = cancellationResevation;

        return "Cancellazione di " + cancellationResevation + " posti confermata.";
    }

    @Override
    public String toString(){
        String dateFormatter = date.toString().replace('T', ' ');
        return dateFormatter + " - " + title;
    }

}