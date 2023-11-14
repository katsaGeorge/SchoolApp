package gr.aueb.cf.schoolapppro.service;

import gr.aueb.cf.schoolapppro.dao.ISpecialityDAO;
import gr.aueb.cf.schoolapppro.dao.exceptions.SpecialityDAOException;
import gr.aueb.cf.schoolapppro.dto.SpecialityInsertDTO;
import gr.aueb.cf.schoolapppro.model.Speciality;

import java.util.List;

public class SpecialityServiceImpl implements ISpecialityService{

    private final ISpecialityDAO dao;

    public SpecialityServiceImpl(ISpecialityDAO specialityDAO){this.dao = specialityDAO;}

    @Override
    public Speciality insertSpeciality(SpecialityInsertDTO insertDTO) throws SpecialityDAOException {
        if(insertDTO == null) return null;

        Speciality speciality;
        try {
            speciality = map(insertDTO);
            dao.insertSpeciality(speciality);

        }catch (SpecialityDAOException e){
            e.printStackTrace();
            throw e;
        }
        return speciality;
    }

    @Override
    public List<Speciality> getSpecialityByName(String SpecialityName)  {
          List<Speciality> specialities;

            try {
                specialities = dao.getSpecialityByName(SpecialityName);
                return specialities;
            }catch (SpecialityDAOException e){
                e.printStackTrace();
            }

            return null;

    }

    @Override
    public void deleteSpeciality(int id) throws SpecialityDAOException {

        try {
            dao.deleteSpecialityById(id);
        }catch (SpecialityDAOException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Speciality> getAllSpecialities() throws SpecialityDAOException {
        try {
            List<Speciality> specialities = dao.getAllSpecialities();
            return specialities;
        }catch (SpecialityDAOException e){
            e.printStackTrace();

        }
        return null;
    }

    Speciality map (SpecialityInsertDTO dto) {
        return new Speciality(null, dto.getSpeciality());
    }
}
