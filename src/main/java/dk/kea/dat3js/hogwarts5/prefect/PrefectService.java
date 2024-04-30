package dk.kea.dat3js.hogwarts5.prefect;

import dk.kea.dat3js.hogwarts5.students.StudentResponseDTO;
import dk.kea.dat3js.hogwarts5.students.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrefectService {

    private final StudentService studentService;

    public PrefectService(StudentService studentService) {
        this.studentService = studentService;
    }

    public List<StudentResponseDTO> findAllPrefects() {
        // Fetch all students
        List<StudentResponseDTO> students = studentService.findAll();
        // Filter out the prefects

        return students.stream()
                .filter(StudentResponseDTO::prefect)
                .collect(Collectors.toList());
    }
    public List<StudentResponseDTO> findAllPrefectsByHouse(String house) {
        // Fetch all students
        List<StudentResponseDTO> students = studentService.findAll();
        // Filter out the prefects
        return students.stream()
                .filter(student -> student.house().equalsIgnoreCase(house))
                .filter(StudentResponseDTO::prefect)
                .collect(Collectors.toList());
    }
}
