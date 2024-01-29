import java.util.*;

public class Path {
	
	private ArrayList<GridWorld.Coordinate> path;
	private ArrayList<GridWorld.Coordinate> colorPath;
	private ArrayList<GridWorld.Coordinate>	finalPath;
	
	public Path() {
		path = new ArrayList<GridWorld.Coordinate>();
		colorPath = new ArrayList<GridWorld.Coordinate>();
		finalPath = new ArrayList<GridWorld.Coordinate>();}
	
	public void AddSteps(GridWorld.Coordinate coord) {
			path.add(coord);}
	
	public void CleanPath(Grid grid) {
		for (int i = 0; i < path.size()-1; i++)
			if (!grid.SearchColor(path.get(i).row, path.get(i).col, "BROWN"))
				path.remove(i);}
	
	public void Results() {
		ConsecutivePath();
		NaiveSteps();
		for (int i = 0; i < finalPath.size(); i++) 
			System.out.print("(" +(finalPath.get(i)).row+ ", " +(finalPath.get(i)).col+ ") ");}
	
	/**
	 * Metodo che trova tutti i passi consecutivi effettuati dal robot.
	 */
	public void ConsecutivePath() {
		for (int j = 0; j < path.size()-1; j++)
			if (path.get(j).row == 0 && path.get(j).col == 0) 
				colorPath.add(path.get(j));
		boolean truePath = false;
		while (!ConsecutiveSteps(colorPath.get(colorPath.size()-1), path.get(path.size()-1))) {
			truePath = false;
			for (int j = 0; j < path.size()-1; j++) 
				if(ConsecutiveSteps(colorPath.get(colorPath.size()-1), path.get(j))){
					colorPath.add(path.get(j));
					path.remove(j);
					truePath = true;
					break;}
			if (!truePath) 
				colorPath.remove(colorPath.size()-1);}
		colorPath.add(path.get(path.size()-1));}
	
	/**
	 * Metodo che elimina i passi superflui, tra quelli consecutivi salvati in precedenza.
	 */
	public void NaiveSteps() {
		finalPath.add(colorPath.get(0));
		int i = 0;		
		while(i != colorPath.size()-3) {
			GridWorld.Coordinate step = finalPath.get(finalPath.size()-1);
			GridWorld.Coordinate trueStep = colorPath.get(0);
			for (int j = i+1; j < colorPath.size()-2; j++) {
				GridWorld.Coordinate nextStep = colorPath.get(j);
				if (ConsecutiveSteps(step, nextStep)) {
					trueStep = colorPath.get(j);
					i = j;}}
				finalPath.add(trueStep);}
		finalPath.add(colorPath.get(colorPath.size()-2));
		finalPath.add(colorPath.get(colorPath.size()-1));}
	
	/**
	 * Metodo che ritorna true se due determinati passi sono consecutivi.
	 * @param step
	 * @param nextStep
	 * @return
	 */
	public boolean ConsecutiveSteps(GridWorld.Coordinate step, GridWorld.Coordinate nextStep) {
		int verticalMove = step.row - nextStep.row;
		int horizontalMove = step.col - nextStep.col;
		if ((Math.abs(verticalMove) == 0 && Math.abs(horizontalMove) == 1) ||(Math.abs(horizontalMove) == 0 && Math.abs(verticalMove) == 1))
			return true;
		else return false;}
	
}
