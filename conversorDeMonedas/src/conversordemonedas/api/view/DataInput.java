package conversordemonedas.api.view;

import java.util.Scanner;

import conversordemonedas.api.connection.Endpoint;
import conversordemonedas.api.data.Coins;

public class DataInput {

	private static final Scanner scanner = new Scanner(System.in);

	public static int selectEndpoint() {
		int opcion = 0;

		while (!Endpoint.endpointValid(opcion)) {
			System.out.println("🔢 Seleccione el número del recurso que desea consultar:");
			if (scanner.hasNextInt()) {
				opcion = scanner.nextInt();
				if (!Endpoint.endpointValid(opcion)) 
					System.out.println("❌ Recurso no disponible. Intente nuevamente.");
			} else {
				System.out.println("⚠️ Entrada no válida. Por favor, ingrese un número.");
				scanner.next();
			}
		}

		return opcion - 1;
	}

	public static int selectOptionResource(int opcion) {
		int opcion2 = 0;

		if (Endpoint.endpointIsPair(opcion)) {
			InfoDisplay.availableResources();
			while (opcion2 <= 0 || opcion2 > 2) {
				System.out.println("🔢 Seleccione una de las opciones disponibles para este recurso:");
				if (scanner.hasNextInt()) {
					opcion2 = scanner.nextInt();
					if (opcion2 <= 0 || opcion2 > 2)
						System.out.println("❌ Opción no válida. Intente nuevamente.");
				} else {
					System.out.println("⚠️ Entrada no válida. Por favor, ingrese un número.");
					scanner.next();
				}
			}
		}

		return opcion2;
	}

	public static String insertEndpoint(int optionEndpoint, int optionResource) {
		if (!Endpoint.endpointIsPair(optionEndpoint)) {
			if (Endpoint.endpointIsLatest(optionEndpoint)) {
				System.out.println("💬 Ingrese el código de la moneda base que desea consultar (por ejemplo: USD, EUR, ARS):");
				return Endpoint.values()[optionEndpoint].getName() + "/" + insertCodeCoin();
			}
		}

		if (optionResource == 1) {
			System.out.println("💬 Ingrese el código de la moneda base para consultar su tasa de cambio:");
			String coinBase = insertCodeCoin();
			System.out.println("💬 Ingrese el código de la moneda destino:");
			String coinTarget = insertCodeCoin();
			return Endpoint.values()[optionEndpoint].getName() + "/" + coinBase + "/" + coinTarget;
		} else if (optionResource == 2) {
			System.out.println("💬 Ingrese el código de la moneda base que desea convertir:");
			String coinBase = insertCodeCoin();
			System.out.println("💬 Ingrese el código de la moneda destino:");
			String coinTarget = insertCodeCoin();
			System.out.println("💬 Ingrese el monto a convertir:");
			double amount = insertAmount();
			return Endpoint.values()[optionEndpoint].getName() + "/" + coinBase + "/" + coinTarget + "/" + amount;
		}

		return Endpoint.values()[optionEndpoint].getName();
	}

	public static String insertCodeCoin() {
		String code = "";

		while (!Coins.isCodeCoinValid(code)) {
			code = scanner.next().toUpperCase();
			if (!Coins.isCodeCoinValid(code))
				System.out.println("❌ El código ingresado no corresponde a ninguna moneda activa. Intente nuevamente.");
		}

		return code;
	}

	public static double insertAmount() {
		while (!scanner.hasNextDouble()) {
			System.out.println("⚠️ Entrada no válida. Por favor, ingrese un valor numérico.");
			scanner.next();
		}
		return scanner.nextDouble();
	}

	public static int insertLimit() {
		int limit = 0;

		while (!Coins.isLimitValid(limit)) {
			System.out.println("🔢 Ingrese la cantidad de monedas que desea solicitar (máximo 161):");
			if (scanner.hasNextInt()) {
				limit = scanner.nextInt();
				if (!Coins.isLimitValid(limit))
					System.out.println("❌ El límite debe ser mayor a 0 y menor o igual a 161. Intente nuevamente.");
			} else {
				System.out.println("⚠️ Entrada no válida. Por favor, ingrese un número.");
				scanner.next();
			}
		}

		return limit;
	}

	public static int insertOption() {
	    int option = -1;

	    while (option < 1 || option > 4) {
	        System.out.println("🔢 Ingrese una de las opciones disponibles (1-4):");

	        if (scanner.hasNextInt()) {
	            option = scanner.nextInt();
	            if (option < 1 || option > 4) {
	                System.out.println("❌ Opción incorrecta, intente nuevamente.");
	            }
	        } else {
	            System.out.println("⚠️ Entrada no válida. Por favor, ingrese un número.");
	            scanner.next();
	        }
	    }

	    return option;
	}
	
	public static int insertOperation () {
	    int option = -1;

	    while (option < 0 || option > 1) {
	        System.out.println("🔢 Ingrese '0' para salir, '1' para seguir operando.");
	        if (scanner.hasNextInt()) {
	            option = scanner.nextInt();
	            if (option < 0 || option > 1) {
	                System.out.println("❌ Opción incorrecta, intente nuevamente.");
	            }
	        } else {
	            System.out.println("⚠️ Entrada no válida. Por favor, ingrese un número.");
	            scanner.next();
	        }
	    }

	    return option;
	}
	
	public static int insertOptionHistory () {
	    int option = -1;

	    while (option < 1 || option > 3) {
	        System.out.println("🔢 Ingrese una de las opciones disponibles (1-3):");

	        if (scanner.hasNextInt()) {
	            option = scanner.nextInt();
	            if (option < 1 || option > 3) {
	                System.out.println("❌ Opción incorrecta, intente nuevamente.");
	            }
	        } else {
	            System.out.println("⚠️ Entrada no válida. Por favor, ingrese un número.");
	            scanner.next();
	        }
	    }

	    return option;
	}
	
	public static String insertFileName() {
	    String fileName = "";

	    while (fileName.isEmpty()) {
	        System.out.println("💬 Ingrese el nombre del archivo (sin extensión):");
	        fileName = scanner.nextLine().trim();

	        if (fileName.isEmpty()) {
	            System.out.println("⚠️ El nombre del archivo no puede estar vacío. Intente nuevamente.");
	        }
	    }
	    return fileName + ".txt"; 
	}
}
