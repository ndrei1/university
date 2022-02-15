package com.university.application.service;

import com.university.application.model.Plan;
import com.university.application.model.TimeTable;
import com.university.application.repository.PlanRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = {PlanService.class})
@RunWith(SpringRunner.class)
class PlanServiceTest {

    @Autowired
    private PlanService planService;

    @MockBean
    private PlanRepository planRepository;

    @Test
    void deleteAllPlans() {
        List<TimeTable> timeTables = new ArrayList<>();
        Plan plan = new Plan();
        plan.setTimeTables(timeTables);
        Plan plan1 = new Plan();
        plan1.setTimeTables(timeTables);
        List<Plan> plans = new ArrayList<>();
        plans.add(plan);
        plans.add(plan1);
        planService.deleteAllPlans(plans);
        Mockito.verify(planRepository, Mockito.times(1)).saveAndFlush(plan);
        Mockito.verify(planRepository, Mockito.times(1)).saveAndFlush(plan1);
    }

    @Test
    void deletePlan() {
        List<TimeTable> timeTables = new ArrayList<>();
        Plan plan = new Plan();
        plan.setTimeTables(timeTables);
        planService.deletePlan(plan);
        Mockito.verify(planRepository, Mockito.times(1)).saveAndFlush(plan);
        Mockito.verify(planRepository, Mockito.times(1)).delete(plan);
    }

    @Test
    void getAllPlan() {
        List<Plan> plans = planService.getAllPlan();
        Mockito.verify(planRepository, Mockito.times(1)).findAll();
    }

    @Test
    void savePlan() {
        Plan plan = new Plan();
        planService.savePlan(plan);
        Mockito.verify(planRepository, Mockito.times(1)).save(plan);
    }

    @Test
    void saveAllPlan() {
        List<Plan> plans = new ArrayList<>();
        planService.saveAllPlan(plans);
        Mockito.verify(planRepository, Mockito.times(1)).saveAll(plans);
    }
}