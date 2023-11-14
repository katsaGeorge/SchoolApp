package gr.aueb.cf.schoolapppro.dto;

public class TeacherUpdateDto extends Base{
    private  String firstname;
    private  String lastname;
    private  Integer ssn;

    private Integer specialityID;

        public TeacherUpdateDto(){}

    public TeacherUpdateDto(Integer id, String firstname, String lastname, Integer ssn, Integer specialityID, Integer userID) {
        this.setId(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
        this.specialityID = specialityID;
        this.setUserID(userID);
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

    public Integer getSsn() {
        return ssn;
    }

    public void setSsn(Integer ssn) {
        this.ssn = ssn;
    }

    public Integer getSpecialityID() {
        return specialityID;
    }

    public void setSpecialityID(Integer specialityID) {
        this.specialityID = specialityID;
    }
}
