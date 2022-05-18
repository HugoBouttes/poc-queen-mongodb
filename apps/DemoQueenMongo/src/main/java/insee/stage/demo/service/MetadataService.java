package insee.stage.demo.service;


import insee.stage.demo.model.Metadata;
import insee.stage.demo.model.Paradata;
import insee.stage.demo.repository.MetadataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetadataService {

    private final MetadataRepository metadatarepository ;

    public MetadataService(MetadataRepository metadataRepository){
        this.metadatarepository = metadataRepository;
    }

    public void addMetadata(Metadata metadata) {
        metadatarepository.insert(metadata);
    }


    public void updateMetadata(Metadata metadata) {
        Metadata savedMetadata = metadatarepository.findById(metadata.getId()).orElseThrow(() -> new RuntimeException(
                String.format("Cannot find survey units by ID %s",metadata.getId())));

        savedMetadata.setInseeContext(metadata.getInseeContext());
        savedMetadata.setVariables(metadata.getVariables());
        /**savedMetadata.setPersonalization(metadata.getMetadata()); **/

        metadatarepository.save(metadata);
    }

    public Metadata getMetadataById(String id){
        return metadatarepository.findById(id).orElseThrow(() -> new RuntimeException(
                String.format("Cannot fin survey units by Id %s", id)));
    }

    public List<Metadata> getAllMetadata() {
        return metadatarepository.findAll();
    }

}
