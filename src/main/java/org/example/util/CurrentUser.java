package org.example.util;

public class CurrentUser {
    private String name="";
    private String phone;
    private String street;
    private String city;
    private String state;
    private String zip;
   public CurrentUser(String name, String phone,String street,  String city, String state, String zip){
        this.name=name;
        this.phone=phone;
        this.street=street;
        this.city=city;
        this.state=state;
        this.zip=zip;
    }

    public String getName(){
        return name;
    }
    public String getPhone(){
        return phone;
    }
    public String getState(){
        return state;
    }
    public String getCity(){
        return city;
    }
    public String getStreet(){
        return street;
    }
    public String getZip(){
        return zip;
    }
}

