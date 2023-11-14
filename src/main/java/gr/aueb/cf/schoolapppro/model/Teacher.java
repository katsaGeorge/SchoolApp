package gr.aueb.cf.schoolapppro.model;

public class Teacher {
    private Integer id;
    private Integer ssn;
    private String firstname;
    private String lastname;
    private Integer specialityId;
    private Integer userId;

    public Teacher () {}

    public Teacher(Integer id, Integer ssn, String firstname, String lastname, Integer specialityId, Integer userId) {
        this.id = id;
        this.ssn = ssn;
        this.firstname = firstname;
        this.lastname = lastname;
        this.specialityId = specialityId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
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

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", ssn=" + ssn +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", specialityId=" + specialityId +
                ", userId=" + userId +
                '}';
    }
}
