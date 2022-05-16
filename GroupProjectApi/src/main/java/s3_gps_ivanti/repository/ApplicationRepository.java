package s3_gps_ivanti.repository;

import s3_gps_ivanti.model.Application;

import java.io.File;
import java.util.ArrayList;

public interface ApplicationRepository  {

    //All
    ArrayList<Application> getApplications();

    ArrayList<Application> getApplicationsByName(String name);


    //Content Creator
    ArrayList<Application> getApplicationsByCreatorId(int creatorId);

    ArrayList<Application> getApplicationsByCreatorIdAndName(int creatorId, String name);

    Application getApplicationsByID(int id);

    ArrayList<Application> getApplicationDetails(String appName);

    boolean createApplications(Application app);

    ArrayList<Application> getApplicationsByCreator(int ID);

    boolean updateApplications(Application app);

    boolean deleteApplications(String name);

    boolean FindAppWithSameName(String appName);


    //Customer
    Application getApplicationToUpdate(String appname);

    File downloadApplications(int appID);

    Application getApplicationInfoByName(String name);

    ArrayList<Application> getApplicationsByCustomer(int ID);
}
