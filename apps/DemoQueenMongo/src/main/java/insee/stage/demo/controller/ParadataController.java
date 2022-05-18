package insee.stage.demo.controller;

import insee.stage.demo.model.Paradata;
import insee.stage.demo.model.SurveyUnit;
import insee.stage.demo.service.ParadataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/paradata")
public class ParadataController {


    private final ParadataService paradataService;

    public ParadataController(ParadataService paradataService){
        this.paradataService = paradataService;
    }


    @GetMapping
    public ResponseEntity<List<Paradata>> getAllExpenses() {
        return ResponseEntity.ok(paradataService.getAllParadata());
    }
    /** POST paradata **/

    @PostMapping
    public ResponseEntity addParadataById(@RequestBody Paradata paradata, @RequestBody String Id) {
        paradataService.addParadata(paradata);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
