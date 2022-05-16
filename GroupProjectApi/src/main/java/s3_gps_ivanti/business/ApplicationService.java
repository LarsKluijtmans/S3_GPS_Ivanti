package s3_gps_ivanti.business;


import s3_gps_ivanti.dto.ApplicationDetailedInfoDTO;
import s3_gps_ivanti.dto.ApplicationStatisticsDTO;
import s3_gps_ivanti.dto.CreateApplicationRequestDTO;
import s3_gps_ivanti.dto.CreateApplicationResponseDTO;
import s3_gps_ivanti.dto.UpdateApplicationDTO;
import s3_gps_ivanti.model.Application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface ApplicationService {

    //all
    ArrayList<Application> getApplicationsSorted(boolean rating, boolean date);
    ArrayList<Application> getApplicationsBySearch(String search);
    ApplicationDetailedInfoDTO getApplicationInfoByID(int id);
    ArrayList<Application> getApplicationDetails(String appName);
    ArrayList<Application> getApplications();
    ArrayList<ApplicationStatisticsDTO> getApplicationStatisticsDTO(ArrayList<Application> applications);

    //Creator
    UpdateApplicationDTO getApplicationToUpdate(String appname);
    CreateApplicationResponseDTO createApplications(CreateApplicationRequestDTO app);
    boolean updateApplications(UpdateApplicationDTO app);
    boolean deleteApplications(String name);

    ArrayList<Application> getApplicationsByCustomer(int id);
    File downloadApplications(int id);

    ArrayList<Application> getApplicationsByCreator(int id);

    ApplicationDetailedInfoDTO getApplicationInfoByName(String name);

             //Versions
    GetVersionDTO getVersion(int applicationId, Double number);
    GetVersionDTO getVersionsByApplication(String appname);
    double createVersion(CreateVersionDTO versionDTO);
    boolean deleteVersion(DeleteVersionDTO versionDTO);
    GetVersionDTO updateVersion(UpdateVersionDTO versionDTO);

}
