package ser516.project3.server.Components.Health;

import ser516.project3.interfaces.ModelInterface;

public class HealthModel implements ModelInterface {


    private double pulse;
    private double heartRate;
    private double bodyTemperature;
    private double bmi;
    private double height;
    private double weight;

    /**
     * Gets the pulse in  <code>HealthModel</code>.
     *
     * @return the pulse value
     */
    public double getPulse() {
        return pulse;
    }

    /**
     * Sets the pulse in <code>HealthModel</code>.
     *
     * @param pulse value of pulse
     */
    public void setPulse(double pulse) {
        this.pulse = pulse;
    }


    /**
     * Gets the heart rate from <code>HealthModel</code>.
     *
     * @return the heart rate value
     */
    public double getHeartRate() {
        return heartRate;
    }

    /**
     * Sets the heart rate value in <code>HealthModel</code>.
     *
     * @param heartRate value of the heart rate
     */
    public void setHeartRate(double heartRate) {
        this.heartRate = heartRate;
    }

    /**
     * Gets the body temperature from <code>HealthModel</code>.
     *
     * @return the body temperature
     */
    public double getBodyTemperature() {
        return bodyTemperature;
    }

    /**
     * Sets the body temperature in  <code>HealthModel</code>.
     *
     * @param bodyTemperature value of body temperature
     */
    public void setBodyTemperature(double bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }


    /**
     * Gets the bmi from <code>HealthModel</code>.
     *
     * @return the bmi value
     */
    public double getBmi() {
        return bmi;
    }

    /**
     * Sets the bmi value in  <code>HealthModel</code>.
     *
     * @param bmi value of bmi
     */
    public void setBmi(double bmi) {
        this.bmi = bmi;
    }


    /**
     * Gets the height value  from <code>HealthModel</code>.
     *
     * @return the height value
     */

    public double getHeight() {
        return height;
    }

    /**
     * Sets the height value in the <code>GraphModel</code>.
     *
     * @param height length of X-axis
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Gets the weight value  from <code>HealthModel</code>.
     *
     * @return the weight value
     */

    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight value in the <code>HealthModel</code>.
     *
     * @param weight length of X-axis
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
