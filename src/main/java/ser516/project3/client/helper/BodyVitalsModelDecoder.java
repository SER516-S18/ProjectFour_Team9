package ser516.project3.client.helper;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import ser516.project3.client.Components.BodyVitals.BodyVitalsModel;

/**
 * The BodyVitalsModelDecoder class implements the web-socket Decoder interface. This
 * class handles the Unmarshalling of JSON into BodyVitalsModel class object.
 *
 * @author vsriva12
 */
public class BodyVitalsModelDecoder implements Decoder.Text<BodyVitalsModel> {

    /**
     * Initialization method overridden from the interface
     *
     * @param config configuration for the decoding of the model
     */
    @Override
    public void init(EndpointConfig config) {
        // Intentionally empty.
    }

    /**
     * Destroys the object, if exists. Overridden from the interface
     */
    @Override
    public void destroy() {
        // Intentionally empty.
    }

    /**
     * Decodes the Strings to get a messageModel object
     *
     * @param payload JSON string to be decoded
     * @return Returns the MessageModel object
     * @throws DecodeException
     */
    @Override
    public BodyVitalsModel decode(String payload) throws DecodeException {

    	BodyVitalsModel bodyVitalsModel = new BodyVitalsModel();

        // Read the payload.
        JsonObject root = Json.createReader(new StringReader(payload)).readObject();

        // Unmarshal the time attributes
        //TODO: replace them with the vital model
        bodyVitalsModel.setBloodSugarColor(null);
        bodyVitalsModel.setBmiColor(null);
        bodyVitalsModel.setDisplayLength(30);
        bodyVitalsModel.setHeartRateColor(null);
        bodyVitalsModel.setPulseColor(null);

      

		return bodyVitalsModel;
	}
	
	/**
	 * Returns a boolean indicating if a given string 
	 * will be decoded or not
	 * @param - string to check for decoding
	 */
	@Override
	public boolean willDecode(String string) {
		return true;
	}

}