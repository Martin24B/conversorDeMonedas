package conversordemonedas.api.datos;

import com.google.gson.Gson;

public class Quota {
	private Gson gson; 
	private QuotaData quotaData;
	private int plan_quota, request_remaining, refresh_day_of_month;
	
	public Quota (String jsonData) {
		this.gson = new Gson();
		this.quotaData = this.gson.fromJson(jsonData, QuotaData.class);
		this.plan_quota = quotaData.plan_quota();
		this.request_remaining = quotaData.request_remaining();
		this.refresh_day_of_month = quotaData.refresh_day_of_month();
	}

	public QuotaData getQuotaData() {
	    return quotaData;
	}

	public int getPlanQuota() {
	    return plan_quota;
	}

	public int getRequestRemaining() {
	    return request_remaining;
	}

	public int getRefreshDayOfMonth() {
	    return refresh_day_of_month;
	}
}
