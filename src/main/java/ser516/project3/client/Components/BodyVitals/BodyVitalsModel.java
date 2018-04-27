package ser516.project3.client.Components.BodyVitals;

import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ModelInterface;

import java.awt.*;

public class BodyVitalsModel implements ModelInterface {
    private Color pulseColor;
    private Color heartRateColor;
    private Color bodyTemperatureColor;
    private Color bloodSugarColor;
    private Color bmiColor;

    private int displayLength;
    
    public BodyVitalsModel()
    {
    	pulseColor = Color.decode(ClientConstants.PULSE_DEFAULT_COLOR_HEX);
        heartRateColor = Color.decode(ClientConstants.HEART_RATE_DEFAULT_COLOR_HEX);
        bodyTemperatureColor = Color.decode(ClientConstants.BODY_TEMPERATURE_DEFAULT_COLOR_HEX);
        bloodSugarColor = Color.decode(ClientConstants.BLOOD_SUGAR_DEFAULT_COLOR_HEX);
        bmiColor = Color.decode(ClientConstants.BMI_DEFAULT_COLOR_HEX);
        displayLength = ClientConstants.DEFAULT_DISPLAY_LENGTH;
    }
    
    public void setPulseColor(Color pulseColor) {
        this.pulseColor = pulseColor;
    }
    
    public Color getPulseColor() {
        return pulseColor;
    }
    
    public void setHeartRateColor(Color heartRateColor) {
        this.heartRateColor = heartRateColor;
    }
    
    public Color getHeartRateColor() {
        return heartRateColor;
    }
    
    public void setBodyTemperatureColor(Color bodyTemperatureColor) {
        this.bodyTemperatureColor = bodyTemperatureColor;
    }
    
    
    public Color getBodyTemperatureColor() {
        return bodyTemperatureColor ;
    }
    
    public void setBloodSugarColor(Color bloodSugarColor) {
        this.bloodSugarColor = bloodSugarColor;
    }
    
    public Color getBloodSugarColor() {
        return bloodSugarColor;
    }
    public void setBmiColor(Color bmiColor) {
        this.bmiColor = bmiColor;
    }
    
    public Color getBmiColor() {
        return bmiColor;
    }
    
    
    public void setDisplayLength(int displayLength) {
        this.displayLength = displayLength;
    }
    
    public int getDisplayLength() {
        return displayLength;
    }

}
