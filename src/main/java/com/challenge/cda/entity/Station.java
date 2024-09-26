package com.challenge.cda.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Station {

    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "source")
    private List<Path> sourcePaths;

    @OneToMany(mappedBy = "destination")
    private List<Path> destinationPaths;
}