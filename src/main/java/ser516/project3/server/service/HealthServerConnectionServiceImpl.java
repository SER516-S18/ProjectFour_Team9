package ser516.project3.server.service;

import org.apache.log4j.Logger;

import ser516.project3.constants.ServerConstants;
import ser516.project3.server.controller.ServerController;
import ser516.project3.server.helper.ServerContainerThread;
import ser516.project3.server.service.HealthServerConnectionServiceInterface;


/**
 * Class to implement the connection service for health server
 * 
 * @author abhinab, garv
 *
 */
public class HealthServerConnectionServiceImpl
		implements HealthServerConnectionServiceInterface {

	final static Logger logger = Logger
			.getLogger(HealthServerConnectionServiceImpl.class);
	Thread serverContainerThread;
	ServerContainerThread threadInstance;

	/**
	 * Method responsible for creating threads for web socket
	 */
	@Override
	public void initServerEndpoint() {
		threadInstance = new ServerContainerThread(ServerConstants.HEALTH_SERVER);
		serverContainerThread = new Thread(threadInstance);
		serverContainerThread.start();
//		ServerController.getInstance().getConsoleController().getConsoleModel()
//				.logMessage(ServerConstants.SERVER_STARTED);
	}

	/**
	 * Method responsible for closing any thread instance
	 */
	@Override
	public void stopServerEndpoint() {
		if (threadInstance != null || serverContainerThread != null) {
			threadInstance.getServer().stop();
//			ServerController.getInstance().getConsoleController()
//					.getConsoleModel()
//					.logMessage(ServerConstants.SERVER_STOPPED);
			serverContainerThread.interrupt();
		}
		/*ServerController.getInstance().getTopController().getTopModel()
				.setServerStarted(false);
		ServerController.getInstance().getTopController().getTopModel()
				.setSendButtonEnabled(false);
		ServerController.getInstance().getTopController().getTopModel()
				.setServerStartStopButtonText(ServerConstants.START_SERVER);
		ServerController.getInstance().getTopController()
				.updateServerStartStopButtonText();
		ServerController.getInstance().getTopController()
				.updateEnableDisableSendButton();
		ServerController.getInstance().getTopController().setBlinking(false);*/
	}
}