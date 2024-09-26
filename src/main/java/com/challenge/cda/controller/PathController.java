package com.challenge.cda.controller;

import com.challenge.cda.dto.request.PathRequestDto;
import com.challenge.cda.services.PathService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cda/paths")
public class PathController {
    private final PathService pathService;

    public PathController(PathService pathService) {
        this.pathService = pathService;
    }


    @PutMapping("/{pathId}")
    public void createPath(@PathVariable Long pathId, @RequestBody PathRequestDto dto){
        pathService.createPath(pathId,dto);
    }


}
