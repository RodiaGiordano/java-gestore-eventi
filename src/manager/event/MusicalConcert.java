package manager.event;

import manager.event.exceptions.PastDateException;
import manager.event.exceptions.ValueToSmallException;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MusicalConcert extends Show{

    private LocalTime time;
    private BigDecimal price;

    public MusicalConcert(String title, LocalDate date, int placesAvailable, LocalTime time, BigDecimal price)throws ValueToSmallException{
        super(title, date, placesAvailable);
        this.time = time; // non mi vengono in mente validazioni sensate in questo caso, tornarci
        this.price = checkPrice(price);
    }

   /* private LocalTime checkTimeEvent(LocalTime time){

    }*/

    private BigDecimal checkPrice(BigDecimal price) throws ValueToSmallException{
        if(price == null || price.compareTo(BigDecimal.ZERO) < 0){
            throw new ValueToSmallException("Il valore non può essere negativo");
        }
        return price.setScale(2, RoundingMode.HALF_EVEN);
    }
// caso mai cmabiare l'ordine della data
    public String getDateTimeEvent(){
      return getDate() + " - " + time.toString();
    }


    public String getPrice(){
        return String.format("Il prezzo del biglietto è: %.2f€", price);
    }

    public LocalTime getTime(){
        return time;
    }

    public void setTime(LocalTime time){

        this.time = time;
    }

    public void setDateTime() throws PastDateException{
        if(LocalDateTime.of(getDate(), time ).isBefore(LocalDateTime.now())){
            throw new PastDateException("L'orario è nel passato");
        }
    }


// refactoring per riutilizzo codice della superclasse
    @Override
    public String toString(){
        return getDateTimeEvent() + " - Titolo: " + getTitle() + " - " + getPrice();
    }

    // test
    /*
    public static void main(String[] args) {
        BigDecimal big = new BigDecimal(10.00);
        MusicalConcert prova = new MusicalConcert("ciao", LocalDate.of(2024, 06, 24), 10, LocalTime.of(18,00),big );

        System.out.println(prova);
    }
*/
}
