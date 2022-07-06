package co.za.mahasha.dal.domain.utils;

import co.za.mahasha.dal.student.entities.Student;
import co.za.mahasha.dal.student.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository) {
        return args -> {
            Student retshepile = new Student(
                    1L,
                    "Retshepile",
                    "molozwi@gmail.com",
                    LocalDate.of(1990, Month.AUGUST, 1));
            Student tshepo = new Student(
                    2L,
                    "Tshepo",
                    "molozwi@gmail.com",
                    LocalDate.of(1994, Month.SEPTEMBER, 2));

            repository.saveAll(List.of(retshepile, tshepo));
        };
    }
}
