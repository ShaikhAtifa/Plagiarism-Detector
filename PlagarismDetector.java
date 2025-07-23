import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;


public class PlagarismDetector{
    private static final Set<String> STOPWORDS= Set.of("the","is","in","at","of","on","and","a","an","to","this","that");


    public void run(String f1,String f2)
    {
        Map<String, Integer> frq1=new HashMap<>();
        Map<String,Integer> frq2=new HashMap<>();

    Set<String> words1=FileProcessor.readWords(f1,frq1);
    Set<String>words2=FileProcessor.readWords(f2,frq2);

    if(words1==null || words2==null)
    {
        System.err.println("Error!! can't");
        return;
    }
    int total1=frq1.values().stream().mapToInt(i->i).sum();
    int total2=frq2.values().stream().mapToInt(i->i).sum();

    List<String> matches=words1.stream().filter(words2::contains).collect(Collectors.toList());

    double similarity =computeConsineSimilarity(frq1,frq2);

   try(PrintWriter out= new PrintWriter("Result_File.txt")){
    out.println("--Matched Words--");
    for(String word:matches)
    {
      out.println(word);
    }
   }
   catch(FileNotFoundException e)
   {
    System.err.println("Can't find file Named Result_File");
   }

   System.out.println("\n***FINAL PLAGARISm REPORT***");
   System.out.println("Total Words in File1:"+total1);
   System.out.println("Total Words in File2:"+total2);
   System.out.println("Unique Words in File1:"+words1.size());
   System.out.println("Unique Words in File2:"+words2.size());
   System.out.println("Matching Words :"+matches.size());
   System.out.printf("Similarity Score:%.2f%%\n",similarity);

   if(similarity * 100 >65.0)
   {
    System.out.println("WARNING!!\n High Level of Similarity Detected!");
   }
   else
   {
    System.out.println("Files are not Copied");
   }
   System.out.println("Note:You can check the matched words in Result_File.txt");
    }
    private double computeConsineSimilarity(Map<String, Integer> frq1, Map<String, Integer> frq2)
    {
        Set<String> allWords=new HashSet<>();
        allWords.addAll(frq1.keySet());
        allWords.addAll(frq2.keySet());

        int dotProduct =0;
        double normA =0, normB=0;

        for(String word : allWords)
        {
            int f1 =frq1.getOrDefault(word,0);
            int f2= frq2.getOrDefault(word,0);
            dotProduct += f1 * f2;
            normA +=f1 * f1;
            normB += f2 * f2;
        }
        return (normA==0|| normB==0) ?0.0 : dotProduct / (Math.sqrt(normA)*Math.sqrt(normB));
    }
}