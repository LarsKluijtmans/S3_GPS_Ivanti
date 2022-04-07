package s3_gps_ivanti.repository.impl;


import s3_gps_ivanti.DTO.UpdateApplicationDTO;
import s3_gps_ivanti.model.Application;
import s3_gps_ivanti.model.Review;
import s3_gps_ivanti.model.User;
import s3_gps_ivanti.repository.ApplicationRepository;
import s3_gps_ivanti.repository.DataBaseForNow;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.ArrayList;

@Primary
@Service
public class ApplicationRepositoryImpl implements ApplicationRepository {

    private DataBaseForNow database = new DataBaseForNow();

    @Override
    public ArrayList<Application> getApplicationsSorted(boolean rating, boolean date) {
        return new ArrayList<Application>();
    }

    @Override
    public ArrayList<Application> getApplicationsBySearch(String search) {
        return new ArrayList<Application>();
    }

    @Override
    public Application getApplicationsByID(String ID) {
        for(Application p : database.applications) {
            if (p.getId().equals(ID)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Application> getApplications() {
        return new ArrayList<Application>();
    }

    @Override
    public boolean createApplications(Application app) {
        return false;
    }

    @Override
    public boolean updateApplications(Application app) {
        for(Application a : database.applications)
        {
            if(a.getId().equals(app.getId())){
                a.setName(app.getName());
                a.setDescription(app.getDescription());
                a.setScreenshots(app.getScreenshots());
                a.setIcon(app.getIcon());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteApplications(String appID) {
        return false;
    }

    @Override
    public Application getApplicationToUpdate(String appname){
        for(Application a: database.applications)
        {
            if(a.getName().equals(appname)){
                return a;
            }
        }
        return null;
    }

    @Override
    public File downloadApplications(String appID) {
        return null;
    }

    public ArrayList<Application> getAllOfAUsersAppointments(User user) {
        if(user != null){
            return null;
        }else{
            return new ArrayList<Application>();
        }
    }
}
