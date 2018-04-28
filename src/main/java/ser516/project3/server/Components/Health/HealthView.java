package ser516.project3.server.Components.Health;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.EventListener;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;

import ser516.project3.constants.ServerConstants;
import ser516.project3.interfaces.ViewInterface;

/**
 * View to show the panel on the UI for health control data
 * 
 * @author abhinab, garv
 *
 */
public class HealthView extends HealthAbstractView {
    //private JPanel mainPanel;
    private JSpinner jspinner[];

    private static final Font SUBFONT = new Font(ServerConstants.FONT_NAME, Font.BOLD, 14);

    /**
     * Method to set emotion model
     *
     * @param healthModel object containing required health data.
     */
    public HealthView(HealthModel healthModel) {
        super(healthModel);
    }

    /**
     * Method to initialize the emotions view panel
     *
     * @param subViews object of type ViewInterface
     */
    @Override
    public void initializeView(ViewInterface[] subViews) {
        setBorder(new TitledBorder(null, "Health", TitledBorder.LEADING,
                TitledBorder.TOP, SUBFONT, null));
        setBackground(Color.decode(ServerConstants.COLOR_CODE));
        Dimension spinnerDimension = new Dimension(65, 30);
        double current = 0.0;
        double min = 0.0;
        double max = 100.0;
        double step = 1.0;
        jspinner = new JSpinner[7];

        for (HealthPanel hm : HealthPanel.values()) {
            JLabel interest_label = new JLabel(hm.name());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = hm.gbc_x;
            gbc.gridy = hm.gbc_y;
            add(interest_label, gbc);
            SpinnerModel spinner = new SpinnerNumberModel(current, min, max, step);
            jspinner[hm.id] = new JSpinner(spinner);
            jspinner[hm.id].setPreferredSize(spinnerDimension);
            jspinner[hm.id].setName(hm.name);
            GridBagConstraints spinnerGbc = new GridBagConstraints();
            spinnerGbc.gridx = hm.spinner_x;
            spinnerGbc.gridy = hm.spinner_y;
            add(jspinner[hm.id], spinnerGbc);
        }
    }

    /**
     * Enumeration for setting constants for all the emotion options
     */
    public enum HealthPanel {
        Pulse(0, 1, 0, 2, 0, "Pulse"), HeartRate(1, 3, 0, 4, 0, "HeartRate"),
        Temperature(2, 1, 1, 2, 1, "Temperature"), BloodSugar(3, 3, 1, 4, 1, "BloodSugar"),
        Weight(4, 1, 2, 2, 2, "Weight"), Height(5, 3, 2, 4, 2, "Height");
        int id, gbc_x, gbc_y, spinner_x, spinner_y;
        String name;

        HealthPanel(int id, int gbc_x, int gbc_y, int spinner_x, int spinner_y, String name) {
            this.id = id;
            this.gbc_x = gbc_x;
            this.gbc_y = gbc_y;
            this.spinner_x = spinner_x;
            this.spinner_y = spinner_y;
            this.name = name;
        }
    }

    /**
     * Method to listener to every spinner in the emotions panel
     *
     * @param eventListener object of ChangeListener
     */
    @Override
    public void addListener(EventListener eventListener, String componentName) {
        if(componentName.equals("SPINNER_HEALTH")) {
            for(HealthPanel healthPanel: HealthPanel.values()) {
                jspinner[healthPanel.id].addChangeListener((ChangeListener)eventListener);
            }
        }
    }
}

