package com.cdsofias.MojRozvrh.classroom;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClassroomRepository extends JpaRepository<Classroom, UUID> {
    Optional<Classroom> findByType(String type);
}
