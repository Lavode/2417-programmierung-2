/**
 * Filters file names using command-line wildcards.
 *
 * '*' matches any number of character.
 * '?' matches exactly one character.
 *
 * Examples:
 * '*.md' matches all files with the markdown extension.
 * 'exercise_??.md' matches, for example, 'exercise_01.md'.
 * 
 * @author You!
 *
 */
public class FilePattern {
	/**
	 * Creates a new instance of the FilePattern class that filters
	 * file names based on the given pattern.
	 * 
	 * @param pattern the pattern used to filter file names.
	 * @see FilePattern
	 */
	private String pattern;
	
	public FilePattern(String pattern) {
		// your implementation
		this.pattern = pattern;
	}

	/**
	 * Returns whether the given filename matches this pattern.
	 * @param filename
	 * @return true if filename matches the pattern
	 */
	public boolean matches(String filename) {
		int i = 0;
		int valueOfStar;
		//boolean error = false;
		boolean starfound = false;
		//String copyFilename = filename;
		//String copyPattern = this.pattern;
		//check from beginning to '*' or end
		if(this.pattern.length()>filename.length())
		{
			return false;
		}
		while(i<this.pattern.length() && !starfound)
		{
			if(this.pattern.charAt(i) == '*')
			{
				starfound = true;
			}
			else if(this.pattern.charAt(i) == '?' || this.pattern.charAt(i) == filename.charAt(i))
			{
				i++;
			}
			else
			{
				return false;
			}
		}
		valueOfStar = i;
		if(!starfound && this.pattern.length()!= filename.length())
		{
			return false;
		}
		//check from end to '*'
		if(starfound)
		{
			i = filename.length()-1;
			int j = this.pattern.length()-1;
			while(this.pattern.charAt(j)!='*')
			{
				if(this.pattern.charAt(j) == '&' || this.pattern.charAt(j) == filename.charAt(i))
				{
					i--;
					j--;
				}
				else
				{
					return false;
				}
			}
			if(filename.length() - (filename.length() - i -1) - valueOfStar <1)
			{
				return false;
			}
		}
		
		return true;
		
		//throw new NotImplementedException();
	}
    
}
