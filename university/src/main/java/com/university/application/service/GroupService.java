package com.university.application.service;

import com.university.application.model.Group;
import com.university.application.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAllGroup() {
        return groupRepository.findAll();
    }

    public void deleteGroup(Group group) {
        groupRepository.delete(group);
    }

    public void saveGroup(Group group) {
        groupRepository.save(group);
    }

    public void saveAllGroup(List<Group> groups) {
        groupRepository.saveAll(groups);
    }

}