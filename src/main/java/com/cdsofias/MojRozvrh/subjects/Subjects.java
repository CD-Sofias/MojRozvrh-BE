package com.cdsofias.MojRozvrh.subjects;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "subject")
public class Subjects {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String type;
    private String name;
}
