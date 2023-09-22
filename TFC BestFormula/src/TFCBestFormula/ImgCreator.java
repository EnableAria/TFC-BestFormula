package TFCBestFormula;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImgCreator {   //附图控件创建类
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

    public static JButton createChooseJButton(int x, int y, String imgNum){    //创建附带背景图和图标的按钮(工具选择按钮)
        try{
            JButton jButton = new JButton();
            BufferedImage bgImage = JarFileInput.loadJarBufferedImg("image/button.png");
            BufferedImage icoImage;
            Graphics2D g2d;
            if(imgNum.isEmpty()){
                icoImage = JarFileInput.loadJarBufferedImg("image/reel.png");
                jButton.setToolTipText(ConfigLoad.langText[9] + ConfigLoad.langText[10]);
            }
            else{
                int num = Integer.parseInt(imgNum);
                if(num < ConfigLoad.internalNum){
                    icoImage = JarFileInput.loadJarBufferedImg("image/tool_head/" + ConfigLoad.forgingText[num][0] + ".png");
                }
                else {
                    try{
                        icoImage = ImageIO.read(new FileInputStream(ConfigLoad.path + "image/" + ConfigLoad.forgingText[num][0] + ".png"));
                    }
                    catch (FileNotFoundException e){
                        icoImage = JarFileInput.loadJarBufferedImg("image/need_metal/unknown.png");
                    }
                }
                jButton.setToolTipText(ConfigLoad.forgingText[AnvilView.runNum][1]);
            }
            g2d = bgImage.createGraphics();
            g2d.drawImage(icoImage, 2, 2, 32, 32, null);
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

    public static JButton createForgingJButton(int x, int y, int num){   //创建附带背景图和图标的按钮(工具按钮)
        try{
            BufferedImage icoImage;
            BufferedImage bgImage = JarFileInput.loadJarBufferedImg("image/button.png");
            if(num < ConfigLoad.internalNum){
                icoImage = JarFileInput.loadJarBufferedImg("image/tool_head/" + ConfigLoad.forgingText[num][0] + ".png");
            }
            else {
                try{
                    icoImage = ImageIO.read(new FileInputStream(ConfigLoad.path + "image/" + ConfigLoad.forgingText[num][0] + ".png"));
                }
                catch (FileNotFoundException e){
                    icoImage = JarFileInput.loadJarBufferedImg("image/need_metal/unknown.png");
                }
            }
            Graphics2D g2d = bgImage.createGraphics();
            g2d.drawImage(icoImage, 2, 2, 32, 32, null);
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

    public static JButton createSelectJButton(int x, int y, int recipeNum){ //创建附带背景图和图标的按钮(配方选择按钮)
        if(recipeNum > -2){
            try{
                JButton jButton = new JButton();
                ImageIcon icoImage;

                try{
                    ListenerCreator.anvilView.mainPanel.remove(ListenerCreator.anvilView.recipe);
                }
                catch (NullPointerException ignored){}
                if(recipeNum > -1){
                    icoImage = new ImageIcon(JarFileInput.loadJarImg("image/recipe/recipe_" + ConfigLoad.metalList[recipeNum][0] + ".png"));
                    jButton.setToolTipText(ConfigLoad.langText[28] + ": " + ConfigLoad.metalList[recipeNum][1]);
                    jButton.addActionListener(ListenerCreator.createSelectButtonListener());
                }
                else{
                    if(AnvilView.runNum < ConfigLoad.internalNum){
                        if(Integer.parseInt(ConfigLoad.recipeID[AnvilView.runNum][1]) == 0){
                            icoImage = new ImageIcon(JarFileInput.loadJarImg("image/recipe/recipe_not.png"));
                            jButton.setToolTipText(ConfigLoad.langText[27] + ConfigLoad.langText[28]);
                        }
                        else {
                            icoImage = new ImageIcon(JarFileInput.loadJarImg("image/recipe/recipe_null.png"));
                            jButton.setToolTipText(ConfigLoad.langText[9] + ConfigLoad.langText[28]);
                            jButton.addActionListener(ListenerCreator.createSelectButtonListener());
                        }
                    }
                    else {
                        icoImage = new ImageIcon(JarFileInput.loadJarImg("image/recipe/recipe_not.png"));
                        jButton.setToolTipText(ConfigLoad.langText[27] + ConfigLoad.langText[28]);
                    }
                }

                jButton.setBounds(x, y, icoImage.getIconWidth(), icoImage.getIconHeight());
                jButton.setContentAreaFilled(false);
                jButton.setBorder(null);
                jButton.setIcon(icoImage);
                return jButton;
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static JButton createRecipeJButton(int x, int y, String recipeID){   //创建附带背景图和图标的按钮(配方按钮)
        try{
            BufferedImage icoImage;
            BufferedImage bgImage = JarFileInput.loadJarBufferedImg("image/button.png");
            icoImage = JarFileInput.loadJarBufferedImg("image/recipe/recipe_" + recipeID + ".png");
            Graphics2D g2d = bgImage.createGraphics();
            g2d.drawImage(icoImage, 2, 2, 32, 32, null);
            g2d.dispose();
            ImageIcon imageIcon = new ImageIcon(bgImage);
            JButton jButton = new JButton();
            jButton.setBounds(x, y, imageIcon.getIconWidth(), imageIcon.getIconHeight());
            jButton.setContentAreaFilled(false);
            jButton.setBorder(null);
            jButton.setIcon(imageIcon);
            jButton.addActionListener(ListenerCreator.createRecipeButtonListener());
            return jButton;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static JLabel createFreeImgJLabel(int x, int y, String imgSrc){   //创建图片标签(自由)
        JLabel jLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(JarFileInput.loadJarImg(imgSrc));
        jLabel.setIcon(imageIcon);
        jLabel.setBounds(x, y, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        return jLabel;
    }

    public static JLabel createImgJLabel(int x, int y, String imgSrc){   //创建图片标签(内部)
        BufferedImage nullImage = JarFileInput.loadJarBufferedImg("image/null.png");
        BufferedImage image = JarFileInput.loadJarBufferedImg(imgSrc);
        Graphics2D g2d = nullImage.createGraphics();
        g2d.drawImage(image, 0, 0, 32, 32, null);
        g2d.dispose();
        JLabel jLabel = new JLabel();
        jLabel.setBounds(x, y, 32, 32);
        jLabel.setIcon(new ImageIcon(nullImage));
        return jLabel;
    }

    public static JLabel createImgJLabel(int x, int y, int num){    //创建图片标签(外部)
        BufferedImage nullImage = JarFileInput.loadJarBufferedImg("image/null.png");
        BufferedImage image;
        JLabel jLabel = new JLabel();
        try {
            try {
                image = ImageIO.read(new FileInputStream(ConfigLoad.path + "image/" + ConfigLoad.forgingText[num][8] + ".png"));
            }
            catch (FileNotFoundException e){
                image = JarFileInput.loadJarBufferedImg("image/need_metal/unknown.png");
            }
            Graphics2D g2d = nullImage.createGraphics();
            g2d.drawImage(image, 0, 0, 32, 32, null);
            g2d.dispose();
            jLabel.setBounds(x, y, 32, 32);
            jLabel.setIcon(new ImageIcon(nullImage));
        }
        catch (Exception e){
            e.printStackTrace();
        }
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