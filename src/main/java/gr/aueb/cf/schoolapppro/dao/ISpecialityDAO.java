package gr.aueb.cf.schoolapppro.dao;

import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.model.Speciality;

import java.util.List;

public interface ISpecialityDAO {
    Speciality insertSpeciality(Speciality speciality) throws SpecialityDAOException;
    List<Speciality> updateSpecialityByName(String speciality) throws SpecialityDAOException;
    void deleteSpecialityById(int id) throws SpecialityDAOException;
    List<Speciality> getSpecialityByName(String speciality) throws SpecialityDAOException;
    List<Speciality> getAllSpecialities() throws SpecialityDAOException;
}
