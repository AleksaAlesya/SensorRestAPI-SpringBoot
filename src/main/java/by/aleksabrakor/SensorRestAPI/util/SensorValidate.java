package by.aleksabrakor.SensorRestAPI.util;

import by.aleksabrakor.SensorRestAPI.dtos.SensorDto;
import by.aleksabrakor.SensorRestAPI.services.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidate implements Validator {
   private final SensorService sensorService;

    public SensorValidate(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
       SensorDto sensorDto = (SensorDto) target;
        if (sensorService.findByName(sensorDto.getName()).isPresent()){
            errors.rejectValue("name", "", "This sensor already exists");
        }
    }
}
