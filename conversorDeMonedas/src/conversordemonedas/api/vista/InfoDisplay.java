package conversordemonedas.api.vista;

import conversordemonedas.api.conexion.Endpoint;
import conversordemonedas.api.datos.*;

public class InfoDisplay {
	public static void welcomeMessage() {
		String mensaje = """
				================================================================
				🌐  BIENVENIDOS AL CONVERSOR DE DIVISAS EN TIEMPO REAL
				    ¡EL MÁS CONFIABLE DEL MERCADO!
				================================================================

				Este conversor de divisas le permitirá al usuario consultar las
				tasas de cambio y las equivalencias monetarias en tiempo real
				de hasta 161 monedas activas a nivel mundial, cubriendo el 99%
				de los estados y territorios reconocidos por la ONU.

				📌 Para comenzar a operar, el usuario puede elegir una de las
				funcionalidades disponibles en la aplicación.

				---------------------------------------------------------------
				🔍 ACLARACIONES IMPORTANTES:

				✔️ Si es su primera vez utilizando la app, se recomienda elegir
				   el recurso “codes” o consultar el archivo Readme.md para
				   estar al tanto de las últimas novedades.

				✔️ Para realizar varias operaciones, utilice el recurso “quota”
				   y así mantenerse informado sobre el límite de solicitudes
				   disponibles y evitar bloqueos inesperados.

				---------------------------------------------------------------
				🙏 MUCHAS GRACIAS POR SU VISITA — ¡QUE LO DISFRUTE!
				================================================================
				""";

		System.out.println(mensaje);
	}

	public static void availableEndpoints() {
	    String endpoints = "📚 Recursos disponibles:\n\n";

	    for (int pos = 0; pos < Endpoint.values().length; pos++) {
	        endpoints += (pos + 1) + " 👉 " + Endpoint.values()[pos].getDescription() + "\n";
	    }

	    System.out.println(endpoints);
	}

	public static String availableResources() {
	    return """
	        📌 Opciones disponibles para el recurso seleccionado:

	        1️⃣  Consultar la tasa de cambio actual entre dos monedas.
	        2️⃣  Realizar una conversión monetaria ingresando un monto entre dos monedas.
	        """;
	}

	public static void availableData(JsonData jsonData) {
	    System.out.println("""
	        ✅ Los recursos solicitados han sido obtenidos con éxito. ¡Que los disfrute!

	        ℹ️ Para obtener más información sobre el conversor de monedas, puede consultar la documentación
	        y los términos de uso de la aplicación en los siguientes enlaces:
	        """);

	    System.out.println("📄 Documentación: " + jsonData.documentation());
	    System.out.println("📜 Términos de uso: " + jsonData.terms_of_use());
	    System.out.println("📅 Fecha de última actualización: " + jsonData.time_last_update_utc());
	    System.out.println("🔄 Fecha de próxima actualización: " + jsonData.time_last_update_utc());
	}

	public static void availableCoins(String name, String code) {
	    System.out.println("💱 Available Currency:");
	    System.out.println("🧾 Name: " + name);
	    System.out.println("🆔 Code: " + code + "\n");
	}

	public static void showConvertionRate(String key, Double value, String baseCode) {
	    System.out.println(baseCode + " 🟰 " + key + " ➡️ " + value);
	}

	public static void showConvertionDetails(Convertion convertion) {
	    System.out.println("💱 Moneda base: " + convertion.getBaseCode());
	    System.out.println("🎯 Moneda destino: " + convertion.getTargetCode());
	    System.out.println("💰 Monto ingresado: " + convertion.getConvertionData());
	    System.out.println("🔁 Resultado de la conversión: " + convertion.getConvertionResult());
	}
	
	public static void showQuotaDetails(Quota quota) {
	    System.out.println("📦 Plan actual: " + quota.getPlanQuota());
	    System.out.println("📊 Cuota total disponible: " + quota.getQuotaData());
	    System.out.println("✅ Solicitudes restantes: " + quota.getRequestRemaining());
	    System.out.println("🗓️ Día de reinicio mensual: " + quota.getRefreshDayOfMonth());
	}
}
