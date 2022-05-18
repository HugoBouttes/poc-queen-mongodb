package insee.stage.demo.controller;

import insee.stage.demo.model.Metadata;
import insee.stage.demo.model.Paradata;
import insee.stage.demo.service.MetadataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/metadata")
public class MetadataController {
    private final MetadataService metadataService;

    public MetadataController(MetadataService metadataService){
        this.metadataService = metadataService;
    }

    @GetMapping
    public ResponseEntity<List<Metadata>> getAllExpenses() {
        return ResponseEntity.ok(metadataService.getAllMetadata());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Metadata> getMetadataById(@PathVariable String id) {
        return ResponseEntity.ok(metadataService.getMetadataById(id));
    }
}
