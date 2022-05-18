package insee.stage.demo.model;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document("paradata")
public class Paradata {

    @Id
    private String idSU;
    @Field(name = "events")
    private JSONObject events;

    public Paradata(String idSU, JSONObject events) {
        this.idSU = idSU;
        this.events = events;
    }

    public String getIdSU() {
        return idSU;
    }

    public void setIdSU(String idSU) {
        this.idSU = idSU;
    }

    public JSONObject getEvents() {
        return events;
    }

    public void setEvents(JSONObject events) {
        this.events = events;
    }
}
