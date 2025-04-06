package conversordemonedas.api.vista;

import java.util.Scanner;
import conversordemonedas.api.conexion.Endpoint;
import conversordemonedas.api.datos.Coins;

public class IngresoDeDatos {

	public static int selectEndpoint(Scanner escaner) {
		int opcion = 0;

		while (!Endpoint.endpointValid(opcion)) {
			System.out.println("Seleccione el número del recurso que desea consultar:");
			opcion = escaner.nextInt();
			if (!Endpoint.endpointValid(opcion)) 
				System.out.println("Recurso no disponible. Intente nuevamente.");
		}

		return opcion - 1;
	}

	public static int selectOptionResource(Scanner escaner, int opcion) {
		int opcion2 = 0;

		if (Endpoint.endpointIsPair(opcion)) {
			InfoDisplay.availableResources();
			while (opcion2 <= 0 || opcion2 > 2) {
				System.out.println("Seleccione una de las opciones disponibles para este recurso:");
				opcion2 = escaner.nextInt();
				if (opcion2 <= 0 || opcion2 > 2)
					System.out.println("Opción no válida. Intente nuevamente.");
			}
		}

		return opcion2;
	}

	public static String insertEndpoint(int optionEndpoint, int optionResource, Scanner escaner) {
		if (!Endpoint.endpointIsPair(optionEndpoint)) {
			if (Endpoint.endpointIsLatest(optionEndpoint)) {
				System.out.println("Ingrese el código de la moneda base que desea consultar:");
				return Endpoint.values()[optionEndpoint].getName() + "/" + insertCodeCoin(escaner);
			}
		}

		if (optionResource == 1) {
			System.out.println("Ingrese el código de la moneda base para la conversión:");
			String coinBase = insertCodeCoin(escaner);
			System.out.println("Ingrese el código de la moneda destino:");
			String coinTarget = insertCodeCoin(escaner);
			System.out.println("Ingrese el monto a convertir:");
			double amount = insertAmount(escaner);
			return Endpoint.values()[optionEndpoint].getName() + "/" + coinBase + "/" + coinTarget + "/" + amount;
		}

		return Endpoint.values()[optionEndpoint].getName();
	}

	public static String insertCodeCoin(Scanner escaner) {
		String code = "";

		while (!Coins.isCodeCoinValid(code)) {
			System.out.println("Ingrese un código de moneda válido (por ejemplo: USD, EUR, ARS):");
			code = escaner.nextLine().trim();
			if (!Coins.isCodeCoinValid(code))
				System.out.println("El código ingresado no corresponde a ninguna moneda activa. Intente nuevamente.");
		}

		return code;
	}

	public static double insertAmount(Scanner escaner) {
		return escaner.nextDouble();
	}

	public static int insertLimit(Scanner escaner) {
		int limit = 0;

		while (!Coins.isLimitValid(limit)) {
			System.out.println("Ingrese la cantidad de monedas que desea solicitar:");
			limit = escaner.nextInt();

			if (!Coins.isLimitValid(limit))
				System.out.println("El límite debe ser mayor a 0 y menor a 161. Intente nuevamente.");
		}

		return limit;
	}
}
