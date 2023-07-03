package TFCBestFormula;

import javax.swing.*;
import java.awt.*;

public class ChooseView extends JPanel {
    int width = 368, height = 453, chooseX = 13, chooseY = 28, chooseInterval = 36;
    JLayeredPane pane;
    JPanel bgPanel, mainPanel;
    JLabel title;
    JButton[] itemButton;

    public ChooseView(){
        init();
        setSize(width, height);
        setVisible(true);
    }

    public void init(){
        pane = new JLayeredPane();
        pane.setBounds(0, 0, width, height);
        bgPanel = ImgCreator.createBgImgJPanel(0, 0, "image/background_choose.png");
        mainPanel = new JPanel(null);
        mainPanel.setBounds(0, 0, width, height);
        mainPanel.setOpaque(false);

        title = new JLabel(ConfigLoad.langText[9]);
        title.setFont(new Font(null,Font.PLAIN,14));
        title.setBounds(13, 6, 40, 20);
        mainPanel.add(title);

        itemButton = new JButton[ConfigLoad.itemNum];
        for(int i = 0; i < ConfigLoad.itemNum; i++){
            int j = i / 9;
            itemButton[i] = ImgCreator.createForgingJButton(chooseX + (chooseInterval * (i - (9 * j))), chooseY + (chooseInterval * j), i);
            itemButton[i].setToolTipText(ConfigLoad.forgingText[i][1]);
            itemButton[i].setName(i + "");
            mainPanel.add(itemButton[i]);
        }

        pane.add(bgPanel, 10);
        pane.add(mainPanel, 0);
        add(pane);
        setLayout(null);
    }
}