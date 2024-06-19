/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package myhibernatetutorial;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ADVAN
 */
public class MyHibernateTutorial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction transaction = session.beginTransaction();
        
        User user = new User("ninja", "12345");
        session.save(user);
        
        //Dosen
        Teacher teacher1 = new Teacher("naruto", "12345", "2222");
        session.save(teacher1);
        Teacher teacher2 = new Teacher("minato", "12345", "1111");
        session.save(teacher2);

        //Mahasiswa
        Student student1 = new Student("sasuke", "12345", "12121212");
        student1.setSupervisor(teacher1);
        session.save(student1);

        Student student2 = new Student("tsunade", "12345", "13131313");
        student2.setSupervisor(teacher2);
        session.save(student2);

        Student student3 = new Student("obito", "12345", "14141414");
        student3.setSupervisor(teacher1);
        session.save(student3);

        //Menambahkan dosen PA
        teacher1.getStudentsSupervised().add(student1);
        teacher1.getStudentsSupervised().add(student3);
        teacher2.getStudentsSupervised().add(student2);
        

        //Matkul
        Course course1 = new Course("Game Development", "IK010101");
        session.save(course1);
        Course course2 = new Course("Teori Bahasa Otomata", "IK020202");
        session.save(course2);
        
        //kelas
        Class class1 = new Class("class01");
        class1.setTeacher(teacher1);
        class1.setCourse(course1);
        class1.getStudents().add(student1);
        class1.getStudents().add(student2);
        session.save(class1);
        
        Class class2 = new Class("class02");
        class2.setTeacher(teacher2);
        class2.setCourse(course2);
        class2.getStudents().add(student1);
        class2.getStudents().add(student2);
        session.save(class2);
        
        //nilai
        // Creating and saving Scores
        Score score1 = new Score(95, student1, course1);
        student1.getScores().add(score1);
        course1.getScores().add(score1);
        session.save(score1);

        Score score2 = new Score(95, student1, course2);
        student1.getScores().add(score2);
        course2.getScores().add(score2);
        session.save(score2);

        Score score3 = new Score(80, student2, course1);
        student2.getScores().add(score3);
        course1.getScores().add(score3);
        session.save(score3);

        Score score4 = new Score(80, student2, course2);
        student2.getScores().add(score4);
        course2.getScores().add(score4);
        session.save(score4);

        Score score5 = new Score(65, student3, course2);
        student3.getScores().add(score5);
        course1.getScores().add(score5);
        session.save(score5);    
        
        Score score6 = new Score(65, student3, course2);
        student3.getScores().add(score6);
        course2.getScores().add(score6);
        session.save(score5);    
    
        transaction.commit();

        // Print all scores for each student
        System.out.println("Scores " + student1.getUsernameId() + ":");
        student1.printAllScores();
        
        System.out.println("Scores " + student2.getUsernameId() + ":");
        student2.printAllScores();
        
        System.out.println("Scores " + student3.getUsernameId() + ":");
        student3.printAllScores();

        // Calculate and print GPA for each student
        System.out.println("GPA " + student1.getUsernameId() + ": " + student1.calculateGPA());
        System.out.println("GPA " + student2.getUsernameId() + ": " + student2.calculateGPA());
        System.out.println("GPA " + student3.getUsernameId() + ": " + student3.calculateGPA());

        
        
        session.close();
    }
    
}
