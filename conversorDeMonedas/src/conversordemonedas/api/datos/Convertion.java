package conversordemonedas.api.datos;

import java.util.Map;
import com.google.gson.Gson;

public class Convertion {
	private Gson gson;
	private ConvertionRateData convertionData;
	private String baseCode, targetCode;
	private Map <String, Double> convertionRates; 
	private double convertionResult; //valor final
	
	public Convertion (String jsonData) {
		this.convertionData = this.gson.fromJson(jsonData, ConvertionRateData.class);
		this.baseCode = this.convertionData.base_code();
		this.targetCode = this.convertionData.target_code();
		this.convertionResult = this.convertionData.convertion_result();
		this.convertionRates = this.convertionData.conversion_rates();
	}

	public ConvertionRateData getConvertionData() {
	    return convertionData;
	}

	public String getBaseCode() {
	    return baseCode;
	}

	public String getTargetCode() {
	    return targetCode;
	}

	public Map<String, Double> getConvertionRates() {
	    return convertionRates;
	}

	public double getConvertionResult() {
	    return convertionResult;
	}
}