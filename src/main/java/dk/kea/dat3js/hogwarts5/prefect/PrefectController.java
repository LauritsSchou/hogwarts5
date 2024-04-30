package dk.kea.dat3js.hogwarts5.prefect;

import dk.kea.dat3js.hogwarts5.students.Student;
import dk.kea.dat3js.hogwarts5.students.StudentResponseDTO;
import dk.kea.dat3js.hogwarts5.students.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prefects")
public class PrefectController {
    private final StudentService studentService;
    private final PrefectService prefectService;

    public PrefectController(StudentService studentService, PrefectService prefectService) {
        this.studentService = studentService;
        this.prefectService = prefectService;
    }

    @PostMapping
    public ResponseEntity<Student> createPrefect(@RequestBody Student student) {
        student.setPrefect(true);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<StudentResponseDTO>> getPrefect(@PathVariable int id) {
        // Fetch the student from the data source using the provided id
        Optional<StudentResponseDTO> student = studentService.findById(id);
       // Return the student if the prefect property is true
        if (student.isPresent() && student.get().prefect()) {
            return ResponseEntity.of(Optional.of(student));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping()
    public List<StudentResponseDTO> getAllPrefects() {
        return prefectService.findAllPrefects();
    }
}
