/**
 * @author Joe Corbett
 * Course: CSc 210 Summer 2019
 * 
 * ObstacleCourse: A type that represents an obstacle course from which to
 * escape. This does not find the shorted path. Because of this, we must always
 * assume there is only one exit.
 */
public class ObstacleCourse {

	// Instance variables
	protected char[][] course;
	private int sRow;
	private int sCol;
	private int foundRow;
	private int foundCol;
	private int maxRow;
	private int maxCol;

	// Constants (or you could use 'O' and '.' directly)
	private static final char PART_OF_PATH = 'O';
	private static final char TRIED = '.';

	/**
	 * Initializes the 2d char array course.
	 */
	public ObstacleCourse(int sRow, int sCol, char[][] course) {
		this.sRow = sRow;
		this.sCol = sCol;
		this.course = course;
		this.maxRow = course.length;
		this.maxCol = course[0].length;

		// The default values in case there is no exit.
		foundRow = -1;
		foundCol = -1;
	}

	// Returns the start column in the array
	public int getStartColumn() {
		return sCol;
	}

	// Returns the starting row in the array
	public int getStartRow() {
		return sRow;
	}

	// Return the column of the solution
	public int getExitColumn() {
		return foundCol;
	}

	// Return the row of the solution
	public int getExitRow() {
		return foundRow;
	}

	// Returns a string representation of the array
	public String toString() {
		String result = "";
		for (int i = 0; i < course.length; i++) {
			for (int j = 0; j < course[0].length; j++) {
				result += course[i][j];
			}
			result += "\n";
		}
		return result;
	}

	// This method is called by the user to begin the search for the one exit.
	public void findTheExit() {
		findExit(sRow, sCol);
	}

	/**
	* Finds the exit from the 2-D array. This method also must record the row and
	* col where the exit was found
	*/
	private boolean findExit(int row, int col) {
		boolean escaped = false;
		if (course[row][col] == ' ') {
			course[row][col] = TRIED;
			if (row == maxRow - 1 || col == maxCol - 1 || row == 0
					|| col == 0) {
				escaped = true;
				this.foundRow = row;
				this.foundCol = col;
			} else {
				escaped = findExit(row + 1, col);
				if (!escaped) {
					escaped = findExit(row, col + 1);
				}
				if (!escaped) {
					escaped = findExit(row - 1, col);
				}
				if (!escaped) {
					escaped = findExit(row, col - 1);
				}
			}
			if (escaped) {
				course[row][col] = PART_OF_PATH;
			}
		}
		return escaped;
	}

}