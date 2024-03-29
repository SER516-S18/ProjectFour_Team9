package ser516.project3.server.helper;

import ser516.project3.model.MessageModel;
import ser516.project3.model.MessageModel.AbstractExpression;
import ser516.project3.model.MessageModel.ConcreteExpression;
import ser516.project3.model.MessageModel.Emotion;
import ser516.project3.model.MessageModel.SelectedCriteria;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.HashMap;
import java.util.Map;

/**
 * The MessageEncoder class implements the web-sockets Encoder interface. This
 * class Marshals the MessageModel class object into a json object.
 *
 * @author vsriva12
 */
public class MessageEncoder implements Encoder.Text<MessageModel> {

    /**
     * Override method for Initializer of the Message encoder. 
     * Empty as no initial configuration needs to be updated
     *
     * @param config The configuration of endpoint for the encoding
     */
    @Override
    public void init(EndpointConfig config) {
        // Intentionally empty.
    }

    /**
     * Override Mthod to destroy the component if created
     */
    @Override
    public void destroy() {
        // Intentionally empty.
    }

    /**
     * Override Method to encodes the model to JSON format to be transmitted
     *
     * @param messageModel model to be encoded
     * @return returns the encoded
     * @throws EncodeException
     */
    @Override
    public String encode(MessageModel messageModel) throws EncodeException {
        Map<String, Object> config = new HashMap<String, Object>();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);

        // Build Time attributes
        JsonObject timeAttributes = factory.createObjectBuilder().add("Interval", messageModel.getInterval())
                .add("TimeStamp", messageModel.getTimeStamp()).build();

        // Build selection flag attributes
        JsonObjectBuilder selectionFlagBuilder = factory.createObjectBuilder();
        for (SelectedCriteria selectionFlag : SelectedCriteria.values()) {
            selectionFlagBuilder.add(selectionFlag.name(), messageModel.getSelectionFlag(selectionFlag.name()));
        }

        // Build "Expression" object.
        JsonObjectBuilder expressionBuilder = factory.createObjectBuilder();
        for (AbstractExpression aex : AbstractExpression.values()) {
            expressionBuilder.add(aex.name(), messageModel.getAbstractExpression(aex.name()));
        }
        for (ConcreteExpression cex : ConcreteExpression.values()) {
            expressionBuilder.add(cex.name(), messageModel.getConcreteExpression(cex.name()));
        }
        // Build "Emotion" object.
        JsonObjectBuilder emotionBuilder = factory.createObjectBuilder();
        for (Emotion em : Emotion.values()) {
            emotionBuilder.add(em.name(), messageModel.getEmotion(em.name()));
        }
        return Json.createObjectBuilder().add("Time-Attributes", timeAttributes)
                .add("Selection-Flags", selectionFlagBuilder.build()).add("Expression", expressionBuilder.build())
                .add("Emotion", emotionBuilder.build()).build().toString();
    }

}