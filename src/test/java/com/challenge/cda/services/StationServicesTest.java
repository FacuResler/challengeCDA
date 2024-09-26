package com.challenge.cda.services;

import com.challenge.cda.dto.request.StationRequestDto;
import com.challenge.cda.entity.Station;
import com.challenge.cda.repository.StationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
@Rollback
public class StationServicesTest {

    @Autowired
    private StationService stationService;

    @Autowired
    private StationRepository stationRepository;

    private StationRequestDto stationRequestDto;

    @BeforeEach
    public void setUp() {
        stationRequestDto = new StationRequestDto();
        stationRequestDto.setName("Test Station");
    }

    @Test
    public void testCreateStation() {

        Long stationId = 1L;

        stationService.createStation(stationId, stationRequestDto);

        Station savedStation = stationRepository.findById(stationId).orElse(null);
        assertThat(savedStation).isNotNull();
        assertThat(savedStation.getName()).isEqualTo("Test Station");
    }
}
