package conversordemonedas.api.datos;

public record QuotaData(
	int plan_quota,
	int request_remaining,
	int refresh_day_of_month
) {}
