package TFCBestFormula;

import javax.swing.*;
import java.util.Arrays;

public class Log {  //日志输出类
    public static AnvilView anvilView;
    public static void outputLog(int target, JTextArea log){    //日志输出方法
        int[] output;
        if(AnvilView.runNum >= 0){
            log.setText(ConfigLoad.langText[13] + "\n");
            Calculator calculator = new Calculator();
            int[] required = new int[3];
            String[] requiredS = Arrays.copyOfRange(ConfigLoad.forgingText[AnvilView.runNum], 2, 5);
            for(int i = 0; i < 3; i++){
                required[i] = Integer.parseInt(requiredS[i]) - 1;
            }
            calculator.setValue(target, required);
            output = calculator.calculation();
            if(output[0] != 0){
                log.append(ConfigLoad.forgingText[AnvilView.runNum][1] + "[" + target + "] " + ConfigLoad.langText[14] + ":\n");
                for(int i : output){
                    log.append((i + 1) + " ");
                }
                for(int i = 0; i < output.length; i++){
                    int j = i / 9;
                    anvilView.mainPanel.remove(anvilView.logLabel[i]);
                    anvilView.logLabel[i] = ImgCreator.createImgJLabel(26 + (34 * (i - (9 * j))), 310 + (34 * j), "image/forging/forging_" + (output[i] + 1) + ".png");
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