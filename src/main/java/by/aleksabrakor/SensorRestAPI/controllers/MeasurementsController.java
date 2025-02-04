package by.aleksabrakor.SensorRestAPI.controllers;

import by.aleksabrakor.SensorRestAPI.dtos.MeasurementDto;
import by.aleksabrakor.SensorRestAPI.dtos.responses.MeasurementsResponse;
import by.aleksabrakor.SensorRestAPI.entities.Measurement;
import by.aleksabrakor.SensorRestAPI.services.MeasurementService;
import by.aleksabrakor.SensorRestAPI.util.ErrorResponse;
import by.aleksabrakor.SensorRestAPI.util.MeasurementValidator;
import by.aleksabrakor.SensorRestAPI.util.NotCreatedException;
import by.aleksabrakor.SensorRestAPI.util.NotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static by.aleksabrakor.SensorRestAPI.util.ErrorsUtil.returnErrorsToClient;


@RequestMapping("/measurements")
@RestController
public class MeasurementsController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementsController(MeasurementService measurementService, ModelMapper modelMapper, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }

    @GetMapping
    public MeasurementsResponse getMeasurement() {
        return new MeasurementsResponse(measurementService.findAll().stream()
                .map(this::convertToMeasurementDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public MeasurementDto findById(@PathVariable("id") int id) {
        return convertToMeasurementDto(measurementService.findById(id));
    }

    @GetMapping("rainyDaysCount")
    public Long rainyDaysCount() {
        return measurementService.getRainyDaysCount();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDto measurementDto,
                                          BindingResult bindingResult) {
        measurementValidator.validate(measurementDto, bindingResult);

        if (bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult);
        }

        measurementService.save(convertToMeasurement(measurementDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handException(NotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                "Object was not found",
                Timestamp.valueOf(LocalDateTime.now())
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handException(NotCreatedException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                "Measurement  was not create. " + e.getMessage(),
                Timestamp.valueOf(LocalDateTime.now())
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private Measurement convertToMeasurement(MeasurementDto measurementDto) {
        return modelMapper.map(measurementDto, Measurement.class);
    }

    private MeasurementDto convertToMeasurementDto(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDto.class);
    }

}
