package com.cdsofias.MojRozvrh.schedule_cell;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScheduleCellRepository extends JpaRepository<ScheduleCell, UUID> {
}
