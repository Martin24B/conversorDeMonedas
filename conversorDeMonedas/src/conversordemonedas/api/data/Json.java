package conversordemonedas.api.data;

import com.google.gson.Gson;

import conversordemonedas.api.view.InfoDisplay;

public class Json {
	private Gson gson;
	private JsonData jsonData;

	public Json(String jsonData) {
		this.gson = new Gson();
		this.jsonData = this.getJsonData(jsonData);
	}

	public void printCoins(Coins coins, int limit) {
		InfoDisplay.availableData(this.jsonData);
		InfoDisplay.availableCoins(coins, limit);
	}
	
	public void printConversionRates(Convertion conversion, int limit) {
		InfoDisplay.availableData(this.jsonData);
		InfoDisplay.showConversionRates (conversion, limit);
		
	}
	
	public void printConversionRate (Convertion conversion) {
		InfoDisplay.availableData(this.jsonData);
		InfoDisplay.showConversionRate(conversion);
	}
	
	public void printConversion (Convertion conversion) {
		InfoDisplay.availableData(this.jsonData);
		InfoDisplay.showConversionDetails(conversion);
		
	}
	

	public void printCuota(Quota quota) {
		InfoDisplay.availableData(this.jsonData);
		InfoDisplay.showQuotaDetails(quota);
	}

	private JsonData getJsonData(String jsonData) {
		return this.gson.fromJson(jsonData, JsonData.class);
	}
	
	public void setJsonData (String jsonData) {
		this.jsonData = this.getJsonData(jsonData);
	}
}
