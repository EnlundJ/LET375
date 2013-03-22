// Author(s): Einar Blaberg and Niklas Beischer
// Email:	einar.blaberg@gmail.com, niklas.beischer@gmail.com
// Fire-groupID: 19
// Date:	2013-03-22

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

/**
 * @author Einar Blaberg and Niklas Beischer
 */
public class WordLists
{
	private Reader in = null;
	private TreeMap<String, Integer> map;
	private ReverseStringComp REVERSE_ORDER;

	/**
	 * Comparator for the BackwardsOrder
	 */
	private final class ReverseStringComp implements Comparator<String>
	{
		public int compare(String s1, String s2)
		{
			return WordLists.reverse(s1).compareTo(WordLists.reverse(s2));
		}
	}
	
	/**
	 * @param inputFileName file containing the text to analyze
	 */
	public WordLists(String inputFileName)
	{
		map = new TreeMap<String, Integer>();
		REVERSE_ORDER = new ReverseStringComp();
		
		try
		{
			in = new BufferedReader(new FileReader(inputFileName));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found!" + e.toString());
		}
	}
	
	/**
	 * @param c character to check
	 * @return true if the character is a punctuation-character, false if not
	 * 
	 * Returns whether a given character is a punctuation-character or not
	 */
	private boolean isPunctuationChar(char c)
	{
	    final String punctChars = ",.:;?!";
	    return punctChars.indexOf(c) != -1;
	}
	
	/**
	 * @return String containing the next word
	 * @throws IOException
	 * 
	 * Reads one word from the text-file.
	 */
	private String getWord() throws IOException
	{
		int state = 0;
		int i;
		String word = "";
		while ( true )
		{
			i = in.read();
			char c = (char)i;
			switch ( state )
			{
			case 0:
				if ( i == -1 )
					return null;
				if ( Character.isLetter( c ) )
				{
					word += Character.toLowerCase( c );
					state = 1;
				}
				break;
			case 1:
				if ( i == -1 || isPunctuationChar( c ) || Character.isWhitespace( c ) )
					return word;
				else if ( Character.isLetter( c ) ) 
					word += Character.toLowerCase( c );
				else
				{
					word = "";
					state = 0;
				}
			}
		}
	}
	
	/**
	 * @param s word to add to the Map
	 * Adds a word the the Map, if the word exists the frequency is incremented
	 */
	public void addWord(String s)
	{
		Integer current=0;
		if(map.containsKey(s))
			current=map.get(s);
		map.put(s, ++current);
	}
	
	/**
	 * @param s String to reverse
	 * @return a reversed version of s
	 */
	private static String reverse(String s)
	{
		StringBuffer sbuf = new StringBuffer(s);
		return sbuf.reverse().toString();
	}
	
	/**
	 * Writes a list of all words and their frequency to a file, sorted by word
	 */
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

	/**
	 * Writes a list of all words and their frequency to a file, sorted by frequency
	 */
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
				tmpset=fmap.get(entry.getValue());

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

	/**
	 * Writes list of all words to a file, sorted backwards by word
	 */
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
				out.println("                              ".substring(bWord.length()) + bWord);
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

	public static void main(String[] args) throws IOException
	{
		//FIXME: WordLists wl = new WordLists(args[0]);  // arg[0] contains the input file name
		WordLists wl = new WordLists("provtext.txt");  

		String word;
		while((word=wl.getWord()) != null)
			wl.addWord(word);
		
		wl.computeWordFrequencies();
		wl.computeBackwardsOrder();
		wl.computeFrequencyMap();
		
		System.out.println("Finished!");
	}
}



















