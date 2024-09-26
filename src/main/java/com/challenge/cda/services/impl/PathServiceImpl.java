package com.challenge.cda.services.impl;

import com.challenge.cda.dto.request.PathRequestDto;
import com.challenge.cda.entity.Path;
import com.challenge.cda.entity.Station;
import com.challenge.cda.repository.PathRepository;
import com.challenge.cda.services.PathService;
import com.challenge.cda.services.StationService;
import org.springframework.stereotype.Service;

@Service
public class PathServiceImpl implements PathService {

    private final StationService stationService;
    private final PathRepository pathRepository;
    public PathServiceImpl(StationService stationService, PathRepository pathRepository) {
        this.stationService = stationService;
        this.pathRepository = pathRepository;
    }

    @Override
    public void createPath(Long id, PathRequestDto dto) {
        Station source = stationService.getStationById(dto.getSourceId());
        Station destination = stationService.getStationById(dto.getDestinationId());
        Path path = new Path();
        path.setSource(source);
        path.setDestination(destination);
        path.setCost(dto.getCost());

         pathRepository.save(path);
    }

}
