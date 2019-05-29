// Author       :   Alex Kourkoumelis
// Date         :   5/28/2019
// Title        :   Sensor Adapter
// Class        :   CS410, Software Engineering
// Assignment   :   Assignment 2
// Description  :   Utilize the Adapter Design Pattern on 3 different
//              :   sensors to display their information using JFrame.

import sensor.PressureSensor;
import sensor.RadiationSensor;
import sensor.TemperatureSensor;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class SensorApplication extends JFrame {

    public SensorApplication() {
        setTitle("Sensor Tracker");
        setLayout(new GridLayout(3, 1));
        setPreferredSize(new Dimension(600, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public class SensorPanel extends JPanel {
        private Color color;
        private int width;

        public SensorPanel(Color color, int width) {
            this.color = color;
            this.width = width;
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            g.setColor(this.color);
            g.fillRect(20, 20, width*3, 100);
        }
    }
    public Color setColor(String status) {
        if (status.equals("OK")) {
            return Color.GREEN;
        } else if (status.equals("CRITICAL")) {
            return Color.ORANGE;
        } else {
            return Color.RED;
        }
    }

    public void createPanels(double value, String status, String name) {
        Color color = setColor(status);
        JPanel  sensorPanel = new SensorPanel(color, determineWidth(value, name));
        sensorPanel.setBorder(new TitledBorder(name));
        add(sensorPanel);
    }

    public int determineWidth(double value, String name) {
        double percentage;

        switch(name){
            case "Pressure Sensor":
                percentage = value / 10;
                break;
            case "Radiation Sensor":
                percentage = value / 5;
                break;
            case "Temperature Sensor":
                percentage = value / 400;
                break;
            default:
                percentage = 50;
        }
        percentage = percentage*100;
        int temp = (int) percentage;

        if(temp < 1) {
            temp = 2;
        }
        return temp;
    }

    public static void main(String[] args) {
        SensorApplication app = new SensorApplication();

        RadiationSensor RS = new RadiationSensor();
        Sensors RA = new RadiationAdapter(RS);

        PressureSensor PS = new PressureSensor();
        Sensors PA = new PressureAdapter(PS);

        TemperatureSensor TS = new TemperatureSensor();
        Sensors TA = new TemperatureAdapter(TS);

        app.createPanels(RA.getValue(), RA.getStatus(), RA.getName());
        app.createPanels(PA.getValue(), PA.getStatus(), PA.getName());
        app.createPanels(TA.getValue(), TA.getStatus(), TA.getName());

        app.pack();
    }
}