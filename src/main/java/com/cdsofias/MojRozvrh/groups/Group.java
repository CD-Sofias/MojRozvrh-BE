package com.cdsofias.MojRozvrh.groups;


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
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id;
    private Number quantity;
    private String name;
}

