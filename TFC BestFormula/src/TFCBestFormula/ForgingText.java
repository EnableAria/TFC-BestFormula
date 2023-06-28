package TFCBestFormula;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ForgingText {
    public static int itemNum = 0;
    public static String[][] forgingText;
    public static void loadForgingText(){
        int i, j;
        forgingText = new String[54][9];
        try {
            InputStream inputStream = ForgingText.class.getResourceAsStream("forging_text/forging_text.txt");
            assert inputStream != null;
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            Scanner reader = new Scanner(inputStreamReader);
            for(i = 0; reader.hasNext() && (i < 54); i++){
                for(j = 0; j < 9; j++){
                    forgingText[i][j] = reader.next();
                }
            }
           itemNum = i;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}