package conversordemonedas.api.data;

import com.google.gson.Gson;

public class Json {
    private final Gson gson;
    private JsonData jsonData;

    public Json(String jsonData) {
        this.gson = new Gson();
        this.jsonData = this.gson.fromJson(jsonData, JsonData.class);
    }
    
    public void setJsonData (String jsonData) {
    	this.jsonData = this.gson.fromJson(jsonData, JsonData.class);
    }

    public String getResult() {
        return jsonData.result();
    }

    public String getDocumentation() {
        return jsonData.documentation();
    }

    public String getTermsOfUse() {
        return jsonData.termsOfUse();
    }

    public String getTimeLastUpdateUtc() {
        return jsonData.timeLastUpdateUtc();
    }

    public String getTimeNextUpdateUtc() {
        return jsonData.timeNextUpdateUtc();
    }
}

