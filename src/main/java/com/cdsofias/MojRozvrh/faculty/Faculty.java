package com.cdsofias.MojRozvrh.faculty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "faculty")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Faculty {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id;
    private String name;
}
