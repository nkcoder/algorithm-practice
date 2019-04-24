package io.nkcoder.alg.coding.array;

/**
 * 问题描述：
 *  有n个红、白、蓝三种不同颜色的小球，乱序排列在一起，请通过两两交换任意两个小球，
 *  使得从左到右依次是：一些红球、一些白球、一些蓝球，也就是将所有的红球排在最左边，
 *  所有的白球排在中间，所有的蓝球排在最右边；
 *  因为红白蓝从上到下三色条刚好是荷兰国旗，所以该问题被称为荷兰国旗；
 *
 * 思路：
 *  可以使用三个指针，一个是前指针first，一个是后指针last，一个是当前指针current；前指针用于红球的分界，
 *  后指针用于蓝球的分界，当前指针用于从前向后第遍历所有的小球，判断思路：
 *  - 初始状态：first和current都指向第一个小球，last指向最后一个小球；
 *  - 当current指向红球时，与first互换，且first++， current++；
 *  - 当current指向白球时，current++；
 *  - 当current指向蓝球时，current与last互换，end--；
 *
 * 几个要点：
 *  - 显然，first前面的球都是红球，last后面的球都是蓝球，而first与last指向的球是未知的；那为什么当current
 *  指向红球时，与first互换后，first++，current++呢？因为当current指向白球时是直接后移的，所以可以肯定
 *  的是first指向的肯定是蓝球，交换后current指向的就是蓝球，所以first和current都需要后移；
 *  - 为什么current指向蓝球时，与last互换后，仅仅是end前移呢？因为此时last指向的球是未知的，如果为红球，则
 *  交换后，current指向的是红球，所以current不能直接后移，需要重新判断；
 *  - 在写程序处理时，可以使用特殊值，比如1、2、3分别表示红球、白球、蓝球；
 *
 * 参考：
 *  - https://github.com/nkcoder/The-Art-Of-Programming-By-July/blob/master/ebook/zh/02.07.md
 *
 * @author: lingguo
 * @time: 2014/8/30 22:16
 * @update1: 2016/08/30
 */
public class HollandFlag {

    public static void sort(int[] balls) {
        int begin = 0, current = 0, end = balls.length - 1;
        while (current <= end) {
            if (balls[current] == 0) {
                int tmp = balls[current];
                balls[current] = balls[begin];
                balls[begin] = tmp;

                begin ++;
                current ++;
            } else if (balls[current] == 1) {
                current ++;
            } else if (balls[current] == 2) {
                int tmp = balls[current];
                balls[current] = balls[end];
                balls[end] = tmp;

                end --;
            }
        }
    }

    public static void main(String[] args) {
        int[] balls = {1, 1, 2, 0, 0, 2, 1, 2, 0};
        sort(balls);
        for (int i = 0; i < balls.length; i++) {
            System.out.println(balls[i]);
        }
    }
}
