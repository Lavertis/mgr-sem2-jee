package org.pollub.lab_8.services;

import lombok.RequiredArgsConstructor;
import org.pollub.lab_8.converters.StudentMapper;
import org.pollub.lab_8.dtos.StudentDto;
import org.pollub.lab_8.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::mapStudentToStudentDto)
                .toList();
    }

    @Override
    public List<StudentDto> getAllStudentsNoAttachment() {
        return studentRepository.findAllNoAttachment();
    }
}
