// Author(s): Einar Blaberg and Niklas Beischer
// Email:	einar.blaberg@gmail.com, niklas.beischer@gmail.com
// Fire-groupID: FIXME
// Date:	2013-03-21

import java.util.Arrays;
import java.util.HashSet;
import java.io.*;
import java.util.Random;

/**
 * @author Einar Blaberg and Niklas Beischer
 */
public class AngloTrainer {
	private HashSet<String> dict;
	private HashSet<String> playerWords;
	private int lengthOfLongestWord;
	private String rndLetters, sortedRndLetters; 

	/**
	 * @param dictionaryFile
	 */
	public AngloTrainer(String dictionaryFile) //throws IOException
	{
		dict = new HashSet<String>();
    	playerWords = new HashSet<String>();
		lengthOfLongestWord=5;
		
		try {
			loadDictionary(dictionaryFile);
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found!\n" + e.toString());
			}
		catch(IOException e) {
			System.out.println("IOError!\n");
		}

		rndLetters=randomLetters(getLengthOfLongestWord());
		sortedRndLetters=this.sort(rndLetters);
	}

	/**
	 * Prints the dictionary to STDOUT
	 */
	private void dumpDict()
	{
		for(String word : dict)
			System.out.println(word);
	}

    	/**
	 * @param fileName
	 * @throws IOException
	 * @throws FileNotFoundException
	 * Read the dictionary into a suitable container.
	 * The file is a simple text file. One word per line.
	 */
	private void loadDictionary( String fileName ) throws IOException, FileNotFoundException 
	{
		BufferedReader reader =
				new BufferedReader(
						new FileReader(fileName));
		String line = reader.readLine();
		while(line != null) {
			dict.add(line);
			int lineLen = line.length();
			if(lineLen > lengthOfLongestWord)
				lengthOfLongestWord = lineLen;
			line = reader.readLine();
		}
		reader.close();
		System.out.println(dict.size() + " words loaded from the " + fileName);
	}

	/**
	 * @param length
	 * @return
	 * this makes vovels a little more likely
	 */
	private String randomLetters( int length )
	{
		Random randomGenerator = new Random();
	    String letters = "aabcdeefghiijklmnoopqrstuuvwxyyz";  
	    StringBuffer buf = new StringBuffer(length);
		for ( int i = 0; i < length; i++ ) 
		    buf.append( letters.charAt(randomGenerator.nextInt(letters.length())));
	    return buf.toString();
	}
	
	/**
	 * @param a
	 * @param b
	 * @return
	 * 
	 * Def. includes	
	 * Let #(x,s) = the number of occurrences of the charcter x in the string s.
	 * includes(a,b) holds iff for every character x in b, #(x,b) <= #(x,a)
	 * 
	 * A neccessary precondition for includes is that both strings are sorted
	 * in ascending order.
	 */
	private boolean includes( String a, String b )
	{
		if ( b == null || b.length() == 0 )
			return true;
		else if ( a == null || a.length() == 0 )
			return false;
		
		//precondition: a.length() > 0 && b.length() > 0
		int i = 0, j = 0;
		while ( j < b.length() )
		{
			if (i >= a.length() || b.charAt(j) < a.charAt(i))
				return false;
			else if (b.charAt(j) == a.charAt(i))
			{
				i++; j++;
			}
			else if (b.charAt(j) > a.charAt(i))
				i++;
		}
		//postcondition: j == b.length()
		return true;
	}
	
	/**
	 * @return
	 */
	public int getLengthOfLongestWord()
	{
		return lengthOfLongestWord;
	}
	
	/**
	 * @param s
	 * @return
	 */
	private String sort(String s)
	{
		char[] temp = s.toCharArray();
		Arrays.sort(temp);
		return new String(temp);
	}
	
	/**
	 * @param s
	 * @return
	 */
	public boolean checkWord(String s)
	{
		boolean hasCorrectLetters=includes(sortedRndLetters, this.sort(s));
		boolean isAWord=dict.contains(s);
		if(!hasCorrectLetters)
			System.out.println("Your suggestion contains wrong letters");
		if(!isAWord)
			System.out.println("Your suggestion was not found in the dictionary");
		
		if(hasCorrectLetters && isAWord)
		{
			playerWords.add(s);
			return true;
		}
		else
			return false;
	}
	
	/**
	 * @return
	 */
	public String getRndLetters()
	{
		return rndLetters;
	}
	
	/**
	 * 
	 */
	public void findWords()
	{
		for(String word : dict)
			if(!playerWords.contains(word) && includes(sortedRndLetters, this.sort(word)))
				System.out.println(word);
	}

    public static void main(String[] args)
    {
    	AngloTrainer a = new AngloTrainer(args[0]);

    	String encoding = System.getProperty("file.encoding");
    	System.out.println("The random letters are: " + a.getRndLetters());
    	System.out.println("Try to build as many words from these letters as you can!");

    	try
    	{
    		BufferedReader in = new BufferedReader(new InputStreamReader(System.in,encoding));

    		String line;
    		while((line = in.readLine()) != null)
    		{
    			if(a.checkWord(line))
    				System.out.println("OK\n");
    			else
    				break;
    		}
    	}
    	catch(IOException e)
    	{
    		System.out.println("I/O error: " + e);
    	}

    	System.out.println("I found:");
    	a.findWords();
    }

}












