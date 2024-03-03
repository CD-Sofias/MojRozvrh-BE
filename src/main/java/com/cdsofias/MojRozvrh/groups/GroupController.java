package com.cdsofias.MojRozvrh.groups;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/groups")
@AllArgsConstructor
public class GroupController {
    private final GroupServiceImpl groupService;


    @PostMapping
    public Groups createGroup(@RequestBody Groups group) {
        return groupService.createGroups(group);
    }

    @GetMapping
    public List<Groups> findAllGroups() {
        return groupService.findAllGroups();
    }

    @GetMapping("{id}")
    public Groups findGroupById(@PathVariable UUID id) {
        return groupService.findGroupById(id);
    }

    @DeleteMapping("{id}")
    public Groups deleteGroupById(@PathVariable UUID id) {
        return groupService.deleteGroupById(id);
    }

    @PutMapping("{id}")
    public Groups updateGroupById(@PathVariable UUID id, @RequestBody Groups group) {
        return groupService.updateGroupById(id, group);
    }
}

