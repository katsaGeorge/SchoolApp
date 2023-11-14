package gr.aueb.cf.schoolapppro.dto;

public class TeacherDeleteDTO extends Base{

    private String firstname;
    private String lastname;
    private Integer ssn;
    private Integer specId;

    public TeacherDeleteDTO(){}

    public TeacherDeleteDTO(Integer id, String firstname, String lastname, Integer ssn, Integer specId,Integer userId) {
        this.setId(id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
        this.specId = specId;
        this.setUserID(userId);
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

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }
}
