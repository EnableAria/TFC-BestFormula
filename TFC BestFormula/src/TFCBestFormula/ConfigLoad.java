package TFCBestFormula;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ConfigLoad {    //配置加载类
    public static int itemNum = 0, internalNum = 0, externalNum = 0, internalMetalNum = 0, externalMetalNum = 0;
    public static String path, initial_seed, logText_1, logText_2;
    public static String[] langText;
    public static String[][] recipeID;
    public static String[][] metalList;
    public static String[][] forgingText = new String[90][9];
    public static String[][] needMetalText = new String[90][2];
    public static void load(){
        getPath();
        loadSettings();
        loadLangText();
        loadMetalListText();
        loadForgingText();
        loadNeedMetalText();
        loadRecipeIDText();
    }

    public static void getPath(){   //获取项目所在路径
        path = ConfigLoad.class.getProtectionDomain().getCodeSource().getLocation().getFile();  //获取jar状态下路径
//        path = System.getProperty("exe.path");    //获取exe状态下路径
        try{
            path = java.net.URLDecoder.decode(path, "UTF-8");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        int firstIndex = path.indexOf("/") + 1;
        int lastIndex = path.lastIndexOf("/") + 1;
        path = path.substring(firstIndex, lastIndex);
    }

    public static void loadSettings(){  //加载用户配置
        initial_seed = "";
        try{
            FileInputStream fileInputStream = new FileInputStream(path + "settings.ini");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            Scanner reader = new Scanner(inputStreamReader);
            String buffer;
            buffer = reader.next();
            initial_seed = buffer.split("seed=")[1];
            fileInputStream.close();
            inputStreamReader.close();
        }
        catch(ArrayIndexOutOfBoundsException | IOException ignored){}
    }

    public static void loadLangText(){  //加载文本文件
        int i;
        langText = new String[30];
        try{
            InputStream inputStream = ConfigLoad.class.getResourceAsStream("config/lang.ini");
            assert inputStream != null;
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            Scanner reader = new Scanner(inputStreamReader);
            for(i = 0; reader.hasNext(); i++){
                langText[i] = reader.next();
            }
            inputStream.close();
            inputStreamReader.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void loadRecipeIDText(){  //加载配方数据文件
        int i;
        recipeID = new String[itemNum][2];
        try{
            InputStream inputStream = ConfigLoad.class.getResourceAsStream("config/recipe_id.ini");
            assert inputStream != null;
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            Scanner reader = new Scanner(inputStreamReader);
            for(i = 0; reader.hasNext(); i++){
                recipeID[i][0] = reader.next();
                recipeID[i][1] = reader.next();
            }
            while(i < itemNum){
                recipeID[i][0] = "";
                recipeID[i][1] = "0";
                i++;
            }
            inputStream.close();
            inputStreamReader.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void loadMetalListText(){ //加载金属列表文件
        int i;
        metalList = new String[19][2];
        try{
            InputStream inputStream = ConfigLoad.class.getResourceAsStream("config/metal_list.ini");
            assert inputStream != null;
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            Scanner reader = new Scanner(inputStreamReader);
            for(i = 0; reader.hasNext(); i++){
                metalList[i][0] = reader.next();
                metalList[i][1] = reader.next();
            }
            inputStream.close();
            inputStreamReader.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void loadForgingText(){   //加载锻造项目文件
        loadInternalForgingText();
        loadExternalForgingText();
        itemNum = internalNum + externalNum;
    }

    public static void loadInternalForgingText(){   //加载内部锻造项目
        int i, j;
        try{
            InputStream inputStream = ConfigLoad.class.getResourceAsStream("config/forging.ini");
            assert inputStream != null;
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            Scanner reader = new Scanner(inputStreamReader);
            for(i = 0; reader.hasNext() && (i < 90); i++){
                for(j = 0; j < 9; j++){
                    forgingText[i][j] = reader.next();
                }
                internalNum++;
            }
            inputStream.close();
            inputStreamReader.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void loadExternalForgingText(){   //加载外部锻造项目
        int i, j;
        try{
            FileInputStream fileInputStream = new FileInputStream(path + "config/forging.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            Scanner reader = new Scanner(inputStreamReader);
            for(i = internalNum; reader.hasNext() && (i < 90); i++){
                for(j = 0; j < 9; j++){
                    forgingText[i][j] = reader.next();
                }
                externalNum++;
            }
            logText_1 = langText[17] + externalNum + langText[18] + langText[20];
            fileInputStream.close();
            inputStreamReader.close();
        }
        catch(FileNotFoundException e){
            logText_1 = langText[19] + langText[20];
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void loadNeedMetalText(){ //加载基底金属文件
        loadInternalNeedMetalText();
        loadExternalNeedMetalText();
    }

    public static void loadInternalNeedMetalText(){ //加载内部基底金属
        int i;
        try{
            InputStream inputStream = ConfigLoad.class.getResourceAsStream("config/need_metal.ini");
            assert inputStream != null;
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            Scanner reader = new Scanner(inputStreamReader);
            for(i = 0; reader.hasNext() && (i < 90); i++){
                needMetalText[i][0] = reader.next();
                needMetalText[i][1] = reader.next();
                internalMetalNum++;
            }
            inputStream.close();
            inputStreamReader.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void loadExternalNeedMetalText(){ //加载外部基底金属
        int i;
        try{
            FileInputStream fileInputStream = new FileInputStream(path + "config/need_metal.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            Scanner reader = new Scanner(inputStreamReader);
            for(i = internalMetalNum; reader.hasNext() && (i < 90); i++){
                needMetalText[i][0] = reader.next();
                needMetalText[i][1] = reader.next();
                externalMetalNum++;
            }
            logText_2 = langText[17] + externalMetalNum + langText[18] + langText[21];
            fileInputStream.close();
            inputStreamReader.close();
        }
        catch(FileNotFoundException e){
            logText_2 = langText[19] + langText[21];
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}