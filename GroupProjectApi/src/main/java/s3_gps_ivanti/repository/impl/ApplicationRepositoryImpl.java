package com.example.S3_GPS_Ivanti.repository.Impl;

import S3_GPS_Ivanti.model.Application;
import S3_GPS_Ivanti.model.User;
import S3_GPS_Ivanti.repository.ApplicationRepository;
import S3_GPS_Ivanti.repository.DataBaseForNow;
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
        return null;
    }

    @Override
    public ArrayList<Application> getApplicationsBySearch(String search) {
        return null;
    }

    @Override
    public Application getApplicationsByID(long ID) {
        for(Application p : database.applications) {
            if (p.getId() == ID) {
                return p;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Application> getApplications() {
        return null;
    }

    @Override
    public boolean createApplications(Application app) {
        return false;
    }

    @Override
    public boolean updateApplications(Application app) {
        return false;
    }

    @Override
    public boolean deleteApplications(int appID) {
        return false;
    }

    @Override
    public File downloadApplications(int appID) {
        return null;
    }

    public ArrayList<Application> getAllOfAUsersAppointments(User user) {return null;}
}
