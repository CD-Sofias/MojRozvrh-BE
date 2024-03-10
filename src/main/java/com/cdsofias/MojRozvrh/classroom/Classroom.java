package com.cdsofias.MojRozvrh.classroom;

import com.cdsofias.MojRozvrh.address.Address;
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
public class Classroom {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id;
    private String type;
    private int capacity;
    private int classroom_number;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
}
