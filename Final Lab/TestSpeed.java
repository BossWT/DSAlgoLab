
public class TestSpeed {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataManager dm = new DataManager("/Users/boss_wt/Documents/Programming/CU/DSAlgoLab/Final Lab/covid19.csv");
		// Load everything from the file. All data should be ready at this point.
		long startTime = System.nanoTime();
		for (int i = 0; i < 100; i++) { // i will be changed to 100000 later.
			dm.getData("", "France", "3/10/20");
			dm.getData("", "Algeria", "3/21/20");
		}
		// System.out.println(dm.getData("", "United Kingdom", ""));
		System.out.println((System.nanoTime() - startTime) / (1));
	}

}
