package org.example.esapracticalwork3.model;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator",  sequenceName = "_group_id_seq", allocationSize = 1)
    private Long id;

    private Integer year;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Course> courses = new HashSet<>();

    public Group() {
    }

    public Group(Integer year) {
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
