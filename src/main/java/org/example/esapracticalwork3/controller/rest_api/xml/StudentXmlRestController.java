package org.example.esapracticalwork3.controller.rest_api.xml;

import org.example.esapracticalwork3.model.Student;
import org.example.esapracticalwork3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/xml/students", produces = MediaType.APPLICATION_XML_VALUE)
public class StudentXmlRestController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public void create(@RequestBody Student student, @RequestAttribute Long groupId) {
        studentService.create(student, groupId);
    }

    @GetMapping
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable Long id) {
        return studentService.get(id);
    }

    @PatchMapping("/{courseId}/update")
    public void update(@PathVariable Long courseId, @RequestBody Student student, @RequestAttribute Long groupId) {
        studentService.update(courseId, student, groupId);
    }

    @DeleteMapping("/{id}/delete")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }
}
