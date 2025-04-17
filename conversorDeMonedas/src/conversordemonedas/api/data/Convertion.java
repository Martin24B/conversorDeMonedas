package conversordemonedas.api.data;

import java.util.Map;
import com.google.gson.Gson;

public class Convertion {
	private Gson gson; 
	private ConvertionData conversionData;
	private String baseCode, targetCode;
	private Map <String, Double> conversionRates; 
	private double conversionRate;
	private double conversionResult; 
	
	public Convertion (String jsonData) {
		this.gson = new Gson();
		this.conversionData = this.gson.fromJson(jsonData, ConvertionData.class);
		this.baseCode = this.conversionData.baseCode();
		this.targetCode = this.conversionData.targetCode();
		this.conversionResult = this.conversionData.conversionResult();
		this.conversionRates = this.conversionData.conversionRates();
		this.conversionRate = this.conversionData.conversionRate();
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