package com.cdsofias.MojRozvrh.groups;

import java.util.List;
import java.util.UUID;

public interface GroupService {
    Group createGroup(Group group);
    List<Group> findAllGroups();
    Group findGroupById(UUID id);
    Group deleteGroupById(UUID id);
    Group updateGroupById(UUID id, Group newGroup);
}
