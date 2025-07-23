import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileProcessor {

    // Define a set of common stopwords to be excluded
    private static final Set<String> STOPWORDS = Set.of(
        "the", "is", "in", "at", "of", "on", "and", "a", "an", "to", "this", "that"
    );

    // Cleans a word by converting to lowercase and removing punctuation
    public static String cleanWord(String word) {
        return word.toLowerCase().replaceAll("[^a-z]", "");
    }

    // Opens a file chooser dialog and returns the selected file's path
    public static String chooseFile(String dialogTitle) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(dialogTitle);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
    }

    // Reads words from the file, filters out stopwords, fills frequency map, and returns a set of unique words
    public static Set<String> readWords(String filePath, Map<String, Integer> freqMap) {
        Set<String> wordSet = new HashSet<>();
        File file = new File(filePath);

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                String word = cleanWord(sc.next());
                // Only process non-empty, non-stopwords
                if (!word.isEmpty() && !STOPWORDS.contains(word)) {
                    wordSet.add(word);
                    freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            return null;
        }

        return wordSet;
    }
}
