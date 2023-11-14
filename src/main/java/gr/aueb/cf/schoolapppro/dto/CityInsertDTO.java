package gr.aueb.cf.schoolapppro.dto;

public class CityInsertDTO {

    private String city;

    public CityInsertDTO(){}

    public CityInsertDTO(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
