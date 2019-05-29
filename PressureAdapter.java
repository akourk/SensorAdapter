import sensor.PressureSensor;

public class PressureAdapter implements Sensors {

    private PressureSensor sensor;

    public PressureAdapter(PressureSensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public double getValue() {
        return sensor.readValue();
    }

    @Override
    public String getStatus() {
        return sensor.getReport();
    }

    @Override
    public String getName() {
        return sensor.getSensorName();
    }
}
