package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="teachers")
public class Teacher implements Serializable {
    @Id
    @SequenceGenerator(name="tea_seq", sequenceName="teacherid_seq", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tea_seq")
    @Column(name="id", nullable=false)
    private Integer teacherID;

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
 
    @Min(value = 1, message = "The salary must be a positive whole number.")
    @NotNull(message = "Please enter a salary.")
    @Digits(integer=10, fraction=0)
    @Column(name="salary", updatable=true, nullable=false, length=10)
    private Integer salary;  
    
    @NotBlank(message = "Please enter an email address.")
    @Email(message = "Please enter a valid email address.")
    @Column(name="email", updatable=true, nullable=false, length=50)
    private String email;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "teachersRegistered")
    private Set<Course> coursesRegistered = new HashSet<>();

    public Teacher() {
    }     
    
    public Integer getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Integer teacherID) {
        this.teacherID = teacherID;
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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
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
}