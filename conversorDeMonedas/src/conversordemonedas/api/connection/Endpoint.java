package conversordemonedas.api.connection;

public enum Endpoint {
	LATEST("Conocer las tasas de cambio actual de una moneda suministrada en relacion a todas las monedas actualmente activas en el mercado."),
	CODES("Conocer la lista completa de todas las monedas que estan activas actualmente en el mercado."),
	PAIR("Conocer la tasa de cambio actual de dos monedas suministradas"),
	QUOTA("Conocer la cuota actual de solicitudes disponibles para el usuario."); 
	
	private final String description;

	private Endpoint (String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.getName() + " ➡️ " + this.description;
	}
	
	public String getName () {
		return this.name().toLowerCase();
	}
	
	public static boolean endpointValid (int opcion) {
		return (opcion > 0 && opcion < 5);
	}
	
	public static boolean endpointIsPair (int c) {
		return Endpoint.values()[c].equals(PAIR);
	}

	public static boolean endpointIsLatest(int c) {
		return Endpoint.values()[c].equals(LATEST);
	}
	
	public static boolean endpointIsCodes (int c) {
		return Endpoint.values()[c].equals(CODES);
	}
	
	public static boolean endpointIsQuota (int c) {
		return Endpoint.values()[c].equals(QUOTA);
	}
}
