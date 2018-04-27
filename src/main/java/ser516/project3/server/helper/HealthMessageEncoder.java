package ser516.project3.server.helper;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import ser516.project3.model.MessageModel;

/**
 * The HealthMessageEncoder class implements the web-sockets Encoder interface. This
 * class Marshals the HealthMessageModel class object into a json object.
 *
 * @author vsriva12
 */
public class HealthMessageEncoder implements Encoder.Text<MessageModel> {

	/**
	 * Initializer for the Message encoder. Empty as no initial configuration
	 * needs to be updated
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
	 * Encodes the model to JSON format to be transmitted
	 *
	 * @param messageModel
	 *            model to be encoded
	 * @return returns the encoded
	 * @throws EncodeException
	 */
	@Override
	public String encode(MessageModel messageModel) throws EncodeException {

		return null;
	}

}