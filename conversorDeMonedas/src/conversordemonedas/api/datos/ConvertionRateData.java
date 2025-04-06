package conversordemonedas.api.datos;

import java.util.Map;

public record ConvertionRateData(
	String base_code,
	String target_code,
	Map<String, Double> conversion_rates,
	double convertion_result 
) {}
