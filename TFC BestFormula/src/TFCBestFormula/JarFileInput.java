package TFCBestFormula;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class JarFileInput {  //文件加载类
    public static Image loadJarImg(String fileSrc){ //内部图片加载类(Image)
        Image image = null;
        InputStream is = JarFileInput.class.getResourceAsStream(fileSrc);
        try {
            assert is != null;
            image = ImageIO.read(is);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public static BufferedImage loadJarBufferedImg(String fileSrc){ //内部图片加载类(BufferedImage)
        BufferedImage bufferedImage = null;
        InputStream is = JarFileInput.class.getResourceAsStream(fileSrc);
        try {
            assert is != null;
            bufferedImage = ImageIO.read(is);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return bufferedImage;
    }
}
