package manager.event;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// gestione deggli errori approssimativa, tornarci

public class MakeShow {
    private String title;
    private final ArrayList<Show> SHOWS = new ArrayList<>();
    public MakeShow(String title) throws IllegalArgumentException{
        this.title = title;
    }

    public ArrayList<Show> getSHOWS(){
        return SHOWS;
    }

    public String addShow(Show show){
        // probabilmente ci sono altri tipi di evento in fututo
        if(show instanceof MusicalConcert){
            this.SHOWS.add(show);
            return "Evento aggiunto con successo";
        }else{
            throw new IllegalArgumentException("non puoi programmare questo evento");
        }
    }

    public int getHowManyShows(){
        return SHOWS.size();
    }

    public void setToEmpty(){
        SHOWS.removeAll(SHOWS);
    }

    public Show[] getShowsForDate(LocalDate date){
        ArrayList<Show> showsInDateList = new ArrayList<>();

        for(Show show : SHOWS){
            if(show.getDate().isEqual(date)){
                showsInDateList.add(show);
            }
        }

        Show[] showsInDate = new Show[showsInDateList.size()];
        showsInDateList.toArray(showsInDate);
        return showsInDate;
    }


    public String getAllShowsForDate(){
        ArrayList<Show> showsForDate = new ArrayList<>();
    // non ho capito se l'interfacia clonable permette una copia profonda
        StringBuilder listOfShow = new StringBuilder();
        for(Show show : SHOWS){
            showsForDate.add(show);
        }
        Collections.sort(showsForDate, Comparator.comparing(Show::getDate));
        for(Show show : showsForDate){
            listOfShow.append("o ").append(show).append("\n");
        }

        return listOfShow.toString();
    }
}
