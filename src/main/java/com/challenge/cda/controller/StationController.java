package com.challenge.cda.controller;

import com.challenge.cda.dto.request.StationRequestDto;
import com.challenge.cda.services.StationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cda/stations")
public class StationController {
    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }
    @PutMapping("/{stationId}")
    public void createStation(@PathVariable Long stationId, @RequestBody StationRequestDto dto){
        stationService.createStation(stationId,dto);
    }


}
