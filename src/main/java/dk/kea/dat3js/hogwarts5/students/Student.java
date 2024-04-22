package dk.kea.dat3js.hogwarts5.students;

import dk.kea.dat3js.hogwarts5.house.House;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String firstName;
  private String middleName;
  private String lastName;
  @ManyToOne
  private House house;
  private Integer schoolYear; // 1-7

  public Student() {
  }

  public Student(String firstName, String lastName, House house, int schoolYear) {
    this(firstName, null, lastName, house, schoolYear);
  }

  public Student(String firstName, String middleName, String lastName, House house, int schoolYear) {
    setFirstName(firstName);
    setMiddleName(middleName);
    setLastName(lastName);
    this.house = house;
    this.schoolYear = schoolYear;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = capitalize(firstName);
  }

  public String getMiddleName() {
    return this.middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = capitalize(middleName);
  }
  public String getLastName() {
   return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = capitalize(lastName);
  }
  public String getFullName() {
    return firstName + " " + (middleName != null ? middleName + " " : "") + lastName;
  }
  public void setFullName(String fullName) {
    if (fullName == null) {
      return;
    }

    if (fullName.trim().isEmpty()) {
      setFirstName("");
      setMiddleName(null);
      setLastName(null);
      return;
    }

    int firstSpace = fullName.indexOf(" ");
    int lastSpace = fullName.lastIndexOf(" ");

    if (firstSpace == -1) { // No spaces found, it's a single name
      setFirstName(fullName);
      setMiddleName(null);
      setLastName(null);
    } else {
      setFirstName(fullName.substring(0, firstSpace));

      if (lastSpace > firstSpace) {
        setMiddleName(fullName.substring(firstSpace + 1, lastSpace));
        setLastName(fullName.substring(lastSpace + 1));
      } else {
        setMiddleName(null);
        setLastName(fullName.substring(firstSpace + 1));
      }
    }
  }
  private String capitalize(String name) {
    if (name == null) {
      return null;
    }
    if (name.length() < 2) {
      return name.toUpperCase();
    }
    if (name.contains(" ")) {
      int space = name.indexOf(" ");
      return capitalize(name.substring(0, space)) + " " + capitalize(name.substring(space + 1));
    }
    return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
  }

  public House getHouse() {
    return house;
  }

  public void setHouse(House house) {
    this.house = house;
  }

  public Integer getSchoolYear() {
    return schoolYear;
  }

  public void setSchoolYear(Integer schoolYear) {
    this.schoolYear = schoolYear;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return Objects.equals(getFirstName(), student.getFirstName()) && Objects.equals(getMiddleName(), student.getMiddleName()) && Objects.equals(getLastName(), student.getLastName()) && Objects.equals(getHouse().getName(), student.getHouse().getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getFirstName(), getMiddleName(), getLastName(), getHouse().getName());
  }

}
