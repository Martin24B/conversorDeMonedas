package conversordemonedas.app;

import com.google.gson.Gson;
import conversordemonedas.api.connection.*;
import conversordemonedas.api.data.*;
import conversordemonedas.api.userData.History;
import conversordemonedas.api.view.*;

public class Main {
	public static void main(String[] args) {
		History.createHistory();
		InfoDisplay.welcomeMessage();

		int option = 1;

		while (option != 4) {
			InfoDisplay.availableOption();
			option = DataInput.selectOption();
			processOption(option);
		}
		
		InfoDisplay.goodbyeMessage();
	}

	private static void processOption(int option) {
		if (option == 1) {
			InfoDisplay.showHistory(History.returnHistory());
		} else if (option == 2) {
			deleteFiles();
		} else if (option == 3) {
			executeRequest();
		}
	}

	private static void deleteFiles() {
		int option = 0;
		String fileName = "";

		while (option != 3) {
			InfoDisplay.availableOptionHistory();
			option = DataInput.selectHistoryOption();
			if (option == 1) {
				History.deleteHistory();
			} else if (option == 2) {
				fileName = DataInput.insertFileName();
				History.deleteHistory(fileName);
			}
		}
	}

	private static void executeRequest() {
		Module jsonModule = JsonData.class.getModule();
		jsonModule.addOpens("conversordemonedas.api.data", Gson.class.getModule());

		ApiClient client = new ApiClient();
		client.sendRequest(Config.RESOURCE);
		Json json = new Json(client.getResponseBody());
		Coins coins = new Coins(client.getResponseBody());

		int option = -1;

		while (option != 0) {
			InfoDisplay.availableEndpoints();
			int endpointOption = DataInput.selectEndpoint();
			int resourceOption = DataInput.selectResourceOption(endpointOption);
			String response = parseResponse(client, resourceOption, endpointOption, json, coins);
			History.appendToHistory(response);
			System.out.println(response);
			option = DataInput.selectOperation();
		}
	}

	private static String parseResponse(ApiClient client, int resourceOption, int endpointOption, Json json, Coins coins) {
		String endpoint = "";

		if (!Endpoint.endpointIsPair(endpointOption)) {
			if (Endpoint.endpointIsCodes(endpointOption)) {
				int limit = DataInput.insertLimit();
				return InfoDisplay.availableCoins(coins, limit);
			} else if (Endpoint.endpointIsQuota(endpointOption)) {
				client.sendRequest(Endpoint.QUOTA.getName());
				json.setJsonData(client.getResponseBody());
				Quota quota = new Quota(client.getResponseBody());
				return InfoDisplay.showQuotaDetails(quota);
			} else if (Endpoint.endpointIsLatest(endpointOption)) {
				endpoint = DataInput.insertEndpoint(endpointOption, resourceOption);
				client.sendRequest(endpoint);
				json.setJsonData(client.getResponseBody());
				Convertion conversion = new Convertion(client.getResponseBody());
				int limit = DataInput.insertLimit();
				return InfoDisplay.showConversionRates(conversion, limit);
			}
		} else {
			endpoint = DataInput.insertEndpoint(endpointOption, resourceOption);
			client.sendRequest(endpoint);
			json.setJsonData(client.getResponseBody());
			Convertion conversion = new Convertion(client.getResponseBody());

			if (resourceOption == 1) {
				return InfoDisplay.showConversionRate(conversion);
			} else if (resourceOption == 2) {
				return InfoDisplay.showConversionDetails(conversion);
			}
		}

		return "";
	}
}
