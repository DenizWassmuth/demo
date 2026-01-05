package com.example.demo.controller;

import com.example.demo.model.Student;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @GetMapping
    public String sayHello(){
        return "Hello User";
    }

    @PostMapping
    public String printStudent(@RequestBody Student student){
        return "Hello, " + student.name() + ", age: " + student.id();
    }

    @GetMapping("/{id}")
    public String printAge(@PathVariable int id){
        return "Hello User, your Id is: " + id;
    }

    @GetMapping("/search")
    public String search(@RequestParam String query){
        return "Hello, your search parameter is: " + query;
    }
}
