package ser516.project3.client.helper;

import java.util.Observable;
import java.util.Observer;

import ser516.project3.client.thread.ClientConnectionThreadObservable;

public class ClientConnectionThreadObserver implements Observer {
	Thread clientConnectionThread;
	ClientConnectionThread threadInstance;

	@Override
	public void update(Observable arg0, Object arg1) {
		ClientConnectionThreadObservable clientConnectionThreadObservable = (ClientConnectionThreadObservable) arg0;
		threadInstance = new ClientConnectionThread(clientConnectionThreadObservable.getIpAddress(),
				clientConnectionThreadObservable.getPort(), clientConnectionThreadObservable.getEndpoint());
		clientConnectionThread = new Thread(threadInstance);
		clientConnectionThread.start();
	}

}
