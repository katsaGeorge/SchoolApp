package gr.aueb.cf.schoolapppro.service;

import gr.aueb.cf.schoolapppro.dao.IStudentDAO;
import gr.aueb.cf.schoolapppro.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.dto.StudentInsertDTO;
import gr.aueb.cf.schoolapppro.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolapppro.model.Student;
import gr.aueb.cf.schoolapppro.model.Teacher;
import gr.aueb.cf.schoolapppro.validator.StudentValidator;

import java.sql.Date;
import java.util.List;

public class StudentServiceImpl implements IStudentService{

   private final IStudentDAO dao;

    public StudentServiceImpl(IStudentDAO iStudentDAO){this.dao = iStudentDAO;}

    @Override
    public Student insertStudent(StudentInsertDTO insertDTO) throws StudentDAOException {
        if (insertDTO == null) return null;

        Student student;

        try{
            student = map(insertDTO);
            dao.insertStudent(student);

        }catch (StudentDAOException e){
            e.printStackTrace();
            throw e;
        }
        return student;
    }

    @Override
    public Student updateStudent(StudentUpdateDTO updateDTO) throws StudentDAOException {
        if (updateDTO == null) return  null;

        Student student;

        try{
            student = map(updateDTO);
            dao.updateStudent(student);
        }catch (StudentDAOException e){
            e.printStackTrace();
            throw e;
        }
        return student;
    }

    @Override
    public void  deleteStudent(int id) throws StudentDAOException {

        try{
            dao.deleteStudent(id);

        }catch (StudentDAOException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Student> getListByLastName(String lastname) throws StudentDAOException {

        try{
            List<Student> studentList = dao.getStudentsListByLastname(lastname);
            return studentList;
        }catch (StudentDAOException e){
            e.printStackTrace();
            throw e;
        }
    }

     Student map(StudentInsertDTO dto){
         return new Student(null,dto.getFirstname(),dto.getLastname(), dto.getGender(), dto.getBirthDate(), dto.getCityId(), dto.getUserId());
     }
    Student map(StudentUpdateDTO dto){
        return new Student(dto.getId(), dto.getFirstname(),dto.getLastname(), dto.getGender(),  dto.getBirthdate(), dto.getCityId(), dto.getUserID());
    }
}
