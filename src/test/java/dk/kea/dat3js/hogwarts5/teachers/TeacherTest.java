package dk.kea.dat3js.hogwarts5.teachers;

import dk.kea.dat3js.hogwarts5.teachers.Teacher;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TeacherTest {

    @Test
    void getFullNameWithMiddleName() {
//arrange
        Teacher teacher = new Teacher("Severus", "Prince", "Snape", null, "Potions", LocalDate.of(1981, 11, 1));


// act
        var fullName = teacher.getFullName();

//assert
        assertEquals("Severus Prince Snape", fullName);
    }
    @Test
    void getFullNameWithoutMiddleName() {
//arrange
        Teacher teacher = new Teacher("Minerva", "Mcgonagall", null, null, null);

        // act
        var fullName = teacher.getFullName();

        //assert
        assertEquals("Minerva Mcgonagall", fullName);

    }
    @Test
    void setFullNameWithMiddleName() {
//arrange
        Teacher teacher = new Teacher("first", "middle",  "last", null, "Potions", LocalDate.of(1981, 11, 1));
// act
        teacher.setFullName("Severus Prince Snape");
//assert
        assertEquals("Severus", teacher.getFirstName());
        assertEquals("Prince", teacher.getMiddleName());
        assertEquals("Snape", teacher.getLastName());
    }

    @Test
    void setFullNameWithoutMiddleName() {
//arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, null, null);
// act
        teacher.setFullName("Minerva Mcgonagall");
//assert
        assertEquals("Minerva", teacher.getFirstName());
        assertNull(teacher.getMiddleName());
        assertEquals("Mcgonagall", teacher.getLastName());
    }

    @Test
    void setFullNameWithoutLastName(){
//arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, null, null);
// act
        teacher.setFullName("Firenze");
//assert
        assertEquals("Firenze", teacher.getFirstName());
        assertNull(teacher.getMiddleName());
        assertNull(teacher.getLastName());
    }
    @Test
    void setFullNameWithMultipleMiddleNames(){
//arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, null, null);
// act
        teacher.setFullName("Albus Pervical Wulfric Dumbledore");
//assert
        assertEquals("Albus", teacher.getFirstName());
        assertEquals("Pervical Wulfric", teacher.getMiddleName());
        assertEquals("Dumbledore", teacher.getLastName());
    }
    @Test
    void setFullNameWithEmptyString(){
//arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, null, null);
// act
        teacher.setFullName("");
//assert
        assertEquals("", teacher.getFirstName());
        assertNull(teacher.getMiddleName());
        assertNull(teacher.getLastName());
    }
    @Test void setFullNameWithNull(){
//arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, null, null);
// act
        teacher.setFullName(null);
//assert
        assertEquals("first", teacher.getFirstName());
        assertEquals("middle", teacher.getMiddleName());
        assertEquals("last", teacher.getLastName());
    }
    @Test
    void capitalizeIndividualNames(){
//arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, null, null);
// act
        teacher.setFirstName("harry");
        teacher.setMiddleName("james");
        teacher.setLastName("potter");
//assert
        assertEquals("Harry", teacher.getFirstName());
        assertEquals("James", teacher.getMiddleName());
        assertEquals("Potter", teacher.getLastName());
    }

    @Test
    void capitalizeIndividualNamesWithCrazyCapitalization(){
//arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, null, null);
// act
        teacher.setFirstName("hArRy");
        teacher.setMiddleName("jAmEs");
        teacher.setLastName("pOtTeR");
//assert
        assertEquals("Harry", teacher.getFirstName());
        assertEquals("James", teacher.getMiddleName());
        assertEquals("Potter", teacher.getLastName());
    }
}