package org.example.esapracticalwork3.controller.rest_api.json;

import org.example.esapracticalwork3.model.Course;
import org.example.esapracticalwork3.model.Group;
import org.example.esapracticalwork3.model.Student;
import org.example.esapracticalwork3.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/json/groups", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupJsonRestController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/create")
    public void create(@RequestBody Group group) {
        groupService.create(group);
    }

    @GetMapping
    public List<Group> getAll() {
        return groupService.getAll();
    }

    @GetMapping("/{id}")
    public Group get(@PathVariable Long id) {
        return groupService.get(id);
    }

    @PatchMapping("/{id}/update")
    public void update(@PathVariable("id") Long groupId, @RequestBody Group group) {
        groupService.update(groupId, group);
    }

    @DeleteMapping("/{id}/delete")
    public void delete(@PathVariable Long id) {
        groupService.delete(id);
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<List<Course>> getCourses(@PathVariable Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("groupId", String.valueOf(id));
        return new ResponseEntity<>(groupService.getCourses(id), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<Student>> getStudents(@PathVariable Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("groupId", String.valueOf(id));
        return new ResponseEntity<>(groupService.getStudents(id), httpHeaders, HttpStatus.OK);
    }
}
