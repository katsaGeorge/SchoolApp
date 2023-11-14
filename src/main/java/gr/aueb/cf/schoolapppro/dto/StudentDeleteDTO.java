package gr.aueb.cf.schoolapppro.dto;

import java.util.Date;

public class StudentDeleteDTO extends Base{


        private String firstname;
        private String lastname;
        private char gender;

        private Date birthdate;
        private int  cityId;

        public StudentDeleteDTO(){}

        public StudentDeleteDTO(Integer id, String firstname, String lastname, char gender, Date birthdate, int cityId, Integer userid) {
            this.setId(id);
            this.firstname = firstname;
            this.lastname = lastname;
            this.gender = gender;
            this.birthdate = birthdate;
            this.cityId = cityId;
            this.setUserID(userid);
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

        public char getGender() {
            return gender;
        }

        public void setGender(char gender) {
            this.gender = gender;
        }

        public Date getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(Date birthdate) {
            this.birthdate = birthdate;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }
    }


