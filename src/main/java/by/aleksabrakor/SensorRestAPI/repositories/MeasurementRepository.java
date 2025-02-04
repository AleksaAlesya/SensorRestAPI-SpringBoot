package by.aleksabrakor.SensorRestAPI.repositories;

import by.aleksabrakor.SensorRestAPI.entities.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findMeasurementsByRainingTrue();
}
