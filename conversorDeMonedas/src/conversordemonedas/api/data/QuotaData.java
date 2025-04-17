package conversordemonedas.api.data;

import com.google.gson.annotations.SerializedName;

public record QuotaData(
    @SerializedName("plan_quota")
    int planQuota,

    @SerializedName("requests_remaining")
    int requestsRemaining,

    @SerializedName("refresh_day_of_month")
    int refreshDayOfMonth
) {}
