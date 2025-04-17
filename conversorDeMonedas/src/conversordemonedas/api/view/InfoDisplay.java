package conversordemonedas.api.view;

import java.io.File;

import conversordemonedas.api.connection.Endpoint;
import conversordemonedas.api.data.*;

public class InfoDisplay {
	public static void welcomeMessage() {
		String mensaje = """
				================================================================
				🌐  BIENVENIDOS AL CONVERSOR DE DIVISAS EN TIEMPO REAL
				    ¡MÁS CONFIABLE DEL MERCADO!
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


				🔄Cargando, por favor espere...
				""";
		System.out.println(mensaje);
	}

	public static void availableOption() {
	    System.out.println("""
	        📋 Opciones disponibles:

	        1️⃣  Consultar el historial de operaciones.
	        2️⃣  Vaciar el historial actual de operaciones.
	        3️⃣  Comenzar a operar.
	        4️⃣  Finalizar.
	        """);
	}

	public static void availableOptionHistory() {
	    System.out.println("""
	        📋 Opciones disponibles:

	        1️⃣ Eliminar todo el historial de operaciones.
	        2️⃣ Eliminar un archivo específico del historial.
	        3️⃣ Salir.
	        """);
	}

	public static void availableEndpoints() {
	    String endpoints = "📚 Recursos disponibles:\n\n";

	    for (int pos = 0; pos < Endpoint.values().length; pos++) {
	        endpoints += (pos + 1) + " 👉 " + Endpoint.values()[pos].getDescription() + "\n";
	    }

	    System.out.println(endpoints);
	}

	public static void availableResources() {
	    System.out.println("""
	        📌 Opciones disponibles para el recurso seleccionado:

	        1️⃣  Consultar la tasa de cambio actual entre dos monedas.
	        2️⃣  Realizar una conversión monetaria entre dos monedas ingresando un monto fijo.
	        """);
	}

	public static void availableData(JsonData json) {
		System.out.println("""
				================================================================================================
				✅ Los recursos solicitados han sido obtenidos con éxito. ¡Que los disfrute!

				ℹ️ Para obtener más información sobre el conversor de monedas, puede consultar la documentación
				y los términos de uso de la aplicación en los siguientes enlaces:
				================================================================================================
				""");

	    System.out.println("📄 Documentación: " + json.documentation());
	    System.out.println("📜 Términos de uso: " + json.termsOfUse());
	    System.out.println("📅 Fecha de última actualización: " + json.timeLastUpdateUtc());
	    System.out.println("🔄 Fecha de próxima actualización: " + json.timeNextUpdateUtc());
	}

	public static String availableCoins(Coins coins, int limit) {
	    String result = "💱Monedas disponibles:\n";

	    for (int pos = 0; pos < limit; pos++) {
	        result += "\n🧾 Nombre: " + coins.getNameCoins().get(pos);
	        result += "\n🆔 Codigo: " + coins.getCodeCoins().get(pos);
	    }

	    return result;
	}

	public static String showConversionRates(Convertion conversion, int limit) {
	    int pos = 0;
	    String baseCode = conversion.getBaseCode();
	    String result = "1 " + baseCode + " equivale a:\n";

	    for (String key : conversion.getConversionRates().keySet()) {
	        if (pos >= limit) break;
	        Double value = conversion.getConversionRates().get(key);
	        String formattedValue = String.format("%.2f", value);
	        result += "\t🟰 " + formattedValue + " ➡️ " + key + "\n";
	        pos++;
	    }

	    return result;
	}

	public static String showConversionRate(Convertion conversion) {
	    String baseCode = conversion.getBaseCode();
	    String formattedRate = String.format("%.2f", conversion.getConversionRate());
	    return "\nResultados:\n1 " + baseCode + " 🟰 " +
	            formattedRate + " ➡️ " + conversion.getTargetCode();
	}

	public static String showConversionDetails(Convertion convertion) {
	    String formattedRate = String.format("%.2f", convertion.getConversionRate());
	    String formattedResult = String.format("%.2f", convertion.getConversionResult());

	    return "\n🔄Resultados de la conversion: \n" +
	           "💱 Moneda base: " + convertion.getBaseCode() + "\n" +
	           "🎯 Moneda destino: " + convertion.getTargetCode() + "\n" +
	           "💰 Tasa de cambio: " + formattedRate + "\n" +
	           "🔁 Resultado de la conversión: " + formattedResult;
	}

	public static String showQuotaDetails(Quota quota) {
	    return "\nℹ️Datos del usuario:\n" +
	           "📦 Plan actual: " + quota.getPlanQuota() + "\n" +
	           "📊 Cuota total disponible: " + quota.getRequestRemaining() + "\n" +
	           "✅ Solicitudes realizadas: " + (quota.getPlanQuota() - quota.getRequestRemaining()) + "\n" +
	           "🗓️ Día de reinicio mensual: " + quota.getRefreshDayOfMonth();
	}

	public static void showHistory(File[] files) {
	    if (files != null && files.length > 0) {
	        System.out.println("📜 Historial de operaciones:");
	        for (File file : files) {
	            if (file.isFile()) {
	                System.out.println(" - " + file.getName());
	            }
	        }
	    }
	}
	
	public static void goodbyeMessage() {
	    String message = """
	        ================================================================
	        👋 ¡Gracias por utilizar el Conversor de Divisas!
	        Esperamos que haya sido de ayuda en sus operaciones financieras.
	        
	        📆 Recuerde que puede volver en cualquier momento.
	        📜 Puede consultar el historial de operaciones en la carpeta:
	        👉 users/history

	        ¡Hasta la próxima! 🌍💱
	        ================================================================
	        """;
	    System.out.println(message);
	}
}
