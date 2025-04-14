package conversordemonedas.app;

import com.google.gson.Gson;

import conversordemonedas.api.connection.*;
import conversordemonedas.api.data.*;
import conversordemonedas.api.user.*;
import conversordemonedas.api.view.*;

public class Main {
	public static void main(String[] args) {
		History.createHistory();
		InfoDisplay.welcomeMessage();

		int option = 1;

		while (option != 4) {
			InfoDisplay.availableOption();
			option = DataInput.insertOption();
			procesarOpcion(option);
		}
	}

	private static void procesarOpcion(int option) {
		switch (option) {
		case 1:
			InfoDisplay.showHistory(History.returnHistory());
			break;
		case 2:
			eliminarArchivos();
			break;
		case 3:
			realizarSolicitud();
			break;
		}
	}

	private static void eliminarArchivos() {
		int option = -1;
		String fileName = "";

		while (option != 0) {
			option = DataInput.insertOptionHistory();
			if (option == 1)
				History.deleteHistory();

			if (option == 2) {
				fileName = DataInput.insertFileName();
				History.deleteHistory(fileName);
			}
		}
	}

	private static void realizarSolicitud() {
		Client client = new Client();
		client.sendRequest(Configuration.RESOURCE);
		int option = -1;

		while (option != 0) {
			InfoDisplay.availableEndpoints();
			int optionEndpoint = DataInput.selectEndpoint();
			int optionResource = DataInput.selectOptionResource(optionEndpoint);
			responseParser(client, optionResource, optionEndpoint);
			option = DataInput.insertOperation();

			// se podria preguntar si se desea guardar la operacion en el historial
		}
	}

	private static void responseParser(Client client, int optionResource, int optionEndpoint) {
		Module jsondata = JsonData.class.getModule();
		jsondata.addOpens("conversordemonedas.api.data", Gson.class.getModule());

		Json json = new Json(client.getBody());
		Coins coins = new Coins(client.getBody());
		String endpoint = "";

		if (!Endpoint.endpointIsPair(optionEndpoint)) {
			if (Endpoint.endpointIsCodes(optionEndpoint)) {
				int limit = DataInput.insertLimit();
				json.printCoins(coins, limit);
			} else if (Endpoint.endpointIsQuota(optionEndpoint)) {
				client.sendRequest(Endpoint.QUOTA.getName());
				json.setJsonData(client.getBody());
				Quota quota = new Quota(client.getBody());
				json.printCuota(quota);
			} else if (Endpoint.endpointIsLatest(optionEndpoint)) {
				endpoint = DataInput.insertEndpoint(optionEndpoint, optionResource);
				client.sendRequest(endpoint);
				json.setJsonData(client.getBody());
				Convertion convertion = new Convertion(client.getBody());
				int limit = DataInput.insertLimit();
				json.printConversionRates(convertion, limit);
			}
		} else {
			endpoint = DataInput.insertEndpoint(optionEndpoint, optionResource);
			client.sendRequest(endpoint);
			json.setJsonData(client.getBody());
			Convertion convertion = new Convertion(client.getBody());

			if (optionResource == 1) {
				json.printConversionRate(convertion);
			} else if (optionResource == 2) {
				json.printConversion(convertion);
			}
		}
	}
}
