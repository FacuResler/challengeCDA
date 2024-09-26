package com.challenge.cda.services.impl;

import com.challenge.cda.dto.request.PathRequestDto;
import com.challenge.cda.dto.response.PathResponseDto;
import com.challenge.cda.entity.Path;
import com.challenge.cda.entity.Station;
import com.challenge.cda.repository.PathRepository;
import com.challenge.cda.services.PathService;
import com.challenge.cda.services.StationService;
import org.springframework.stereotype.Service;

import java.util.*;

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
        path.setId(id);
        path.setSource(source);
        path.setDestination(destination);
        path.setCost(dto.getCost());

        pathRepository.save(path);
    }

    @Override
    public PathResponseDto getShortestPath(Long sourceId, Long destinationId) {
        Station sourceStation = stationService.getStationById(sourceId);
        Station destinationStation = stationService.getStationById(destinationId);

        if (sourceStation == null || destinationStation == null) {
            throw new IllegalArgumentException("Source or destination station not found.");
        }

        Map<Long, List<Path>> graph = buildGraph();
        Map<Long, Double> minDistance = new HashMap<>();
        Map<Long, Long> previousStation = new HashMap<>();
        PriorityQueue<StationNode> queue = initializeQueue(sourceStation);

        minDistance.put(sourceStation.getId(), 0.0);

        while (!queue.isEmpty()) {
            StationNode current = queue.poll();

            if (current.getStationId().equals(destinationStation.getId())) {
                return buildResponse(destinationStation, previousStation, minDistance);
            }

            processNeighbors(current, graph, minDistance, previousStation, queue);
        }


        return null;
    }

    private PriorityQueue<StationNode> initializeQueue(Station sourceStation) {
        PriorityQueue<StationNode> queue = new PriorityQueue<>(Comparator.comparingDouble(StationNode::getCost));
        queue.add(new StationNode(sourceStation.getId(), 0.0));
        return queue;
    }

    private void processNeighbors(StationNode current, Map<Long, List<Path>> graph,
                                  Map<Long, Double> minDistance, Map<Long, Long> previousStation,
                                  PriorityQueue<StationNode> queue) {
        Long currentStationId = current.getStationId();
        double currentDistance = minDistance.getOrDefault(currentStationId, Double.MAX_VALUE);

        for (Path path : graph.getOrDefault(currentStationId, new ArrayList<>())) {
            Long neighborId = path.getDestination().getId();
            double newDist = currentDistance + path.getCost();

            if (newDist < minDistance.getOrDefault(neighborId, Double.MAX_VALUE)) {
                minDistance.put(neighborId, newDist);
                previousStation.put(neighborId, currentStationId);
                queue.add(new StationNode(neighborId, newDist));
            }
        }
    }

    private PathResponseDto buildResponse(Station destinationStation, Map<Long, Long> previousStation,
                                          Map<Long, Double> minDistance) {
        List<Long> pathStations = new ArrayList<>();
        for (Long step = destinationStation.getId(); step != null; step = previousStation.get(step)) {
            pathStations.add(0, step);
        }
        return new PathResponseDto(minDistance.get(destinationStation.getId()), pathStations);
    }

    private Map<Long, List<Path>> buildGraph() {
        List<Path> allPaths = pathRepository.findAll();
        Map<Long, List<Path>> graph = new HashMap<>();

        for (Path path : allPaths) {
            graph.computeIfAbsent(path.getSource().getId(), k -> new ArrayList<>()).add(path);
            graph.computeIfAbsent(path.getDestination().getId(), k -> new ArrayList<>())
                    .add(new Path(path.getId(), path.getDestination(), path.getSource(), path.getCost()));
        }

        return graph;
    }

    private static class StationNode {
        private final Long stationId;
        private final double cost;

        public StationNode(Long stationId, double cost) {
            this.stationId = stationId;
            this.cost = cost;
        }

        public Long getStationId() {
            return stationId;
        }

        public double getCost() {
            return cost;
        }
    }

}
