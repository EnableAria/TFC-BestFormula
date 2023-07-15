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
            String[] requiredS = Arrays.copyOfRange(ConfigLoad.forgingText[AnvilView.runNum], 2, 5);
            if(output != null){
                for(int i = 0; i < output.length; i++){ //清除历史输出标签
                    anvilView.mainPanel.remove(anvilView.logLabel[i]);
                }
            }
            for(int i = 0; i < 3; i++){
                required[i] = Integer.parseInt(requiredS[i]) - 1;
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

    public static void setAnvilView(AnvilView anvilView){
        Log.anvilView = anvilView;
    }
}