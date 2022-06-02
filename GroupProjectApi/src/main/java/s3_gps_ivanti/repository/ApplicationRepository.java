package s3_gps_ivanti.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import s3_gps_ivanti.repository.entity.Application;

import java.util.List;

public interface ApplicationRepository extends MongoRepository<Application, String> {


    @Query("{name:'?0'}")
    Application findByName(String name);

    @Query("{creatorID:'?0'}")
    List<Application> findAllByCreatorID(String creatorID);

}
