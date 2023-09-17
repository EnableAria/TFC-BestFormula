package TFCBestFormula;

import javax.swing.*;
import java.util.Arrays;

public class Log {  //日志输出类
    public static AnvilView anvilView;
    static int[] output;
    public static void outputLog(int target, JTextArea log){    //日志输出方法
        if(AnvilView.runNum >= 0){
            log.setText(ConfigLoad.langText[13] + "\n");
            Calculator calculator = new Calculator();
            int[] required = new int[3];
            String[] requiredS;
            if(output != null){
                for(int i = 0; i < output.length; i++){ //清除历史输出标签
                    anvilView.mainPanel.remove(anvilView.logLabel[i]);
                }
            }
            if(AnvilView.runNum >= ConfigLoad.internalNum){ //整理末三步步骤
                int[][] sortArray = new int[2][3];
                requiredS = Arrays.copyOfRange(ConfigLoad.forgingText[AnvilView.runNum], 2, 8);
                for(int i = 0; i < 2; i++){
                    for(int j = 0; j < 3; j++){
                        sortArray[i][j] = Integer.parseInt(requiredS[(i * 3) + j]);
                    }
                }
                required = sort(sortArray);
            }
            else{
                requiredS = Arrays.copyOfRange(ConfigLoad.forgingText[AnvilView.runNum], 2, 5);
                for(int i = 0; i < 3; i++){
                    required[i] = Integer.parseInt(requiredS[i]) - 1;
                }
            }
            calculator.setValue(target, required);
            output = calculator.calculation();
            if(output[0] != 0){
                log.append(ConfigLoad.forgingText[AnvilView.runNum][1] + "[" + target + "] " + ConfigLoad.langText[14] + ":\n");
                for(int i : output){
                    if(i != -1){
                        log.append((i + 1) + " ");
                    }
                }
                int t = 0;
                for(int i = 0; i < output.length; i++){
                    if(output[i] == -1){
                        t++;
                    }
                    int j = i - t;
                    int k = j / 9;
                    anvilView.logLabel[i] = ImgCreator.createImgJLabel(26 + (34 * (j - (9 * k))), 310 + (34 * k), "image/forging/forging_" + (output[i] + 1) + ".png");
                    anvilView.mainPanel.add(anvilView.logLabel[i]);
                }
            }
            else{
                log.setText(ConfigLoad.langText[15]);   //15步内无法得到结果
            }
        }
        else{
            log.setText(ConfigLoad.langText[16]);   //未选择锻造目标
        }
    }

    private static int[] sort(int[][] sortArray){   //外置锻造方法排序
        int[] t = new int[2];
        for(int i = 0; i < 3; i++){ //冒泡排序
            for(int j = 0; j < i; j++){
                if(sortArray[1][i] < sortArray[1][j]){
                    for(int k = 0; k < 2; k++){
                        t[k] = sortArray[k][i];
                        sortArray[k][i] = sortArray[k][j];
                        sortArray[k][j] = t[k];
                    }
                }
            }
        }
        for(int i = 0; i < 3; i++){ //再排序
            for(int j = 0; j < 3; j++){
                if(j == i){
                    continue;
                }
                if(sortArray[1][j] == (i + 1)){
                    for(int k = 0; k < 2; k++){
                        t[k] = sortArray[k][i];
                        sortArray[k][i] = sortArray[k][j];
                        sortArray[k][j] = t[k];
                    }
                }
            }
        }
        for(int i = 0; i < 3; i++){
            sortArray[0][i] -= 1;
        }
        return sortArray[0];
    }

    public static void setAnvilView(AnvilView anvilView){
        Log.anvilView = anvilView;
    }
}