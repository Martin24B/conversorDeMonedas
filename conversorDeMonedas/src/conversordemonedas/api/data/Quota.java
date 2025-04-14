package conversordemonedas.api.data;

import com.google.gson.Gson; 

public class Quota {
	private Gson gson; 
	private QuotaData quotaData;
	private int plan_quota, requests_remaining, refresh_day_of_month;
	
	public Quota (String jsonData) {
		this.gson = new Gson();
		this.quotaData = this.gson.fromJson(jsonData, QuotaData.class);
		this.plan_quota = quotaData.plan_quota();
		this.requests_remaining = quotaData.requests_remaining();
		this.refresh_day_of_month = quotaData.refresh_day_of_month();
	}

	public int getPlanQuota() {
	    return plan_quota;
	}

	public int getRequestRemaining() {
	    return requests_remaining;
	}

	public int getRefreshDayOfMonth() {
	    return refresh_day_of_month;
	}
}
