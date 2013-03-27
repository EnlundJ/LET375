import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {    
         ArrayList<String> names = new ArrayList<String>();

         // Test print for an empty list
         CollectionOps.print(names); System.out.println();

         // Test print for a list containing one element
         names.add("a");
         CollectionOps.print(names); System.out.println();

         // Test print for a list containing more than one elment
         names.add("b");
         names.add("c");
         CollectionOps.print(names); System.out.println();
         
         // Test the return value from reverse
         CollectionOps.print(CollectionOps.reverse(names));
         System.out.println();
         // Test that reverse mutates its argument
         CollectionOps.print(names);
         System.out.println();

         // Write code to test less here
         IntegerComparator intcomp = new IntegerComparator(); 
         StringComparator stringcomp = new StringComparator(); 
         List<Integer> li1 = Arrays.asList(4,2,5,1,3);
         List<Integer> li2 = Arrays.asList(8,6,7,9);
         List<Integer> li3 = Arrays.asList(97,5,123,18);
         List<String> johanneberg = Arrays.asList("HC2", "ED", "HC3");
         List<String> lindholmen = Arrays.asList("Saga", "Svea", "Jupiter");
         
         if(CollectionOps.less(li1,li2,intcomp))
        	 System.out.println("true");
         else
        	 System.out.println("false");
        	 
         if(CollectionOps.less(li1,li3,intcomp))
        	 System.out.println("true");
         else
        	 System.out.println("false");

         if(CollectionOps.less(johanneberg,lindholmen,stringcomp))
        	 System.out.println("true");
         else
        	 System.out.println("false");

         // Write code to test map here
             
         // Write code to test filter here
    }
}

class IntegerComparator implements Comparator<Integer>
{
	public int compare(Integer a, Integer b)
	{
		return a.compareTo(b);
	}
}

class StringComparator implements Comparator<String>
{
	public int compare(String a, String b)
	{
		return a.compareTo(b);
	}
}













