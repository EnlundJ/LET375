import java.util.Iterator;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Map;
import java.util.LinkedList;


public class ExtendedGraph extends Graph
{
	public List<Integer> getPath(int destName)
	{
		return getPath(vertexMap.get(destName));
	}
	
	private List<Integer> getPath(Vertex dest)
	{
        if( dest.prev != null )
        {
        	List<Integer> list = getPath(dest.prev);
        	list.add(dest.name);
            return list;
        }

        List<Integer> list = new ArrayList<Integer>();
        list.add(dest.name);
        return list;
	}

}
