package insee.stage.demo.service;

import insee.stage.demo.model.SurveyUnit;
import insee.stage.demo.model.Statedata;
import insee.stage.demo.repository.SurveyUnitRepository;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyUnitService {

    private final SurveyUnitRepository surveyUnitRepository;

    public SurveyUnitService(SurveyUnitRepository surveyUnitRepository) {
        this.surveyUnitRepository = surveyUnitRepository;
    }

    public void addExpense(SurveyUnit surveyUnit) {
        surveyUnitRepository.insert(surveyUnit);
    }




    public void updateExpense(SurveyUnit surveyUnit) {
        SurveyUnit savedSurveyUnit = surveyUnitRepository.findById(surveyUnit.getId()).orElseThrow(() -> new RuntimeException(
                String.format("Cannot find survey units by ID %s", surveyUnit.getId())));

        savedSurveyUnit.setComment(surveyUnit.getComment());
        savedSurveyUnit.setData(surveyUnit.getData());
        savedSurveyUnit.setPersonalization(surveyUnit.getPersonalization());
        savedSurveyUnit.setStateData(surveyUnit.getStateData());

        surveyUnitRepository.save(surveyUnit);
    }


    public void updateExpenseData(SurveyUnit surveyUnit, JSONObject data) {
        surveyUnit.setData(data);
        surveyUnitRepository.save(surveyUnit);
    }

    public void updateExpenseComment(SurveyUnit surveyUnit, String comment) {
        surveyUnit.setComment(comment);
        surveyUnitRepository.save(surveyUnit);
    }

    public void updateExpenseStateData(SurveyUnit surveyUnit, Statedata stateData) {
        surveyUnit.setStateData(stateData);
        surveyUnitRepository.save(surveyUnit);
    }

    public void updateExpensePersonalization(SurveyUnit surveyUnit, JSONObject personalization) {
        surveyUnit.setPersonalization(personalization);
        surveyUnitRepository.save(surveyUnit);
    }


    public List<SurveyUnit> getAllExpenses() {
        return surveyUnitRepository.findAll();
    }

    public SurveyUnit getExpenseByName(String name) {
        return surveyUnitRepository.findByName(name).orElseThrow(() -> new RuntimeException(
                String.format("Cannot fin survey units by name %s", name)));

    }

    public SurveyUnit getExpenseById(String id) {
        return surveyUnitRepository.findById(id).orElseThrow(() -> new RuntimeException(
                String.format("Cannot fin survey units by Id %s", id)));
    }

    public void deleteExpense(String id) {
        surveyUnitRepository.deleteById(id);

    }
}
