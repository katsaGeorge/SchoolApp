package gr.aueb.cf.schoolapppro.dto;

public class SpecialityInsertDTO {

    private String speciality;

    public SpecialityInsertDTO(){}
    public SpecialityInsertDTO(String speciality) {
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
