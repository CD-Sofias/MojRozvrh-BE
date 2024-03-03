package com.cdsofias.MojRozvrh.groups;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GroupRepository extends JpaRepository<Groups, UUID> {
}
