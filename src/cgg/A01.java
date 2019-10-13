package cgg;

import cgtools.*;
import static cgtools.Color.*;

public class A01 {

    public static void main(String[] args) {
        final int width = 100;
        final int height = 100;
        final String filename14 = "doc/a01-image.png";
        final String filename15 = "doc/a01-square.png";
        final String filename16 = "doc/a01-checkered-background.png";
        //diese Klasse austauschen
        class ConstantColor {
            Color color;

            ConstantColor(Color color) {
                this.color = color;
            }

            Color getColor(double x, double y) {
                return color;
            }
        }
        //diese Klasse austauschen

        class ColoredSquare {
            Color color;
            Color color2;

            ColoredSquare(Color color) {
                this.color = color;
            }

            Color getColor(double x, double y) {

                if(x > 60 && x <100 && y > 25 && y < 65){

                    return white;
                }
                  else {
                      return color;
                }
            }
        }


        class Chessboard {
            Color color;

            Chessboard(Color color) {

                this.color = color;

            }

            double z = 0;
            boolean pruefe = true;
            boolean pruefe2 = false;
            Color getColor(double x, double y) {

            if(y < 20 && x < 20){
                return gray;


            }else if(y < 40 && x < 40){
                return color;

            }else if (y < 60 && x < 60){
                return gray;
            }else if (y < 80 && x < 80){
                return color;
            }else if(y < 100 && x < 100){
                return gray;
            }else if (y < 120 && x < 120){
                return color;
            }else if(y < 140 && x < 140){
                return gray;
            }else{
                return color;
            }






            }


        }

        Image image = new Image(width, height);

        ConstantColor allGray = new ConstantColor(red);
        ColoredSquare quadrat = new ColoredSquare(white);
        ColoredSquare quadrat2 = new ColoredSquare(red);
        Chessboard chessboardBlack = new Chessboard(black);
        Chessboard chessboardGrey = new Chessboard(gray);

        /*
        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                image.setPixel(x, y, chessboard.getColor(x, y));
            }
        }

         */

        for(int j = 0; j<5;j++) {
            for(int i = 0; i<5;i++) {
                for (int x = 0; x != width/10; x++) {
                    for (int y = 0; y != height/10; y++) {
                        image.setPixel(x+i*20, y+j*20, chessboardBlack.getColor(x, y));
                    }
                }
            }
        }

        for(int j = 0; j<5;j++) {
            for(int i = 0; i<5;i++) {
                for (int x = 0; x != width/10; x++) {
                    for (int y = 0; y != height/10; y++) {
                        image.setPixel(10+x+i*20, 10+y+j*20, chessboardGrey.getColor(x, y));
                    }
                }
            }
        }

        for (int x = 30; x != width-30; x++) {
            for (int y = 30; y != height-30; y++) {
                image.setPixel(x, y, quadrat2.getColor(x, y));
            }
        }

        image.write(filename16);
        System.out.println("Wrote image: " + filename16);
    }
}
