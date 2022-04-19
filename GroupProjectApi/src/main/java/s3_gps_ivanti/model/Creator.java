package s3_gps_ivanti.model;

import lombok.*;

import java.util.ArrayList;

@Data
public class Creator extends User {

    private ArrayList<Application> MyApplications;
    private ArrayList<Response> MyResponses;

    public Creator(int id, String username, String password, String firstName, String lastName){
        super(id, username, password, firstName, lastName);
        MyApplications = new ArrayList<>();
        MyResponses = new ArrayList<>();
    }

    public Creator(int id, String username, String password, ArrayList<Application> myApplications, ArrayList<Response> myResponses, String firstName, String lastName)
    {
        super(id, username, password, firstName, lastName);
        this.MyApplications = myApplications;
        this.MyResponses = myResponses;
    }
}