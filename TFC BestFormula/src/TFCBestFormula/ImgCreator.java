package TFCBestFormula;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImgCreator {
    public static JPanel createBgImgJPanel(int x, int y, String imgSrc){    //创建附带背景图的面板
        ImageIcon imageIcon = new ImageIcon(JarFileInput.loadJarImg(imgSrc));
        JPanel jPanel = new JPanel(null);
        JLabel label = new JLabel(imageIcon);
        label.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        jPanel.setBounds(x, y, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        jPanel.setOpaque(true);
        jPanel.add(label);
        return jPanel;
    }

    public static JButton createBgImgJButton(int x, int y, String imgSrc){  //创建附带图片的按钮(步骤按钮)
        ImageIcon imageIcon = new ImageIcon(JarFileInput.loadJarImg(imgSrc));
        JButton jButton = new JButton();
        jButton.setBounds(x, y, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        jButton.setContentAreaFilled(false);
        jButton.setIcon(imageIcon);
        jButton.setBorder(null);
        return jButton;
    }

    public static JButton createChooseJButton(int x, int y, String imgName){    //创建附带背景图和图标的按钮(选择按钮)
        try{
            JButton jButton = new JButton();
            BufferedImage bgImage = JarFileInput.loadJarBufferedImg("image/button.png");
            BufferedImage icoImage;
            Graphics2D g2d;
            if(imgName.isEmpty()){
                icoImage = JarFileInput.loadJarBufferedImg("image/reel.png");
                jButton.setToolTipText("选择锻造品");
                g2d = bgImage.createGraphics();
                g2d.drawImage(icoImage, 2, 2, icoImage.getWidth(), icoImage.getHeight(), null);
            }
            else{
                icoImage = JarFileInput.loadJarBufferedImg("image/tool_head/" + imgName + ".png");
                jButton.setToolTipText(ForgingText.forgingText[AnvilView.runNum][1]);
                g2d = bgImage.createGraphics();
                g2d.drawImage(icoImage, 2, 2, icoImage.getWidth()*2, icoImage.getHeight()*2, null);
            }
            g2d.dispose();
            ImageIcon imageIcon = new ImageIcon(bgImage);
            jButton.setBounds(x, y, imageIcon.getIconWidth(), imageIcon.getIconHeight());
            jButton.setContentAreaFilled(false);
            jButton.setBorder(null);
            jButton.setIcon(imageIcon);
            jButton.addActionListener(ListenerCreator.createChooseButtonListener());
            return jButton;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static JButton createForgingJButton(int x, int y, String imgName){   //创建附带背景图和图标的按钮(工具按钮)
        try{
            BufferedImage bgImage = JarFileInput.loadJarBufferedImg("image/button.png");
            BufferedImage icoImage = JarFileInput.loadJarBufferedImg("image/tool_head/" + imgName + ".png");
            Graphics2D g2d = bgImage.createGraphics();
            g2d.drawImage(icoImage, 2, 2, icoImage.getWidth()*2, icoImage.getHeight()*2, null);
            g2d.dispose();
            ImageIcon imageIcon = new ImageIcon(bgImage);
            JButton jButton = new JButton();
            jButton.setBounds(x, y, imageIcon.getIconWidth(), imageIcon.getIconHeight());
            jButton.setContentAreaFilled(false);
            jButton.setBorder(null);
            jButton.setIcon(imageIcon);
            jButton.addActionListener(ListenerCreator.createItemButtonListener());
            return jButton;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static JLabel createImgJLabel(int x, int y, String imgSrc){   //创建图片标签
        ImageIcon imageIcon = new ImageIcon(JarFileInput.loadJarBufferedImg(imgSrc));
        JLabel jLabel = new JLabel();
        jLabel.setBounds(x, y, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        jLabel.setIcon(imageIcon);
        return jLabel;
    }

    public static JLabel createImgShowJLabel(int x, int y, int bgNum, int imgNum){   //创建复合图片标签
        try{
            BufferedImage bgImage = JarFileInput.loadJarBufferedImg("image/step_" + bgNum + ".png");
            BufferedImage icoImage = JarFileInput.loadJarBufferedImg("image/forging/forging_" + imgNum + ".png");
            Graphics2D g2d = bgImage.createGraphics();
            g2d.drawImage(icoImage, 11, 7, 18, 18, null);
            g2d.dispose();
            ImageIcon imageIcon = new ImageIcon(bgImage);
            JLabel jLabel = new JLabel(imageIcon);
            jLabel.setBounds(x, y, imageIcon.getIconWidth(), imageIcon.getIconHeight());
            return jLabel;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}