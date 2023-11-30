package com.pollub.lab_7.services;

import com.pollub.lab_7.entities.Student;
import com.pollub.lab_7.exceptions.NotFoundException;
import com.pollub.lab_7.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudentList() {
        return (List<Student>) studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Integer id, Student newStudent) throws NotFoundException {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student not found"));
        student.setName(newStudent.getName());
        student.setSurname(newStudent.getSurname());
        student.setAverage(newStudent.getAverage());
        return studentRepository.save(student);
    }

    public void deleteStudent(Integer id) throws NotFoundException {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student not found"));
        studentRepository.delete(student);
    }
}
