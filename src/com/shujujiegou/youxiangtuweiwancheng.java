package com.shujujiegou;

import org.junit.Test;

import java.util.*;

public class youxiangtuweiwancheng {
    //邻接矩阵
    public class MyMgraph {
        int numNodes;
        String[] vexs;//一维数组储存图的顶点(V)信息
        int[][] edgs;//邻接矩阵，二维数组储存图的边或是弧(E)的信息
    }

    @Test
    public void test(){
        MyMgraph G = directed_mgraph();
        printResult(G.vexs,G.edgs);//打印结果

        //广度优先搜索BFS
        System.out.println("-----广度优先搜索BFS-------");
        boolean[] visitedBFS=new boolean[G.numNodes];
        BFSTrave(G,visitedBFS);

        //深度优先搜索DFS
        System.out.println("------深度优先搜索DFS------");
        boolean[] visitedDFS=new boolean[G.numNodes];
        DFSTrave(G,visitedDFS);
        System.out.println();
        //Dijkstra迪杰斯特拉求单源最短路径
        System.out.println("==========Dijkstra迪杰斯特拉============");
        int[] result = getShortestPaths(G.edgs);
        for(int i=0;i<result.length;i++){
            System.out.print(result[i]+" ");
        }
        System.out.println();
        //Floyd算法求各顶点间最短路径
        System.out.println("===========Floyd算法===========");
        int[][] Floydresult=Floyd(G.edgs);
        printResultFloyd(G.vexs,Floydresult);//打印结果
        //floyd(G.edgs);
        getShortestPaths(G.edgs);
        printResultFloyd(G.vexs,G.edgs);//打印结果
    }

    public MyMgraph directed_mgraph() {
        int _numNodes=10;  //得到顶点的个数
        String[] vexs = new String[_numNodes];//创建顶点一维数组
        int[][] edgs = new int[_numNodes][_numNodes];//创建邻接矩阵，二维数组
        for(int i=0;i<vexs.length;i++) {  //给各个顶点赋值
            vexs[i] = String.valueOf(i);
        }
        for(int i=0;i<_numNodes;i++) { //给各个边赋值，此处使用了一个随机数，假定当随机数小于2时，赋值为0，即无关联
            for(int j=0;j<_numNodes;j++) {
                double random = Math.random()*10;
                if(i==j)
                    edgs[i][j] = 0;
                else if(random<2)
                    edgs[i][j] = 0;
                else
                    edgs[i][j] = (int)random;
            }
        }
        MyMgraph G = new MyMgraph();
        G.numNodes=_numNodes;
        G.vexs = vexs;
        G.edgs = edgs;
        return G;
    }
    public void printResult(String[] vexs,int[][] edgs) {
        System.out.println("顶点的数组：");
        System.out.print("[");
        for(int i=0;i<vexs.length;i++) {
            if(i==vexs.length-1) {
                System.out.print(vexs[i]);
                break;
            }
            else
                System.out.print(vexs[i]+",");
        }
        System.out.println("]");

        System.out.println("边的数组");
        System.out.print("  ");
        for(int i=0;i<vexs.length;i++) {
            if(i==vexs.length-1) {
                System.out.print(vexs[i]);
                break;
            }
            else
                System.out.print(vexs[i]+",");
        }
        System.out.println();
        for(int i=0;i<vexs.length;i++) {
            System.out.print(vexs[i]+"|");
            for(int j=0;j<vexs.length;j++) {
                if(j==vexs.length-1) {
                    System.out.print(edgs[i][j]);
                    break;
                }
                else
                    System.out.print(edgs[i][j]+",");
            }
            System.out.println();
        }
    }


    //邻接表
    public class Vexs{//创建一个类型,用来表示顶点信息
        String data;//顶点的值
        Chain chain;//本顶点的链表
    }
    public class Chain{//创建一个链表类型
        int id;//相邻接顶点的数组下标
        Chain next;//下一个相邻接顶点的数组下标
    }

    @Test
    public void test1(){
        int _numNodes=10;          //节点个数
        System.out.println("请输入节点的个数:"+_numNodes);
        Vexs[] vexs = new Vexs[_numNodes];//定义顶点数组

        System.out.println("请输入各个顶点的值");
        for(int i=0;i<vexs.length;i++) {//建立顶点表
            Vexs v = new Vexs();
            v.data = String.valueOf(i);
            vexs[i] = v;
        }
        directed_algraph(vexs);
        printResult(vexs);
    }

    public void directed_algraph(Vexs[] vexs) {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入各个顶点的出度");
        for(int i=0;i<vexs.length;i++) {
            System.out.println(vexs[i].data+"的出度:");
            int temp = (int)( Math.random()*10);//temp临时存储控制台传来的出度值
            Chain head_chain = null;
            Chain current_chain = null;
            for(int j=0;j<temp;j++) {
                if(head_chain==null) {
                    head_chain = new Chain();
                    head_chain.id = (int)( Math.random()*10);
                    vexs[i].chain = head_chain;
                }else {
                    current_chain = new Chain();
                    current_chain.id = (int)( Math.random()*10);
                    head_chain.next = current_chain;
                    head_chain = current_chain;
                }
            }
        }
    }
    public void printResult(Vexs[] vexs){
        for(int i=0;i<vexs.length;i++) {
            int num = 0;
            while(vexs[i].chain != null) {
                if(num==0) {
                    System.out.print("顶点的值"+vexs[i].data+"的邻接点为:"+vexs[i].chain.id);
                    num++;
                }
                else {
                    System.out.print(","+vexs[i].chain.id);
                }
                vexs[i].chain = vexs[i].chain.next;
            }
            System.out.println();

        }
    }

    //十字链表

    //邻接多重表

    //广度优先搜索BFS
    public void BFSTrave(MyMgraph G, boolean[] visited) {
        for(int i=0;i<G.numNodes;i++){
            visited[i]=false;
        }
        Queue<Integer> queue=new LinkedList<>();
        for(int i=0;i<G.numNodes;i++){
            BFS(G,i,visited,queue);
        }
    }
    public void BFS(MyMgraph G,int v,boolean[] visited,Queue<Integer> queue){
        boolean tag=false;
        if(!visited[v]){
            tag=true;
            System.out.print(G.vexs[v]+" ");   //访问这个顶点
        }
        visited[v]=true;
        queue.add(v);
        while (!queue.isEmpty()){
            queue.remove();
            for(int w=0;w<visited.length;w++){
                if(G.edgs[v][w]==0){
                    continue;
                }
                if(!visited[w]){
                    visited[w]=true;
                    System.out.print(G.vexs[w]+" ");
                    queue.add(w);
                }
            }
        }
        if(tag==true){
            System.out.println();
        }
    }

    //深度优先搜索DFS
    public void DFSTrave(MyMgraph G, boolean[] visited) {
        for(int v=0;v<G.numNodes;v++){
            visited[v]=false;
        }
        for(int v=0;v<G.numNodes;v++){
            if(!visited[v]){
                DFS(G,v,visited);
            }
        }
    }

    public void DFS(MyMgraph G, int v, boolean[] visited) {
        System.out.print(G.vexs[v]+" ");
        visited[v]=true;
        for(int w=0;w<G.numNodes;w++){
            if(visited[w]==true){
                continue;
            }else {
                if(G.edgs[v][w]!=0){
                    DFS(G,w,visited);
                }
            }
        }
    }

    //Dijkstra迪杰斯特拉求单源最短路径
    public int[] getShortestPaths(int[][] adjMatrix) {
        int[] result = new int[adjMatrix.length];   //用于存放顶点0到其它顶点的最短距离
        boolean[] used = new boolean[adjMatrix.length];  //用于判断顶点是否被遍历
        List<List<Integer>> path=new ArrayList<>();
        used[0] = true;  //表示顶点0已被遍历
        for(int i = 1;i < adjMatrix.length;i++) {
            result[i] = adjMatrix[0][i];
            used[i] = false;
        }

        for(int i = 1;i < adjMatrix.length;i++) {
            int min = Integer.MAX_VALUE;    //用于暂时存放顶点0到i的最短距离，初始化为Integer型最大值
            int k = 0;
            for(int j = 1;j < adjMatrix.length;j++) {  //找到顶点0到其它顶点中距离最小的一个顶点
                if(!used[j] && result[j] != 0 && min > result[j]) {   //1没有被遍历 2可以到达该点 3路径为最短
                    min = result[j];
                    k = j;
                }
            }
            used[k] = true;    //将距离最小的顶点，记为已遍历
            for(int j = 1;j < adjMatrix.length;j++) {  //然后，将顶点0到其它顶点的距离与加入中间顶点k之后的距离进行比较，更新最短距离
                if(!used[j]) {  //当顶点j未被遍历时
                    //首先，顶点k到顶点j要能通行；这时，当顶点0到顶点j的距离大于顶点0到k再到j的距离或者顶点0无法直接到达顶点j时，更新顶点0到顶点j的最短距离
                    if(adjMatrix[k][j] != 0 && (result[j] > min + adjMatrix[k][j] || result[j] == 0))
                        result[j] = min + adjMatrix[k][j];
                }
            }
        }
        return result;
    }
    //Floyd算法求各顶点间最短路径
    public static void floyd(int[][] graph) {
        //开始 Floyd 算法
        //每个点为中转
        for (int i = 0; i < graph.length; i++) {
            //所有入度
            for (int j = 0; j < graph.length; j++) {
                //所有出度
                for (int k = 0; k < graph[j].length; k++) {
                    //以每个点为「中转」，刷新所有出度和入度之间的距离
                    //例如 AB + BC < AC 就刷新距离
                    if (graph[i][k] != 0) {
                        int newDistance = graph[j][i] + graph[i][k];
                        if (newDistance < graph[j][k] || graph[j][k] == 0) {
                            //刷新距离
                            graph[j][k] = newDistance;
                        }
                    }
                }
            }
        }
    }


    public int[][] Floyd(int[][] adjMatrix){
        int[][] temp=adjMatrix.clone();
        for(int i=0;i<temp.length;i++){
            int[][] temp1=temp.clone();
            /*int[] hang=new int[temp.length];
            int[] lie=new int[temp.length];*/
            /*for(int j=0;j<temp.length;j++){
                hang[j]=temp[i][j];
                lie[j]=temp[j][i];
            }*/
            for(int j=0;j<temp.length;j++){
                for(int k=0;k<temp.length;k++){
                    if(j==k||j==i||i==k){
                        temp1[j][k]=temp[j][k];
                        continue;
                    }
                    temp1[j][k]=temp[j][0]+temp[0][k];
                }
            }
            for(int j=0;j<temp.length;j++){
                for(int k=0;k<temp.length;k++){
                    if(j==k||j==i||i==k){
                        continue;
                    }
                    if(temp[j][k]==0||temp[j][k]>temp1[j][k]){
                        temp[j][k]=temp1[j][k];
                    }
                }
            }
            /*for(int j=0;j< temp.length;j++){
                for(int k=0;k<temp.length;k++){
                    if(j==k||j==i||i==k){
                        continue;
                    }
                    if(temp[j][k]==0||(hang[k]+lie[j]<temp[j][k])){
                        temp[j][k]=hang[k]+lie[j];
                    }
                }
            }*/
            //每一次都显示
            //System.out.print("第"+(i+1)+"次");
            //printResultFloyd(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"},temp);
        }
        return temp;
    }
    public void printResultFloyd(String[] vexs,int[][] edgs) {
        System.out.println("Floyd数组");
        System.out.print("  ");
        for(int i=0;i<vexs.length;i++) {
            if(i==vexs.length-1) {
                System.out.print(vexs[i]);
                break;
            }
            else
                System.out.print(vexs[i]+",");
        }
        System.out.println();
        for(int i=0;i<vexs.length;i++) {
            System.out.print(vexs[i]+"|");
            for(int j=0;j<vexs.length;j++) {
                if(j==vexs.length-1) {
                    System.out.print(edgs[i][j]);
                    break;
                }
                else
                    System.out.print(edgs[i][j]+",");
            }
            System.out.println();
        }
    }

    public void getShortestPaths1(int[][] adjMatrix) {
        for(int k = 0;k < adjMatrix.length;k++) {
            for(int i = 0;i < adjMatrix.length;i++) {
                for(int j = 0;j < adjMatrix.length;j++) {
                    if(adjMatrix[i][k] != -1 && adjMatrix[k][j] != -1) {
                        int temp = adjMatrix[i][k] + adjMatrix[k][j];  //含有中间节点k的顶点i到顶点j的距离
                        if(adjMatrix[i][j] == -1 || adjMatrix[i][j] > temp)
                            adjMatrix[i][j] = temp;
                    }
                }
            }
        }
    }

}
