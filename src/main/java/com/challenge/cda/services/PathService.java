package com.challenge.cda.services;

import com.challenge.cda.dto.request.PathRequestDto;


public interface PathService {
    void createPath(Long id, PathRequestDto dto);
}