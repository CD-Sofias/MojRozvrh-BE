package com.cdsofias.MojRozvrh.groups;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public List<Group> findAllGroups() {
        return groupRepository.findAll();
    }

    public Group findGroupById(UUID id) {
        Optional<Group> group = groupRepository.findById(id);
        return group.orElse(null);
    }

    public Group deleteGroupById(UUID id) {
        groupRepository.deleteById(id);
        return null;
    }

    public Group updateGroupById(UUID id, Group newGroup) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if(optionalGroup.isPresent()){
            Group group = optionalGroup.get();
            group.setQuantity(newGroup.getQuantity());
            group.setName(newGroup.getName());
            return groupRepository.save(group);
        }
        return null;
    }
}
