package conversordemonedas.api.datos;

import com.google.gson.Gson;

import conversordemonedas.api.vista.InfoDisplay;

public class Json {
	private Gson gson;
	private JsonData jsonData;

	public Json(String jsonData) {
		this.gson = new Gson();
		this.jsonData = this.gson.fromJson(jsonData, JsonData.class);
	}

	public void printCoins(Coins coins, int limit) {
		InfoDisplay.availableData(this.jsonData);

		for (int pos = 0; pos < limit; pos++)
			InfoDisplay.availableCoins(coins.getNameCoins().get(pos), coins.getCodeCoins().get(pos));
	}

	//falta diferenciar el map de la clave valor que vienen solas
	public void printConvertionRates(Convertion convertion, int limit) {
		InfoDisplay.availableData(this.jsonData);

		int pos = 0;
		String baseCode = convertion.getBaseCode();

		for (String key : convertion.getConvertionRates().keySet()) {
			if (pos >= limit)
				break;

			Double value = convertion.getConvertionRates().get(key);
			InfoDisplay.showConvertionRate(key, value, baseCode);

			pos++;
		}
	}

	public void printConvertion(Convertion convertion) {
		InfoDisplay.availableData(this.jsonData);
		InfoDisplay.showConvertionDetails(convertion);
	}

	public void printCuota(Quota quota) {
		InfoDisplay.availableData(this.jsonData);
		InfoDisplay.showQuotaDetails(quota);
	}

	public JsonData getJsonData() {
		return this.jsonData;
	}
}
