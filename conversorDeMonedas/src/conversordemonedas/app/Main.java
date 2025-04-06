package conversordemonedas.app;

import java.util.Scanner;

import com.google.gson.Gson;
import conversordemonedas.api.conexion.*;
import conversordemonedas.api.datos.*;
import conversordemonedas.api.vista.InfoDisplay;
import conversordemonedas.api.vista.IngresoDeDatos;

public class Main {
	public static void main(String[] args) {
		Module jsondata = JsonData.class.getModule();
		Scanner escaner = new Scanner(System.in);
		Cliente cliente = new Cliente();

		InfoDisplay.welcomeMessage();
		InfoDisplay.availableEndpoints();
		int optionEndpoint = IngresoDeDatos.selectEndpoint(escaner);
		int optionResource = IngresoDeDatos.selectOptionResource(escaner, optionEndpoint);
		String endpoint = IngresoDeDatos.insertEndpoint(optionEndpoint, optionResource, escaner);

		cliente.sendRequest(endpoint);

		jsondata.addOpens("conversordemonedas.api.datos", Gson.class.getModule());

		procesarRespuesta (cliente.getBody(), optionEndpoint, escaner);

		/*
		 * las respuestas generadas deben guardarse en archivos individuales
		 * coins, latest, quota, pero pair es compartido para los 2 casos
		 * 
		 * definir lo de autenticacion de usuarios, lo del while general, readme, y capturas de pantallla
		 * verificar que en el trello no falte nada
		 */
		
		escaner.close();
	}

	public static void procesarRespuesta(String response, int optionEnpoint, Scanner escaner) {
		Json json = new Json (response);
		
		if (Endpoint.endpointIsCodes(optionEnpoint)) {
			Coins coins = new Coins (response);
			int limit = IngresoDeDatos.insertLimit(escaner);
			json.printCoins(coins, limit);
		} else if (Endpoint.endpointIsLatest(optionEnpoint)) {
			Convertion convertion = new Convertion (response);
			int limit = IngresoDeDatos.insertLimit(escaner);
			InfoDisplay.availableData(json.getJsonData());
			json.printConvertionRates (convertion, limit);
		} else if (Endpoint.endpointIsPair(optionEnpoint)) {
			Convertion convertion = new Convertion (response);
			InfoDisplay.availableData(json.getJsonData());
			json.printConvertion(convertion);
		} else if (Endpoint.endpointIsQuota(optionEnpoint)) {
			Quota quota = new Quota (response);
			InfoDisplay.availableData(json.getJsonData());
			json.printCuota (quota);
		}
	}
}
