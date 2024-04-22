package dk.kea.dat3js.hogwarts5.teachers;

import dk.kea.dat3js.hogwarts5.teachers.Teacher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TeacherTest {

    @Test
    void getFullNameWithMiddleName() {
//arrange
        Teacher teacher = new Teacher("Harry", "James", "Potter", null, 1);


// act
        var fullName = teacher.getFullName();

//assert
        assertEquals("Harry James Potter", fullName);
    }
    @Test
    void getFullNameWithoutMiddleName() {
//arrange
        Teacher teacher = new Teacher("Cho", null, "Chang", null, 1);

        // act
        var fullName = teacher.getFullName();

        //assert
        assertEquals("Cho Chang", fullName);

    }
    @Test
    void setFullNameWithMiddleName() {
//arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, 1);
// act
        teacher.setFullName("Harry James Potter");
//assert
        assertEquals("Harry", teacher.getFirstName());
        assertEquals("James", teacher.getMiddleName());
        assertEquals("Potter", teacher.getLastName());
    }

    @Test
    void setFullNameWithoutMiddleName() {
//arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, 1);
// act
        teacher.setFullName("Cho Chang");
//assert
        assertEquals("Cho", teacher.getFirstName());
        assertNull(teacher.getMiddleName());
        assertEquals("Chang", teacher.getLastName());
    }

    @Test
    void setFullNameWithoutLastName(){
//arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, 1);
// act
        teacher.setFullName("Cho");
//assert
        assertEquals("Cho", teacher.getFirstName());
        assertNull(teacher.getMiddleName());
        assertNull(teacher.getLastName());
    }
    @Test
    void setFullNameWithMultipleMiddleNames(){
//arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, 1);
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
        Teacher teacher = new Teacher("first", "middle", "last", null, 1);
// act
        teacher.setFullName("");
//assert
        assertEquals("", teacher.getFirstName());
        assertNull(teacher.getMiddleName());
        assertNull(teacher.getLastName());
    }
    @Test void setFullNameWithNull(){
//arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, 1);
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
        Teacher teacher = new Teacher("first", "middle", "last", null, 1);
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
        Teacher teacher = new Teacher("first", "middle", "last", null, 1);
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