package conversordemonedas.api.data;

import java.util.Map;
import com.google.gson.Gson;

public class Convertion {
	private Gson gson; 
	private ConvertionRateData conversionData;
	private String baseCode, targetCode;
	private Map <String, Double> conversionRates; 
	private double conversionRate;
	private double conversionResult; 
	
	public Convertion (String jsonData) {
		this.gson = new Gson();
		this.conversionData = this.gson.fromJson(jsonData, ConvertionRateData.class);
		this.baseCode = this.conversionData.base_code();
		this.targetCode = this.conversionData.target_code();
		this.conversionResult = this.conversionData.conversion_result();
		this.conversionRates = this.conversionData.conversion_rates();
		this.conversionRate = this.conversionData.conversion_rate();
	}

	public String getBaseCode() {
	    return baseCode;
	}

	public String getTargetCode() {
	    return targetCode;
	}

	public Map<String, Double> getConversionRates() {
	    return conversionRates;
	}
	
	public double getConversionRate() {
	    return conversionRate;
	}

	public double getConversionResult() {
	    return conversionResult;
	}
}