package conversordemonedas.api.data;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public record ConvertionData(
    @SerializedName("base_code")
    String baseCode,

    @SerializedName("target_code")
    String targetCode,

    @SerializedName("conversion_rates")
    Map<String, Double> conversionRates,

    @SerializedName("conversion_rate")
    double conversionRate,

    @SerializedName("conversion_result")
    double conversionResult
) {}
