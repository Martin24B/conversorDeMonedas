package conversordemonedas.api.datos;

import java.util.ArrayList;

import com.google.gson.Gson;

public class Coins {
	private Gson gson;
	private CoinsData coinsData;
	private static ArrayList<String> nameCoins = new ArrayList<>();
	private static ArrayList<String> codeCoins = new ArrayList<>();

	public Coins(String jsonData) {
		this.gson = new Gson();
		this.coinsData = this.gson.fromJson(jsonData, CoinsData.class);
		this.loadCoins();
	}

	private void loadCoins() {
		for (ArrayList<String> lista : this.coinsData.supported_codes()) {
			codeCoins.add(lista.getFirst());
			nameCoins.add(lista.getLast());
		}
	}

	public ArrayList <String> getNameCoins () {
		return nameCoins;
	}
	
	public ArrayList <String> getCodeCoins () {
		return codeCoins;
	}
	
	public static boolean isCodeCoinValid (String code) {
		return codeCoins.contains(code);
	}
	
	public static boolean isLimitValid (int limit) {
		return (limit > 0 && limit < codeCoins.size());
	}
}
