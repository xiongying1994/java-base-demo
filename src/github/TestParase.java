package github;

import java.util.Scanner;

/**
 * 画杨辉三角
 *          1
 *         1   1
 *       1   2   1
 *     1   3   3   1
 *   1   4   6   4   1
 * 1   5   10   10   5   1
 *
 * @author xiongying
 */
public class TestParase {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//内容循环输入
		while(scanner.hasNext()){
			int row = scanner.nextInt();
			//二维数组的初始化，需要指定二维数组的行数
			int[][] arg = new int[row][];
			int blankLeft;
			int blankRight;
			for(int i = 0; i < row; i++){
				//定义二维数组中，每一行的数组内容。
				arg[i] = new int[i+1];
				/**
				 * 计算每一行的前需要空出多少内容
				 * 每一行的长度为 1，3，5，7，9    我们以 "0" 作为起始行，则为 2n + 1
				 * 每一行，都应以最后一行，即最长行作为标准，减去本行的长度，剩下的长度除以二（左右两边）
				 *
				 * row = 4   我们有  0，1，2，3   共四行
				 */
				blankLeft = blankRight = (row-1 + row-1 + 1 - (i + i + 1))/2;
				while(blankLeft > 0){
					blankLeft--;
					System.out.print("  ");
				}
				for(int j = 0; j <= i; j++){
					if(j == 0 || j == i){
						arg[i][j] = 1;
					}else{
						arg[i][j] = arg[i-1][j] + arg[i-1][j-1];
					}
					//空出三格，那样的话，上一层的数字刚好在三格的中间，非常对称美观
					System.out.print(arg[i][j] + "   ");
				}
				/**
				 * 右边的空格是可以删去的。
				 */
				while(blankRight > 0){
					blankRight--;
					System.out.print("  ");
				}
				System.out.println();
			}
		}
	}

}
