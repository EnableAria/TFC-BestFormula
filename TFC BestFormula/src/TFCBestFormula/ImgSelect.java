package TFCBestFormula;

import javax.swing.*;

public class ImgSelect {
    static int width = 368, height = 453;
    static int[][] selectNum = {{9, 0}, {19, 0}, {4, 5}, {2, 7}, {5, 4}};
    public static JLayeredPane[] selectWindow = new JLayeredPane[5];

    public static void allLoad(){
        for(int i = 1; i < 6; i++){
            createImgSelect(i);
        }
    }

    public static void createImgSelect(int num){
        selectWindow[num - 1] = new JLayeredPane();
        selectWindow[num - 1].setBounds(0, 0, width, height);

        JButton backButton = new JButton();
        backButton.setBounds(0, 0, width, height);
        backButton.setContentAreaFilled(false);
        backButton.setBorder(null);
        backButton.addActionListener(ListenerCreator.createBackButtonListener());
        selectWindow[num - 1].add(backButton, 10);

        JLabel selectLabel = ImgCreator.createFreeImgJLabel(16, 170, "image/select/select_" + num + ".png");
        selectWindow[num - 1].add(selectLabel, 0);

        JButton[] recipeButton = new JButton[selectNum[num - 1][0]];
        for(int i = 0; i < selectNum[num - 1][0]; i++){
            recipeButton[i] = ImgCreator.createRecipeJButton(24 + (36 * (i - (7 * (i / 7)))), 178 + (36 * (i / 7)), ConfigLoad.metalList[selectNum[num - 1][1] + i][0]);
            assert recipeButton[i] != null;
            recipeButton[i].setName((selectNum[num - 1][1] + i) + "");
            recipeButton[i].setToolTipText(ConfigLoad.metalList[selectNum[num - 1][1] + i][1]);
            selectWindow[num - 1].add(recipeButton[i], 0);
        }
    }
}