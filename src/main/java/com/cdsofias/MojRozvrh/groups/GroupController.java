package com.cdsofias.MojRozvrh.groups;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("groups")
@AllArgsConstructor
public class GroupController {
    private final GroupServiceImpl groupService;

    @PostMapping
    public ResponseEntity<Group> createGroup(@Valid @RequestBody CreateGroupDto groupDto) {
        Group group = groupService.createGroup(groupDto);
        return ResponseEntity.created(URI.create("/group/" + group.getId())).body(group);
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

