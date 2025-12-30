package com.example.talkweb_spring.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

public class CaptchaUtil {
    private static final int WIDTH = 120;
    private static final int HEIGHT = 40;
    private static final int CODE_LENGTH = 4;
    private static final String CHARACTERS = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private static final Random random = new Random();

    public static CaptchaResult generateCaptcha() {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        drawInterferenceLines(g);

        String captchaCode = generateRandomCode();
        drawCaptchaCode(g, captchaCode);

        g.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "JPEG", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new CaptchaResult(captchaCode, baos.toByteArray());
    }

    private static String generateRandomCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    private static void drawCaptchaCode(Graphics2D g, String code) {
        g.setFont(new Font("SansSerif", Font.BOLD, 24));
        for (int i = 0; i < code.length(); i++) {
            g.setColor(new Color(random.nextInt(128), random.nextInt(128), random.nextInt(128)));
            double rotation = random.nextDouble() * 0.3 - 0.15;
            g.rotate(rotation, 20 + i * 25, 25);
            g.drawString(String.valueOf(code.charAt(i)), 20 + i * 25, 25);
            g.rotate(-rotation, 20 + i * 25, 25);
        }
    }

    private static void drawInterferenceLines(Graphics2D g) {
        for (int i = 0; i < 5; i++) {
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.drawLine(random.nextInt(WIDTH), random.nextInt(HEIGHT), random.nextInt(WIDTH), random.nextInt(HEIGHT));
        }
    }

    public static class CaptchaResult {
        private String code;
        private byte[] imageData;
        public CaptchaResult(String code, byte[] imageData) {
            this.code = code;
            this.imageData = imageData;
        }
        public String getCode() { return code; }
        public byte[] getImageData() { return imageData; }
    }
}

