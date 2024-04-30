package dk.kea.dat3js.hogwarts5.students;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void getFullNameWithMiddleName() {
//arrange
        Student student = new Student("Harry", "James", "Potter", null, 1, false);


// act
        var fullName = student.getFullName();

//assert
        assertEquals("Harry James Potter", fullName);
    }
    @Test
    void getFullNameWithoutMiddleName() {
//arrange
        Student student = new Student("Cho", null, "Chang", null, 1, false);

        // act
        var fullName = student.getFullName();

        //assert
        assertEquals("Cho Chang", fullName);

    }
    @Test
    void setFullNameWithMiddleName() {
//arrange
        Student student = new Student("first", "middle", "last", null, 1, false);
// act
        student.setFullName("Harry James Potter");
//assert
        assertEquals("Harry", student.getFirstName());
        assertEquals("James", student.getMiddleName());
        assertEquals("Potter", student.getLastName());
    }

    @Test
    void setFullNameWithoutMiddleName() {
//arrange
        Student student = new Student("first", "middle", "last", null, 1, false);
// act
        student.setFullName("Cho Chang");
//assert
        assertEquals("Cho", student.getFirstName());
        assertNull(student.getMiddleName());
        assertEquals("Chang", student.getLastName());
    }

    @Test
    void setFullNameWithoutLastName(){
//arrange
        Student student = new Student("first", "middle", "last", null, 1, false);
// act
        student.setFullName("Cho");
//assert
        assertEquals("Cho", student.getFirstName());
        assertNull(student.getMiddleName());
        assertNull(student.getLastName());
    }
    @Test
    void setFullNameWithMultipleMiddleNames(){
//arrange
        Student student = new Student("first", "middle", "last", null, 1, false);
// act
        student.setFullName("Albus Pervical Wulfric Dumbledore");
//assert
        assertEquals("Albus", student.getFirstName());
        assertEquals("Pervical Wulfric", student.getMiddleName());
        assertEquals("Dumbledore", student.getLastName());
    }
    @Test
    void setFullNameWithEmptyString(){
//arrange
        Student student = new Student("first", "middle", "last", null, 1, false);
// act
        student.setFullName("");
//assert
        assertEquals("", student.getFirstName());
        assertNull(student.getMiddleName());
        assertNull(student.getLastName());
    }
    @Test void setFullNameWithNull(){
//arrange
        Student student = new Student("first", "middle", "last", null, 1, false);
// act
        student.setFullName(null);
//assert
        assertEquals("first", student.getFirstName());
        assertEquals("middle", student.getMiddleName());
        assertEquals("last", student.getLastName());
    }
    @Test
    void capitalizeIndividualNames(){
//arrange
        Student student = new Student("first", "middle", "last", null, 1, false);
// act
        student.setFirstName("harry");
        student.setMiddleName("james");
        student.setLastName("potter");
//assert
        assertEquals("Harry", student.getFirstName());
        assertEquals("James", student.getMiddleName());
        assertEquals("Potter", student.getLastName());
    }

    @Test
    void capitalizeIndividualNamesWithCrazyCapitalization(){
//arrange
        Student student = new Student("first", "middle", "last", null, 1, false);
// act
        student.setFirstName("hArRy");
        student.setMiddleName("jAmEs");
        student.setLastName("pOtTeR");
//assert
        assertEquals("Harry", student.getFirstName());
        assertEquals("James", student.getMiddleName());
        assertEquals("Potter", student.getLastName());
    }
}