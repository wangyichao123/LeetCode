package com.shujujiegou;

import org.junit.Test;

import java.util.*;

public class xiansuoerchashu {
    public class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;

        /**
         * 规定当左节点指向左子树时，leftType==0
         * 当左节点指向前驱结点时，leftType==1
         */
        private int leftType;

        /**
         * 规定当右节点指向右子树时，rightType==0
         * 当右节点指向后继结点时，rightType==1
         */
        private int rightType;

        /*构造器、toString()及各属性的get、set方法不再赘述*/

        public Node<T> getLeft() {
            return left;
        }

        public Node<T> getRight() {
            return right;
        }

        public int getLeftType() {
            return leftType;
        }

        public int getRightType() {
            return rightType;
        }

        public void setLeft(Node<T> pre) {
            left=pre;
        }

        public void setLeftType(T i) {
            leftType= (Integer) i;
        }

        public void setRight(Node<T> node) {
            right=node;
        }

        public void setRightType(T i) {
            rightType= (Integer) i;
        }
    }

    @Test
    public void test(){
        Node<Integer> T=new Node<Integer>();
        T.data=0;
        Node<Integer> T1=new Node<Integer>();
        T1.data=1;
        Node<Integer> T2=new Node<Integer>();
        T2.data=2;
        Node<Integer> T3=new Node<Integer>();
        T3.data=3;
        Node<Integer> T4=new Node<Integer>();
        T4.data=4;
        T.left=T1;
        T.right=T2;
        T1.right=T3;
        T2.left=T4;
        setRoot(T);

        preThreadedNodesLast(T);
        System.out.println(last.data);
        last.rightType=1;
        infixThreadedNodesLast(T);
        System.out.println(last.data);
        last.rightType=1;
        postThreadedNodesLast(T);   //后序不需要
        System.out.println(last.data);

        //infixThreadedNodes(T);    //中序
        //preThreadedNodes(T);      //先序
        //postThreadedNodes(T);     //后序
        System.out.println("-------");
        Queue<Node<Integer>> queue=new LinkedList<>();
        queue.add(T);
        int nowceng=1;
        int nextceng=0;
        Node<Integer> p;
        while (!queue.isEmpty()){
            p=queue.peek();
            queue.remove();
            nowceng--;

            if(p.leftType==0&&p.left!=null){
                queue.add(p.left);
                nextceng++;
            }
            System.out.print(p.leftType);
            if(p.left!=null){
                System.out.print(p.left.data+" ");
            }else if(p.left==null){
                System.out.print("null ");
            }
            System.out.print(p.data+" ");
            if(p.rightType==0&&p.right!=null){
                queue.add(p.right);
                nextceng++;
            }
            System.out.print(p.rightType);
            if(p.right!=null){
                System.out.print(p.right.data+" ");
            }else if(p.right==null){
                System.out.print("null ");
            }

            if(nowceng==0){
                nowceng=nextceng;
                nextceng=0;
                System.out.println();
            }
        }
    }


    private Node<Integer> root;
    private Node<Integer> pre;  //实现线索化过程中的前趋结点
    private Node<Integer> last;  //处理最后一个节点的后继null

    public void setRoot(Node<Integer> root) {
        this.root = root;
    }

    public void infixThreadedNodes() {
        infixThreadedNodes(root);
    }

    private void infixThreadedNodes(Node<Integer> node) { //对二叉树进行中序线索化
        if (node == null) {
            return;
        }
        //线索化左子树
        infixThreadedNodes(node.getLeft());
        //线索化当前结点
        threadedNode(node);
        //线索化右子树
        infixThreadedNodes(node.getRight());
    }

    public void infixThreadedNodesLast(Node<Integer> node){  //处理中序最后一个节点的后继null
        if(node==null){
            return;
        }
        infixThreadedNodesLast(node.left);
        last=node;
        infixThreadedNodesLast(node.right);
    }


    public void preThreadedNodes() {
        preThreadedNodes(root);
    }


    private void preThreadedNodes(Node<Integer> node) {  //前序线索化二叉树
        if (node == null) {
            return;
        }
        threadedNode(node);
        if (node.getLeftType() == 0) {
            preThreadedNodes(node.getLeft());
        }
        if (node.getRightType() == 0) {
            preThreadedNodes(node.getRight());
        }
    }


    public void preThreadedNodesLast(Node<Integer> node){  //处理先序最后一个节点的后继null
        if(node==null){
            return;
        }
        last=node;
        preThreadedNodesLast(node.left);
        preThreadedNodesLast(node.right);
    }

    public void postThreadedNodes() {
        postThreadedNodes(root);
    }


    private void postThreadedNodes(Node<Integer> node) { //后序线索化二叉树
        if (node == null) {
            return;
        }
        postThreadedNodes(node.getLeft());
        postThreadedNodes(node.getRight());
        threadedNode(node);
    }

    public void postThreadedNodesLast(Node<Integer> node){  //处理后序最后一个节点的后继null
        if(node==null){
            return;
        }

        postThreadedNodesLast(node.left);
        postThreadedNodesLast(node.right);
        last=node;
    }

    /**
     * 线索化当前结点
     */
    private void threadedNode(Node<Integer> node) {
        //处理前驱结点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //！！！处理后继结点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //处理当前结点后，让当前结点成为下一结点的前驱结点
        pre = node;
    }

}
