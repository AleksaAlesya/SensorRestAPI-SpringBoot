package by.aleksabrakor.SensorRestAPI.dtos.responses;

import by.aleksabrakor.SensorRestAPI.dtos.MeasurementDto;

import java.util.List;

public class MeasurementsResponse {
    List<MeasurementDto> measurementDtoList;

    public MeasurementsResponse() {
    }

    public MeasurementsResponse(List<MeasurementDto> measurementDtoList) {
        this.measurementDtoList = measurementDtoList;
    }

    public List<MeasurementDto> getMeasurementDtoList() {
        return measurementDtoList;
    }

    public void setMeasurementDtoList(List<MeasurementDto> measurementDtoList) {
        this.measurementDtoList = measurementDtoList;
    }
}
