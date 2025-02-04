package by.aleksabrakor.SensorRestAPI.util;

import by.aleksabrakor.SensorRestAPI.dtos.MeasurementDto;
import by.aleksabrakor.SensorRestAPI.services.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {
   private final SensorService sensorService;

    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDto measurementDto = (MeasurementDto) target;
        if (measurementDto.getSensor() == null){
            return;
        }
        if (sensorService.findByName(measurementDto.getSensor().getName()).isEmpty()){
            errors.rejectValue("sensor", "", "This sensor is not find");
        }
    }
}
