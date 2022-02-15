package com.university.application.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.university.application.model.Group;
import com.university.application.model.view.Views;
import com.university.application.service.GroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    @JsonView(Views.GroupView.class)
    public List<Group> getAll() {
        return groupService.getAllGroup();
    }

    @GetMapping("{id}")
    @JsonView(Views.GroupView.class)
    public Group get(@PathVariable("id") Group group) {
        return group;
    }

    @PostMapping
    public Group add(@RequestBody Group group) {
        groupService.saveGroup(group);
        return group;
    }

    @PutMapping("{id}")
    @JsonView(Views.GroupView.class)
    public Group update(@RequestBody Group group, @PathVariable("id") Group groupFromDb) {
        BeanUtils.copyProperties(group, groupFromDb, "id", "timeTables");
        groupService.saveGroup(groupFromDb);
        return groupFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Group group) {
        groupService.deleteGroup(group);
    }
}
