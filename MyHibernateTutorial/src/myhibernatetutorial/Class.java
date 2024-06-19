/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myhibernatetutorial;

import com.sun.istack.NotNull;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ADVAN
 */

@Entity
@Table(name="class")
public class Class {
    
    public Class(String classId){
        this.classId = classId;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name="course_name", nullable=false, unique=true)
    private String classId;
    
    @ManyToOne(fetch=FetchType.LAZY)
    private Teacher teacher;
    
    @ManyToOne(fetch=FetchType.LAZY)
    private Course course;
    
    @ManyToMany
    @JoinTable(
        name="student_class",
        joinColumns = @JoinColumn(name="student_id"),
        inverseJoinColumns = @JoinColumn(name="class_id")
    )
    
    private Set<Student> students = new HashSet<>();
    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the classId
     */
    public String getClassId() {
        return classId;
    }

    /**
     * @param classId the classId to set
     */
    public void setClassId(String classId) {
        this.classId = classId;
    }

    /**
     * @return the teacher
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * @param teacherId
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * @return the course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * @return the students
     */
    public Set<Student> getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(Set<Student> students) {
        this.students = students;
    }
    

}
