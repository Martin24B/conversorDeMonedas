package conversordemonedas.api.data;

import java.util.Map;

public record ConvertionRateData(
	String base_code,
	String target_code,
	Map<String, Double> conversion_rates, 
	double conversion_rate,
	double conversion_result 
) {}
