package com.challenge.cda.services;

import com.challenge.cda.dto.StationDTO;
import com.challenge.cda.dto.request.StationRequestDto;
import com.challenge.cda.entity.Station;

import java.util.List;

public interface StationService {
   void createStation(Long id, StationRequestDto dto);

   Station getStationById(Long id);

}