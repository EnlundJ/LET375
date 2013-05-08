
public class Maze extends Board {
	
	private DisjointSets set;
  		
    public Maze( int rows, int cols )
	{
    	super(rows,cols);
    	set = new DisjointSets(rows*cols);
    }
    
    public void create()
    {
    	// Draw the initial board
    	setChanged();
    	notifyObservers();

    	int knockedWalls = 0;
    	while(knockedWalls < (maxCell-1))
    	{
    		// Get a random cell
    		int cellId = (int)(Math.random()*maxCell);
    		Point first = new Point(getRow(cellId), getCol(cellId));
    		Point second = new Point(first.row, first.col);

    		// Select a random neighbor
    		Point.Direction direction;
    		switch((int)(Math.random()*4))
    		{
    		case 0: direction = Point.Direction.UP; break;
    		case 1: direction = Point.Direction.RIGHT; break;
    		case 2: direction = Point.Direction.LEFT; break;
    		case 3: direction = Point.Direction.DOWN; break;
    		default: direction = Point.Direction.ERROR;
    		}
    		second.move(direction);
    		
    		if(!isValid(second))
    		{
    			switch(direction)
    			{
    			case UP: direction = Point.Direction.DOWN; break;
    			case DOWN: direction = Point.Direction.UP; break;
    			case LEFT: direction = Point.Direction.RIGHT; break;
    			case RIGHT: direction = Point.Direction.LEFT; break;
    			default:
    				continue;
    			}
    			second.move(direction); // Move back
    			second.move(direction); // Move in opposite direction
    		}
    		
    		// Knock down the wall if the second cell is not a part of the same set
    		int firstRoot = set.find(getCellId(first));
    		int secondRoot = set.find(getCellId(second));
    		if(firstRoot != secondRoot)
    		{
    			setChanged();
    			notifyObservers(new Pair<Point, Point>(first, second));
    			set.union(firstRoot, secondRoot);
    			knockedWalls++;
    		}
    	}
    }
    
    public void search() {
//    	 Implement this method!
    }
    
//    ...
}
