package ser516.project3.client;

import ser516.project3.client.Components.BodyVitals.BodyVitalsDataObservable;
import ser516.project3.client.Components.Expressions.ExpressionsDataObservable;
import ser516.project3.client.Components.Face.FaceExpressionsObservable;
import ser516.project3.client.Components.Header.HeaderObservable;
import ser516.project3.client.Components.PerformanceMetric.PerformanceMetricDataObservable;
import ser516.project3.client.controller.ClientController;
import ser516.project3.client.controller.ClientControllerFactory;
import ser516.project3.client.helper.ClientConnectionThreadObserver;
import ser516.project3.client.observers.BodyVitalsGraphObserver;
import ser516.project3.client.observers.ExpressionsGraphObserver;
import ser516.project3.client.observers.FaceViewObserver;
import ser516.project3.client.observers.HeaderObserver;
import ser516.project3.client.observers.PerformanceMetricGraphObserver;
import ser516.project3.client.thread.ClientConnectionThreadObservable;
import ser516.project3.constants.ClientConstants;

import javax.swing.*;
import java.awt.*;

/**
 * Main class to initialize the client
 *
 * @author Adhiraj Tikku, Manish Tandon
 */
public class Client {
    final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Client.class);

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                boolean seaGlassFound = false;
                try {
                    if (System.getProperty("os.name").substring(0, 7).equals("Windows")) {
                        UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
                    }
                    seaGlassFound = true;

                    if (!seaGlassFound) {
                        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                            System.out.println(info.getName());
                            if ("Nimbus".equals(info.getName())) {
                                UIManager.setLookAndFeel(info.getClassName());
                                break;
                            }
                        }

                    }

                } catch (Exception e) {
                    logger.error(ClientConstants.CLIENT_START_ERROR + e.getMessage());

                } finally {
                	// Registering the observers on client start
        	        HeaderObserver headerObserver = new HeaderObserver();
        	        HeaderObservable.getInstance().addObserver(headerObserver);
        	        
        	        ClientConnectionThreadObserver clientConnectionThreadObserver = new ClientConnectionThreadObserver();
        	        ClientConnectionThreadObservable.getInstance().addObserver(clientConnectionThreadObserver);

        	        PerformanceMetricGraphObserver performanceMetricObserver = new PerformanceMetricGraphObserver();
        	        PerformanceMetricDataObservable.getInstance().addObserver(performanceMetricObserver);
        	        
        	        BodyVitalsGraphObserver bodyVitalsObserver = new BodyVitalsGraphObserver();
        	        BodyVitalsDataObservable.getInstance().addObserver(bodyVitalsObserver);
        	        
        	        ExpressionsGraphObserver expressionsGraphObserver = new ExpressionsGraphObserver();
        	        ExpressionsDataObservable.getInstance().addObserver(expressionsGraphObserver);

        	        FaceViewObserver faceViewObserver = new FaceViewObserver();
        	        FaceExpressionsObservable.getInstance().addObserver(faceViewObserver);
        	        
                    ClientControllerFactory controllerFactory = ClientControllerFactory.getInstance();
                    ClientController clientController = (ClientController) controllerFactory
                            .getController(ClientConstants.CLIENT, null, null, null, null);
                    clientController.initializeView();
                }
            }
        });
    }

}
