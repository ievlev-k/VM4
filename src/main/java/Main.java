import drawingGraphics.DrawingGraphics;
import method.CalculateFunctionInPoint;
import method.Function;
import method.MethodSpline;
import method.Spline;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try{
            Scanner scConsole = new Scanner(System.in);

            System.out.println("Вставьте путь к файлу: ");
            String path = scConsole.nextLine();
            File file = new File(path);
            Scanner sc = new Scanner(file);

            String function = sc.nextLine();
            Integer n = Integer.parseInt(sc.nextLine());
            double[] x = new double[n];
            for (int i = 0; i < n; i++) {
                x[i] = Double.parseDouble(sc.nextLine());
            }

            CalculateFunctionInPoint calculateFunctionInPoint = new CalculateFunctionInPoint();

//            double[] x = {1, 2, 3, 4, 5, 6, 8, 10, 20};
            double[] y = new double[x.length];


            for (int i = 0; i < x.length; i++) {
                y[i] = calculateFunctionInPoint.calculatingValue(function, x[i]);

            }

            MethodSpline methodSpline = new MethodSpline(x, y, x.length);

            Spline spline = methodSpline.getSpline();

            DrawingGraphics drawingGraphics = new DrawingGraphics(spline, function);

            drawingGraphics.drawingFunction();
        }catch (Exception e){
            System.exit(0);
        }

    }
}
