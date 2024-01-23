import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * a Java program that counts word occurrences in a given input file and outputs
 * an HTML document with a table of the words and counts listed in alphabetical
 * order.
 *
 * @author Charan
 */
public final class WordCount {

    /**
     * Template Method.
     */
    private WordCount() {
    }

    /**
     * Returns a word from the input string, each separated by a separator.
     *
     * @param inputText
     *            the String that words will be extracted from
     * @param z
     *            the initial positon to iterate from
     * @param separator
     *            the separation characters
     * @param k
     *            final position.
     * @return word from string.
     */
    private static String separateWordfromString(String inputText, int z,
            Set<Character> separator, int[] k) {
        int i = z; // starting position
        int j = z; // ending position
        String word = "";

        while (i < inputText.length()
                && separator.contains(inputText.charAt(i))) {
            i++;
        }

        j = i; //set start = end after incrementing start

        // Find the end of the word
        while (j < inputText.length()
                && !separator.contains(inputText.charAt(j))) {
            j++;
        }

        // Update final position
        k[0] = j;

        // Check if a word was found
        if (i < inputText.length()) {
            // If a word was found, return the word
            word = inputText.substring(i, j);
        }
        return word;
    }

    /**
     * Creates a map to store words and their occurence count.
     *
     * @param in
     *            SimpleReader input text file.
     * @param separator
     *            defined separators.
     * @return returns the map.
     */
    private static Map<String, Integer> createMapofOccurences(SimpleReader in,
            Set<Character> separator) {
        Map<String, Integer> map = new Map1L<>();
        String line = "", word = "";
        int i = 0; // index
        int[] arr = new int[1];
        while (!in.atEOS()) {
            line = in.nextLine();
            while (i < line.length()) {
                word = separateWordfromString(line, i, separator, arr);
                if (map.hasKey(word)) {
                    map.replaceValue(word, map.value(word) + 1);
                } else {
                    map.add(word, 1);//
                }
                i = arr[0] + 1; //move to the next word in the line
            }
            i = 0;
        }
        return map;
    }

    /**
     * puts each character of a given string into a set.
     *
     * @param s
     *            the given {@code String}
     * @param set
     *            the {@code Set} to be replace
     * @replaces set
     */
    static void makeSetfromString(String s, Set<Character> set) {

        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(s.charAt(i))) {
                set.add(s.charAt(i));
            }
        }
    }

    //private static void

    /**
     * Method to generate the HTML page with table of word counts.
     *
     * @param name
     *            the output file name
     * @param in
     *            the input file name
     * @param map
     *            stores words and the counts
     */
    private static void makeHTMLPage(String name, String in,
            Map<String, Integer> map) {
        SimpleWriter out = new SimpleWriter1L(name); //output file
        Queue<String> list = new Queue1L<>(); // list with the words
        Map<String, Integer> temp = map.newInstance(); // map woth the words and the couts
        temp.transferFrom(map); // transfer from map to temp, to manipulate in method.

        while (temp.size() > 0) { //iterate while there are items left in the map.
            Map.Pair<String, Integer> pair = temp.removeAny();
            // removes a random pair from the map
            list.enqueue(pair.key());
            map.add(pair.key(), pair.value());
        }
        out.println("<html>" + "<head>" + "<title>");
        out.println("Words Counted: " + in);
        out.println("</title>" + "</head>" + "<body>");
        out.println("<h2>" + "Words Counted: " + in + "</h2>");
        out.println("<hr />" + "<table border =\"2\">");
        out.println("<tr>" + "<th>" + "Words" + "</th>" + "<th>" + "Count"
                + "</th>" + "</tr>");

        while (list.length() > 0) {
            out.println("<tr>" + "<td>");
            out.print(list.dequeue());
            out.println("</td>" + "<td>");
            out.print(map.value(list.dequeue()));
            out.println("</td>" + "</tr>");
        }
        out.println("</table> + </body> + </html>");
        out.close();
    }

    /**
     * Main.
     *
     * @param args
     */
    public static void main(String[] args) {

        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * Ask for the name for input and output file name
         */
        out.println("input file name? include file type,  ex: file.txt");
        String input = in.nextLine();
        out.println("output file name? include file type,  ex: output.txt");
        String output = in.nextLine();
        SimpleReader readIn = new SimpleReader1L(input);
        Set<Character> characters = new Set1L<>();
        makeSetfromString("! ,.-\";_?<>", characters); //defined separators

        Map<String, Integer> wordMap = createMapofOccurences(readIn,
                characters);

        makeHTMLPage(output, input, wordMap);

        /*
         * Close streams
         */
        in.close();
        out.close();
    }
}
