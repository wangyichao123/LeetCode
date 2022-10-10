package com.shujujiegou;

import org.junit.Test;

public class paixu {
    @Test
    public void test(){
        int[] arr={132,324,412,532,241,341,432,751,441};
        jishuSort(arr,0);
        /*for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }*/
    }

    //直接插入排序
    public void InsertSort(int[] arr){
        for(int i=1;i<arr.length;i++){
            int index=-1;
            for(int j=i-1;j>=0;j--){
                if(arr[j]<arr[i]){
                    index=j;
                    break;
                }
            }
            if(index==-1){
                continue;
            }
            int temp=arr[i];
            for(int j=i-1;j>index;j--){
                arr[j+1]=arr[j];
            }
            arr[index+1]=temp;
        }
    }

    //折半查找
    public void InsertSortzheban(int[] arr){
        int low,high,mid;
        for(int i=1;i<arr.length;i++){
            if(arr[i]>arr[i-1]){
                continue;
            }
            int temp=arr[i];
            low=0;
            high=i-1;
            while (low <= high) {
                mid=(low+high)/2;
                if(arr[mid]>temp){
                    high=mid-1;
                }else {
                    low=mid+1;
                }
            }
            for(int j=i-1;j>=low;j--){
                arr[j+1]=arr[j];
            }
            arr[low]=temp;
        }
    }

    //希尔排序
    public void ShellSort(int[] arr){
        for(int dk=arr.length/2;dk>=1;dk/=2){
            for(int i=dk;i<arr.length;i++){
                if(arr[i]<arr[i-dk]){
                    int temp=arr[i];
                    int j;
                    for(j=i-dk;j>0&&temp<arr[j];j-=dk){
                        arr[j+dk]=arr[j];
                    }
                    arr[j+dk]=temp;
                }
            }
        }
    }

    //冒泡排序
    public void BubbleSort(int[] arr){
        for(int i=0;i<arr.length-1;i++){
            boolean tag=false;
            for(int j=arr.length-1;j>i;j--){
                if(arr[j-1]>arr[j]){
                    int temp=arr[j];
                    arr[j]=arr[j-1];
                    arr[j-1]=temp;
                    tag=true;
                }
            }
            if(tag==false){
                return;
            }
        }
    }
    //快速排序
    public void QuickSort(int[] arr,int low,int high){
        if(low<high){
            int pivotpos=Partition(arr,low,high);
            QuickSort(arr,low,pivotpos-1);
            QuickSort(arr,pivotpos+1,high);
        }
    }
    public int Partition(int[] arr,int low,int high){
        int pivot=arr[low];
        while (low<high){
            while (low<high&&arr[high]>=pivot){
                high--;
            }
            arr[low]=arr[high];
            while (low<high&&arr[low]<=pivot){
                low++;
            }
            arr[high]=arr[low];
        }
        arr[low]=pivot;
        return low;
    }

    //简单选择排序
    public void SelectSort(int[] arr){
        for(int i=0;i<arr.length-1;i++){
            int min=i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]<arr[min]){
                    min=j;
                }
            }
            int temp=arr[min];
            arr[min]=arr[i];
            arr[i]=temp;
        }
    }

    //堆排序
    public static void Duisort(int []arr){
        //1.构建大顶堆
        for(int i=arr.length/2-1;i>=0;i--){
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr,i,arr.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j=arr.length-1;j>0;j--){
            swap(arr,0,j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr,0,j);//重新对堆进行调整
        }

    }

    public static void adjustHeap(int []arr,int i,int length){  //调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
        int temp = arr[i];//先取出当前元素i
        for(int k=i*2+1;k<length;k=k*2+1){//从i结点的左子结点开始，也就是2i+1处开始
            if(k+1<length && arr[k]<arr[k+1]){//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if(arr[k] >temp){//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }

    public static void swap(int []arr,int a ,int b){  //交换
        int temp=arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    //归并排序
    public static void Merge_Sort(int[] arr, int start, int end) {
        if (start < end) { //当start==end时，此时分组里只有一个元素，所以这是临界点
            int mid = (start + end) / 2;  //分成左右两个分组，再进行递归
            Merge_Sort(arr, start, mid);
            Merge_Sort(arr, mid + 1, end);
            Merge(arr, start, mid, end);  //递归之后再归并归并一个大组
        }
    }

    //归并方法
    public static void Merge(int[] arr, int start, int mid, int end) {
        int i_start = start;  //左边分组的起点i_start，终点i_end，也就是第一个有序序列
        int i_end = mid;
        int j_start = mid + 1;
        int j_end = end;
        int[] temp=new int[end-start+1];  //额外空间初始化，数组长度为end-start+1
        int len = 0;
        while (i_start <= i_end && j_start <= j_end) {  //合并两个有序序列
            if (arr[i_start] < arr[j_start]) {  //当arr[i_start]<arr[j_start]值时，将较小元素放入额外空间，反之一样
                temp[len] = arr[i_start];
                len++;
                i_start++;
            } else {
                temp[len] = arr[j_start];
                len++;
                j_start++;
            }
        }
        while(i_start<=i_end){  //i这个序列还有剩余元素
            temp[len] = arr[i_start];
            len++;
            i_start++;
        }
        while(j_start<=j_end){
            temp[len] = arr[j_start];
            len++;
            j_start++;
        }
        for(int i=0;i<temp.length;i++){  //辅助空间数据覆盖到原空间
            arr[start+i]=temp[i];
        }
    }

    //基数排序
    public void jishuSort(int[] arr,int index){
        int length=String.valueOf(arr[0]).length();
        if(index<length){
            int[] temp=new int[arr.length];
            for(int i=0;i<arr.length;i++){
                temp[i]=Integer.valueOf(String.valueOf((String.valueOf(arr[i]).charAt(length-1-index))));
            }

            int[] temp1=new int[arr.length];
            int num=0;
            for(int i=0;i<10;i++){
                for(int j=0;j<temp.length;j++){
                    if(temp[j]==i){
                        temp1[num++]=arr[j];
                    }
                }
            }
            arr=temp1.clone();
            jishuSort(arr,index+1);
        }else if(index==length){
            for(int i=0;i<arr.length;i++){
                System.out.print(arr[i]+" ");
            }
        }

    }

}
