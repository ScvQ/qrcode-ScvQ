package cn.spiderpig.demo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CreateQRCode {

    public static void main(String[] args) throws IOException {

        int version = 7;
        Qrcode qrcode = new Qrcode();

        qrcode.setQrcodeErrorCorrect('M');
        qrcode.setQrcodeEncodeMode('B');
        qrcode.setQrcodeVersion(version);
        String data = "www.spiderpig.cn";
        int width = 67 + 12 * (version - 1);
        int height = 67 + 12 * (version - 1);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D gs = bufferedImage.createGraphics();
        gs.setBackground(Color.WHITE);
        gs.setColor(Color.BLACK);
        gs.clearRect(0, 0, 300, 300);

        int pixoff = 2;

        byte[] d = data.getBytes("gb2312");
        if (d.length > 0 && d.length < 120) {
            boolean[][] s = qrcode.calQrcode(d);

            for (int i = 0; i < s.length; i++) {
                for (int j = 0; j < s.length; j++) {
                    if (s[j][i]) {
                        gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                    }
                }
            }
        }

        gs.dispose();
        bufferedImage.flush();

        ImageIO.write(bufferedImage, "png", new File("g://2.png"));

    }

}
