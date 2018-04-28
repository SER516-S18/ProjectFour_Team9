package ser516.project3.client.helper;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import ser516.project3.model.BodyMessageModel;
import ser516.project3.model.BodyMessageModel.BodyVitals;

/**
 * The BodyVitalsModelDecoder class implements the web-socket Decoder interface.
 * This class handles the Unmarshalling of JSON into BodyVitalsModel class
 * object.
 *
 * @author vsriva12
 */
public class BodyMessageModelDecoder implements Decoder.Text<BodyMessageModel> {

	/**
	 * Initialization method overridden from the interface
	 *
	 * @param config
	 *            configuration for the decoding of the model
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
	 * @param payload
	 *            JSON string to be decoded
	 * @return Returns the MessageModel object
	 * @throws DecodeException
	 */
	@Override
	public BodyMessageModel decode(String payload) throws DecodeException {

		BodyMessageModel bodyVitalsModel = new BodyMessageModel();

		// Read the payload.
		JsonObject root = Json.createReader(new StringReader(payload)).readObject();
		JsonObject timeAttributes = root.getJsonObject("Time-Attributes");
		JsonObject bodyVitalsAttributes = root.getJsonObject("BodyVitals");

		// Unmarshal the time attributes
		bodyVitalsModel.setInterval(timeAttributes.getJsonNumber("Interval").doubleValue());
		bodyVitalsModel.setTimeStamp(timeAttributes.getJsonNumber("TimeStamp").doubleValue());

		// Unmarshal the expression attributes.
		for (BodyVitals bodyVitals : BodyVitals.values()) {
			bodyVitalsModel.setBodyVitalsMap(bodyVitals.name(),
					bodyVitalsAttributes.getJsonNumber(bodyVitals.name()).doubleValue());
		}

		return bodyVitalsModel;
	}

	/**
	 * Returns a boolean indicating if a given string will be decoded or not
	 * 
	 * @param -
	 *            string to check for decoding
	 */
	@Override
	public boolean willDecode(String string) {
		return true;
	}

}