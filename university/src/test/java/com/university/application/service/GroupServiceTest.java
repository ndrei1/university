package com.university.application.service;

import com.university.application.model.Group;
import com.university.application.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = {Group.class, GroupService.class})
@RunWith(SpringRunner.class)
class GroupServiceTest {
    @Autowired
    private GroupService groupService;

    @MockBean
    private GroupRepository groupRepository;


    @Test
    void getAllGroup() {
        List<Group> groups = groupService.getAllGroup();
        Mockito.verify(groupRepository, Mockito.times(1)).findAll();
    }

    @Test
    void deleteGroup() {
        Group group = new Group();
        groupService.deleteGroup(group);
        Mockito.verify(groupRepository, Mockito.times(1)).delete(group);
    }

    @Test
    void saveGroup() {
        Group group = new Group();
        groupService.saveGroup(group);
        Mockito.verify(groupRepository, Mockito.times(1)).save(group);
    }

    @Test
    void saveAllGroup() {
        List<Group> groups = new ArrayList<>();
        groupService.saveAllGroup(groups);
        Mockito.verify(groupRepository, Mockito.times(1)).saveAll(groups);
    }
}