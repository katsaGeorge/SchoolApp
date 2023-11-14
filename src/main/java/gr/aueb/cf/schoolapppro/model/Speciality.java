package gr.aueb.cf.schoolapppro.model;

public class Speciality {
    private Integer id;
    private String speciality;

    public Speciality() {}

    public Speciality(Integer id, String speciality) {
        this.id = id;
        this.speciality = speciality;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
