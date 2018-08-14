package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="students")
public class Student implements Serializable {   
    @Id
    @SequenceGenerator(name="stu_seq", sequenceName="studentid_seq", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="stu_seq")
    @Column(name="id", nullable=false)
    private Integer studentID;

    @NotBlank(message = "Please enter a first name.")
    @Column(name="first_name", updatable=true, nullable=false, length=30)
    private String firstName;

    @NotBlank(message = "Please enter a last name.")
    @Column(name="last_name", updatable=true, nullable=false, length=30)
    private String lastName;
   
    @NotBlank(message = "Please enter an address.")
    @Column(name="address", updatable=true, nullable=false, length=40)
    private String address;

    @NotBlank(message = "Please enter a city.")
    @Column(name="city", updatable=true, nullable=false, length=30)
    private String city;

    @NotBlank(message = "Please enter a state or province.")
    @Column(name="state_province", updatable=true, nullable=false, length=25)
    private String stateProvince;

    @NotBlank(message = "Please enter a country.")
    @Column(name="country", updatable=true, nullable=false, length=30)
    private String country;
 
    @NotBlank(message = "Please enter a ZIP or postal code.")
    @Column(name="zip_postal", updatable=true, nullable=false, length=12)
    private String zipPostal;

    @NotBlank(message = "Please enter a phone number.")
    @Column(name="phone_num", updatable=true, nullable=false, length=15)
    private String phoneNum;
 
    @NotBlank(message = "Please enter a major.")
    @Column(name="major", updatable=true, nullable=false, length=100)
    private String major;  
    
    @NotBlank(message = "Please enter an email address.")
    @Email(message = "Please enter a valid email address.")
    @Column(name="email", updatable=true, nullable=false, length=50)
    private String email;
    
    @ManyToMany(mappedBy = "studentsRegistered")
    private Set<Course> coursesRegistered = new HashSet<>();
    
    @OneToMany(mappedBy = "studentResult")
    private List<Result> results = new ArrayList<>();
     
    public Student() {
    }
     
    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipPostal() {
        return zipPostal;
    }

    public void setZipPostal(String zipPostal) {
        this.zipPostal = zipPostal;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }  

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Course> getCoursesRegistered() {
        return coursesRegistered;
    }

    public void setCoursesRegistered(Set<Course> coursesRegistered) {
        this.coursesRegistered = coursesRegistered;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }    
}
