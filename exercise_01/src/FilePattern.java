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
	private String pattern;

	/**
	 * Creates a new instance of the FilePattern class that filters
	 * file names based on the given pattern.
	 * 
	 * @param pattern the pattern used to filter file names.
	 * @see FilePattern
	 */
	public FilePattern(String pattern) {
		this.pattern = pattern;
	}

	/**
	 * Returns whether the given filename matches this pattern.
	 * @param filename
	 * @return true if filename matches the pattern
	 */
	public boolean matches(String filename) {
		String regex = globToRegex(this.pattern);
		System.out.println(this.pattern + " -> " + regex);

		return false;
	}

	private String globToRegex(String glob) {
		StringBuilder out = new StringBuilder();

		for (char c : glob.toCharArray()) {
			switch (c) {
				case '*':
					out.append(".*");
					break;
				case '?':
					out.append(".");
					break;
				case '[':
				case ']':
				case '{':
				case '}':
					out.append("\\" + c);
					break;
				default:
					out.append(c);
			}
		}

		return out.toString();
	}

	public static void main(String[] args) {
		FilePattern f = new FilePattern("te?st*with?[fu{}nny]*");
		f.matches("foo");
	}
}
