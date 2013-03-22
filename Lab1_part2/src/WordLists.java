import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;


// Author(s): Einar Blaberg and Niklas Beischer
// Email:	einar.blaberg@gmail.com, niklas.beischer@gmail.com
// Date:	2013-03-21

public class WordLists {
	private Reader in = null;
	private TreeMap<String, Integer> map; 

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
		catch(IOException e)
		{
			System.out.println("IOError!" + e.toString());
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
	
	private String reverse(String s) {
	    // define!
		return "";
	}
	
	private void computeWordFrequencies()
	{
		for(String word : map.keySet())
		{
			System.out.println(word + ": " + map.get(word));
		}
	}
	

	private void computeFrequencyMap()
	{
		TreeMap<Integer, TreeSet<String>> fmap = new TreeMap<Integer, TreeSet<String>>(); 
		int freq=0;
		TreeSet<String> tmpset;
		for(String word : map.keySet())
		{
			freq=map.get(word);

			if(!fmap.containsKey(freq))
			{
				tmpset=new TreeSet<String>();
				fmap.put(freq, tmpset);
			}
			else
			{
				tmpset=fmap.get(freq);
			}
			tmpset.add(word);
		}
		System.out.println(fmap);
	}

	private void computeBackwardsOrder() {
	    // define!
	}

	public static void main(String[] args) throws IOException {
		String word;
//		WordLists wl = new WordLists(args[0]);  // arg[0] contains the input file name
		WordLists wl = new WordLists("C:\\Users\\eb\\workspace\\LET375 Lab1\\src\\provtext.txt");  
		
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



















