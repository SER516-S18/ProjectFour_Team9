package ser516.project3.server.helper;

import org.apache.log4j.Logger;
import org.glassfish.tyrus.server.Server;
import ser516.project3.constants.ServerConstants;
import ser516.project3.server.controller.ServerController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * The thread class for Server Container which initializes the glass fish
 * container to provide environment for the server's web socket to receive
 * connections
 *
 * @author vsriva12
 */
public class ServerContainerThread implements Runnable {
	final static Logger logger = Logger.getLogger(ServerContainerThread.class);
	private static final int PORT = 1516;
	private static final int HEALTH_PORT = 1517;
	private Server server;
	private Server healthServer;
	private String serverType;

	public ServerContainerThread(String serverType) {
		this.serverType = serverType;
	}

	@Override
	public void run() {
		try {
			if (serverType.equalsIgnoreCase(ServerConstants.HEALTH_SERVER)) {
				healthServer = new Server(ServerConstants.LOCALHOST,
						HEALTH_PORT, "", null,
						HealthServerConnectionEndpoint.class);
				healthServer.start();
			} else if (serverType
					.equalsIgnoreCase(ServerConstants.MAIN_SERVER)) {
				server = new Server(ServerConstants.LOCALHOST, PORT, "", null,
						ServerConnectionEndpoint.class);
				server.start();
			}

			ServiceHelperModel.getInstance().setServerStartedStatus(true);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(System.in));
			reader.readLine();
		} catch (Exception e) {
			logger.error(ServerConstants.ERROR_SERVER_START + e.getMessage());
			ServiceHelperModel.getInstance().setServerError(true);
		} finally {
			server.stop();
			ServiceHelperModel.getInstance().setServerStartedStatus(false);
		}
	}

	/**
	 * @return the healthServer
	 */
	public Server getHealthServer() {
		return healthServer;
	}

	/**
	 * @param healthServer
	 *            the healthServer to set
	 */
	public void setHealthServer(Server healthServer) {
		this.healthServer = healthServer;
	}

	/**
	 * @return the server
	 */
	public Server getServer() {
		return server;
	}

	/**
	 * @param server
	 *            the server to set
	 */
	public void setServer(Server server) {
		this.server = server;
	}

}
