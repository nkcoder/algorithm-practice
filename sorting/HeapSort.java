package io.nkcoder.alg.basic.sorting;


/**
 * 堆排序：
 *  思路：根据数组元素的索引，将数组构造成一个堆(即二叉树)，然后调整成最大堆，堆顶为最大值，
 *  将堆顶与堆的最后一个元素互换，最大堆结构破坏，将最后一个元素排除(已排好序)，调整堆，然
 *  后将堆顶与倒数第二个元素互换，依此类推，直到所有元素排好序。
 *  - 根据索引将数组构造成堆：
 *      0
 *     / \
 *    1   2
 *   / \ / \
 *  3  4 5  6
 *
 *  - 调整为最大堆：
 *      如果当前元素大于左右节点的值，则堆结构调整完毕；否则，将当前元素与左右节点较大值互换，
 *      互换后继续向下调整。
 *
 *  - 复杂度：O(nlogn)，空间O(1)
 *
 * 参考：http://algs4.cs.princeton.edu/24pq/Heap.java.html
 *
 * User: lingguo
 * Date: 14-7-26 上午10:33
 */
public class HeapSort {

    public static void sort(int[] data) {
        int end = data.length - 1;
        /**
         * 先计算最后一个非叶子节点的位置，然后从最后一个非叶子节点开始，对所有的
         * 非叶子节点进行调整，调整后每一个节点的值都大于其左右孩子节点，堆顶为最大值。
         */
        int node = (end - 1) >> 1;
        while (node >= 0) {
            sink(data, node--, end);
        }
        /**
         * 每次将堆顶换到当前堆的最后，将堆调整为最大堆，因此此时出堆顶元素发生变化外，
         * 其它节点都维持了大顶规则，所以只需要对堆顶元素进行一次调整即可。
         */
        while (end > 0) {
            int tmp = data[0];
            data[0] = data[end];
            data[end] = tmp;
            end--;
            sink(data, 0, end);
        }
    }

    /**
     * 将data[k]元素放到堆data[0...end]的适当的位置，即调整堆的结构，
     * 使之保持大顶堆的状态(即节点值大于左右节点的值，所以堆顶是最大值)。
     *
     * @param data      堆
     * @param k         索引为k的元素
     * @param end       堆的尾元素索引
     */
    private static void sink(int[] data, int k, int end) {
        // 如果当前元素有左右节点，左节点：2k+1，右节点2k+2
        while (k * 2 + 1 <= end) {
            // 在左右节点中选择较大值
            int j = 2 * k + 1;
            if (j + 1 <= end) {
                if (data[j] < data[j+1]) {
                    j++;
                }
            }
            // 当前节点大于左右节点，堆调整结束
            if (data[k] >= data[j]) {
                break;
            }
            // 当前节点与左右节点中的较大值互换，继续向下调整
            int tmp = data[k];
            data[k] = data[j];
            data[j] = tmp;
            k = j;
        }
    }
}
