package io.nkcoder.alg.coding.array;

/**
 * 问题描述：
 *  > 输入一个矩阵（二维数组），按照顺时针的方向打印矩阵中的元素；比如，输入：
 *      1   2   3   4   5
 *      6   7   8   9   10
 *      11  12  13  14  15
 *      16  17  18  19  20
 *  输出：1 2 3 4 5 10 15 20 19 18 17 16 11 6 7 8 9 14 13 12
 *
 * 思路：
 *  - 可以将顺时针打印分成四个步骤，从左到右的一行，从上到下的一列，从右到左的一行，从下到上
 *  的一列。打印时有两个条件限制：一是不能出界，二是不能重复打印。第一个条件，可以通过矩阵的
 *  行列值进行限制，第二个条件，可以设置一个与矩阵同样结构的标志数组，用于标记矩阵的每一个元素
 *  是否被遍历过。
 *  - 这里有一个常用的技巧，即试探法，先试探下一个元素是否可遍历，如果是，则遍历，否则终止本轮
 *  遍历。如果不是使用试探法，直接通过边界值的限制，循环遍历，当越界时，需要回退。
 *  - 时间复杂度O(m*n)，空间复杂度O(m*n)，其中m、n分别表示矩阵的行数和列数。
 *
 * User: lingguo
 * Date: 14-3-25
 * Time: 下午9:34
 */
public class PrintMatrixClockWise {

	/**
	 * 顺时针打印矩阵，分为四步：左->右，上->下，右->左，下->上，每一步
     * 使用试探法，通过矩阵的行数、列数作为限制，遍历矩阵, 并使用辅助数组判断
     * 当前是否被遍历。
     *
	 * @param matrix    矩阵
	 * @param rows  矩阵的行数
	 * @param cols  矩阵的列数
	 */
	public static void print1(int[][] matrix, int rows, int cols) {
		// 标志数组初始化
		boolean[][] visited = new boolean[rows][cols];
		for (int i = 0; i < rows; i ++) {
			for (int j = 0; j < cols; j++) {
				visited[i][j] = false;
			}
		}

        // 第一个元素为matrix[rowIndex][colIndex]在矩阵之外
		int rowIndex = 0;
		int colIndex = -1;
		int matrixIndex = 0;
		// 遍历矩阵
		while (matrixIndex++ < rows * cols) {
			// 左到右的一行
			while (colIndex + 1 < cols && !visited[rowIndex][colIndex+1]) {
				printElement(matrix[rowIndex][++colIndex]);
				visited[rowIndex][colIndex] = true;
			}
			// 上到下的一列
			while (rowIndex + 1 < rows && !visited[rowIndex+1][colIndex]) {
				printElement(matrix[++rowIndex][colIndex]);
				visited[rowIndex][colIndex] = true;
			}

			// 右到左的一行
			while (colIndex - 1 >= 0 && !visited[rowIndex][colIndex-1]) {
                printElement(matrix[rowIndex][--colIndex]);
				visited[rowIndex][colIndex] = true;
			}

            // 下到上的一列
			while (rowIndex - 1 >= 0 && !visited[rowIndex-1][colIndex]) {
                printElement(matrix[--rowIndex][colIndex]);
				visited[rowIndex][colIndex] = true;
			}
        }
	}

    /**
     * 根据需要的格式打印一个元素
     *
     * @param element
     */
    private static void printElement(int element) {
        System.out.print("->" + element);
    }

    public static void print2(int[][] matrix, int m, int n) {
        int row = 0, col = 0;
        int maxLayer = m >= n ? (n+1)/2 : (m+1)/2;  // 顺时针遍历一遍算一圈, 最大圈数
        for (; row < maxLayer && col < maxLayer; row++, col++) {
            // 从左到右, 列要回退, 行要加1
            int i = row, j = col;
            while (j < n - col) {
                printElement(matrix[i][j++]);
            }
            i++;
            j--;

            // 从上到下, 行要回退, 列减1
            while (i < m - row) {
                printElement(matrix[i++][j]);
            }
            i--;
            j--;

            // 从右到左, 列要回退, 行减1
            while (j >= col) {
                printElement(matrix[i][j--]);
            }
            i--;
            j++;

            // 从上到下
            while (i > row) {
                printElement(matrix[i--][j]);
            }
        }

    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20}
        };
        print1(matrix, 4, 5);
        System.out.println();
        print2(matrix, 4, 5);
    }

}
