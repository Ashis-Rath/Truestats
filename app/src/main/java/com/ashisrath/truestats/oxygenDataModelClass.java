package com.ashisrath.truestats;

public class oxygenDataModelClass {
    String Address, City, State, Location_URL, Name, Oxygen_Stock_Status, Public_Phone_number, Update_Date, Update_Time;

    public oxygenDataModelClass(){

    }

    public oxygenDataModelClass(String address, String city, String state, String location_URL, String name, String oxygen_Stock_Status, String public_Phone_number, String update_Date, String update_Time) {
        Address = address;
        City = city;
        State = state;
        Location_URL = location_URL;
        Name = name;
        Oxygen_Stock_Status = oxygen_Stock_Status;
        Public_Phone_number = public_Phone_number;
        Update_Date = update_Date;
        Update_Time = update_Time;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public String getState() {
        return State;
    }

    public String getLocation_URL() {
        return Location_URL;
    }

    public String getName() {
        return Name;
    }

    public String getOxygen_Stock_Status() {
        return Oxygen_Stock_Status;
    }

    public String getPublic_Phone_number() {
        return Public_Phone_number;
    }

    public String getUpdate_Date() {
        return Update_Date;
    }

    public String getUpdate_Time() {
        return Update_Time;
    }

}