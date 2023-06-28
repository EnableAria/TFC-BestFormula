package TFCBestFormula;

import javax.swing.*;
import javax.swing.plaf.metal.MetalSliderUI;
import java.awt.*;


public class ImgJSlider extends JSlider{    //自定义滑块类
    Image image;
    public ImgJSlider(int min_value, int max_value, int prefer_value, String imgSrc){   //重写构造方法
        super(min_value, max_value, prefer_value);
        image = Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource(imgSrc));
        setUI(new ImgJSliderUI());
        setPaintTrack(false);   //滑轨不可见
        setOpaque(false);   //背景不可见
    }

    class ImgJSliderUI extends MetalSliderUI{   //自定义滑块UI类
        public void paintThumb(Graphics g){ //更改滑块拇指图标
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(image, thumbRect.x+3, thumbRect.y, 10, 10, null);
        }
    }
}