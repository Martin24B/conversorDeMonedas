package conversordemonedas.api.data;

public record JsonData(
    String result,
    String documentation,
    String terms_of_use,
    String time_last_update_utc,
    String time_next_update_utc
) {}
