package github;

import java.util.Scanner;

/**
 * 画星状图
 * *
 * *   *
 * *   *   *
 * *   *   *   *
 * *   *   *
 * *   *
 * *
 *
 * @author xiongying
 */
public class StarPainting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int row = scanner.nextInt();
            int blankUpLeft;
            int blankDownLeft;
            for (int i = 0; i < row; i++) {
                blankUpLeft = (row - 1 + row - 1 + 1 - (i + i + 1)) / 2;
                while (blankUpLeft > 0) {
                    blankUpLeft--;
                    System.out.print("  ");
                }

                for (int j = 0; j <= i; j++) {
                    System.out.print("*" + "   ");
                }
                System.out.println();
            }
            for (int i = row - 2; i >= 0; i--) {
                blankDownLeft = (row - 1 + row - 1 + 1 - (i + i + 1)) / 2;
                while (blankDownLeft > 0) {
                    blankDownLeft--;
                    System.out.print("  ");
                }
                for (int j = 0; j <= i; j++) {
                    System.out.print("*" + "   ");
                }
                System.out.println();
            }
        }
    }
}
