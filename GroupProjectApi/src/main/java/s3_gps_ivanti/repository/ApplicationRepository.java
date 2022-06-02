package s3_gps_ivanti.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import s3_gps_ivanti.repository.entity.Application;

import java.util.List;

public interface ApplicationRepository extends MongoRepository<Application, String> {
    @Query("{name:'?0'}")
    Application findByName(String name);

    List<Application> findAllByStatusOrderByNameAsc(Boolean status);

    List<Application> findAllByStatusOrderByNameDesc(Boolean status);

    List<Application> findAllByStatusOrderByRatingAsc(Boolean status);

    List<Application> findAllByStatusOrderByRatingDesc(Boolean status);

    List<Application> findAllByNameContainingIgnoreCaseAndStatusOrderByNameAsc(String name, Boolean status);

    List<Application> findAllByNameContainingIgnoreCaseAndStatusOrderByNameDesc(String name, Boolean status);

    List<Application> findAllByNameContainingIgnoreCaseAndStatusOrderByRatingAsc(String name, Boolean status);

    List<Application> findAllByNameContainingIgnoreCaseAndStatusOrderByRatingDesc(String name, Boolean status);

    List<Application> findAllByCreator_UsernameAndStatusOrderByNameAsc(String username, Boolean status);

    List<Application> findAllByCreator_UsernameAndStatusOrderByNameDesc(String username, Boolean status);

    List<Application> findAllByCreator_UsernameAndStatusOrderByRatingAsc(String username, Boolean status);

    List<Application> findAllByCreator_UsernameAndStatusOrderByRatingDesc(String username, Boolean status);

    List<Application> findAllByCreator_UsernameAndNameContainingIgnoreCaseAndStatusOrderByNameAsc(String username, String name, Boolean status);

    List<Application> findAllByCreator_UsernameAndNameContainingIgnoreCaseAndStatusOrderByNameDesc(String username, String name, Boolean status);

    List<Application> findAllByCreator_UsernameAndNameContainingIgnoreCaseAndStatusOrderByRatingAsc(String username, String name, Boolean status);

    List<Application> findAllByCreator_UsernameAndNameContainingIgnoreCaseAndStatusOrderByRatingDesc(String username, String name, Boolean status);
}
