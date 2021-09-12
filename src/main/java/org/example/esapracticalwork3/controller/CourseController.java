package org.example.esapracticalwork3.controller;

import org.example.esapracticalwork3.model.Course;
import org.example.esapracticalwork3.model.Group;
import org.example.esapracticalwork3.service.CourseService;
import org.example.esapracticalwork3.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private GroupService groupService;

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("course", new Course());
        List<Long> groupIds = groupService.getAll().stream().map(Group::getId).collect(Collectors.toList());
        model.addAttribute("groupIds", groupIds);
        return "create_course";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Course course, @RequestParam("groupId") Long groupId) {
        courseService.create(course, groupId);
        return "redirect:/courses";
    }

    @GetMapping
    public String getAll(Model model) {
        List<Course> courses = courseService.getAll();
        model.addAttribute("courses", courses);
        return "show_courses";
    }

    @GetMapping("/{id}/update")
    public String getUpdatePage(@PathVariable("id") Long courseId, Model model) {
        model.addAttribute("course", courseService.get(courseId));
        List<Long> groupIds = groupService.getAll().stream().map(Group::getId).collect(Collectors.toList());
        model.addAttribute("groupIds", groupIds);
        return "update_course";
    }

    @PatchMapping("/{id}/update")
    public String update(@PathVariable("id") Long courseId, @ModelAttribute Course course, @RequestParam Long groupId) {
        courseService.update(courseId, course, groupId);
        return "redirect:/courses";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        courseService.delete(id);
        return "redirect:/courses";
    }
}
