package insee.stage.demo.repository;

import insee.stage.demo.model.Metadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface MetadataRepository extends MongoRepository<Metadata, String> {
    @Query("{'name': ?0}")
    Optional<Metadata> findByName(String name);
}
