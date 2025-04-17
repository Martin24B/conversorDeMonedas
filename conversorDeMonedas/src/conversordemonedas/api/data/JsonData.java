package conversordemonedas.api.data;

import com.google.gson.annotations.SerializedName;

public record JsonData(
    @SerializedName("result")
    String result,

    @SerializedName("documentation")
    String documentation,

    @SerializedName("terms_of_use")
    String termsOfUse,

    @SerializedName("time_last_update_utc")
    String timeLastUpdateUtc,

    @SerializedName("time_next_update_utc")
    String timeNextUpdateUtc
) {}
