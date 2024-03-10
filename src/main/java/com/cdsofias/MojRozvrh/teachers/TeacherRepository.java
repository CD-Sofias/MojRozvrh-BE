package com.cdsofias.MojRozvrh.teachers;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;


public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
}
