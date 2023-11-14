package gr.aueb.cf.schoolapppro.model;

public class City {
    private Integer id;
    private String city;

    public City () {}

    public City(Integer id, String city) {
        this.id = id;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
