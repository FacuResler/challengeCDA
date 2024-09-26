package com.challenge.cda.services.impl;

import com.challenge.cda.dto.request.StationRequestDto;
import com.challenge.cda.entity.Station;
import com.challenge.cda.repository.StationRepository;
import com.challenge.cda.services.StationService;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public void createStation(Long id, StationRequestDto dto) {
        Station station = new Station();
        station.setName(dto.getName());
        station.setId(id);
        stationRepository.save(station);
    }

    @Override
    public Station getStationById(Long id) {
        return stationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro station id: " + id));
    }
}
