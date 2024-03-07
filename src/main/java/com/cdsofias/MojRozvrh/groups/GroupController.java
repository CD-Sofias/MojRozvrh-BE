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
    public Group createGroup(@RequestBody Group group) {
        return groupService.createGroup(group);
    }

    @GetMapping
    public List<Group> findAllGroups() {
        return groupService.findAllGroups();
    }

    @GetMapping("{id}")
    public Group findGroupById(@PathVariable UUID id) {
        return groupService.findGroupById(id);
    }

    @DeleteMapping("{id}")
    public Group deleteGroupById(@PathVariable UUID id) {
        return groupService.deleteGroupById(id);
    }

    @PutMapping("{id}")
    public Group updateGroupById(@PathVariable UUID id, @RequestBody Group group) {
        return groupService.updateGroupById(id, group);
    }
}

