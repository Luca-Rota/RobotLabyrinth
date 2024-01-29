
public class Robot {
	
	private GridWorld robot;
	private Grid grid;
	private Path path;
	private int _size;
	private double _density;
	private long _seed;
	private GridWorld.Coordinate coord;
	
	public Robot(int size, double density, long seed) {
		robot = new GridWorld(size, density, seed);
		grid = new Grid(size);
		path = new Path();
		_size = size;
		_density = density;
		_seed = seed;}
	
	/**
	 * Metodo principale, che finché il robot non raggiunge l'obiettivo, lo fa muovere, se possibile verso una cella bianca,
	 * altrimenti verso una cella grigia. Se ciò non è possibile il robot si è bloccato e quindi viene fatto ripartire.  
	 */
	public void SearchPath() {
		Step("NULL", "WHITE");
		while (!robot.targetReached()) { 
			coord = robot.getCurrentCell();
			if (Move("WHITE"))
				continue;
			else if (Move("GREY"))
				continue;
			else Restart();} 
		path.Results();}
	
	/**
	 * Metodo che ritorna true se il robot si è mosso verso una cella bianca, se possibile, o grigia.
	 * @param color
	 * @return
	 */
	public boolean Move(String color) {
		if ((grid.Control(coord.row, coord.col, _size, "SOUTH") == color) && robot.moveToAdjacentCell(GridWorld.Direction.SOUTH)) { 
			Step("SOUTH", color); return true;}
		else if ((grid.Control(coord.row, coord.col, _size, "EAST") == color) && robot.moveToAdjacentCell(GridWorld.Direction.EAST)) {
			Step("EAST", color); return true;}
		else if ((grid.Control(coord.row, coord.col, _size, "WEST") == color) && robot.moveToAdjacentCell(GridWorld.Direction.WEST)) {
			Step("WEST", color); return true;}
		else if((grid.Control(coord.row, coord.col, _size, "NORTH") == color) && robot.moveToAdjacentCell(GridWorld.Direction.NORTH)) {
			Step("NORTH", color); return true;}
		else return false;}
	
	/**
	 * Metodo che salva la posizione del robot in un ArrayList e cambia il colore della cella corrente in grigio, 
	 * se il robot si era spostato verso una cella bianca, o cambia il colore della cella di provenienza in nero, 
	 * se il robot si era spostato verso una cella grigia.
	 * @param dir
	 * @param color
	 */
	public void Step(String dir, String color) {
		coord = robot.getCurrentCell();
		path.AddSteps(coord);
		if (color == "WHITE")
			grid.ColorToGrey(coord.row, coord.col);
		else 
			grid.ColorToBlack(coord.row, coord.col, dir);}
	
	/**
	 * Metodo che permette al robot di ripartire, eliminando dalla griglia e dall'ArrayList tutte le celle percorse, 
	 * escluse quelle in cui il robot si è bloccato, contrassegnate in BROWN nella griglia.
	 */
	public void Restart() {
		robot = new GridWorld(_size, _density, _seed);
		grid.CleanGrid(_size, coord.row, coord.col);
		path.CleanPath(grid);
		Step("NULL", "WHITE");}

}
