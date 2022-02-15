package com.university.application.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.university.application.model.Plan;
import com.university.application.model.view.Views;
import com.university.application.service.PlanService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("plans")
public class PlanController {
    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping
    @JsonView(Views.PlanView.class)
    public List<Plan> getAll() {
        return planService.getAllPlan();
    }

    @GetMapping("{id}")
    @JsonView(Views.PlanView.class)
    public Plan get(@PathVariable("id") Plan plan) {
        return plan;
    }

    @PostMapping
    public Plan add(@RequestBody Plan classroom) {
        planService.savePlan(classroom);
        return classroom;
    }

    @PutMapping("{id}")
    public Plan update(@RequestBody Plan plan, @PathVariable("id") Plan planFromDb) {
        BeanUtils.copyProperties(plan, planFromDb, "id");
        planService.savePlan(planFromDb);
        return planFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Plan plan) {
        planService.deletePlan(plan);
    }

}
