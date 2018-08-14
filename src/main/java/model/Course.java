package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="courses")
public class Course implements Serializable {
    @Id
    @SequenceGenerator(name="cou_seq", sequenceName="courseid_seq", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cou_seq")
    @Column(name="id", nullable=false)
    private Integer courseID;
    
    @NotBlank(message = "Please enter a course name.")
    @Column(name="first_name", updatable=true, nullable=false, length=50)
    private String courseName;
    
    @Min(value = 1, message = "The credits must be between 1 and 8.")
    @Max(value = 8, message = "The credits must be between 1 and 8.")
    @NotNull(message = "Please enter a credits value.")
    @Digits(integer=1, fraction=0)
    @Column(name="salary", updatable=true, nullable=false, length=1)
    private Integer credits;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "student_in_course",
            joinColumns = { @JoinColumn(name="course_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id") })
    private Set<Student> studentsRegistered = new HashSet<>();
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "teacher_in_course",
            joinColumns = { @JoinColumn(name="course_id") },
            inverseJoinColumns = { @JoinColumn(name = "teacher_id") })
    private Set<Teacher> teachersRegistered = new HashSet<>();
    
    @OneToMany(mappedBy = "courseResult")
    private List<Result> results = new ArrayList<>();
    
    public Course() {
    }

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Set<Student> getStudentsRegistered() {
        return studentsRegistered;
    }

    public Set<Teacher> getTeachersRegistered() {
        return teachersRegistered;
    }  

    public void setStudentsRegistered(Set<Student> studentsRegistered) {
        this.studentsRegistered = studentsRegistered;
    }

    public void setTeachersRegistered(Set<Teacher> teachersRegistered) {
        this.teachersRegistered = teachersRegistered;
    }
   
    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }   
}
