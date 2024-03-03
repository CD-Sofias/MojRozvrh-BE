package com.cdsofias.MojRozvrh.groups;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GroupServiceImpl implements GroupService{
    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Groups createGroups(Groups group) {
        group.setId(UUID.randomUUID());
        return groupRepository.save(group);
    }

    public List<Groups> findAllGroups() {
        return groupRepository.findAll();
    }

    public Groups findGroupById(UUID id) {
        Optional<Groups> group = groupRepository.findById(id);
        return group.orElse(null);
    }

    public Groups deleteGroupById(UUID id) {
        groupRepository.deleteById(id);
        return null;
    }

    public Groups updateGroupById(UUID id, Groups newGroup) {
        Optional<Groups> optionalGroup = groupRepository.findById(id);
        if(optionalGroup.isPresent()){
            Groups group = optionalGroup.get();
            group.setQuantity(newGroup.getQuantity());
            group.setName(newGroup.getName());
            return groupRepository.save(group);
        }
        return null;
    }
}
