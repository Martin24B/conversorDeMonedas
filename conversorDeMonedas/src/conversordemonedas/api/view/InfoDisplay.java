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
	    System.out.println("📜 Términos de uso: " + json.terms_of_use());
	    System.out.println("📅 Fecha de última actualización: " + json.time_last_update_utc());
	    System.out.println("🔄 Fecha de próxima actualización: " + json.time_next_update_utc());
	}

	public static void availableCoins(Coins coins, int limit) {
		System.out.println ("💱Monedas disponibles:");
		for (int pos = 0; pos < limit; pos++) {
			System.out.println ("\n🧾 Nombre: " + coins.getNameCoins().get(pos));
			System.out.println ("🆔 Codigo: " + coins.getCodeCoins().get(pos));
		}
	}

	public static void showConversionRates (Convertion conversion, int limit) {
		int pos = 0;
		String baseCode = conversion.getBaseCode();
		System.out.println ("1 " + baseCode + " equivale a: ");
		
	    for (String key : conversion.getConversionRates().keySet()) {
			if (pos >= limit)
				break;

			Double value = conversion.getConversionRates().get(key);
			System.out.println ("\t🟰 " + value + " ➡️ " + key);

			pos++;
		}
	}
	
	public static void showConversionRate(Convertion conversion) {
		String baseCode = conversion.getBaseCode();
		System.out.println ("\nResultados:\n1 " + baseCode + " 🟰 " + conversion.getConversionRate() + " ➡️ " + conversion.getTargetCode());
	}

	public static void showConversionDetails(Convertion convertion) {
		System.out.println ("\n🔄Resultados de la conversion: ");
	    System.out.println ("💱 Moneda base: " + convertion.getBaseCode());
	    System.out.println ("🎯 Moneda destino: " + convertion.getTargetCode());
	    System.out.println ("💰 Tasa de cambio: " + convertion.getConversionRate());
	    System.out.println ("🔁 Resultado de la conversión: " + convertion.getConversionResult());
	}
	
	public static void showQuotaDetails(Quota quota) {
		System.out.println ("\nℹ️Datos del usuario:");
	    System.out.println ("📦 Plan actual: " + quota.getPlanQuota());
	    System.out.println ("📊 Cuota total disponible: " + quota.getRequestRemaining());
	    System.out.println ("✅ Solicitudes realizadas: " + (quota.getPlanQuota () - quota.getRequestRemaining()));
	    System.out.println ("🗓️ Día de reinicio mensual: " + quota.getRefreshDayOfMonth());
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
}
