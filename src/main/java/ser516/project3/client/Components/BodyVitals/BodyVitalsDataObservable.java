package ser516.project3.client.Components.BodyVitals;

import ser516.project3.client.Components.Graph.CoordinatesModel;

import java.util.ArrayList;
import java.util.Observable;


public class BodyVitalsDataObservable extends Observable {
	
	private static BodyVitalsDataObservable instance;
    private ArrayList<ArrayList<CoordinatesModel>> bodyVitalsData;
	
    private BodyVitalsDataObservable() {
    	bodyVitalsData = new ArrayList<ArrayList<CoordinatesModel>>();
    }
    
    public ArrayList<ArrayList<CoordinatesModel>> getBodyVitalsData() {
        return bodyVitalsData;
    }
    
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
    
    public void addToListValues(ArrayList<CoordinatesModel> valueToBeAdded) {
        this.bodyVitalsData.add(valueToBeAdded);
        setChanged();
        this.notifyObservers();
    }
}
