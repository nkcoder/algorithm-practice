package io.nkcoder.alg.basic.search;

/**
 * 对有序序列可以采用二分查找，需要注意的一点就是边界条件的判断：
 *  如果n为数组的长度，则右边界right可以取n，也可以取n-1，此时
 *  mid的赋值就是不同的：
 *  - 如果right = n，循环使用while(left < right), 则当
 *      data[mid] > x时， right = mid；
 *  - 如果right = n-1，循环使用while(left <= right)，则当
 *      data[mid] > x时，right = mid-1；
 *
 * @author: lingguo
 * @time: 2014/8/31 11:47
 */
public class BinarySearch {

    /**
     * 方式一：取right=n，即右边界为数组最后一个元素的下一个位置；
     *  此时right=mid，否则如果right=mid-1，则mid-1这个元素被忽略了。
     *
     * @param data
     * @param x
     * @return
     */
    public int search_1(int[] data, int x) {
        int left = 0;
        int right = data.length;
        while (left < right) {
            // 避免加法溢出
//            int mid = (left + right) >> 1;
            int mid = left + (right - left) / 2;
            if (data[mid] < x) {
                left = mid + 1;
            } else if (data[mid] > x) {
                right = mid;
            } else {    // 最后才比较相等，因为数组中不相等的情况居多，避免不必要的比较而浪费时间；
                return mid;
            }
        }
        return -1;
    }

    /**
     * 方式二：取right=n-1，即右边界为最后一个元素的位置；
     *  while(left<=right)，由于当前元素mid已经判断过了，所以直接忽略，使用
     *      right=mid-1；
     *
     * @param data
     * @param x
     * @return
     */
    public int search_2(int[] data, int x) {
        int left = 0;
        int right = data.length - 1;
        while (left <= right) {
//            int mid = (left + right) >> 1;
            int mid = left + (right - left) / 2;
            if (data[mid] < x) {
                left = mid + 1;
            } else if (data[mid] > x) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 方式三：取right=n-1，即右边界为最后一个元素的位置；
     *  while(left<right)，仍然使用mid-1忽略当前元素，则最后退出循环有
     *  两种可能：
     *      - 有一个mid使得data[mid]=x，直接返回了mid；
     *      - left=right时，还没有找到mid，此时需要判断data[left]==x；
     *
     * @param data
     * @param x
     * @return
     */
    public int search_3(int[] data, int x) {
        int left = 0;
        int right = data.length - 1;
        while (left < right) {
//            int mid = (left + right) >> 1;
            int mid = left + (right - left) / 2;
            if (data[mid] < x) {
                left = mid + 1;
            } else if (data[mid] > x) {
                right = mid - 1;
            } else {
                return mid;
            }
        }

        /**
         * 如果while循环没有返回mid，则退出循环的肯定是left=right，
         * 此时数组中就剩这最后一个元素没有比较了，比较后返回即可；
         */
        if (data[left] == x) {
            return left;
        }
        return -1;
    }
}

