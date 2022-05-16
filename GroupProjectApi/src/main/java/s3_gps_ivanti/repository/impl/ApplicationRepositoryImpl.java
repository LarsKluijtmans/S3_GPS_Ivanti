package s3_gps_ivanti.repository.impl;


import s3_gps_ivanti.dto.GetVersionDTO;
import s3_gps_ivanti.repository.ApplicationRepository;
import s3_gps_ivanti.model.Application;
import s3_gps_ivanti.model.Version;
import s3_gps_ivanti.repository.DataBaseForNow;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class ApplicationRepositoryImpl implements ApplicationRepository {

    private DataBaseForNow database = new DataBaseForNow();

    //all
    @Override
    public ArrayList<Application> getApplicationsSorted(boolean rating, boolean date) {
        return new ArrayList<Application>();
    }
    @Override
    public ArrayList<Application> getApplicationsBySearch(String search) {
        return new ArrayList<Application>();
    }
    @Override
    public Application getApplicationsByID(int id) {
        for(Application app : database.applications) {
            if (app.getId() == id) {
                return app;
            }
        }
        return null;
    }
    @Override
    public ArrayList<Application> getApplications() {
        return null;
    }


    //Creator
    @Override
    public ArrayList<Application> getApplicationDetails(String appName){
        ArrayList<Application> apps = new ArrayList<>();

        for (Application a : database.applications) {
            if(a.getName().equals(appName)) {
                apps.add(a);
            }
        }
        return apps;
    }
    @Override
    public boolean createApplications(Application app){

        int maxid = 1;
        for (Application a :database.applications) {
            if(a.getId() > maxid)
            {
                maxid = a.getId();
            }
        }

        maxid++;
        app.setId(maxid);

        database.applications.add(app);
        return true;
    }
    @Override
    public ArrayList<Application> getApplicationsByCreator(int id) {
        ArrayList<Application> creatorApps = new ArrayList<>();

        for (Application app : database.applications){
            if (app.getCreator() != null && app.getCreator().getId() == id){
                creatorApps.add(app);
            }
        }

        return creatorApps;
    }

    @Override
    public boolean updateApplications(Application app) {
        for(Application a : database.applications)
        {
            if(a.getId() == app.getId()){
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
    public boolean deleteApplications(String name) {
        database.applications.remove(getApplicationInfoByName(name));
        return true;
    }
    @Override
    public  boolean FindAppWithSameName(String appName){
        for(Application a : database.applications)
        {
            if(a.getName().equals(appName))
            {
                return true;
            }
        }
        return false;
    }

    //Customers
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
    public File downloadApplications(int id) {
        return null;
    }

    @Override
    public Application getApplicationInfoByName(String name) {
        for (Application app : database.applications){
            if (app.getName().equals(name)){
                return app;
            }
        }

        return null;
    }

    @Override
    public ArrayList<Application> getApplicationsByCustomer(int id) {
        if(id != 0){
            return null;
        }else{
            return new ArrayList<Application>();
        }
    }



    //Version // creator only
    @Override
    public Version getVersion(int applicationId, Double number) {
        for (Application a : database.applications) {
            if(a.getId() == applicationId)
            {
                for (Version v: a.getVersions()) {
                    if(v.getNumber() == number) {
                        return v;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean createVersion(int applicationId, Version version) {
        for (Application a : database.applications) {
            if(a.getId() == applicationId){

               List<Version> ver =  a.getVersions();
               ver.add(version);
               a.setVersions(ver);

               return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteVerison(int applicationId, Double number) {
        for (Application a : database.applications) {

            if(a.getId() == applicationId)
            {
                for (Version v: a.getVersions()) {
                    if(v.getNumber() == number) {

                        List<Version> ver =  a.getVersions();
                        ver.remove(v);
                        a.setVersions(ver);

                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Version updateVersion(int applicationId, Version version) {
        for (Application a : database.applications) {
            if(a.getId() == applicationId)
            {
                for (Version v: a.getVersions()) {
                    v = version;
                    return version;

                }
            }
        }
        return null;
    }

    @Override
    public List<Version> getVersionsByApplication(String appname) {
        for(Application a: database.applications)
        {
            if(a.getName().equals(appname)){
                return a.getVersions();
            }
        }
        return null;
    }
}
