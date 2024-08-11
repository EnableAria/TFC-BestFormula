package TFCBestFormula;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class ListenerCreator {   //自定义监听类
    public static MainUI mainUI;
    public static AnvilView anvilView;

    public static void setMainUI(MainUI mainUI){
        ListenerCreator.mainUI = mainUI;
    }
    public static void setAnvilView(AnvilView anvilView){
        ListenerCreator.anvilView = anvilView;
    }

    public static TargetSliderListener createTargetSliderListener(JTextField target){   //目标滑块监听器创建方法
        TargetSliderListener targetSliderListener = new TargetSliderListener();
        targetSliderListener.setTarget(target);
        return targetSliderListener;
    }

    public static TargetListener createTargetListener(JTextField target, ImgJSlider targetSlider){  //目标输入框监听器创建方法
        TargetListener targetListener = new TargetListener();
        targetListener.setJTextField(target);
        targetListener.setForgingSlider(targetSlider);
        return targetListener;
    }

    public static SeedListener createSeedListener(JTextField seed){  //种子输入框监听器创建方法
        SeedListener seedListener = new SeedListener();
        seedListener.setJTextField(seed);
        return seedListener;
    }

    public static StepButtonListener createStepButtonListener(int i, ImgJSlider forgingSlider){ //步骤按钮监听器创建方法
        StepButtonListener stepButtonListener = new StepButtonListener();
        stepButtonListener.setButtonValue(Calculation.value[i]);
        stepButtonListener.setForgingSlider(forgingSlider);
        return stepButtonListener;
    }

    public static ChooseButtonListener createChooseButtonListener(){    //工具选择按钮监听器创建方法
        return new ChooseButtonListener();
    }

    public static ItemButtonListener createItemButtonListener(){    //工具按钮监听器创建方法
        return new ItemButtonListener();
    }

    public static SelectButtonListener createSelectButtonListener(){    //配方选择按钮监听器创建方法
        return new SelectButtonListener();
    }

    public static RecipeButtonListener createRecipeButtonListener(){    //配方按钮监听器创建方法
        return new RecipeButtonListener();
    }

    public static BackButtonListener createBackButtonListener(){    //背景返回按钮监听器创建方法
        return new BackButtonListener();
    }

    public static StartButtonListener createStartButtonListener(ImgJSlider targetSlider, ImgJSlider forgingSlider, JTextArea log){    //开始按钮监听器创建方法
        StartButtonListener startButtonListener = new StartButtonListener();
        startButtonListener.setTargetSlider(targetSlider);
        startButtonListener.setForgingSlider(forgingSlider);
        startButtonListener.setLog(log);
        return startButtonListener;
    }

    public static ExitListener createExitListener(){    //关闭程序监听器创建方法
        return new ExitListener();
    }
}

class TargetSliderListener implements ChangeListener {   //目标滑块监听类
    JTextField target;
    public void stateChanged(ChangeEvent e){
        ImgJSlider imgJSlider = (ImgJSlider)e.getSource();
        String value = imgJSlider.getValue() + "";
        target.setText(value);
    }

    public void setTarget(JTextField target){
        this.target = target;
    }
}

class TargetListener implements KeyListener {    //目标输入框监听类
    JTextField target;
    ImgJSlider targetSlider;
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        int textNum = 0;
        StringBuilder outText = new StringBuilder();
        String text = target.getText();
        if(key != 8 && key != 37 && key != 39){
            if(!text.isEmpty()){
                try{
                    textNum = Integer.parseInt(text);
                    if(!text.equals(textNum + "")){
                        target.setText(textNum + "");
                    }
                }
                catch(RuntimeException e1){
                    String[] num = text.split("\\D+");
                    if(num[0].isEmpty()){
                        num = Arrays.copyOfRange(num, 1, num.length);
                    }
                    for(String s:num){
                        outText.append(s);
                    }
                    textNum = Integer.parseInt(outText.toString());
                    target.setText(textNum + "");
                }
                if(textNum < 0 || textNum > 145){
                    if(textNum < 0){
                        textNum = 0;
                    }
                    else{
                        textNum = 145;
                    }
                    target.setText(textNum + "");
                }
            }
            else{
                target.setText(textNum + "");
            }
            targetSlider.setValue(textNum);
        }
    }

    public void keyReleased(KeyEvent e){
        keyPressed(e);
    }

    public void keyTyped(KeyEvent e){
        keyPressed(e);
    }

    public void setJTextField(JTextField target){
        this.target = target;
    }

    public void setForgingSlider(ImgJSlider targetSlider){
        this.targetSlider = targetSlider;
    }
}

class SeedListener implements KeyListener { //种子输入框监听类
    JTextField seed;
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        String text = seed.getText();
        try{
            if(key != 8 && key != 37 && key != 39){
                if(!text.isEmpty()){
                    if(text.length() > 20){
                        text = text.substring(0, 20);
                        seed.setText(text);
                    }
                    if(text.matches("-?\\d{1,19}")){
                        ListenerCreator.anvilView.mainPanel.add(ListenerCreator.anvilView.recipe);
                        if(AnvilView.recipeNum < -1){
                            AnvilView.recipeNum = -1;
                        }
                    }
                    else {
                        ListenerCreator.anvilView.mainPanel.remove(ListenerCreator.anvilView.recipe);
                        AnvilView.recipeNum = -2;
                    }
                }
                else {
                    ListenerCreator.anvilView.mainPanel.remove(ListenerCreator.anvilView.recipe);
                    AnvilView.recipeNum = -2;
                }
                ListenerCreator.mainUI.validate();
                ListenerCreator.mainUI.repaint();
            }
        }
        catch(ArrayIndexOutOfBoundsException ignored){}
    }

    public void keyReleased(KeyEvent e){
        keyPressed(e);
    }

    public void keyTyped(KeyEvent e){
        keyPressed(e);
    }

    public void setJTextField(JTextField seed){
        this.seed = seed;
    }
}

class StepButtonListener implements ActionListener { //步骤按钮监听类
    int buttonValue;
    ImgJSlider forgingSlider;
    public void actionPerformed(ActionEvent e){
        int value = forgingSlider.getValue() + buttonValue;
        if(value < 0){
            value = 0;
        }
        else if(value > 145){
            value = 145;
        }
        forgingSlider.setValue(value);
    }

    public void setButtonValue(int buttonValue){
        this.buttonValue = buttonValue;
    }

    public void setForgingSlider(ImgJSlider forgingSlider){
        this.forgingSlider = forgingSlider;
    }
}

class ChooseButtonListener implements ActionListener {   //工具选择按钮监听类
    public void actionPerformed(ActionEvent e){
        ListenerCreator.mainUI.remove(ListenerCreator.mainUI.anvilView);
        ListenerCreator.mainUI.add(ListenerCreator.mainUI.chooseView);
        ListenerCreator.mainUI.validate();
        ListenerCreator.mainUI.repaint();
    }
}

class ItemButtonListener implements ActionListener { //工具按钮监听类
    public static String[] order = Arrays.copyOfRange(ConfigLoad.langText, 22, 27);
    public void actionPerformed(ActionEvent e){
        JButton itemButton = (JButton)e.getSource();
        int num = Integer.parseInt(itemButton.getName());
        AnvilView.runNum = num;
        ListenerCreator.anvilView.mainPanel.remove(ListenerCreator.anvilView.choose);
        ListenerCreator.anvilView.choose = ImgCreator.createChooseJButton(42, 82, num + "");
        ListenerCreator.anvilView.mainPanel.add(ListenerCreator.anvilView.choose);
        ListenerCreator.anvilView.mainPanel.remove(ListenerCreator.anvilView.needMetal);
        if(num < ConfigLoad.internalNum){
            ListenerCreator.anvilView.needMetal = ImgCreator.createImgJLabel(62, 136, "image/need_metal/" + ConfigLoad.needMetalText[Integer.parseInt(ConfigLoad.forgingText[num][8])][0] + ".png");
            ListenerCreator.anvilView.needMetal.setToolTipText(ConfigLoad.langText[11] + ConfigLoad.needMetalText[Integer.parseInt(ConfigLoad.forgingText[num][8])][1]);
        }
        else {
            int i, j = 1;
            for(i = 0; i < ConfigLoad.internalMetalNum; i++){
                if(ConfigLoad.forgingText[num][8].equals(ConfigLoad.needMetalText[i][0])){
                    j = 0;
                    break;
                }
            }
            if(j == 0){
                ListenerCreator.anvilView.needMetal = ImgCreator.createImgJLabel(62, 136, "image/need_metal/" + ConfigLoad.forgingText[num][8] + ".png");
                ListenerCreator.anvilView.needMetal.setToolTipText(ConfigLoad.langText[11] + ConfigLoad.needMetalText[i][1]);
            }
            else{
                for(i = 0; i < ConfigLoad.externalMetalNum; i++){
                    if(ConfigLoad.forgingText[num][8].equals(ConfigLoad.needMetalText[ConfigLoad.internalMetalNum + i][0])){
                        break;
                    }
                }
                ListenerCreator.anvilView.needMetal = ImgCreator.createImgJLabel(62, 136, num);
                ListenerCreator.anvilView.needMetal.setToolTipText(ConfigLoad.langText[11] + ConfigLoad.needMetalText[ConfigLoad.internalMetalNum + i][1]);
            }
        }
        ListenerCreator.mainUI.anvilView.mainPanel.add(ListenerCreator.anvilView.needMetal);
        for(int i = 0; i < 3; i++){
            ListenerCreator.anvilView.mainPanel.remove(ListenerCreator.anvilView.showLabel[i]);
            ListenerCreator.anvilView.showLabel[i] = ImgCreator.createImgShowJLabel(194 - (38 * i), 14, Integer.parseInt(ConfigLoad.forgingText[num][i + 5]), Integer.parseInt(ConfigLoad.forgingText[num][i + 2]));
            if(!ConfigLoad.forgingText[num][i + 2].equals("0")){
                ListenerCreator.anvilView.showLabel[i].setToolTipText(AnvilView.forgingName[Integer.parseInt(ConfigLoad.forgingText[num][i + 2]) - 1] + " " + order[Integer.parseInt(ConfigLoad.forgingText[num][i + 5]) - 1]);
            }
            ListenerCreator.anvilView.mainPanel.add(ListenerCreator.mainUI.anvilView.showLabel[i]);
        }
        if(AnvilView.recipeNum > -2){
            int recipeListNum = Integer.parseInt(ConfigLoad.recipeID[num][1]);
            try{
                ListenerCreator.anvilView.seed.setEditable(false);
                ListenerCreator.anvilView.seed.setForeground(Color.lightGray);
                ListenerCreator.anvilView.mainPanel.remove(ListenerCreator.anvilView.select);
            }
            catch(NullPointerException ignored){}

            if(recipeListNum <= 0){
                AnvilView.recipeNum = -1;
            }
            else{
                if(!(AnvilView.recipeNum >= ImgSelect.selectNum[recipeListNum - 1][1] && AnvilView.recipeNum < (ImgSelect.selectNum[recipeListNum - 1][0] + ImgSelect.selectNum[recipeListNum - 1][1]))){
                    AnvilView.recipeNum = -1;
                }
            }
            ListenerCreator.anvilView.select = ImgCreator.createSelectJButton(26, 136, AnvilView.recipeNum);
            ListenerCreator.anvilView.mainPanel.add(ListenerCreator.anvilView.select);
        }
        int target = 0;
        if(AnvilView.recipeNum >= -1){
            target = SeedCalculator.getTarget();
        }
        ListenerCreator.anvilView.target.setText(target + "");
        ListenerCreator.anvilView.targetSlider.setValue(target);
        ListenerCreator.anvilView.initial.setText("0");
        ListenerCreator.anvilView.forgingSlider.setValue(0);
        ListenerCreator.anvilView.mainPanel.remove(ListenerCreator.anvilView.hammer);
        ListenerCreator.anvilView.hammer = ImgCreator.createImgJLabel(258, 136, "image/hammer.png");
        ListenerCreator.anvilView.hammer.setToolTipText(ConfigLoad.langText[12]);
        ListenerCreator.anvilView.mainPanel.add(ListenerCreator.anvilView.hammer);
        ListenerCreator.mainUI.remove(ListenerCreator.mainUI.chooseView);
        ListenerCreator.mainUI.add(ListenerCreator.mainUI.anvilView);
        ListenerCreator.mainUI.validate();
        ListenerCreator.mainUI.repaint();
    }
}

class SelectButtonListener implements ActionListener {  //配方选择按钮监听类
    public void actionPerformed(ActionEvent e){
        ListenerCreator.anvilView.selectWindow = ImgSelect.selectWindow[Integer.parseInt(ConfigLoad.recipeID[AnvilView.runNum][1]) - 1];
        ListenerCreator.anvilView.pane.add(ListenerCreator.anvilView.selectWindow, 0);
        ListenerCreator.anvilView.validate();
        ListenerCreator.anvilView.repaint();
    }
}

class RecipeButtonListener implements ActionListener {  //配方按钮监听类
    public void actionPerformed(ActionEvent e){
        JButton recipeButton = (JButton)e.getSource();
        int recipeNum = Integer.parseInt(recipeButton.getName());
        AnvilView.recipeNum = recipeNum;
        int target = SeedCalculator.getTarget();
        ListenerCreator.anvilView.target.setText(target + "");
        ListenerCreator.anvilView.targetSlider.setValue(target);
        ListenerCreator.anvilView.mainPanel.remove(ListenerCreator.mainUI.anvilView.select);
        ListenerCreator.anvilView.select = ImgCreator.createSelectJButton(26, 136, recipeNum);
        ListenerCreator.anvilView.mainPanel.add(ListenerCreator.mainUI.anvilView.select);
        ListenerCreator.anvilView.pane.remove(ListenerCreator.anvilView.selectWindow);
        ListenerCreator.anvilView.validate();
        ListenerCreator.anvilView.repaint();
    }
}

class BackButtonListener implements ActionListener {    //背景返回按钮监听类
    public void actionPerformed(ActionEvent e){
        ListenerCreator.anvilView.pane.remove(ListenerCreator.anvilView.selectWindow);
        ListenerCreator.anvilView.validate();
        ListenerCreator.anvilView.repaint();
    }
}

class StartButtonListener implements ActionListener {   //开始按钮监听类
    ImgJSlider targetSlider, forgingSlider;
    JTextArea log;
    public void actionPerformed(ActionEvent e){
        Log.outputLog(targetSlider.getValue(), forgingSlider.getValue(), log);
    }

    public void setTargetSlider(ImgJSlider targetSlider){
        this.targetSlider = targetSlider;
    }

    public void setForgingSlider(ImgJSlider forgingSlider){
        this.forgingSlider = forgingSlider;
    }

    public void setLog(JTextArea log){
        this.log = log;
    }
}

class ExitListener extends WindowAdapter{   //关闭程序监听类
    public void windowClosing(WindowEvent e) {
        super.windowClosing(e);
        SaveSettings.saveSettings(ListenerCreator.anvilView.seed.getText());
    }
}