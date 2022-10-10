package com.shujujiegou;

import org.junit.Test;

public class moshipipei {
    @Test
    public void test(){
        System.out.println(moshipipei("abaabaabcabaabc","abaabc"));  //返回匹配到的第一个位置
        System.out.println("模式匹配用了"+cishu+"次");
        System.out.println("---------------------------");
        int[] next=getNext("abaabc");
        System.out.println(KMP("abaabaabcabaabc","abaabc",next));  //KMP
        System.out.println("KMP用了"+cishu+"次");
        System.out.println("---------------------------");
        int[] nextval=getNextval("abaabc",next);
        System.out.println(KMP("abaabaabcabaabc","abaabc",nextval)); //KMP优化
        System.out.println("KMP优化用了"+cishu+"次");
        System.out.println("---------------------------");

    }
    int cishu=0;
    //简单的模式匹配算法
    public int moshipipei(String s,String t){
        int i=0,j=0;
        cishu=0;
        while (i<s.length()&&j<t.length()){
            if(s.charAt(i)==t.charAt(j)){
                i++;
                j++;
            }else {
                i=i-j+1;
                j=0;
            }
            cishu++;
        }
        if(j==t.length()){
            return i-t.length();
        }else {
            return -1;
        }
    }
    //KMP
    public int KMP(String s,String t,int[] next){
        int i=0;
        int j=0;
        cishu=0;
        while (i<s.length()&&j<t.length()){
            if(j==-1||s.charAt(i)==t.charAt(j)){
                i++;
                j++;
            }else {
                j=next[j];
            }
            cishu++;
        }
        if(j==t.length()){
            return i-t.length();
        }else {
            return -1;
        }
    }
    public int[] getNext(String t){
        int[] next=new int[t.length()+1];
        int i=0;
        int j=-1;
        next[0]=-1;
        cishu=0;
        while (i<t.length()){
            if (j==-1 || t.charAt(i) == t.charAt(j)){
                next[++i]=++j;
                cishu++;
            }else {
                j=next[j];
            }
        }
        return next;
    }
    //KMP进一步优化

    public int[] getNextval(String t,int[] next){
        int[] nextval=new int[t.length()+1];
        nextval[0]=next[0];
        int i=1;
        while (i<t.length()){
            if(t.charAt(i)==t.charAt(next[i])){
                nextval[i]=nextval[next[i]];
                i++;
            }else {
                nextval[i]=next[i];
                i++;
            }
        }
        return nextval;
    }
}
