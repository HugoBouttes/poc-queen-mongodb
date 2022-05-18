package insee.stage.demo.model;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document("metadata")
public class Metadata {

    @Id
    private String id;
    @Field(name = "inseeContext")
    private InseeContext inseeContext;
    @Field(name = "variables")
    private JSONObject variables;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private JSONObject metadata;

    public Metadata(String Id,InseeContext inseeContext, JSONObject variables) {
        this.id = Id;
        this.inseeContext = inseeContext;
        this.variables = variables;
    }



    public InseeContext getInseeContext() {
        return inseeContext;
    }

    public void setInseeContext(InseeContext inseeContext) {
        this.inseeContext = inseeContext;
    }

    public JSONObject getVariables() {
        return variables;
    }

    public void setVariables(JSONObject variables) {
        this.variables = variables;
    }

    public void addVariables(String name, String value){
        this.variables.put("name", name);
        this.variables.put("value", value);
    }

    public JSONObject getMetadata() {
        return metadata;
    }

    public void setMetadata(JSONObject metadata) {
        this.metadata = metadata;
    }

    public void addMetdata(JSONObject variables, InseeContext inseeContext){
        this.metadata.put("inseeContext", inseeContext);
        this.metadata.put("variables", variables);
    }
}
