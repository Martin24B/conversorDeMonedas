package conversordemonedas.api.userData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import conversordemonedas.api.exceptions.FileError;

public class History {
	private static final File FOLDER_PATH = new File("users/history");
	private static final File FILE_NAME = new File(FOLDER_PATH, LocalDate.now().toString() + ".txt");

	private static boolean fileExists() {
		return FILE_NAME.exists();
	}
	
	private static boolean folderExist() {
		return FOLDER_PATH.exists();
	}

	public static void createHistory() {

		if (!folderExist()) 
			FOLDER_PATH.mkdirs();
	
		if (!fileExists()) {
			try {
				FILE_NAME.createNewFile();
			} catch (IOException e) {
				FileError.errorCreatingFile(); 
			}
		}
	}
	
	public static File[] returnHistory() {
	    if (folderExist() && FOLDER_PATH.isDirectory())
	        return FOLDER_PATH.listFiles();

	    FileError.folderNotFound(); 
	    return new File[0];
	}


	public static void deleteHistory() {
		if (folderExist() && FOLDER_PATH.isDirectory()) {
			File[] files = FOLDER_PATH.listFiles();

			if (files != null && files.length > 0) {
				for (File file : files)
					file.delete();

				FileError.historyCleared(); 
			} else {
				FileError.noFilesToDelete(); 
			}
		} else {
			FileError.folderNotFound(); 
		}
	}

	public static void deleteHistory(String nameFile) {
	    File file = new File(FOLDER_PATH, nameFile);

	    if (file.exists() && file.isFile()) {
	        file.delete();
	        FileError.fileDeleted(nameFile); 
	    } else {
	        FileError.fileNotFound(nameFile);
	    }
	}

	
	public static void appendToHistory(String data) {
	    if (folderExist() && fileExists()) {
	        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {	 //el archivo no se sobrescribe     
	            LocalDateTime now = LocalDateTime.now();
	            String currentTime = now.toString();
	            writer.write("\n\n[" + currentTime + "] " + data + "\n");
	        } catch (IOException e) {
	            FileError.errorCreatingFile(); 
	        }
	    } else {
	        FileError.folderNotFound(); 
	    }
	}
}
