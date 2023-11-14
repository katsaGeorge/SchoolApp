package gr.aueb.cf.schoolapppro.service;

import gr.aueb.cf.schoolapppro.dao.ITeacherDAO;
import gr.aueb.cf.schoolapppro.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapppro.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapppro.dto.TeacherUpdateDto;
import gr.aueb.cf.schoolapppro.model.Teacher;
import gr.aueb.cf.schoolapppro.service.exceptions.TeachersNotFoundException;

import java.util.List;

public class TeacherServiceImpl implements ITeacherService {

    private final ITeacherDAO teacherDAO;

    public TeacherServiceImpl(ITeacherDAO teacherDAO) {this.teacherDAO = teacherDAO;}
    @Override
    public Teacher insertTeacher(TeacherInsertDTO teacherInsertDTO)  {
            if (teacherInsertDTO == null) {
                System.out.println("isnull");
                return null;
            }
            Teacher teacher = null;

            try{
                teacher = map(teacherInsertDTO);
            teacherDAO.insertTeacher(teacher);
            }catch (TeacherDAOException e){
                e.printStackTrace();

            }
            return teacher;

    }


    @Override
    public Teacher updateTeacher(TeacherUpdateDto dto) {
        if (dto == null) return null;

        Teacher teacher= null;

        try {
        teacher = map(dto);
        teacherDAO.updateTeacher(teacher);

        }catch (TeacherDAOException e){
            e.printStackTrace();
        }
        return teacher;
    }

    @Override
    public void deleteTeacher(int id) throws TeacherDAOException {

       try {
           teacherDAO.deleteTeacher(id);
       }catch (TeacherDAOException e){
           e.printStackTrace();
           throw e;
       }

    }



    @Override
    public List<Teacher> getListByLastname(String lastname) throws TeacherDAOException {
        try {
            List<Teacher> teachers = teacherDAO.getTeachersListByLastname(lastname);

            return teachers;
        }catch (TeacherDAOException e){
            throw e;
        }

    }

    Teacher map(TeacherInsertDTO dto){
                return new Teacher(null,dto.getSsn(),dto.getFirstname(),dto.getLastname(), dto.getSpecialityId(), dto.getUserId());
            }


    Teacher map(TeacherUpdateDto dto){
                return new Teacher(dto.getId(), dto.getSsn(), dto.getFirstname(),dto.getLastname(), dto.getSpecialityID(), dto.getUserID());
    }
}
