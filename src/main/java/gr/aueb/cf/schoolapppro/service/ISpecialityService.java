package gr.aueb.cf.schoolapppro.service;

import gr.aueb.cf.schoolapppro.dao.SpecialityDAOImpl;
import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.dto.SpecialityInsertDTO;
import gr.aueb.cf.schoolapppro.model.Speciality;

import java.util.List;

public interface ISpecialityService {

        Speciality insertSpeciality(SpecialityInsertDTO insertDTO) throws SpecialityDAOException;

        void deleteSpeciality(int id) throws SpecialityDAOException;

        List<Speciality> getAllSpecialities() throws SpecialityDAOException;

       List <Speciality> getSpecialityByName(String SpecialityName) throws SpecialityDAOException;
}
