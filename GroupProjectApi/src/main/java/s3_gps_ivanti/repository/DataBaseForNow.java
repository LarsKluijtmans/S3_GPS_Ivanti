package s3_gps_ivanti.repository;

import s3_gps_ivanti.model.*;

import java.util.ArrayList;

public class
DataBaseForNow {

    public ArrayList<User> users;
    public ArrayList<Review> reviews;
    public ArrayList<Application> applications;
    public ArrayList<Response> responses;

    public DataBaseForNow()
    {

        users = new ArrayList<>();
        reviews = new ArrayList<>();
        applications = new ArrayList<>();
        responses = new ArrayList<>();

     //Create users
        Creator user1 = new Creator("Lars1", "Lars1",null,null);
        Creator user2 = new Creator("Lars2", "Lars2",null,null);
        Customer user3 = new Customer("Lars3", "Lars3", null, null);
        Customer user4 = new Customer ("Customer", "Customer");
        users.add(user1);
        users.add(user2);
        users.add(user3);

        ArrayList<String> images = new ArrayList<>();
        images.add("https://drive.google.com/uc?export=view?&id=1tFkazCdwZids6CqOHPWMVxq-DQwZ2sJW/");
        images.add("https://drive.google.com/uc?export=view?&id=12lGO68ekH92R_uMS7NyZ1-ZAZ8jJA7fD/");

        //Create applications
        Application application1 = new Application();
        application1.setName("aaa");
        application1.setId("1");
        application1.setIcon("Icon here");
        application1.setDescription("description here");
        application1.setScreenshots(images);

        Application application2 = new Application();
        application2.setName("ccc");
        application2.setId("2");
        application2.setScreenshots(images);


        Application application3 = new Application();
        application3.setName("bbb");
        application3.setId("3");
        application3.setScreenshots(images);

        //Giving applications to users
        ArrayList<Application> applications1 = new ArrayList<Application>();
        applications1.add(application1);
        user1.setMyApplications(applications1);

        ArrayList<Application> applications2 = new ArrayList<Application>();
        applications2.add(application2);
        applications2.add(application3);
        user2.setMyApplications(applications2);

        applications.add(application1);
        applications.add(application2);
        applications.add(application3);
    }
}
