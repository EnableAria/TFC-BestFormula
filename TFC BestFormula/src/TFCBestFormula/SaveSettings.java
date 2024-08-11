package TFCBestFormula;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveSettings{
    public static void saveSettings(String seed){
        FileOutputStream settings;
        if(AnvilView.recipeNum != -2 && seed.length() > 0){
            try{
                settings = new FileOutputStream(ConfigLoad.path + "settings.ini");
                settings.write("seed=".getBytes());
                settings.write(seed.getBytes());
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        else{
            File settings_file = new File(ConfigLoad.path + "settings.ini");
            if(settings_file.exists() && settings_file.isFile()){
                if(!settings_file.delete()){
                    System.out.println("File Delete Error");
                }
            }
        }
    }
}
