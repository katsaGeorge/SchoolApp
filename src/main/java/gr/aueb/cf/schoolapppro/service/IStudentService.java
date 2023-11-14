package gr.aueb.cf.schoolapppro.service;

import gr.aueb.cf.schoolapppro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapppro.dto.StudentInsertDTO;
import gr.aueb.cf.schoolapppro.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolapppro.model.Student;

import java.util.List;

public interface IStudentService {

        Student insertStudent(StudentInsertDTO insertDTO) throws StudentDAOException;

        Student updateStudent(StudentUpdateDTO updateDTO) throws StudentDAOException;

        void deleteStudent(int id) throws StudentDAOException;

        List<Student> getListByLastName(String lastname) throws StudentDAOException;


}
