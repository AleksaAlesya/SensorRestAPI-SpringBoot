package by.aleksabrakor.SensorRestAPI.services;

import by.aleksabrakor.SensorRestAPI.entities.Measurement;
import by.aleksabrakor.SensorRestAPI.repositories.MeasurementRepository;
import by.aleksabrakor.SensorRestAPI.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;


    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    public Measurement findById(int id) {
        return measurementRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    @Transactional
    public void save(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    public Long getRainyDaysCount() {
        return (long) measurementRepository.findMeasurementsByRainingTrue().size();
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setCreatedAt(LocalDateTime.now());
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).get());
    }
}
