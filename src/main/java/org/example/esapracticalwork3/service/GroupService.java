package org.example.esapracticalwork3.service;

import org.example.esapracticalwork3.model.Course;
import org.example.esapracticalwork3.model.Group;
import org.example.esapracticalwork3.model.Student;
import org.example.esapracticalwork3.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public void create(Group group) {
        groupRepository.save(group);
    }

    public Group get(Long id) {
        return groupRepository.findById(id).get();
    }

    public List<Group> getAll() {
        return groupRepository.findAll().stream().sorted(Comparator.comparing(Group::getId)).collect(Collectors.toList());
    }

    public void update(Long groupId, Group newGroupData) {
        Group group = groupRepository.findById(groupId).get();
        group.setYear(newGroupData.getYear());
        groupRepository.save(group);
    }

    public void delete(Long id) {
        groupRepository.deleteById(id);
    }

    public List<Student> getStudents(Long id) {
        Group group = get(id);
        return group.getStudents().stream().sorted(Comparator.comparing(Student::getLastName).thenComparing(Student::getFirstName)).collect(Collectors.toList());
    }

    public List<Course> getCourses(Long id) {
        Group group = get(id);
        return group.getCourses().stream().sorted(Comparator.comparing(Course::getName)).collect(Collectors.toList());
    }
}
