/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myhibernatetutorial;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author ADVAN
 */

@Entity
@Table(name="student")
@PrimaryKeyJoinColumn(name="user_id")
public class Student extends User{
   
    public Student(String username, String password, String studentId) {
        super(username, password);
        this.studentId = studentId;
        this.studentNameId = username;
    }
    
    @Column(name="student_id", nullable=false, unique=true)
    private String studentId;
    
    @Column(name="student_name", nullable=false)
    private String studentNameId;
    
    @ManyToMany(mappedBy = "students")
    private Set<Class> classes = new HashSet<>();
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // One-to-Many relationship with Score entity
    private Set<Score> scores = new HashSet<>();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dosenPA")
    private Teacher supervisor;
    

    /**
     * @return the studentId
     */
    public String getStudentId() {
        return studentId;
    }
    
    public String getUsernameId() {
        return studentNameId;
    }
    
    public Teacher getSupervisor() {
        return supervisor;
    }
    
    public Set<Score> getScores() {
        return scores;
    }

    /**
     * @param studentId the studentId to set
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public void setUsernameId(String studentNameId) {
        this.studentNameId = studentNameId;
    }
    
    public void setSupervisor(Teacher supervisor) {
        this.supervisor = supervisor;
    }
    
    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }
    
    public void printAllScores() {
        if (scores.isEmpty()) {
            System.out.println("No scores available.");
        } else {
            for (Score score : scores) {
                System.out.println(score.getCourse().getCourseId() + " " + score.getCourse().getName() + " " + score.getScore());
            }
        }
    }

    public double calculateGPA() {
        double totalPoints = 0.0;
        int totalCredits = 0;

        for (Score score : scores) {
            double gpa = convertScoreToGPA(score.getScore());
            totalPoints += gpa; // Assuming each course has the same credit weight
            totalCredits++;
        }

        return totalCredits == 0 ? 0.0 : totalPoints / totalCredits;
    }
    // Helper method to convert a score to GPA
    private double convertScoreToGPA(int score) {
        if (score >= 85) {
            return 4.0; // A
        } else if (score >= 75) {
            return 3.5; // B+
        } else if (score >= 70) {
            return 3.0; // B
        } else if (score >= 65) {
            return 2.5; // C+
        } else if (score >= 60) {
            return 2.0; // C
        } else if (score >= 55) {
            return 1.5; // D+
        } else if (score >= 50) {
            return 1.0; // D
        } else {
            return 0.0; // E
        }
    } 
    
    
}
