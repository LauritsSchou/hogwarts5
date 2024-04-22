package dk.kea.dat3js.hogwarts5.prefect;

import dk.kea.dat3js.hogwarts5.students.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prefects")
public class PrefectController {
    @PostMapping
    public ResponseEntity<Student> createPrefect(@RequestBody Student student) {
        student.setPrefect(true);
        return ResponseEntity.ok(student);
    }
}
