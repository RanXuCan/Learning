package algorithm;

import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

/**
 * @Description: 复习排序算法时，设计的类
 * @author: RanXuCan
 * @date: 2020年08月25日 15:33
 */

public class SortAlgorithmTest {
    int[] a;
    int len;
    final int MAX_LENGTH = 100;

    @Before
    public void setUp() {
        a = new int[MAX_LENGTH];
        len = a.length;
        for (int i = 0; i < len; i++) {
            a[i] = (int) (Math.random() * 100);
        }
        System.out.println(Arrays.toString(a));
    }

    @Test
    public void mpsortTest() {
        /*
         * 冒泡排序
         */
        long startTime = System.nanoTime();
        for (int i = 1; i < len; i++)
            for (int j = 0; j < len - i; j++) {
                if (a[j] >= a[j + 1]) {
//                    int temp = a[j];
//                    a[j] = a[j + 1];
//                    a[j + 1] = temp;
//                    //不借助临时变量交换元素    2, 3
//                    a[j] = a[j] + a[j + 1]; // 5, 3
//                    a[j + 1] = a[j] - a[j + 1];// 5, 2
//                    a[j] = a[j] - a[j + 1];//3, 2
                    //异或运算交换元素
                    a[j] = a[j] ^ a[j + 1];
                    a[j + 1] = a[j] ^ a[j + 1];
                    a[j] = a[j] ^ a[j + 1];
                }
            }
        System.out.println("冒泡排序运行时间：" + (System.nanoTime() - startTime) + "ns");
        System.out.println(Arrays.toString(a) + "\n*********************************************************\n");
    }

    @Test
    public void xzsortTest() {
        /*
         * 选择排序
         */
        long startTime = System.nanoTime();
        for (int i = 0; i < len - 1; i++){
            int index = i;
            int min = a[i];   //每一轮选择，都需更新最小值为当前待排序的数组的范围的首元素值
            for (int j = i + 1; j < len; j++) {
                if (a[j] < min) {   //发现当前最小值，更新最小值并记录下标
                    min = a[j];
                    index = j;
                }
            }
            if (index != i) {
//                int temp = a[i];
//                a[i] = min;
//                a[index] = temp;
                a[i] = a[i] ^ a[index];
                a[index] = a[i] ^ a[index];
                a[i] = a[i] ^ a[index];
            }
        }
        System.out.println("选择排序运行时间：" + (System.nanoTime() - startTime) + "ns");
        System.out.println(Arrays.toString(a) + "\n" + "*********************************************************\n");
    }

    @Test
    public void crsortTest() {
        /*
         * 插入排序
         */
        //[3,2,7,1,4,9,5,8,6]
        for (int i = 1; i < a.length; i++)
            for (int j = 0; j < i; j++) {
                if (a[i] < a[j]) {
                    a[i] = a[i] ^ a[j];
                    a[j] = a[i] ^ a[j];
                    a[i] = a[i] ^ a[j];
                    break;
                }
            }
        System.out.println(Arrays.toString(a));
    }
}