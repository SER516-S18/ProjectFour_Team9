package ser516.project3.server.Components;

import ser516.project3.model.MessageModel;

/**
 * The ServerCommonData is a singleton class which contains the user defined
 * values of server configurations, which needs to be transmitted to client
 *
 * @author vsriva12
 */
public class ServerCommonData {

    private static ServerCommonData instance = null;
    private static MessageModel messageModel;
    private boolean shouldSendData;
    private boolean shouldRepeat;

    /**
     * Empty constructor to allow only single instance of the class
     */
    protected ServerCommonData() {

    }

    /**
     * The static function to get the instance of this class
     *
     * @return the instance of this class
     */
    public static ServerCommonData getInstance() {
        if (instance == null) {
            instance = new ServerCommonData();
            messageModel = new MessageModel();
        }
        return instance;
    }

    /**
     * The setter function of MessageModel class
     *
     * @param messageModel the messageModel to set
     */
    public static void setMessageModel(MessageModel messageModel) {
        ServerCommonData.messageModel = messageModel;
    }

    /**
     * The getter function of MessageModel
     *
     * @return the messageModel to get
     */
    public MessageModel getMessage() {
        return messageModel;
    }

	public boolean isShouldSendData() {
		return shouldSendData;
	}

	public void setShouldSendData(boolean shouldSendData) {
		this.shouldSendData = shouldSendData;
	}

	/**
	 * @return the shouldRepeat
	 */
	public boolean isShouldRepeat() {
		return shouldRepeat;
	}

	/**
	 * @param shouldRepeat the shouldRepeat to set
	 */
	public void setShouldRepeat(boolean shouldRepeat) {
		this.shouldRepeat = shouldRepeat;
	}
	
    
}
