import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Comparator;

// Author(s): Einar Blaberg and Niklas Beischer
// Email:	einar.blaberg@gmail.com, niklas.beischer@gmail.com
// Date:	2013-03-21
public class WordLists
{
	private Reader in = null;
	private TreeMap<String, Integer> map;

	static final Comparator<String> REVERSE_ORDER = 
			new Comparator<String>() {

		public int compare(String s1, String s2)
		{
			return WordLists.reverse(s1).compareTo(WordLists.reverse(s2));
		}
	};

	public WordLists(String inputFileName)
	{
		map = new TreeMap<String, Integer>();
		try
		{
			in = new BufferedReader(new FileReader(inputFileName));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found!" + e.toString());
		}
	}
	
	private boolean isPunctuationChar(char c) {
	    final String punctChars = ",.:;?!";
	    return punctChars.indexOf(c) != -1;
	}
	
	private String getWord() throws IOException {
		int state = 0;
		int i;
		String word = "";
		while ( true ) {
			i = in.read();
			char c = (char)i;
			switch ( state ) {
			case 0:
				if ( i == -1 )
					return null;
				if ( Character.isLetter( c ) ) {
					word += Character.toLowerCase( c );
					state = 1;
				}
				break;
			case 1:
				if ( i == -1 || isPunctuationChar( c ) || Character.isWhitespace( c ) )
					return word;
				else if ( Character.isLetter( c ) ) 
					word += Character.toLowerCase( c );
				else {
					word = "";
					state = 0;
				}
			}
		}
	}
	
	public void addWord(String s)
	{
		Integer current=0;
		if(map.containsKey(s))
			current=map.get(s);
		map.put(s, ++current);
	}
	
	private static String reverse(String s)
	{
		StringBuffer sbuf = new StringBuffer(s);
		return sbuf.reverse().toString();
	}
	
	private void computeWordFrequencies()
	{
		PrintStream out = null;
		try
		{
		    out = new PrintStream(new FileOutputStream("alfaSorted.txt"));
			for(Map.Entry<String,Integer> entry : map.entrySet())
				out.println(entry.getKey() + ": " + entry.getValue());
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found!" + e.toString());
		}
		finally
		{
		    if (out != null)
		    	out.close();
		}
	}

	private void computeFrequencyMap()
	{
		TreeMap<Integer, TreeSet<String>> fmap = new TreeMap<Integer, TreeSet<String>>(); 
		TreeSet<String> tmpset;
		for(Map.Entry<String,Integer> entry : map.entrySet())
		{
			if(!fmap.containsKey(entry.getValue()))
			{
				tmpset=new TreeSet<String>();
				fmap.put(entry.getValue(), tmpset);
			}
			else
			{
				tmpset=fmap.get(entry.getValue());
			}
			tmpset.add(entry.getKey());
		}
		
		PrintStream out = null;
		try
		{
		    out = new PrintStream(new FileOutputStream("frequencySorted.txt"));
			for(Map.Entry<Integer, TreeSet<String>> entry : fmap.descendingMap().entrySet())
			{
				out.println(entry.getKey().toString() + ": ");
				for(String s : entry.getValue())
					out.println("    " + s);
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found!" + e.toString());
		}
		finally
		{
		    if (out != null)
		    	out.close();
		}
	}

	private void computeBackwardsOrder()
	{
		TreeSet<String> reverseSet = new TreeSet<String>(REVERSE_ORDER);
		for(String word : map.keySet())
			reverseSet.add(word);

		PrintStream out = null;
		try
		{
		    out = new PrintStream(new FileOutputStream("backwardsSorted.txt"));
			for(String bWord : reverseSet)
				out.println(bWord);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found!" + e.toString());
		}
		finally
		{
		    if (out != null)
		    	out.close();
		}		
//		for(Map.Entry<String,Integer> entry : map.entrySet())
//			System.out.println(WordLists.reverse(entry.getKey()) + ": " + entry.getValue());
	}

	public static void main(String[] args) throws IOException {
		String word;
//		WordLists wl = new WordLists(args[0]);  // arg[0] contains the input file name
		WordLists wl = new WordLists("provtext.txt");  
		
		word=wl.getWord();
		while(word != null)
		{
			wl.addWord(word);
			word=wl.getWord();
		}
		
		wl.computeWordFrequencies();
		wl.computeBackwardsOrder();
		wl.computeFrequencyMap();
		
		System.out.println("Finished!");
	}
}



















