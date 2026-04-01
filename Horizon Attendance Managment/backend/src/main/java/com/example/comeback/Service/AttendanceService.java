package com.example.comeback.Service;

import com.example.comeback.Entity.Attendance;
import com.example.comeback.Entity.Student;
import com.example.comeback.Repository.StudentRepository;
import com.example.comeback.Repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@Component
public class AttendanceService {

    @Autowired
    private AttendanceRepository repoA;
    @Autowired
    private StudentRepository repoS;



    public Attendance Attendy(Long student_id,String Status,LocalDate date){

        Student student= repoS.findById(student_id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + student_id));



        Attendance attendy=new Attendance(student,LocalDate.now(),Status);

        return repoA.save(attendy);
    }

    public List<Attendance> show(){
        return repoA.findAll();

    }



}
