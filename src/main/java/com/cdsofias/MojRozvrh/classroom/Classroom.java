package com.cdsofias.MojRozvrh.classroom;

import com.cdsofias.MojRozvrh.address.Address;
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
@Table(name = "classroom")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Classroom {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id;
    private String type;
    private int capacity;
    private int classroomNumber;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
}
