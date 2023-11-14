package gr.aueb.cf.schoolapppro.dao;

import gr.aueb.cf.schoolapppro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapppro.model.Student;

import java.util.List;

public interface IStudentDAO {
    Student insertStudent(Student student) throws StudentDAOException;
    Student updateStudent(Student student) throws StudentDAOException;
    void deleteStudent(int id) throws StudentDAOException;
    Student getStudentByLastname(String lastname) throws StudentDAOException;
    List<Student> getStudentsListByLastname(String lastname) throws StudentDAOException;
    List<Student> getAllStudents() throws StudentDAOException;
}
