package com.example.test;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BinarySearch {
    public static int binarySearch(int[] arr, int start, int end, int hkey){
        int result = -1;
        // 进行二分查找
        while (start <= end){
            int mid = start + (end - start)/2;    //防止溢位
            if (arr[mid] > hkey)
                // 让下标从中间向左
            {
                end = mid - 1;
            } else if (arr[mid] < hkey)
                // 让起始下标右移动
            {
                start = mid + 1;
            } else {
                result = mid ;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int [] arr = {1,2,3,4,5,6,7,8,9,10};
        int res = binarySearch(arr,0,arr.length-1,10);
        System.out.println("res="+res + ", arr["+ res +"]=" + arr[res]); // arr[res] = 10
        System.out.println(Integer.toBinaryString(10));
        Set<String> set = new HashSet<>(16);
        set.add(null);
        set.add(null);
        set.add("a");
        System.out.println(set.size());

    }
}