package org.example.esapracticalwork3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator",  sequenceName = "course_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    private Integer hours;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public Course() {
    }

    public Course(String name, Integer hours) {
        this.name = name;
        this.hours = hours;
    }

    public Course(String name, Integer hours, Group group) {
        this.name = name;
        this.hours = hours;
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
