
import javax.swing.UIManager;
public class Main{

    public static void main(String[] args)
        {
            try {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()) ;  
            }
             catch(Exception e)
             {
                System.out.println("Can't make it like System UI");
            }
            System.out.println("\t\tJAVA Plagarism checker");
            String f1= FileProcessor.chooseFile("Select 1st File to Check");
            String f2=FileProcessor.chooseFile("Select 2nd File you want to compare with");

            if(f1==null || f2==null)
            {
                System.err.println("File Can't be checked! its Empty>> ");
                return;
            }
            PlagarismDetector plag=new PlagarismDetector();
            plag.run(f1,f2);
        }
}
