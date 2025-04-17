package conversordemonedas.api.exceptions; 

public class ConnectionError {

    public static void unknownHostError(String message) {
        System.out.println("❌ Error desconocido de host: " + message);
    }

    public static void connectionError(String message) {
        System.out.println("❌ Error de conexión: " + message);
    }

    public static void httpTimeoutError(String message) {
        System.out.println("⏳ Tiempo de espera agotado en la solicitud HTTP: " + message);
    }

    public static void ioError(String message) {
        System.out.println("❌ Error de entrada/salida: " + message);
    }

    public static void interruptionError(String message) {
        System.out.println("⚠️ La operación fue interrumpida: " + message);
    }

    public static void generalError(String message) {
        System.out.println("⚠️ Error inesperado: " + message);
    }

    public static void getStatusCodeMessage(int statusCode) {
        if (statusCode < 200) {
            System.out.println("ℹ️ Solicitud aceptada.\n");
        } else if (statusCode < 300) {
            System.out.println("✅ Solicitud exitosa. La acción solicitada se completó con éxito.\n");
        } else if (statusCode < 400) {
            System.out.println("⚠️ Solicitud rechazada. Se requieren acciones adicionales para completar la solicitud.\n");
        } else if (statusCode < 500) {
            System.out.println("🚧 Servidor temporalmente no disponible.\n");
        } else {
            System.out.println("❌ El servidor encontró un error o no puede procesar la solicitud.\n");
        }
    }
}
