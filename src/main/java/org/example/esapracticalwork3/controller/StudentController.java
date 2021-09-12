package org.example.esapracticalwork3.controller;

import org.example.esapracticalwork3.model.Group;
import org.example.esapracticalwork3.model.Student;
import org.example.esapracticalwork3.service.GroupService;
import org.example.esapracticalwork3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private GroupService groupService;

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("student", new Student());
        List<Long> groupIds = groupService.getAll().stream().map(Group::getId).collect(Collectors.toList());
        model.addAttribute("groupIds", groupIds);
        return "create_student";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Student student, @RequestParam Long groupId) {
        studentService.create(student, groupId);
        return "redirect:/students";
    }

    @GetMapping
    public String getAll(Model model) {
        List<Student> students = studentService.getAll();
        model.addAttribute("students", students);
        return "show_students";
    }

    @GetMapping("/{id}/update")
    public String getUpdatePage(@PathVariable("id") Long studentId, Model model) {
        Student student = studentService.get(studentId);
        model.addAttribute("student", student);
        List<Long> groupIds = groupService.getAll().stream().map(Group::getId).collect(Collectors.toList());
        model.addAttribute("groupIds", groupIds);
        return "update_student";
    }

    @PatchMapping("/{id}/update")
    public String update(@PathVariable("id") Long studentId, @ModelAttribute Student student, @RequestParam Long groupId) {
        studentService.update(studentId, student, groupId);
        return "redirect:/students";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        studentService.delete(id);
        return "redirect:/students";
    }
}
