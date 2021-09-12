package org.example.esapracticalwork3.service;

import org.example.esapracticalwork3.model.Group;
import org.example.esapracticalwork3.model.Student;
import org.example.esapracticalwork3.repository.GroupRepository;
import org.example.esapracticalwork3.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    public void create(Student student, Long groupId) {
        Group group = groupRepository.findById(groupId).get();
        student.setGroup(group);
        studentRepository.save(student);
    }

    public Student get(Long id) {
        return studentRepository.findById(id).get();
    }

    public List<Student> getAll() {
        return studentRepository.findAll().stream().sorted(Comparator.comparing(Student::getLastName).thenComparing(Student::getFirstName)).collect(Collectors.toList());
    }

    public void update(Long studentId, Student newStudentData, Long groupId) {
        Student student = studentRepository.findById(studentId).get();
        student.setFirstName(newStudentData.getFirstName());
        student.setLastName(newStudentData.getLastName());
        student.setDateOfBirth(newStudentData.getDateOfBirth());
        Group group = groupRepository.findById(groupId).get();
        student.setGroup(group);
        studentRepository.save(student);
    }

    public void delete(Long id) {
        Student student = studentRepository.getById(id);
        student.getGroup().getStudents().remove(student);
        studentRepository.deleteById(id);
    }


}
