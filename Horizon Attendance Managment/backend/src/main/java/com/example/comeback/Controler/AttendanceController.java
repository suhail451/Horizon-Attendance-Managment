package com.example.comeback.Controler;


import com.example.comeback.Entity.Attendance;
import com.example.comeback.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController


public class AttendanceController {

    @Autowired
    private AttendanceService sc;


    private Attendance myattendy;

    @PostMapping("/mark/{student_id}/{Status}")
    public Attendance markattendace(@PathVariable Long student_id, @PathVariable String Status){

        return sc.Attendy(student_id,Status,LocalDate.now());
    }


    @GetMapping("/Attendace")

    public List<Attendance> showAttendance(){

        return sc.show();

    }





}
