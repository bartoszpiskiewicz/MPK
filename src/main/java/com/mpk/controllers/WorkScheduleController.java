package com.mpk.controllers;

import com.mpk.helpers.WorkScheduleHelper;
import com.mpk.services.WorkScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("workschedule")
public class WorkScheduleController {

    private final WorkScheduleService workScheduleService;

    @Autowired
    public WorkScheduleController(WorkScheduleService workScheduleService) {
        this.workScheduleService = workScheduleService;
    }

    @GetMapping
    public List<WorkScheduleHelper> findAll() {
        return workScheduleService.findAll();
    }


    @PostMapping
    public ResponseEntity<Void> add(@RequestBody WorkScheduleHelper workScheduleHelper) {
        workScheduleService.add(workScheduleHelper);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


}
