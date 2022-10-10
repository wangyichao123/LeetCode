package com.shujujiegou;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class chazhao {
    @Test
    public void test(){
        int[] arr={1,3,4,2,1,1,1,3,33,2,2,1,6};
        int keyno=9;
        int keyyes=6;
        System.out.println(shunxuchazhao(arr,keyyes));
        System.out.println(shunxuchazhao(arr,keyno));

        System.out.println(fenkuai(arr,keyyes));
        System.out.println(fenkuai(arr,keyno));

        Arrays.sort(arr);
        System.out.println(Binary_zheban(arr,keyyes));
        System.out.println(Binary_zheban(arr,keyno));
    }

    //顺序查找
    public int shunxuchazhao(int[] arr,int key){
        for(int i=0;i<arr.length;i++){
            if(key==arr[i]){
                return i;   //找到了
            }
        }
        return -1;  //没找到
    }

    //有序表的顺序查找
    public int youxushunxuchazhao(int[] arr,int key){
        for(int i=0;i<arr.length;i++){
            if(key==arr[i]){
                return i;   //找到了
            }else if(key<arr[i]){
                return -2;   //没有这个数
            }
        }
        return -1;  //没找到
    }

    //折半查找
    public int Binary_zheban(int[] arr,int key){
        int low=0;
        int high=arr.length-1;
        while (low<=high){
            int mid=(low+high)/2;
            if(arr[mid]==key){
                return mid;
            }else if(arr[mid]>key){
                high=mid-1;
            }else if(arr[mid]<key){
                low=mid+1;
            }
        }
        return -1;  //没找到
    }

    //分块查找
    public int fenkuai(int[] arr,int key){
        List<List<Integer>> list=new ArrayList<>();
        int firstkey=0;
        if(key==arr[0]){
            return 0;
        }
        for(int i=1;i<arr.length;i++){
            if(arr[i]<=arr[firstkey]){
                continue;
            }else if(arr[i]>arr[firstkey]){
                List<Integer> list1=new ArrayList<>();
                for(int j=firstkey;j<i;j++){
                    list1.add(arr[j]);
                }
                list.add(list1);
                firstkey=i;
            }
        }
        List<Integer> list1=new ArrayList<>();
        for(int j=firstkey;j<arr.length;j++){
            list1.add(arr[j]);
        }
        list.add(list1);
        int index=-1;
        int sum=0;
        for(int i=0;i<list.size();i++){
            if(list.get(i).get(0)>=key){
                index=i;
                break;
            }
            sum+=list.get(i).size();
        }
        if(index==-1){
            return -1;
        }
        for(int i=0;i<list.get(index).size();i++){
            sum++;
            if(list.get(index).get(i)==key){
                return sum-1;
            }
        }
        return -1; //没找到
    }

}
