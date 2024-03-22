package com.cdsofias.MojRozvrh.groups;

import java.util.List;
import java.util.UUID;

public interface GroupService {
    Group createGroup(CreateGroupDto groupDto);
    List<Group> findAllGroups();
    Group findGroupById(UUID id);
    Group deleteGroupById(UUID id);
    Group updateGroupById(UUID id, Group newGroup);
}
