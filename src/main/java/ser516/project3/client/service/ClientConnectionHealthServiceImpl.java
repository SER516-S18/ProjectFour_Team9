package ser516.project3.client.service;

import java.io.IOException;

import org.apache.log4j.Logger;

import ser516.project3.client.controller.ClientControllerFactory;
import ser516.project3.client.helper.ClientConnectionThread;

/**
 * Class to register the client and connect it to the server. Also, updates the
 * observers whenever the data is received from server
 *
 * @author vsriva12
 */
public class ClientConnectionHealthServiceImpl
		implements ClientConnectionServiceInterface {
	final static Logger logger = Logger
			.getLogger(ClientConnectionHealthServiceImpl.class);
	Thread clientConnectionThread;
	ClientConnectionThread threadInstance;

	/**
	 * Creates a client server connection
	 *
	 * @param ipAddress
	 *            the ip address
	 * @param port
	 *            port number
	 * @param endpoint
	 *            endpoint string
	 */
	@Override
	public void createClientConnection(final String ipAddress, final int port,
			final String endpoint) {
		// TODO: Add UI observables here like the other class
		threadInstance = new ClientConnectionThread(ipAddress, port, endpoint);
		clientConnectionThread = new Thread(threadInstance);
		clientConnectionThread.start();
	}

	/**
	 * Stops the client server connection.
	 */
	@Override
	public void stopClientConnection() {
		try {
			if (threadInstance != null
					&& threadInstance.getClientSession() != null)
				threadInstance.getClientSession().close();
			ClientControllerFactory.getInstance().getClientController()
					.setConnectionStatus(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error while stopping health client end point::::"
					+ e.getMessage().toString());
		}
		clientConnectionThread.interrupt();
	}

}