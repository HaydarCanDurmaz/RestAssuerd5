package Model;

import java.util.ArrayList;

public class Location {
    String postcode;
    String country;
    String countryyabbreviation;
    ArrayList<Place>places;

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryyabbreviation() {
        return countryyabbreviation;
    }

    public void setCountryyabbreviation(String countryyabbreviation) {
        this.countryyabbreviation = countryyabbreviation;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }
}
