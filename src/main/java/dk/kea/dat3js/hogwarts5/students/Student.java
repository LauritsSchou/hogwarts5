package dk.kea.dat3js.hogwarts5.students;

import dk.kea.dat3js.hogwarts5.common.PersonWithNames;
import dk.kea.dat3js.hogwarts5.house.House;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Student implements PersonWithNames {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String firstName;
  private String middleName;
  private String lastName;
  @ManyToOne
  private House house;
  private Integer schoolYear; // 1-7
  private boolean prefect;

  public Student() {
  }

  public Student(String firstName, String lastName, House house, int schoolYear, boolean prefect) {
    this(firstName, null, lastName, house, schoolYear, prefect);
  }

  public Student(String firstName, String middleName, String lastName, House house, int schoolYear, boolean prefect) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.house = house;
    this.schoolYear = schoolYear;
    this.prefect = prefect;
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

  public boolean isPrefect() {
    return prefect;
  }

  public void setPrefect(boolean prefect) {
    this.prefect = prefect;
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
