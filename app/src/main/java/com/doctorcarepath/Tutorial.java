package com.doctorcarepath;

/**
 * Created by Om on 15/3/2020.
 */

public class Tutorial {
    String id, Name, Specialists,image;

    public Tutorial(String  id, String  Name, String Specialists,String image) {
        this.id = id;
        this.Name = Name;
        this.Specialists = Specialists;
        this.image = image;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return Name;
    }
    public String getSpecialists() {
        return Specialists;
    }
    public String getImage(){
        return image;
    }
}
