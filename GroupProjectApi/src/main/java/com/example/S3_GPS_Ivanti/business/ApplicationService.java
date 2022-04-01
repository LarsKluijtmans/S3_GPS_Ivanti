package com.example.S3_GPS_Ivanti.business;

import com.example.S3_GPS_Ivanti.model.Application;
import com.example.S3_GPS_Ivanti.model.User;

import java.io.File;
import java.util.ArrayList;

public interface ApplicationService {

    ArrayList<Application> getApplicationsSorted( boolean rating, boolean date);

    ArrayList<Application> getApplicationsBySearch(String search);

    Application getApplicationsByID(long ID);

    ArrayList<Application> getApplications();

   Application getApplicationDetails(String appName);

    boolean createApplications( Application app);

    boolean updateApplications(Application app, User user);

    boolean deleteApplications( int appID, User user);

    File downloadApplications(String username, String password, int appID);

    public ArrayList<Application> getAllOfAUsersAppointments(User user);
}
