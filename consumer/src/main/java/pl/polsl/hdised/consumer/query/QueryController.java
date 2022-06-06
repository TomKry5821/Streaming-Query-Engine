package pl.polsl.hdised.consumer.query;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.polsl.hdised.consumer.averageresponse.AverageResponseDto;
import pl.polsl.hdised.consumer.device.DeviceDto;
import pl.polsl.hdised.consumer.exception.ParametersNotFoundException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/v1/query")
@RestController
public class QueryController {

    private final QueryService queryService;

    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    public AverageResponseDto getHistoricalAverage(@RequestParam("deviceId") String deviceId, @RequestParam("location") String location, @RequestParam("startDate") String stringStartDate, @RequestParam("finishDate") String stringFinishDate) throws ParseException, ParametersNotFoundException {
        return this.queryService.getHistoricalAverage(deviceId, location, stringStartDate, stringFinishDate);
    }

    @PostMapping("/set-query-parameters")
    public String setQueryParameters(@RequestParam("deviceId") String deviceId, @RequestParam("location") String location) throws ParametersNotFoundException {
        this.queryService.setQueryParameters(deviceId, location);

        return "Parameters set successfully";
    }

    @GetMapping("/get-average")
    public Float getStreamAverage() {
        return this.queryService.getStreamAverage();
    }

    @GetMapping("/devices")
    public List<DeviceDto> getDevices() {
        return this.queryService.getDevices();
    }

    @GetMapping("/locations")
    public void getLocations() {

        //TODO CREATE GET LOCATIONS METHOD AND LOCATION DTO
    }

    @ExceptionHandler(ParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleException() {
        return "Provided date format is invalid";
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleException(NullPointerException nullPointerException) {
        return nullPointerException.getMessage();
    }

    @ExceptionHandler(ParametersNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleException(ParametersNotFoundException parametersNotFoundException) {
        return parametersNotFoundException.getMessage();
    }
}
