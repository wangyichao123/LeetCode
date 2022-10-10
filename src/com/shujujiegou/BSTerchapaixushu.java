package com.shujujiegou;

import org.junit.Test;
import java.util.LinkedList;
import java.util.Queue;

public class BSTerchapaixushu {
    public class BSTNode<T> {
        private T data;
        private BSTNode<T> left;
        private BSTNode<T> right;
    }
    @Test
    public void test(){
        BSTNode<Integer> T=new BSTNode<>();  //根节点要不为null
        T.data=0;
        int[] arr={3,4,5,1,2};
        for(int i=0;i<arr.length;i++){
            Insert(T,null,arr[i],false);
        }
        Queue<BSTNode<Integer>> queue=new LinkedList<>();
        queue.add(T);
        int nowceng=1;
        int nextceng=0;
        BSTNode<Integer> p;
        while (!queue.isEmpty()){
            p=queue.peek();
            queue.remove();
            System.out.print(p.data+" ");
            nowceng--;
            if(p.left!=null){
                queue.add(p.left);
                nextceng++;
            }
            if(p.right!=null){
                queue.add(p.right);
                nextceng++;
            }
            if(nowceng==0){
                nowceng=nextceng;
                nextceng=0;
                System.out.println();
            }
        }
        System.out.println("----------------");

    }

    public boolean Insert(BSTNode<Integer> T,BSTNode<Integer> pre,int k,boolean tag){
        if(T==null){
            BSTNode<Integer> T1=new BSTNode<>();
            if(tag){
                pre.right=T1;
            }else {
                pre.left=T1;
            }
            T1.data=k;
            return true;
        }else if(T.data==k){
            return false;
        }else if(T.data>k){
            return Insert(T.left,T,k,false);
        }else{
            return Insert(T.right,T,k,true);
        }
    }

}
