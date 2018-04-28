package ser516.project3.server.helper;

import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import ser516.project3.model.BodyMessageModel;
import ser516.project3.model.BodyMessageModel.BodyVitals;

/**
 * The HealthMessageEncoder class implements the web-sockets Encoder interface.
 * This class Marshals the HealthMessageModel class object into a json object.
 *
 * @author vsriva12
 */
public class BodyMessageModelEncoder implements Encoder.Text<BodyMessageModel> {

	/**
	 * Initializer for the Message encoder. Empty as no initial configuration needs
	 * to be updated
	 *
	 * @param config
	 *            The configuration of endpoint for the encoding
	 */
	@Override
	public void init(EndpointConfig config) {
		// Intentionally empty.
	}

	/**
	 * Destroys the component if created
	 */
	@Override
	public void destroy() {
		// Intentionally empty.
	}

	/**
	 * Override Method to encodes the body vitals model to JSON format to be transmitted
	 *
	 * @param messageModel
	 *            model to be encoded
	 * @return returns the encoded
	 * @throws EncodeException
	 */
	@Override
	public String encode(BodyMessageModel bodyMessageModel) throws EncodeException {
		Map<String, Object> config = new HashMap<String, Object>();
		JsonBuilderFactory factory = Json.createBuilderFactory(config);

		// Build Time attributes
		JsonObject timeAttributes = factory.createObjectBuilder().add("Interval", bodyMessageModel.getInterval())
				.add("TimeStamp", bodyMessageModel.getTimeStamp()).build();

		// Build "Body Vitals" object.
		JsonObjectBuilder bodyMessageBuilder = factory.createObjectBuilder();
		for (BodyVitals bodyVital : BodyVitals.values()) {
			bodyMessageBuilder.add(bodyVital.name(), bodyMessageModel.getBodyVitalsMap(bodyVital.name()));
		}

		return Json.createObjectBuilder().add("Time-Attributes", timeAttributes)
				.add("BodyVitals", bodyMessageBuilder.build())
				.build().toString();
	}

}