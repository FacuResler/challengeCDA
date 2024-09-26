package com.challenge.cda.services;

import com.challenge.cda.dto.request.PathRequestDto;
import com.challenge.cda.dto.response.PathResponseDto;


public interface PathService {
    void createPath(Long id, PathRequestDto dto);
    PathResponseDto getShortestPath(Long sourceId, Long destinationId);
}