package Result;

import Model.Events;

import java.util.ArrayList;

public class EventsResult {

    private ArrayList<Events> events;
    private String message;
    private boolean success;

    /**
     * This will store all the events from the Events Services
     * @param events
     */
    public EventsResult(ArrayList<Events> events) {

        this.events = events;
        this.success = true;
    }

    public EventsResult (String message){
        this.message = message;
        this.success = false;
    }

    /**
     * This will return all the events to the client
     * @return
     */
    public ArrayList<Events> getEvents() {
        return events;
    }

    /**
     * This will store all the events from the client
     * @param events
     */
    public void setEvents(ArrayList<Events> events) {
        this.events = events;
    }

    public String getMessage(){ return message;}

    public void setMessage(String message){ this.message = message; }

    public boolean getSuccess(){return success;}

    public void setSuccess(boolean success){ this.success = success; }
}
