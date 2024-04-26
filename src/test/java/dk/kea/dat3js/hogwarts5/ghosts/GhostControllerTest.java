package dk.kea.dat3js.hogwarts5.ghosts;

import dk.kea.dat3js.hogwarts5.house.House;
import dk.kea.dat3js.hogwarts5.house.HouseRepository;
import dk.kea.dat3js.hogwarts5.house.HouseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Optional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(GhostController.class)
@ComponentScan(basePackageClasses = {HouseService.class})

class GhostControllerTest {
    @MockBean
    private HouseRepository houseRepository;
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    void setUpMockedRepository(){
        when(houseRepository.findById("Ravenclaw")).thenReturn(
                Optional.of(new House("Ravenclaw", "Rowena Ravenclaw", new String[]{"blue", "green"}))
        );
        when(houseRepository.findById("Gryffindor")).thenReturn(
                Optional.of(new House("Gryffindor", "Godric Gryffindor", new String[]{"red", "gold"}))
        );
        when(houseRepository.findById("Slytherin")).thenReturn(
                Optional.of(new House("Slytherin", "Salazar Slytherin", new String[]{"green", "black"}))
        );
        when(houseRepository.findById("Hufflepuff")).thenReturn(
                Optional.of(new House("Hufflepuff", "Helga Hufflepuff", new String[]{"green", "blue"}))
        );
    }
    @Test
    void getGhostByName() throws Exception {

        mockMvc.perform(get("/ghosts/Moaning Myrtle"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Moaning Myrtle"))
                .andExpect(jsonPath("$.realName").value("Myrtle Elizabeth Warren"));
    }
    @Test
    void getAllGhosts() throws Exception {

           mockMvc.perform(get("/ghosts"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(5)))
                   .andExpect(jsonPath("$[*].name").value(containsInAnyOrder(
                            "Nearly Headless Nick",
                            "The Bloody Baron",
                            "The Fat Friar",
                            "The Grey Lady",
                            "Moaning Myrtle"
                   )));
        }
}