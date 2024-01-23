import components.map.Map;
import components.map.Map1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and SpotBugs warnings).
 *
 * @author Evan Frisbie
 */
public final class WordCounter {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private WordCounter() {
        // no code needed here
    }

    /**
     * Parsing function intended to parse words from input file into a sequence.
     * Assumes that words will be split my non-alphabetic characters. Assumes
     * that no word is split between lines.
     *
     * @param in
     *            The system.input line connected to the text file.
     *
     * @return wordList The list of words found by the parsing function.
     */
    private static Sequence<String> parser(SimpleReader in) {
        Sequence<String> words = new Sequence1L<String>();
        boolean atEnd = false;

        while (!atEnd) {
            String line = in.nextLine();
            int i = 0;

            while (line.length() != 0) {
                if (i >= line.length()) {
                    words.add(0, line);
                    line = "";
                } else if (!Character.isAlphabetic(line.charAt(i))) {
                    if (i != 0) {
                        String word = line.substring(0, i);
                        words.add(0, word.toLowerCase());

                        line = line.substring(i);
                        i = -1;
                    } else if (i == 0) {
                        line = line.substring(1);
                        i = -1;
                    }
                }
                i++;
            }
            atEnd = in.atEOS();
        }

        return words;
    }

    /**
     * Sort function sorts the words in the given sequence alphabetically and
     * then returns the sorted sequence.
     *
     * @param words
     *            The sequence of words found in the input file.
     *
     * @return words The sorted sequence of words.
     */
    private static Sequence<String> sort(Sequence<String> words) {
        boolean sorted = false;

        while (!sorted) {
            int length = words.length();
            int sortedLength = 0;

            for (int i = 0; i < words.length() - 1; i++) {
                for (int j = 0; j < words.length(); j++) {
                    if (words.entry(i).charAt(0) < words.entry(j).charAt(0)) {

                        String word1 = words.entry(i);
                        words.replaceEntry(i, words.entry(j));
                        words.replaceEntry(j, word1);

                    } else if (words.entry(i).equals(words.entry(j)) || words
                            .entry(i).charAt(0) > words.entry(j).charAt(0)) {
                        sortedLength++;
                    } else if (words.entry(i).charAt(0) == words.entry(j)
                            .charAt(0)) {
                        if (words.entry(i).length() < words.entry(j).length()) {
                            String word1 = words.entry(i);
                            words.replaceEntry(i, words.entry(j));
                            words.replaceEntry(j, word1);
                        } else if (words.entry(i).length() > 1
                                && words.entry(j).length() > 1) {

                            if (words.entry(i).charAt(1) < words.entry(j)
                                    .charAt(1)) {

                                String word1 = words.entry(i);
                                words.replaceEntry(i, words.entry(j));
                                words.replaceEntry(j, word1);

                            }
                        }
                    }
                }
            }

            if (length < sortedLength) {
                sorted = true;
            }

        }

        Sequence<String> sortedWords = new Sequence1L<String>();
        Set<String> totalWords = new Set1L<String>();

        for (int k = 0; k < words.length(); k++) {
            if (sortedWords.length() == 0) {
                sortedWords.add(0, words.entry(k));
                totalWords.add(words.entry(k));
            } else if (!totalWords.contains(words.entry(k))) {
                sortedWords.add(0, words.entry(k));
                totalWords.add(words.entry(k));
            }
        }

        sortedWords.flip();
        return sortedWords;
    }

    /**
     * Counting function intended to count the occurrences of each word in the
     * sequence and return a list of simple objects each containing the given
     * word and the number of times it was seen.
     *
     * @param words
     *            The sequence of words parsed from the text file.
     *
     * @return occurrences The list of maps each containing the word and its
     *         number of occurrences.
     */
    private static Map<String, Integer> counter(Sequence<String> words) {
        Map<String, Integer> occurrences = new Map1L<String, Integer>();

        for (int i = 0; i < words.length(); i++) {
            if (occurrences.hasKey(words.entry(i))) {
                int value = occurrences.value(words.entry(i));
                occurrences.remove(words.entry(i));
                occurrences.add(words.entry(i), value + 1);
            } else if (!occurrences.hasKey(words.entry(i))) {
                occurrences.add(words.entry(i), 1);
            }
        }

        return occurrences;
    }

    /**
     * Output function creates the output HTML file that is returned to the
     * client for UI viewing of the processed information.
     *
     * @param occurrences
     *            The list of maps containing the given words and their
     *            occurrences.
     *
     * @param name
     *            The name of the output file for the title.
     * @param out
     *            The system.output line for writing to a receiving file.
     */
    private static void outputFunction(Map<String, Integer> occurrences,
            String name, SimpleWriter out, Sequence<String> sorted) {

        out.println("<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n"
                + "<head>\r\n" + "    <meta charset=\"UTF-8\">\r\n"
                + "    <meta name=\"viewport\" content=\"width=device-width,"
                + "initial-scale=1.0\">\r\n" + "    <title>Document</title>\r\n"
                + "</head>\r\n" + "<body><h1>" + name
                + "</h1><table><tr><th>Word</th><th>Occurrences</th></tr>");

        for (int i = 0; i < sorted.length(); i++) {
            out.println("<tr><td>" + sorted.entry(i) + "</td><td>"
                    + occurrences.value(sorted.entry(i)) + "</td></tr>");
        }

        out.println("</table></body></html>");

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.println("Input File Name: ");
        String inputFile = in.nextLine();
        out.println("Output File Name: ");
        String outputFile = in.nextLine();

        SimpleReader input = new SimpleReader1L(inputFile);
        SimpleWriter output = new SimpleWriter1L(outputFile);

        Sequence<String> words = parser(input);
        Sequence<String> sorted = sort(words);
        Map<String, Integer> occurrences = counter(words);

        outputFunction(occurrences, outputFile, output, sorted);

        in.close();
        out.close();
        input.close();
        output.close();
    }

}
