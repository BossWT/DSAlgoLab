import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class DataManager {
	private String filePath;
	Hashtable<String, Integer> stateNum = new Hashtable<>();
	Hashtable<String, Integer> countryNum = new Hashtable<>();
	Hashtable<String, Integer> dateNum = new Hashtable<>();
	Hashtable<String, Hashtable<String, Integer>> stateDateNum = new Hashtable<>();
	Hashtable<String, Hashtable<String, Integer>> countryDateNum = new Hashtable<>();
	int totalCases = 0;

	public DataManager(String filePath) {
		this.setFilePath(filePath);
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
			String row = "";
			String[] column = csvReader.readLine().split(",");
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");
				String state = data[0];
				String country = data[1];
				int lastDayCases = 0;
				if (!countryNum.containsKey(country)) {
					countryDateNum.put(country, new Hashtable<>());
					for (int i = 4; i < data.length; i++) {
						String date = column[i];
						countryDateNum.get(country).put(date, 0);
					}
				}
				if (state.length() > 0)
					stateDateNum.put(state, new Hashtable<>());

				for (int i = 4; i < data.length; i++) {
					int dateCase = Integer.parseInt(data[i]);
					String date = column[i];
					lastDayCases = Integer.parseInt(data[i]);
					if (dateNum.containsKey(date)) {
						int currentDateNum = dateNum.get(date);
						dateNum.put(date, currentDateNum + dateCase);
					} else
						dateNum.put(date, dateCase);
					if (state.length() > 0)
						stateDateNum.get(state).put(date, dateCase);
					int currentCountryDateNum = countryDateNum.get(country).get(date);
					countryDateNum.get(country).put(date, currentCountryDateNum + dateCase);
				}
				totalCases += lastDayCases;
				if (state.length() > 0)
					stateNum.put(state, lastDayCases);
				if (countryNum.containsKey(country)) {
					int currentCountryNum = countryNum.get(country);
					countryNum.put(country, currentCountryNum + lastDayCases);
				} else
					countryNum.put(country, lastDayCases);
			}
			csvReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			filePath = "";
		} catch (IOException e) {
			System.out.println("IOException");

		}
	}

	public int getData(String state, String country, String date) {
		if (state.length() == 0 && country.length() == 0 && date.length() == 0)
			return totalCases;
		if (state.length() > 0) {
			if (date.length() > 0)
				return stateDateNum.get(state).get(date);
			else
				return stateNum.get(state);
		} else if (country.length() > 0) {
			if (date.length() > 0)
				return countryDateNum.get(country).get(date);
			else
				return countryNum.get(country);
		} else
			return dateNum.get(date);
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
