package org.example.esapracticalwork3.service;

import org.example.esapracticalwork3.model.Course;
import org.example.esapracticalwork3.model.Group;
import org.example.esapracticalwork3.repository.CourseRepository;
import org.example.esapracticalwork3.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GroupRepository groupRepository;

    public void create(Course course, Long groupId) {
        Group group = groupRepository.findById(groupId).get();
        course.setGroup(group);
        courseRepository.save(course);
    }

    public Course get(Long id) {
        return courseRepository.findById(id).get();
    }

    public List<Course> getAll() {
        return courseRepository.findAll().stream().sorted(Comparator.comparing(Course::getName)).collect(Collectors.toList());
    }

    public void update(Long courseId, Course newCourseData, Long groupId) {
        Course course = courseRepository.findById(courseId).get();
        course.setName(newCourseData.getName());
        course.setHours(newCourseData.getHours());
        Group group = groupRepository.findById(groupId).get();
        course.setGroup(group);
        courseRepository.save(course);
    }

    public void delete(Long id) {
        Course course = courseRepository.getById(id);
        course.getGroup().getCourses().remove(course);
        courseRepository.deleteById(id);
    }
}
