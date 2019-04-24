package io.nkcoder.alg.basic.sorting;


/**
 * 归并排序：
 *  思路：归并排序与快速排序刚好相反，快速排序是将整体分为有序的两部分，继续分割直到最小单元，
 *  此时整个序列排序完毕；而归并排序是先将整体分为无序两个部分，继续分割直到有序的最小单元，将有
 *  序的最小单元两两有序归并，所有归并动作完成后，整个序列排序完毕。
 *  复杂度：时间O(nlogn)，空间O(n)；
 *
 * 适用场景:
 *  1. 当数据结构不支持随机访问(random access), 只能顺序访问(sequential access)时; 因此归并排序广泛应用于外部排序,
 *  因为此时随机访问的代价比顺序访问要高很多;
 *  2. 稳定性是归并排序很重要的一个特性;
 *  3. 链表用归并排序会比较快, 因为归并时只需要改变指针方向;
 *  4. 归并排序容易并发优化;
 *
 * 参考：
 *  http://algs4.cs.princeton.edu/22mergesort/Merge.java.html
 *  http://algs4.cs.princeton.edu/22mergesort/
 *
 * User: lingguo
 * Date: 14-7-26 上午12:00
 */
public class MergeSort {

    /**
     * 1. 原地归并排序, 也可以是非原地排序, 参考: http://howtodoinjava.com/algorithm/merge-sort-java-example/
     *
     * @param data      数组
     * @param lo        首元素索引
     * @param hi        尾元素索引
     */
    public static void sort1(int[] data, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        // 取中点
        int mid = lo + ((hi - lo) >> 1);
        sort1(data, lo, mid);
        sort1(data, mid + 1, hi);
        merge1(data, lo, mid, hi);
    }

    /**
     * 归并过程：需要注意的是，将两个有序序列归并的时候，注意边界条件。
     *
     * @param data
     * @param lo
     * @param mid
     * @param hi
     */
    private static void merge1(int[] data, int lo, int mid, int hi) {
        // 将需要归并的元素保存到辅助数组
        int aux[] = new int[data.length];
        for (int i = lo; i <= hi; i++) {
            aux[i] = data[i];
        }

        int i = lo;
        int j = mid + 1;
        // 对辅助数组中的元素依次处理后，将有序序列保存到原数组
        for (int k = lo; k <= hi; k++) {
            // 前两个条件用于判断边界，如果j > hi，表示mid+1到hi部分的序列
            // 已经遍历完毕，只需将lo到mid中剩余的元素保存到data即可；
            if (i > mid) {
                data[k] = aux[j++];         // 不是必须的
            } else if (j > hi) {
                data[k] = aux[i++];
            } else if (aux[i] <= aux[j]) {
                data[k] = aux[i++];
            } else {
                data[k] = aux[j++];
            }
        }
    }
    ///////////////////////////////////////////////////////////////////////////

}
