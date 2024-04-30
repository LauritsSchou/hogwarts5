package dk.kea.dat3js.hogwarts5.prefects;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.kea.dat3js.hogwarts5.house.House;
import dk.kea.dat3js.hogwarts5.house.HouseRepository;
import dk.kea.dat3js.hogwarts5.prefect.PrefectController;
import dk.kea.dat3js.hogwarts5.students.Student;
import dk.kea.dat3js.hogwarts5.students.StudentResponseDTO;
import dk.kea.dat3js.hogwarts5.students.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest

@AutoConfigureMockMvc
public class PrefectControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private StudentService studentService;


    @Test
    public void testCreatePrefect() throws Exception {
        Student student = new Student("Harry", "James", "Potter", null, 5, false);

        mockMvc.perform(post("/prefects")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prefect").value(true));
    }
    @Test
    public void testGetPrefectWhenTrue() throws Exception {
        // Create a Student object with the prefect property set to true
        Student student = new Student("Harry", "James", "Potter", null, 5, true);
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO(student.getId(), student.getFirstName(), student.getMiddleName(), student.getLastName(), student.getFullName(), null, student.getSchoolYear(), student.isPrefect());

        // Mock the service to return the student when findById is called with 1
        when(studentService.findById(1)).thenReturn(Optional.of(studentResponseDTO));

        // Perform a GET request to /prefects/1
        mockMvc.perform(get("/prefects/1")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prefect").value(true));
    }
    @Test
    public void testGetPrefectWhenFalse() throws Exception {
        Student student = new Student("Harry", "James", "Potter", null, 5, false);
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO(student.getId(), student.getFirstName(), student.getMiddleName(), student.getLastName(), student.getFullName(), null, student.getSchoolYear(), student.isPrefect());

        when(studentService.findById(1)).thenReturn(Optional.of(studentResponseDTO));

        mockMvc.perform(get("/prefects/1")
                        .contentType("application/json"))
                .andExpect(status().isNotFound());
    }
}
