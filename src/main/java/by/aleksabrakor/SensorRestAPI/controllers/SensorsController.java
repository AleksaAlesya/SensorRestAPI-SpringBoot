package by.aleksabrakor.SensorRestAPI.controllers;


import by.aleksabrakor.SensorRestAPI.dtos.SensorDto;
import by.aleksabrakor.SensorRestAPI.entities.Sensor;
import by.aleksabrakor.SensorRestAPI.dtos.responses.SensorsResponse;
import by.aleksabrakor.SensorRestAPI.services.SensorService;
import by.aleksabrakor.SensorRestAPI.util.ErrorResponse;
import by.aleksabrakor.SensorRestAPI.util.NotCreatedException;
import by.aleksabrakor.SensorRestAPI.util.SensorValidate;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static by.aleksabrakor.SensorRestAPI.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/sensors")
public class SensorsController {
    private  final SensorService sensorService;
    private  final ModelMapper modelMapper;
    private  final SensorValidate sensorValidate;

    public SensorsController(SensorService sensorService, ModelMapper modelMapper, SensorValidate sensorValidate) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidate = sensorValidate;
    }

    @GetMapping()
    public SensorsResponse findAll(){
        return new SensorsResponse(sensorService.findAll().stream().map(this::convertToSensor).collect(Collectors.toList()));
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDto sensorDto,
                                         BindingResult bindingResult){
        sensorValidate.validate(sensorDto, bindingResult);

        if (bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult);
        }

        sensorService.save(convertToSensorDto(sensorDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handException(NotCreatedException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                "Sensor  was not create. " + e.getMessage(),
                Timestamp.valueOf(LocalDateTime.now())
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private SensorDto convertToSensor (Sensor sensor){
        return  modelMapper.map(sensor, SensorDto.class);
    }

    private Sensor convertToSensorDto (SensorDto sensorDto){
        return  modelMapper.map(sensorDto, Sensor.class);
    }
}
