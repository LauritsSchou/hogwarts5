package dk.kea.dat3js.hogwarts5.prefects;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.kea.dat3js.hogwarts5.house.House;
import dk.kea.dat3js.hogwarts5.house.HouseRepository;
import dk.kea.dat3js.hogwarts5.prefect.PrefectController;
import dk.kea.dat3js.hogwarts5.prefect.PrefectService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    @MockBean
    private PrefectService prefectService;


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
    @Test
    public void testGetAllPrefects() throws Exception {
        // Create a list of StudentResponseDTO objects
        List<StudentResponseDTO> prefects = new ArrayList<>();
        prefects.add(new StudentResponseDTO(1, "Harry", "James", "Potter", "Harry James Potter", "Gryffindor", 5, true));
        prefects.add(new StudentResponseDTO(2, "Hermione", "Jean", "Granger", "Hermione Jean Granger", "Gryffindor", 5, true));

        // Mock the PrefectService to return the list of prefects when findAllPrefects is called
        when(prefectService.findAllPrefects()).thenReturn(prefects);

        // Perform a GET request to /prefects
        mockMvc.perform(get("/prefects")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(prefects.get(0).id()))
                .andExpect(jsonPath("$[0].firstName").value(prefects.get(0).firstName()))
                .andExpect(jsonPath("$[0].middleName").value(prefects.get(0).middleName()))
                .andExpect(jsonPath("$[0].lastName").value(prefects.get(0).lastName()))
                .andExpect(jsonPath("$[0].fullName").value(prefects.get(0).fullName()))
                .andExpect(jsonPath("$[0].house").value(prefects.get(0).house()))
                .andExpect(jsonPath("$[0].schoolYear").value(prefects.get(0).schoolYear()))
                .andExpect(jsonPath("$[0].prefect").value(prefects.get(0).prefect()))
                .andExpect(jsonPath("$[1].id").value(prefects.get(1).id()))
                .andExpect(jsonPath("$[1].firstName").value(prefects.get(1).firstName()))
                .andExpect(jsonPath("$[1].middleName").value(prefects.get(1).middleName()))
                .andExpect(jsonPath("$[1].lastName").value(prefects.get(1).lastName()))
                .andExpect(jsonPath("$[1].fullName").value(prefects.get(1).fullName()))
                .andExpect(jsonPath("$[1].house").value(prefects.get(1).house()))
                .andExpect(jsonPath("$[1].schoolYear").value(prefects.get(1).schoolYear()))
                .andExpect(jsonPath("$[1].prefect").value(prefects.get(1).prefect()));
    }
    @Test
    public void testGetPrefectsByHouse() throws Exception {
        // Create a list of StudentResponseDTO objects
        List<StudentResponseDTO> prefects = new ArrayList<>();
        prefects.add(new StudentResponseDTO(1, "Harry", "James", "Potter", "Harry James Potter", "Gryffindor", 5, true));
        prefects.add(new StudentResponseDTO(2, "Hermione", "Jean", "Granger", "Hermione Jean Granger", "Gryffindor", 5, true));

        // Mock the PrefectService to return the list of prefects when findAllPrefectsByHouse is called
        when(prefectService.findAllPrefectsByHouse("Gryffindor")).thenReturn(prefects);

        // Perform a GET request to /prefects/house/Gryffindor
        mockMvc.perform(get("/prefects/house/Gryffindor")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(prefects.get(0).id()))
                .andExpect(jsonPath("$[0].firstName").value(prefects.get(0).firstName()))
                .andExpect(jsonPath("$[0].middleName").value(prefects.get(0).middleName()))
                .andExpect(jsonPath("$[0].lastName").value(prefects.get(0).lastName()))
                .andExpect(jsonPath("$[0].fullName").value(prefects.get(0).fullName()))
                .andExpect(jsonPath("$[0].house").value(prefects.get(0).house()))
                .andExpect(jsonPath("$[0].schoolYear").value(prefects.get(0).schoolYear()))
                .andExpect(jsonPath("$[0].prefect").value(prefects.get(0).prefect()))
                .andExpect(jsonPath("$[1].id").value(prefects.get(1).id()))
                .andExpect(jsonPath("$[1].firstName").value(prefects.get(1).firstName()))
                .andExpect(jsonPath("$[1].middleName").value(prefects.get(1).middleName()))
                .andExpect(jsonPath("$[1].lastName").value(prefects.get(1).lastName()))
                .andExpect(jsonPath("$[1].fullName").value(prefects.get(1).fullName()))
                .andExpect(jsonPath("$[1].house").value(prefects.get(1).house()))
                .andExpect(jsonPath("$[1].schoolYear").value(prefects.get(1).schoolYear()))
                .andExpect(jsonPath("$[1].prefect").value(prefects.get(1).prefect()));
    }

    @Test
    public void testDeletePrefect() throws Exception {
        // Create a Student object with the prefect property set to true
        Student student = new Student("Harry", "James", "Potter", null, 5, true);

        // Mock the service to return the student when findStudentById is called with 1
        when(studentService.findStudentById(1)).thenReturn(Optional.of(student));

        // Perform a DELETE request to /prefects/1
        mockMvc.perform(delete("/prefects/1")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prefect").value(false));

        // Verify that the prefect property of the student is now false
        assertFalse(student.isPrefect());
    }

}
