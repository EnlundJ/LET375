import java.util.Map;
import java.util.TreeMap;

public class FlakeDB {
	public Map<String,Flake> theFlakes = new TreeMap<String,Flake>();
	
	public FlakeDB() {
		theFlakes.put("Convex Koch",new ConvexKoch());
		theFlakes.put("Mandelbrot PS",new MandelbrotPScurve());
		theFlakes.put("Penta",new Penta());
		theFlakes.put("Square",new Square());
		// Add your favourite flake objects here ...
		
	}
}
