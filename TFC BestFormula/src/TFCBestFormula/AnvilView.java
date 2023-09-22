package TFCBestFormula;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class AnvilView extends JPanel {
    public static String[] forgingName = Arrays.copyOfRange(ConfigLoad.langText, 0, 8);
    public static int runNum = -1;
    public static int recipeNum = -2;
    int width = 368, height = 453;
    JLayeredPane pane, selectWindow;
    JPanel bgPanel, mainPanel;
    JLabel title, needMetal, hammer, flux, recipe;
    JTextField target, seed;
    JTextArea log;
    JButton choose, select, start;
    JButton[] forgingButton;
    JLabel[] showLabel, logLabel;
    ImgJSlider targetSlider, forgingSlider;

    public AnvilView(){
        init();
        setSize(width, height);
        setVisible(true);
    }

    public void init(){
        ImgSelect.allLoad();
        Log.setAnvilView(this);
        ListenerCreator.setAnvilView(this);
        pane = new JLayeredPane();
        pane.setBounds(0, 0, width, height);
        bgPanel = ImgCreator.createBgImgJPanel(0, 0, "image/background_anvil.png");
        mainPanel = new JPanel(null);
        mainPanel.setBounds(0, 0, width, height);
        mainPanel.setOpaque(false);
        needMetal = ImgCreator.createImgJLabel(62, 136, "image/not_need_metal.png");
        hammer = ImgCreator.createImgJLabel(258, 136, "image/not_hammer.png");
        flux = ImgCreator.createImgJLabel(294, 136, "image/not_flux.png");
        recipe = ImgCreator.createImgJLabel(26, 136, "image/not_recipe.png");
        mainPanel.add(needMetal);
        mainPanel.add(hammer);
        mainPanel.add(flux);
        title = new JLabel(ConfigLoad.langText[8]);
        title.setFont(new Font(null,Font.PLAIN,14));
        title.setBounds(13, 6, 20, 20);
        mainPanel.add(title);

        target = new JTextField("0");
        targetSlider = new ImgJSlider(0, 145, 0, "image/pointer_red.png");
        targetSlider.setBounds(23, 188, 305, 16);
        targetSlider.addChangeListener(ListenerCreator.createTargetSliderListener(target));
        mainPanel.add(targetSlider);
        forgingSlider = new ImgJSlider(0, 145, 0, "image/pointer_green.png");
        forgingSlider.setBounds(23, 200, 305, 16);
        forgingSlider.setEnabled(false);
        mainPanel.add(forgingSlider);

        target.setFont(new Font(null,Font.PLAIN,12));
        target.setHorizontalAlignment(SwingConstants.RIGHT);
        target.setForeground(Color.white);
        target.setBorder(null);
        target.setBounds(300, 171, 26, 15);
        target.addKeyListener(ListenerCreator.createTargetListener(target, targetSlider));
        target.setOpaque(false);
        mainPanel.add(target);
        seed = new JTextField("");
        seed.setFont(new Font(null,Font.PLAIN,12));
        seed.setHorizontalAlignment(SwingConstants.RIGHT);
        seed.setForeground(Color.white);
        seed.setBorder(null);
        seed.setBounds(176, 227, 160, 15);
        seed.addKeyListener(ListenerCreator.createSeedListener(seed));
        seed.setOpaque(false);
        mainPanel.add(seed);
        log = new JTextArea();
        log.setFont(new Font(null,Font.PLAIN,14));
        log.setForeground(Color.white);
        log.setBounds(21, 250, 315, 148);
        log.setText(ConfigLoad.logText_1 + "\n" + ConfigLoad.logText_2);
        log.setOpaque(false);
        log.setEditable(false);
        mainPanel.add(log);

        forgingButton = new JButton[8];
        for(int i = 0; i < 8; i++){
            String tipText = "";
            String imgSrc = "image/forging_" + (i + 1) + ".png";
            if(i < 4){
                forgingButton[i] = ImgCreator.createBgImgJButton(106 + 36*i, 100, imgSrc);
            }
            else{
                forgingButton[i] = ImgCreator.createBgImgJButton(106 + 36*(i-4), 136, imgSrc);
            }
            if(Calculation.value[i] > 0){
                tipText = "+";
            }
            forgingButton[i].addActionListener(ListenerCreator.createStepButtonListener(i, forgingSlider));
            forgingButton[i].setToolTipText(forgingName[i] + " " + tipText + Calculation.value[i]);
            mainPanel.add(forgingButton[i]);
        }

        start = ImgCreator.createBgImgJButton(274, 17, "image/start.png");
        start.addActionListener(ListenerCreator.createStartButtonListener(targetSlider, log));
        mainPanel.add(start);
        choose = ImgCreator.createChooseJButton(42, 82, "");
        mainPanel.add(choose);

        showLabel = new JLabel[3];
        for(int i = 0; i < 3; i++){
            showLabel[i] = new JLabel();
            mainPanel.add(showLabel[i]);
        }
        logLabel = new JLabel[15];
        for(int i = 0; i < 15; i++){
            logLabel[i] = new JLabel();
            mainPanel.add(logLabel[i]);
        }

        pane.add(bgPanel, 10);
        pane.add(mainPanel, 0);
        add(pane);
        setLayout(null);
    }
}