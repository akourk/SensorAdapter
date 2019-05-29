import sensor.TemperatureSensor;

public class TemperatureAdapter implements Sensors {

    private TemperatureSensor sensor;

    public TemperatureAdapter(TemperatureSensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public double getValue() {
        return sensor.senseTemperature();
    }

    @Override
    public String getStatus() {
        return sensor.getTempReport();
    }

    @Override
    public String getName() {
        return sensor.getSensorType();
    }
}
