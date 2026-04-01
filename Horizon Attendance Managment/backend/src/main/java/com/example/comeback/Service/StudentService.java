package com.example.comeback.Service;

import com.example.comeback.Entity.Student;
import com.example.comeback.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component

public class StudentService {

@Autowired
        private StudentRepository RepoS;






    public List<Student> getMember(){

       return RepoS.findAll();
    }

    public String addMember(Student student){
        RepoS.save(student);

        return "student added";

    }

    public String update(Student student){

            RepoS.save(student);


        return "updated";

    }

    public List<Student> Delete(Long id){
        if (!RepoS.existsById(id)) {

            throw new RuntimeException("Cannot delete: Student with ID " + id + " not found.");
        }
        RepoS.deleteById(id);
        return null;
    }


}
