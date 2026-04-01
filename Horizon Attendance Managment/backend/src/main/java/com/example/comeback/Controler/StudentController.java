package com.example.comeback.Controler;
import com.example.comeback.Entity.Student;
import com.example.comeback.Service.StudentService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class StudentController {

//    classes objects
    @Autowired
    private StudentService sc;
    private Student student;

//mappings
//    get mapping for show the data
    @GetMapping("/Students")

    public List<Student> get(){

        return sc.getMember();
    }


    // post mapping for add teh data
    @PostMapping("/add/{name}/{semester}")
    public Student addData( @PathVariable String name, @PathVariable int semester){
        Student s = new Student(name,semester);
        sc.addMember(s);
        return s;

    }



//  put mapping for update the data

    @PutMapping("/update/{index}/{id}/{name}/{semester}")

    public String updateData(Student student){

        sc.update(student);
        return "Student Updated Successfully";

    }


//    delete mapping for remove the data

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete/{id}")
    public List<Student> deletebyid(@PathVariable Long id) {
        sc.Delete(id); // Ensure this calls repo.deleteById(id)
        return sc.getMember(); // Return the updated list
    }


}
