package gr.aueb.cf.schoolapppro.service;

import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapppro.dto.TeacherUpdateDto;
import gr.aueb.cf.schoolapppro.model.Teacher;

import java.util.List;

public interface ITeacherService {

        Teacher insertTeacher(TeacherInsertDTO teacherInsertDTO);
        Teacher updateTeacher(TeacherUpdateDto teacherUpdateDto) throws TeacherDAOException;
        void deleteTeacher(int id) throws TeacherDAOException;


        List<Teacher> getListByLastname(String lastname) throws TeacherDAOException;

}
