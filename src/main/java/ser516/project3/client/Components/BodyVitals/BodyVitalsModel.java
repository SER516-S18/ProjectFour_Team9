package ser516.project3.client.Components.BodyVitals;

import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ModelInterface;

import java.awt.*;

/**
 * BodyVitalsModel is a class to represent the data associated with health
 * and the display length of the x-axis depicting these body related data.
 *
 * @author Naga Ravi Teja Thoram
 * @version 1.0
 * @since 2018-04-26
 */
public class BodyVitalsModel implements ModelInterface {
    private Color pulseColor;
    private Color heartRateColor;
    private Color bodyTemperatureColor;
    private Color bloodSugarColor;
    private Color bmiColor;

    private int displayLength;
    
    /**
     * Creates a model for the Body Vitals metric data.
     */
    public BodyVitalsModel()
    {
    	pulseColor = Color.decode(ClientConstants.PULSE_DEFAULT_COLOR_HEX);
        heartRateColor = Color.decode(ClientConstants.HEART_RATE_DEFAULT_COLOR_HEX);
        bodyTemperatureColor = Color.decode(ClientConstants.BODY_TEMPERATURE_DEFAULT_COLOR_HEX);
        bloodSugarColor = Color.decode(ClientConstants.BLOOD_SUGAR_DEFAULT_COLOR_HEX);
        bmiColor = Color.decode(ClientConstants.BMI_DEFAULT_COLOR_HEX);
        displayLength = ClientConstants.DEFAULT_DISPLAY_LENGTH;
    }
    
    /**
     * Sets the color for pulse.
     *
     * @param pulse color for pulse data
     */
    public void setPulseColor(Color pulseColor) {
        this.pulseColor = pulseColor;
    }
    

    /**
     * Gets the color for pulse data.
     *
     * @return color for pulse data.
     */
    public Color getPulseColor() {
        return pulseColor;
    }
    
    /**
     * Sets the color for heart rate.
     *
     * @param heart rate color for heart rate data
     */
    public void setHeartRateColor(Color heartRateColor) {
        this.heartRateColor = heartRateColor;
    }

    /**
     * Gets the color for heart rate data.
     *
     * @return color for heart rate data.
     */
    public Color getHeartRateColor() {
        return heartRateColor;
    }
    
    /**
     * Sets the color for Body Temperature.
     *
     * @param Body Temperature color for Body Temperature data
     */
    public void setBodyTemperatureColor(Color bodyTemperatureColor) {
        this.bodyTemperatureColor = bodyTemperatureColor;
    }
    
    /**
     * Gets the color for body temperature data.
     *
     * @return color for body temperature data.
     */
    public Color getBodyTemperatureColor() {
        return bodyTemperatureColor ;
    }
    
    /**
     * Sets the color for Blood Sugar.
     *
     * @param Blood Sugar color for Blood Sugar data
     */
    public void setBloodSugarColor(Color bloodSugarColor) {
        this.bloodSugarColor = bloodSugarColor;
    }
    
    /**
     * Gets the color for blood Sugar data.
     *
     * @return color for blood Sugar data.
     */
    public Color getBloodSugarColor() {
        return bloodSugarColor;
    }
    
    /**
     * Sets the color for BMI.
     *
     * @param BMI color for BMI data
     */
    public void setBmiColor(Color bmiColor) {
        this.bmiColor = bmiColor;
    }
    
    /**
     * Gets the color for bmi data.
     *
     * @return color for bmi data.
     */
    public Color getBmiColor() {
        return bmiColor;
    }
    
    /**
     * Sets the display length for the x axis.
     *
     * @param displayLength the display length for the x axis
     */
    public void setDisplayLength(int displayLength) {
        this.displayLength = displayLength;
    }
    

    /**
     * Gets the display length for the x axis.
     *
     * @return the display length for the x axis
     */
    public int getDisplayLength() {
        return displayLength;
    }

}
