package org.example.esapracticalwork3.repository;

import org.example.esapracticalwork3.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
