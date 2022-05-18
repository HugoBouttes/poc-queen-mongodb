package insee.stage.demo.controller;

import insee.stage.demo.model.SurveyUnit;
import insee.stage.demo.model.Statedata;
import insee.stage.demo.service.SurveyUnitService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/survey-units")
public class SurveyUnitController {



    private final SurveyUnitService surveyUnitService;

    public SurveyUnitController(SurveyUnitService surveyUnitService){
        this.surveyUnitService = surveyUnitService;
    }

    @PostMapping
    public ResponseEntity addExpense(@RequestBody SurveyUnit surveyUnit) {
        surveyUnitService.addExpense(surveyUnit);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping
    public ResponseEntity<List<SurveyUnit>> getAllExpenses() {
       return ResponseEntity.ok(surveyUnitService.getAllExpenses());
    }

    /** GET /survey-unit**/
    @GetMapping("/{id}")
    public ResponseEntity<SurveyUnit> getExpenseById(@PathVariable String id) {
        return ResponseEntity.ok(surveyUnitService.getExpenseById(id));
    }

    /**GET nomenclature rep **/
    /**GET nomenclature  **/

    /**GET questionnaire modele **/

    /**GET metadata **/



    /**GET personalization **/

    /** PUT state-data **/

    @PutMapping("/{id}/state-data")
    public ResponseEntity updateStateDataById(@PathVariable String id, @RequestBody Statedata  stateData){
        SurveyUnit surveyUnit = surveyUnitService.getExpenseById(id);
        surveyUnitService.updateExpenseStateData(surveyUnit, stateData);
        return ResponseEntity.ok().build();
    }

    /** PUT data **/

    @PutMapping("/{id}/data")
    public ResponseEntity updateDataById(@PathVariable String id, @RequestBody JSONObject  data){
      SurveyUnit surveyUnit = surveyUnitService.getExpenseById(id);
      surveyUnitService.updateExpenseData(surveyUnit, data);
      return ResponseEntity.ok().build();
    }



    /** PUT survey unit **/
    @PutMapping
    public ResponseEntity<Object> updateExpense(@RequestBody SurveyUnit surveyUnit) {
        surveyUnitService.updateExpense(surveyUnit);
        return ResponseEntity.ok().build();
    }


    /** GET personalization **/

    @GetMapping("/{id}/personalization")
    public ResponseEntity<JSONObject> getDataByPersonalization(@PathVariable String id) {
        return  ResponseEntity.ok(surveyUnitService.getExpenseById(id).getPersonalization());
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteExpense(@PathVariable String  id) {
        surveyUnitService.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
