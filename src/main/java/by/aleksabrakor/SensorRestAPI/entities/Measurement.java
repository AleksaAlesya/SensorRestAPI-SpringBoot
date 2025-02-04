package by.aleksabrakor.SensorRestAPI.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "temperature_value")
    @NotNull(message = "TemperatureValue should not be null")
    @Min(value = -100, message = "TemperatureValue should be between -100 and +100")
    @Max(value = 100, message = "TemperatureValue should be between -100 and +100")
    private Double temperatureValue;

    @Column(name = "raining")
    @NotNull(message = "Raining should not be empty")
    private Boolean raining;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "sensor_name", referencedColumnName = "name")
    private Sensor sensor;

    public Measurement() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(Double temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }


    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", temperatureValue=" + temperatureValue +
                ", raining=" + raining +
                ", createdAt=" + createdAt +
                ", sensor=" + sensor.getName() +
                '}';
    }
}
