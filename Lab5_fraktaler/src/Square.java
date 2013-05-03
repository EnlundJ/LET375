public class Square extends Flake {
	
	public void draw(Turtle turtle,int n,double size) {
		this.turtle = turtle;
		turtle.turnTo(0.0);
		turtle.turn(-90.0);
		drawSide(n,size);
		turtle.turn(90.0);
		drawSide(n,size);
		turtle.turn(90.0);
		drawSide(n,size);
		turtle.turn(90.0);
		drawSide(n,size);
	}

	private void drawSide(int n,double size) {
		if ( n <= 0 )
			turtle.walk(size);
		else {
			double l = size/4.0;
			drawSide(n-1,l);
			turtle.turn(-90.0);
			drawSide(n-1,l);
			turtle.turn(90.0);
			drawSide(n-1,l);
			turtle.turn(90.0);
			drawSide(n-1,l);
			drawSide(n-1,l);
			turtle.turn(-90.0);
			drawSide(n-1,l);
			turtle.turn(-90.0);
			drawSide(n-1,l);
			turtle.turn(90.0);
			drawSide(n-1,l);
		}
	}
}
