package conversordemonedas.api.excepciones;

public class ErrorDeConexion {
    public static void unknownHostError(String message) {
        System.out.println("Error de host desconocido: " + message);
    }

    public static void connectionError(String message) {
        System.out.println("Error de conexión: " + message);
    }

    public static void httpTimeoutError(String message) {
        System.out.println("Tiempo de espera agotado para la solicitud HTTP: " + message);
    }

    public static void ioError(String message) {
        System.out.println("Error de entrada/salida: " + message);
    }

    public static void interruptionError(String message) {
        System.out.println("La operación fue interrumpida: " + message);
    }

    public static void generalError(String message) {
        System.out.println("Error inesperado: " + message);
    }
    
    public static void getStatusCodeMessage(int statusCode) {
        if (statusCode < 200) {
            System.out.println("Solicitud aceptada.\n");
        } else if (statusCode < 300) {
            System.out.println("Solicitud exitosa, la acción solicitada fue procesada correctamente.\n");
        } else if (statusCode < 400) {
            System.out.println("Solicitud rechazada, se deben realizar acciones adicionales para completar la solicitud.\n");
        } else if (statusCode < 500) {
            System.out.println("Servidor temporalmente fuera de servicio.\n");
        } else {
            System.out.println("El servidor ha encontrado un error o no puede procesar la solicitud...\n");
        }
    }
}
