package gr.aueb.cf.schoolapppro.dto;

public abstract class Base {

    private Integer id;
    private Integer userID;

    public Base (){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
