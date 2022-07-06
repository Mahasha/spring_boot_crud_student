package co.za.mahasha.dal.student.services;

import co.za.mahasha.dal.student.entities.Student;
import co.za.mahasha.dal.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

        if (studentByEmail.isPresent())
            throw new IllegalStateException("Student already registered!");
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudentById(Long studentId) {
        boolean studentExists = studentRepository.existsById(studentId);

        if (!studentExists)
            throw new IllegalStateException("Student with ID " + studentId + " does not exist!");
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student " +
                "with ID " + studentId + " does not exist!"));

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email))
            student.setEmail(email);

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name))
            student.setName(name);
    }
}
