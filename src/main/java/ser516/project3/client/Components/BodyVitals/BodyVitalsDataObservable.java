package ser516.project3.client.Components.BodyVitals;

import ser516.project3.client.Components.Graph.CoordinatesModel;

import java.util.ArrayList;
import java.util.Observable;

/**
 * This class holds the data for body vitals data and notifies the observer
 * that body vitals graph 
 *
 * @author Naga Ravi Teja
 */
public class BodyVitalsDataObservable extends Observable {
	
	private static BodyVitalsDataObservable instance;
    private ArrayList<ArrayList<CoordinatesModel>> bodyVitalsData;
	
    
    /**
     * Constructor that initializes array list of observers
     * */
    private BodyVitalsDataObservable() {
    	bodyVitalsData = new ArrayList<ArrayList<CoordinatesModel>>();
    }
    
    /**
     * Getter for array list of observers
     * @return bodyVitalsData an instance of ArrayList
     */
    public ArrayList<ArrayList<CoordinatesModel>> getBodyVitalsData() {
        return bodyVitalsData;
    }
    /**
     * Creates a singleton instance . If exists, returns
     * it, else creates it.
     *
     * @return instance of the BodyVitalsDataObservable
     */
    public static BodyVitalsDataObservable getInstance() {

        try {
            if (instance == null) {
                instance = new BodyVitalsDataObservable();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return instance;
    }
    /**
     * On receiving of new data from server, this method inserts them to the common
     * list and notifies the observers
     *
     * @param valueToBeAdded - the new value received from server.
     */
    public void addToListValues(ArrayList<CoordinatesModel> valueToBeAdded) {
        this.bodyVitalsData.add(valueToBeAdded);
        setChanged();
        this.notifyObservers();
    }
}
