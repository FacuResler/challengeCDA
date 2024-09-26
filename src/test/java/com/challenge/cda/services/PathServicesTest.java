package com.challenge.cda.services;
import com.challenge.cda.dto.request.PathRequestDto;
import com.challenge.cda.entity.Path;
import com.challenge.cda.entity.Station;
import com.challenge.cda.repository.PathRepository;
import com.challenge.cda.repository.StationRepository;
import com.challenge.cda.services.impl.PathServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback
public class PathServicesTest {
    @Autowired
    private PathServiceImpl pathService;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private PathRepository pathRepository;

    private Station sourceStation;
    private Station destinationStation;

    @BeforeEach
    void setUp() {

        sourceStation = new Station();
        sourceStation.setId(10L);
        sourceStation.setName("Source Station");
        stationRepository.save(sourceStation);

        destinationStation = new Station();
        destinationStation.setId(20L);
        destinationStation.setName("Destination Station");
        stationRepository.save(destinationStation);
    }

    @Test
    void testCreatePath() {
        PathRequestDto pathRequest = new PathRequestDto();
        pathRequest.setSourceId(sourceStation.getId());
        pathRequest.setDestinationId(destinationStation.getId());
        pathRequest.setCost(50.0);

        pathService.createPath(1L, pathRequest);


        Path savedPath = pathRepository.findAll().stream()
                .filter(path -> path.getSource().getId().equals(sourceStation.getId()) &&
                        path.getDestination().getId().equals(destinationStation.getId()))
                .findFirst()
                .orElse(null);

        assertThat(savedPath).isNotNull();
        assertThat(savedPath.getCost()).isEqualTo(50.0);
        assertThat(savedPath.getSource()).isEqualTo(sourceStation);
        assertThat(savedPath.getDestination()).isEqualTo(destinationStation);
    }
}