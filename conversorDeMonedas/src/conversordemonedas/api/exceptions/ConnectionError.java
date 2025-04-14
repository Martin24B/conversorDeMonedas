package conversordemonedas.api.exceptions;

public class ConnectionError {

    public static void unknownHostError(String message) {
        System.out.println("❌ Unknown host error: " + message);
    }

    public static void connectionError(String message) {
        System.out.println("❌ Connection error: " + message);
    }

    public static void httpTimeoutError(String message) {
        System.out.println("⏳ HTTP request timeout: " + message);
    }

    public static void ioError(String message) {
        System.out.println("❌ Input/output error: " + message);
    }

    public static void interruptionError(String message) {
        System.out.println("⚠️ The operation was interrupted: " + message);
    }

    public static void generalError(String message) {
        System.out.println("⚠️ Unexpected error: " + message);
    }

    public static void getStatusCodeMessage(int statusCode) {
        if (statusCode < 200) {
            System.out.println("ℹ️ Request accepted.\n");
        } else if (statusCode < 300) {
            System.out.println("✅ Successful request. The requested action was completed successfully.\n");
        } else if (statusCode < 400) {
            System.out.println("⚠️ Request rejected. Additional actions are required to complete the request.\n");
        } else if (statusCode < 500) {
            System.out.println("🚧 Server temporarily unavailable.\n");
        } else {
            System.out.println("❌ The server encountered an error or cannot process the request.\n");
        }
    }
}
