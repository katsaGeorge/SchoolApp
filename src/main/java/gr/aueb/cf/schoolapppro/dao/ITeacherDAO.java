package gr.aueb.cf.schoolapppro.dao;

import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.model.Teacher;

import java.time.temporal.TemporalQueries;
import java.util.List;

public interface ITeacherDAO {
    Teacher insertTeacher(Teacher teacher) throws TeacherDAOException;
    Teacher updateTeacher(Teacher teacher) throws TeacherDAOException;
    void deleteTeacher(int id) throws TeacherDAOException;
    Teacher getTeacherByLastname(String lastname) throws TeacherDAOException;

    List<Teacher> getTeachersListByLastname(String lastname) throws TeacherDAOException;

    List<Teacher> getAllTeachers() throws TeacherDAOException;
}
