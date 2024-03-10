package com.cdsofias.MojRozvrh.subjects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface SubjectRepository extends JpaRepository<Subject, UUID> {
}
