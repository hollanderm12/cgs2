package model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="results")
public class Result implements Serializable {
    @Id
    @SequenceGenerator(name="res_seq", sequenceName="resultid_seq", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="res_seq")
    @Column(name="id", nullable=false)
    private Integer resultID;
    
    @Min(value = 101, message = "The session ID must be between 101 and 1299.")
    @Max(value = 1299, message = "The session ID must be between 101 and 1299.")
    @NotNull(message = "Please enter a session ID.")
    @Digits(integer=4, fraction=0)
    @Column(name="session_id", updatable=true, nullable=false, length=4)
    private Integer sessionID;
    
    @Min(value = 0, message = "The mark must be between 0 and 100.")
    @Max(value = 100, message = "The mark must be between 0 and 100.")
    @NotNull(message = "Please enter a mark.")
    @Digits(integer=3, fraction=0)
    @Column(name="mark", updatable=true, nullable=false, length=3)
    private Integer mark;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", updatable=true, nullable=false)
    private Student studentResult;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", updatable=true, nullable=false)
    private Course courseResult;
            
    @Transient
    private String studentID;
    @Transient
    private String courseID;
    
    public Result() {        
    }

    public Integer getResultID() {
        return resultID;
    }

    public void setResultID(Integer resultID) {
        this.resultID = resultID;
    }

    public Integer getSessionID() {
        return sessionID;
    }

    public void setSessionID(Integer sessionID) {
        this.sessionID = sessionID;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Student getStudentResult() {
        return studentResult;
    }

    public void setStudentResult(Student studentResult) {
        this.studentResult = studentResult;
    }

    public Course getCourseResult() {
        return courseResult;
    }

    public void setCourseResult(Course courseResult) {
        this.courseResult = courseResult;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }   
}
