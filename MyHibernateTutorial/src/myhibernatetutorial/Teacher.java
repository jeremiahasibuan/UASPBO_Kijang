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
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author ADVAN
 */

@Entity
@Table(name="teacher")
@PrimaryKeyJoinColumn(name="user_id")
public class Teacher extends User {
    

    @Column(name="teacher_id", nullable=false, unique=true)
    String teacherId;
    @Column(name="teacher_name", nullable=false, unique=true)
    String teacherNameId;
    
    
    public Teacher(String username, String password, String teacherId) {
        super(username, password);
        this.teacherId = teacherId;
        this.teacherNameId = username;
    }
    
    @OneToMany(mappedBy = "supervisor", fetch = FetchType.LAZY) // One-to-Many relationship with Student
    private Set<Student> studentsSupervised = new HashSet<>();

    // Getters and Setters

    public Set<Student> getStudentsSupervised() {
        return studentsSupervised;
    }

    public void setStudentsSupervised(Set<Student> studentsSupervised) {
        this.studentsSupervised = studentsSupervised;
    }
}
