package conversordemonedas.api.user;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import conversordemonedas.api.exceptions.FileError;

public class History {
	private static final String FOLDER_PATH = "history/users";
	private static final String FILE_NAME = LocalDate.now().toString() + ".txt";

	private static boolean fileExists(File file) {
		return file.exists();
	}
	
	private static boolean folderExist(File folderPath) {
		return folderPath.exists();
	}

	public static void createHistory() {
		File folder = new File(FOLDER_PATH);
		File file = new File(FOLDER_PATH, FILE_NAME);

		if (!folderExist(folder)) 
			folder.mkdir();
	
		if (!fileExists(file)) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				FileError.errorCreatingFile(); 
			}
		}
	}
	
	public static File[] returnHistory() {
		File folder = new File(FOLDER_PATH);

		if (folderExist(folder) && folder.isDirectory())
			return folder.listFiles();
			
		FileError.folderNotFound(); 
		return null;
	}

	public static void deleteHistory() {
		File folder = new File(FOLDER_PATH);

		if (folderExist(folder) && folder.isDirectory()) {
			File[] files = folder.listFiles();

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
	    File folder = new File(FOLDER_PATH);
	    File file = new File(FOLDER_PATH, FILE_NAME);

	    if (folderExist(folder) && fileExists(file)) {
	        try (FileWriter writer = new FileWriter(file, true)) {	      
	            LocalDateTime now = LocalDateTime.now();
	            String currentTime = now.toString();
	            writer.write("[" + currentTime + "] " + data + "\n");

	        } catch (IOException e) {
	            FileError.errorCreatingFile(); 
	        }
	    } else {
	        FileError.folderNotFound(); 
	    }
	}

}
