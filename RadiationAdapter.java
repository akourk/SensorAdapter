import sensor.RadiationSensor;

public class RadiationAdapter implements Sensors {

    private RadiationSensor sensor;

    public RadiationAdapter(RadiationSensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public double getValue() {
        return sensor.getRadiationValue();
    }

    @Override
    public String getStatus() {
        return sensor.getStatusInfo();
    }

    @Override
    public String getName() {
        return sensor.getName();
    }
}
