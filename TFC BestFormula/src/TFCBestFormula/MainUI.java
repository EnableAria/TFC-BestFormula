package TFCBestFormula;

import javax.swing.*;
import java.awt.*;

public class MainUI extends JFrame {
    BorderLayout borderLayout;
    AnvilView anvilView;
    ChooseView chooseView;
    public MainUI(){
        init();
        setLocation(775,300);
        setSize(368, 453);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void init(){
        ConfigLoad.load();
        ChooseButtonListener.setMainUI(this);
        ItemButtonListener.setMainUI(this);
        setIconImage(JarFileInput.loadJarImg("image/ico.png"));
        setTitle("TFC Best Formula");
        borderLayout = new BorderLayout();
        setLayout(borderLayout);
        chooseView = new ChooseView();
        anvilView = new AnvilView();
        add(anvilView, BorderLayout.CENTER);
        validate();
    }
}