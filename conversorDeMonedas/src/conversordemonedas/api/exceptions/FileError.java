package conversordemonedas.api.exceptions;

public class FileError {

    public static void errorCreatingFile() {
        System.out.println("❌ Ocurrió un error al crear el archivo de historial.");
    }

    public static void folderNotFound() {
        System.out.println("❌ Carpeta de historial no encontrada.");
    }

    public static void historyCleared() {
        System.out.println("✅ Todos los archivos del historial han sido eliminados correctamente.");
    }

    public static void noFilesToDelete() {
        System.out.println("⚠️ No hay archivos en el historial para eliminar.");
    }

    public static void fileDeleted(String nameFile) {
        System.out.println("✅ El archivo '" + nameFile + "' fue eliminado correctamente.");
    }

    public static void fileNotFound(String nameFile) {
        System.out.println("❌ El archivo '" + nameFile + "' no se encontró en la carpeta de historial.");
    }
}
