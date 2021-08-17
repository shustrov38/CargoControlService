package com.shustrov38.cargocontrolservice.components.webapi.controller;

import com.shustrov38.cargocontrolservice.components.postgre.service.PostgreService;
import com.shustrov38.cargocontrolservice.components.webapi.dto.IncomingRequestBody;
import com.shustrov38.cargocontrolservice.components.webapi.dto.OutgoingRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WebApiController {

    private PostgreService postgreService;

    @Autowired
    public WebApiController(PostgreService postgreService) {
        this.postgreService = postgreService;
    }

    @GetMapping("/emulate/view-data")
    public List<OutgoingRequestBody> getAllEmulationDataFromDb(@Valid @RequestBody IncomingRequestBody incomingRequestBody) {
        List<OutgoingRequestBody> result = new ArrayList<>();

        var persistedEmulationDataDtos =
                postgreService.getEmulationDataFromDbFilteredByTimeInterval(
                        incomingRequestBody.getTimeAfter(),
                        incomingRequestBody.getTimeTo()
                );

        int pageNumber = 1;
        int statesOnCurrentPage = 0;

        for (var emulationDataDto : persistedEmulationDataDtos) {

            int containerIndex = 0;
            for (var container : emulationDataDto.getContainers()) {

                for (var s : container.getState()) {

                    var sensorValues = s.getSensorValues();
                    for (var parName : sensorValues.keySet()) {

                        result.add(new OutgoingRequestBody(
                                pageNumber,
                                containerIndex + 1,
                                s.getTime(),
                                parName,
                                sensorValues.get(parName)
                        ));

                        if (++statesOnCurrentPage == incomingRequestBody.getStringsForPage()) {
                            ++pageNumber;
                            statesOnCurrentPage = 0;
                        }

                    }
                }
            }
        }

        return result;
    }
}
