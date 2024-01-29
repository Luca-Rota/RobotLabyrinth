
public class Grid {
	
	private String[][] grid;
	
	public Grid(int size) {
		grid = new String[size][size];
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				grid[i][j] = "WHITE";}
	
	/**
	 * Metodo che controlla il colore della cella nella griglia in cui il robot si vuole spostare. 
	 * @param row
	 * @param col
	 * @param _size
	 * @param dir
	 * @return
	 */
	public String Control(int row, int col, int _size, String dir) {
		String color = "WHITE";
		switch (dir) {
		case "SOUTH": if (row == _size-1) color = "BLACK"; 
					  else color = ControlColor(row+1, col);
					  break;
		case "EAST":  if (col == _size-1) color = "BLACK";
					  else color = ControlColor(row, col+1); 
					  break;
		case "WEST":  if (col == 0) color = "BLACK";
					  else color = ControlColor(row, col-1); 
					  break;
		case "NORTH": if (row == 0) color = "BLACK";
					  else color = ControlColor(row-1, col); 
					  break;}
		return color;}
	
	public String ControlColor(int row, int col) {
		if (grid[row][col] == "GREY")	return "GREY";
		if (grid[row][col] == "BLACK") return "BLACK";
		if (grid[row][col] == "BROWN") return "BROWN";
		else return "WHITE";}
	
	public void ColorToGrey(int row, int col){
		grid[row][col] = "GREY";}
	
	public void ColorToBlack(int row, int col, String dir) {
		switch (dir) {
			case "SOUTH": grid[row-1][col] = "BLACK"; break;
			case "EAST": grid[row][col-1] = "BLACK"; break;
			case "WEST": grid[row][col+1] = "BLACK"; break;
			case "NORTH": grid[row+1][col] = "BLACK"; break;}}
	
	public void CleanGrid(int _size, int row, int col) {
		for(int i = 0; i < _size; i++)
			for(int j=0; j < _size; j++)
				if (grid[i][j] == "GREY" || grid[i][j] == "BLACK")
					grid[i][j] = "WHITE";
		grid[row][col] = "BROWN";}
	
	public boolean SearchColor(int row, int col, String color) {
		if (grid[row][col] == color)
			return true;
		else return false;}

}
	
	
	
	
	
	
	
	
