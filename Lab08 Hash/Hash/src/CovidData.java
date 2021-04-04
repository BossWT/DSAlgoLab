import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

public class CovidData {
	Hashtable<String, Integer> data;

	public CovidData(String filename) {
		data = new Hashtable<String, Integer>();
		try {
			Scanner in = new Scanner(new File(filename));
			while (in.hasNextLine()) {
				String line = in.nextLine();
				String[] arr = new String[2];
				arr = line.split(",");
				data.put(arr[0], Integer.parseInt(arr[1]));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public int find(String d) {
		if (data.containsKey(d))
			return data.get(d);
		return -1;
	}
}
