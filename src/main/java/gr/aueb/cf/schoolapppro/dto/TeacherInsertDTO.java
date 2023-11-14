package gr.aueb.cf.schoolapppro.dto;

public class TeacherInsertDTO {
    private Integer ssn;
    private String firstname;
    private String lastname;
    private Integer specialityId;
    private Integer userId;

    public TeacherInsertDTO() {}

    public TeacherInsertDTO(Integer ssn, String firstname, String lastname, Integer specialityId, Integer userId) {
        this.ssn = ssn;
        this.firstname = firstname;
        this.lastname = lastname;
        this.specialityId = specialityId;
        this.userId = userId;
    }

    public Integer getSsn() {
        return ssn;
    }

    public void setSsn(Integer ssn) {
        this.ssn = ssn;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(int specialityId) {
        this.specialityId = specialityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
