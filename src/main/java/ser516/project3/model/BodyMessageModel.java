package ser516.project3.model;

import java.util.HashMap;
/*
 * MessageModel is a data model class that encapsulates the data messages sent
 * between the client and the server. The {@link BodyMessageModelDecoder} and
 * {@link BodyMessageModelEncoder} marshal and unmarshal instances of this class,
 * respectively.
 */
public class BodyMessageModel {
	 	private double interval;
	    private double timeStamp;
	    
	    private HashMap<String, Double> bodyVitalsMap = new HashMap<String, Double>();
	    
	    public BodyMessageModel(){
	    	interval = 1;
	    	timeStamp = 0;
	    }
	    


	    /**
	     * @return the interval
	     */
	    public double getInterval() {
	        return interval;
	    }

	    /**
	     * @param interval the interval to set
	     */
	    public void setInterval(double interval) {
	        this.interval = interval;
	    }

	    /**
	     * @return the timeStamp
	     */
	    public double getTimeStamp() {
	        return timeStamp;
	    }

	    /**
	     * @param timeStamp the timeStamp to set
	     */
	    public void setTimeStamp(double timeStamp) {
	        this.timeStamp = timeStamp;
	    }

	    /**
	     * Adds the passed double value to the corresponding string in the hashmap
	     *
	     * @param emotionKey key for the emotion type
	     * @param val        value for the emotion key
	     */
	    public void setBodyVitalsMap(String bodyVitalsKey, Double val) {
	        this.bodyVitalsMap.put(bodyVitalsKey.toLowerCase(), val);
	    }

	    /**
	     * Returns the value for the respective key
	     *
	     * @param bodyVitalsKey  for which the value has to be received
	     * @return returns the value for the respective emotion key
	     */
	    public double getBodyVitalsMap(String bodyVitalsKey) {
	        if (bodyVitalsKey == null || this.bodyVitalsMap.get(bodyVitalsKey.toLowerCase()) == null) {
	            return 0.0;
	        }
	        return this.bodyVitalsMap.get(bodyVitalsKey.toLowerCase());
	    }

	    

	    /**
	     * Overridden method to convert the model into string in the specified format
	     *
	     * @return returns the string conversion of the class
	     */
	    @Override
	    public String toString() {
	        StringBuilder builder = new StringBuilder();
	        builder.append("MessageModel [timeStamp=").append(timeStamp);
	        builder.append(", interval=").append(interval);

	        for (BodyVitals exp : BodyVitals.values()) {
	            builder.append(", " + exp.name() + "=").append(this.bodyVitalsMap.get(exp.name()));
	        }

	        return builder.toString();
	    }



	    /**
	     * Enum to standardize the kind of body vitals
	     */
	    public enum BodyVitals {
	    		pulse,heartrate,bodytemperature,bloodsugar,bmi
	    }

}
