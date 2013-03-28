import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import java.util.Comparator;

public class CollectionOps {

    // Put your code for print here ... 
	public static <T> void print(List<T> l)
	{
		System.out.print('[');
		Iterator<T> iter = l.iterator();
		
		if(iter.hasNext())
			System.out.print(iter.next().toString());
		
		while(iter.hasNext())
			System.out.print(", "+iter.next().toString());

		System.out.print("]\n");
	}
    
    // Put your code for reverse here ...
	public static <T> List<T> reverse(List<T> l)
	{
		for(int i=0; i < l.size()/2; i++)
		{
			int swapPos = l.size() - (i+1);
			T temp = l.get(i);
			l.set(i, l.get(swapPos));
			l.set(swapPos, temp);
		}
		return l;
	}

    // Put your code for less here ...
	public static <T> boolean less(Collection<T> c1, Collection<T> c2, Comparator<T> comp)
	{
		return (comp.compare(Collections.max(c1, comp), Collections.min(c2, comp)) < 0);
	}
    
    // Example
    public static <T1,T2> Collection<T2>
    map(UnaryOp<T1,T2> functor,Collection<T1> c) 
    {
        // Determine the dynamic type of the collection
        Class<? extends Collection> cls = c.getClass();
        try {
            // Create an object of the same dynamic type as c
            Collection<T2> result = cls.newInstance();
            // Copy the elements and apply op to them
            for ( T1 x : c )
                result.add(functor.op(x));
            return result;   
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Put your code for filter here ...
    public static <T1> Collection<T1>
    filter(UnaryPred<T1> functor,Collection<T1> c) 
    {
        // Determine the dynamic type of the collection
        Class<? extends Collection> cls = c.getClass();
        try {
            // Create an object of the same dynamic type as c
            Collection<T1> result = cls.newInstance();
            // Copy the elements and apply op to them
            for ( T1 x : c )
            	if(functor.pred(x))
            		result.add(x);
            return result;   
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

class ReverseStringFunctor implements UnaryOp<String,String>
{
	public String op(String s)
	{
		StringBuffer sbuf = new StringBuffer(s);
		return sbuf.reverse().toString();		
	}
}

class IsEvenFunctor implements UnaryPred<Integer>
{
	public boolean pred(Integer i)
	{
		return (i.intValue() % 2 == 0);
	}
}
