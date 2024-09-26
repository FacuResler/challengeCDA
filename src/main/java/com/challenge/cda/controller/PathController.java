package com.challenge.cda.controller;

import com.challenge.cda.dto.request.PathRequestDto;
import com.challenge.cda.dto.response.PathResponseDto;
import com.challenge.cda.services.PathService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cda/paths")
public class PathController {
    private final PathService pathService;

    public PathController(PathService pathService) {
        this.pathService = pathService;
    }


    @PutMapping("/{pathId}")
    public void createPath(@PathVariable Long pathId,  @Valid @RequestBody PathRequestDto dto){
        pathService.createPath(pathId,dto);
    }

    @GetMapping("/{sourceId}/{destinationId}")
    public ResponseEntity<PathResponseDto> getShortestPath(@PathVariable Long sourceId, @PathVariable Long destinationId) {
        PathResponseDto shortestPath = pathService.getShortestPath(sourceId, destinationId);
        return ResponseEntity.ok(shortestPath);
    }
}
