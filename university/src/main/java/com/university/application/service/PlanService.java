package com.university.application.service;

import com.university.application.model.Plan;
import com.university.application.model.TimeTable;
import com.university.application.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {
    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public void deleteAllPlans(List<Plan> plans) {
        for (Plan p : plans) {
            updateTimeTableAndPlan(p);
        }
    }

    private void updateTimeTableAndPlan(Plan plan) {
        for (TimeTable timeTable : plan.getTimeTables()) {
            timeTable.getPlans().remove(plan);
        }
        planRepository.saveAndFlush(plan);
    }

    public void deletePlan(Plan plan) {
        updateTimeTableAndPlan(plan);
        planRepository.delete(plan);
    }

    public List<Plan> getAllPlan() {
        return planRepository.findAll();
    }

    public void savePlan(Plan plan) {
        planRepository.save(plan);
    }

    public void saveAllPlan(List<Plan> plans) {
        planRepository.saveAll(plans);
    }

}
