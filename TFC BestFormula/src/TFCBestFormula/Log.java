package TFCBestFormula;

import javax.swing.*;
import java.util.Arrays;

public class Log {
    public static AnvilView anvilView;
    public static void outputLog(int target, JTextArea log){
        int[] output;
        if(AnvilView.runNum >= 0){
            log.setText(" 开始计算......\n");
            Calculator calculator = new Calculator();
            int[] required = new int[3];
            String[] requiredS = Arrays.copyOfRange(ForgingText.forgingText[AnvilView.runNum], 2, 5);
            for(int i = 0; i < 3; i++){
                required[i] = Integer.parseInt(requiredS[i]) - 1;
            }
            calculator.setValue(target, required);
            output = calculator.calculation();
            if(output[0] != 0){
                log.append(" " + ForgingText.forgingText[AnvilView.runNum][1] + "[" + target + "] 的最优锻造解为:\n");
                for(int i : output){
                    log.append(" " + (i + 1));
                }
                for(int i = 0; i < output.length; i++){
                    int j = i / 9;
                    anvilView.mainPanel.remove(anvilView.logLabel[i]);
                    anvilView.logLabel[i] = ImgCreator.createImgJLabel(26 + (34 * (i - (9 * j))), 310 + (34 * j), "image/forging/forging_" + (output[i] + 1) + ".png");
                    anvilView.mainPanel.add(anvilView.logLabel[i]);
                }
            }
            else{
                log.append("在15步之内无法得到结果");
            }
        }
        else{
            log.append(" 未选定工具头");
        }
    }

    public static void setAnvilView(AnvilView anvilView){
        Log.anvilView = anvilView;
    }
}