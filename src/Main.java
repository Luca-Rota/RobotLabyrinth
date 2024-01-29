
public class Main {
	
	public static void main(String[] args) {
		if (args.length != 3)
			System.exit(0);
		int size = Integer.parseInt(args[0]);
		double density = Double.parseDouble(args[1]);
		long seed = Long.parseLong(args[2]);
		Robot robot = new Robot(size, density, seed);
		robot.SearchPath();}
	
}
