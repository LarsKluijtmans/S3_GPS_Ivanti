package s3_gps_ivanti.repository;

import s3_gps_ivanti.model.Application;

import java.io.File;
import java.util.ArrayList;

public interface ApplicationRepository {

    ArrayList<Application> getApplicationsSorted(boolean rating, boolean date);

    ArrayList<Application> getApplicationsBySearch(String search);

    ArrayList<Application> getApplications();

    Application getApplicationsByID(long ID);

    boolean createApplications(Application app);

    boolean updateApplications(Application app);

    boolean deleteApplications(int appID);

    File downloadApplications(int appID);
}
