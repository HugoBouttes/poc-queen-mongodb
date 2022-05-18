package insee.stage.demo.model;

import org.json.JSONObject;

import java.util.Date;

public class Statedata {
    private State state;
    private Date date;
    private int currentPage;

    private JSONObject statedata = new JSONObject();

    public Statedata(State state, Date date, int currentPage) {
        this.state = state;
        this.date = date;
        this.currentPage = currentPage;
        JSONObject temp = this.statedata;
        temp.put("state", state);
        temp.put("date", date);
        temp.put("currentPage", currentPage);
        this.statedata = temp;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public JSONObject getStatedata(){
        return  statedata;
    }

    public void addStatedata(State state, Date date, int currentPage){
        JSONObject temp = this.statedata;
        temp.put("state", state);
        temp.put("date", date);
        temp.put("currentPage", currentPage);
        this.statedata = temp;
    }
}
