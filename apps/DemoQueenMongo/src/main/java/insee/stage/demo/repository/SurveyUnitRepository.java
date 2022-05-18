package insee.stage.demo.repository;

import insee.stage.demo.model.SurveyUnit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface SurveyUnitRepository extends MongoRepository<SurveyUnit, String> {
    @Query("{'name': ?0}")
    Optional<SurveyUnit> findByName(String name);
}
