package org.example.esapracticalwork3.repository;

import org.example.esapracticalwork3.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
