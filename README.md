###Java Plagiarism Detection Tool###

A lightweight and effective Java text similarity checker that identifies plagiarism between two text files using cosine similarity. The tool preprocesses input, eliminates stopwords and punctuation, and identifies the similarity percentage of two files.

---

* Features

-  Reads and tokenizes content from `.txt` files
-  Eliminates common stopwords and punctuation
-  employs **cosine similarity** to measure content similarity
-  finds and shows **matching words**
-  produces a plagiarism report in console and writes to a text file
-  basic **file chooser GUI** for input file selection
- identifies whether content similarity exceeds a set threshold (e.g., 65%)

---

* Project Structure
    PlagiarismDetector
      ->Main.java
      ->FileProcessor.java
      ->PlagarismDetector.java

*How to RUN:
Compile:javac Main.java
RUN:java Main

