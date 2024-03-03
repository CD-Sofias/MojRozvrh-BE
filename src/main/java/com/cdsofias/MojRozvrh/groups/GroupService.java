package com.cdsofias.MojRozvrh.groups;

import java.util.List;
import java.util.UUID;

public interface GroupService {
    Groups createGroups(Groups group);
    List<Groups> findAllGroups();
    Groups findGroupById(UUID id);
    Groups deleteGroupById(UUID id);
    Groups updateGroupById(UUID id, Groups newGroup);
}
