package by.aleksabrakor.SensorRestAPI.dtos.responses;

import by.aleksabrakor.SensorRestAPI.dtos.SensorDto;

import java.util.List;

public class SensorsResponse {

    List<SensorDto> sensorDtoList;

    public SensorsResponse() {
    }

    public SensorsResponse(List<SensorDto> sensorDtoList) {
        this.sensorDtoList = sensorDtoList;
    }

    public List<SensorDto> getSensorDtoList() {
        return sensorDtoList;
    }

    public void setSensorDtoList(List<SensorDto> sensorDtoList) {
        this.sensorDtoList = sensorDtoList;
    }
}
