package by.aleksabrakor.SensorRestAPI.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MeasurementDto {

    @NotNull(message = "TemperatureValue should not be null")
    @Min(value = -100, message = "TemperatureValue should be between -100 and +100")
    @Max(value = 100, message = "TemperatureValue should be between -100 and +100")
    private Double temperatureValue;

    @NotNull(message = "Raining should not be empty")
    private Boolean raining;

    private SensorDto sensor;

    public Double getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(Double temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDto getSensor() {
        return sensor;
    }

    public void setSensor(SensorDto sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "MeasurementDto{" +
                "temperatureValue=" + temperatureValue +
                ", raining=" + raining +
                ", sensor=" + sensor.getName() +
                '}';
    }
}
