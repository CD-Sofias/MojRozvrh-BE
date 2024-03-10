package com.cdsofias.MojRozvrh.department;
import com.cdsofias.MojRozvrh.faculty.Faculty;
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
@Table(name = "department")
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;
}
