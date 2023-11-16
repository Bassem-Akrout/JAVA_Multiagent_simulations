import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class EventManager {
    private long currentDate;
    private List<Event> events;

    public EventManager() {
        currentDate = 0;
        events = new ArrayList<>();
    }
    public List<Event> getEvents(){
        return events;
    }
    // Ajoute un évènement au gestionnaire
    public void addEvent(Event event) {
        events.add(event);
        
        Collections.sort(events, Comparator.comparingLong(Event::getDate)); // Trie la liste des évènements par date
    }

    // Passe au pas de temps suivant et exécute les évènements jusqu'à la date courante
    public void next() {
        currentDate++;
        List<Event> currentEvents = new ArrayList<>();
        // Sélectionne les évènements à exécuter à la date courante
        for (Event event : events) {
            if (event.getDate() <= currentDate) {
                currentEvents.add(event);
            }
        }
        
        // Exécute les évènements
        for (Event event : currentEvents) {
            event.execute();
            
            events.remove(event); // Retire les évènements exécutés de la liste
        }
    }

    // Indique si tous les évènements ont été traités
    public boolean isFinished() {
        return events.isEmpty();
    }

    // Réinitialise le gestionnaire
    public void restart() {
        currentDate = 0;
        events.clear();
    }
}