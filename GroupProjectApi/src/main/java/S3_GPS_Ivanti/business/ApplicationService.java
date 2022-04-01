package S3_GPS_Ivanti.business;

<<<<<<< HEAD:GroupProjectApi/src/main/java/S3_GPS_Ivanti/business/ApplicationService.java
import S3_GPS_Ivanti.model.Application;
import S3_GPS_Ivanti.model.User;
=======
import com.example.S3_GPS_Ivanti.model.*;
>>>>>>> 75208ff8c276fbe9838fb7b8e36d120aec7926ff:GroupProjectApi/src/main/java/com/example/S3_GPS_Ivanti/business/ApplicationService.java

import java.io.File;
import java.util.ArrayList;

public interface ApplicationService {

    ArrayList<Application> getApplicationsSorted( boolean rating, boolean date);

    ArrayList<Application> getApplicationsBySearch(String search);

    Application getApplicationsByID(long ID);

    ArrayList<Application> getApplications();

    boolean createApplications( Application app);

    boolean updateApplications(Application app, User user);

    boolean deleteApplications( int appID, User user);

    File downloadApplications(String username, String password, int appID);

    public ArrayList<Application> getAllOfAUsersAppointments(User user);
}
