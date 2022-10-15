package com.algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Axing {
    Map<Integer, Integer> path1=new HashMap<>();
    @Test
    public void test(){
        int[][] matrix={{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,-1,-1,-1,-1,-1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,-1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,-1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,-1,1,1,1,1,1},
                {1,1,1,1,1,-1,-1,-1,-1,-1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
        int start=32;
        int end=105;
        List<Integer> path=Axing(matrix,start,end);  //改进一下 目前只能找到探测过的格子 要找到最短路径和消耗
        if(path==null){
            System.out.println("没有路径");
        }else {
            for(int i=0;i<path.size();i++){
                System.out.print(path.get(i)+" ");
            }
            System.out.println();
            System.out.println("总共探测了"+path.size()+"个格子");
            for(int i=0;i<path.size();i++){
                System.out.println(path1.get(path.get(i))+"移动到了"+path.get(i));
            }
            System.out.println("---------------------------");
            for(int i=path.size()-1;;i--){
                if(path1.get(path.get(i))==start){
                    int sum=0;
                    for(int j=i;j<path.size();j++){
                        int price=matrix[path.get(j)/matrix[0].length][path.get(j)%matrix[0].length];
                        sum+=price;
                        System.out.println(path1.get(path.get(j))+"移动到了"+path.get(j)+",花费了代价"+price);
                    }
                    System.out.println("总代价为"+sum);
                    break;
                }
            }
        }

    }

    public int heuristic(int end,int next,int length){  //计算曼哈顿距离
        int starty=next/length;
        int startx=next-starty*length;
        int endy=end/length;
        int endx=end-endy*length;
        return Math.abs(endy-starty)+Math.abs(endx-startx);
    }
    public int getcurrent(Map<Integer, Integer> frontier,List<Integer> list){  //找到当前代价最低的格子的index
        int value=Integer.MIN_VALUE;
        int index=-1;
        int key=-1;
        for(int i=list.size()-1;i>=0;i--){   //从后往前找 因为新找到的格子都在后面 可以先找总代价相同的待遍历格子进去
            if(i==list.size()-1){
                value=frontier.get(list.get(i));
                index=list.get(i);
                key=i;
            }else if(frontier.get(list.get(i))<value){
                value=frontier.get(list.get(i));
                index=list.get(i);
                key=i;
            }
        }
        /*for(int i=0;i<list.size();i++){
            if(i==0){
                value=frontier.get(list.get(i));
                index=list.get(i);
                key=i;
            }else if(frontier.get(list.get(i))<value){
                value=frontier.get(list.get(i));
                index=list.get(i);
                key=i;
            }
        }*/
        list.remove(key);
        return index;
    }
    public int[] getnext(int currentindex,int[][] matrix){  //根据格子的位置决定相邻格子
        int[] next;
        if(currentindex==0){  //左上角
            next=new int[2];
            next[0]=currentindex+1;
            next[1]=currentindex+matrix[0].length;
        }else if(currentindex==matrix[0].length-1){  //右上角
            next=new int[2];
            next[0]=currentindex-1;
            next[1]=currentindex+matrix[0].length;
        }else if(currentindex==(matrix.length-1)*matrix[0].length){  //左下角
            next=new int[2];
            next[0]=currentindex-matrix[0].length;
            next[1]=currentindex+1;
        }else if(currentindex==(matrix.length*matrix[0].length)-1){  //右下角
            next=new int[2];
            next[0]=currentindex-matrix[0].length;
            next[1]=currentindex-1;
        }else {   //不是四个角
            if(currentindex<matrix[0].length){  //最上层
                next=new int[3];
                next[0]=currentindex-1;
                next[1]=currentindex+1;
                next[2]=currentindex+matrix[0].length;
            }else if(currentindex%matrix[0].length==0){  //左侧
                next=new int[3];
                next[0]=currentindex-matrix[0].length;
                next[1]=currentindex+1;
                next[2]=currentindex+matrix[0].length;
            }else if((currentindex+1)%matrix[0].length==0){  //右侧
                next=new int[3];
                next[0]=currentindex-matrix[0].length;
                next[1]=currentindex-1;
                next[2]=currentindex+matrix[0].length;
            }else if((currentindex/matrix[0].length)==matrix.length-1){  //最底层
                next=new int[3];
                next[0]=currentindex-matrix[0].length;
                next[1]=currentindex-1;
                next[2]=currentindex+1;
            }else{  //也不是侧边
                next=new int[4];
                next[0] = currentindex - matrix[0].length;  //上面
                next[1] = currentindex - 1;                //左边
                next[2] = currentindex + 1;                //右边
                next[3] = currentindex + matrix[0].length; //下面
            }
        }
        return next;
    }
    public List<Integer> Axing(int[][] matrix,int start,int end) {
        List<Integer> path=new ArrayList<>();   //保存查探的格子

        Map<Integer, Integer> frontier=new HashMap<>();  //保存每个格子的index和总代价
        frontier.put(start,0);
        List<Integer> list=new ArrayList<>();       //保存每个格子的index
        list.add(start);
        Map<Integer, Integer> came_from = new HashMap<Integer, Integer>();   //表示格子的来向
        Map<Integer, Integer> cost_so_far = new HashMap<Integer, Integer>();    //表示当前代价
        came_from.put(start, null);   //最开始就是start
        cost_so_far.put(start, 0);    //一开始就在start 所以代价是0
        boolean tag=false;

        while (!frontier.isEmpty()) {     //只要没空 就一直找下去
            int currentindex = getcurrent(frontier,list);   //拿到最小代价的那个index
            path.add(currentindex);         //探测这个格子 放进去
            if (currentindex == end) {   //如果找到了格子
                tag=true;
                break;
            }
            int[] next = getnext(currentindex,matrix);

            for (int i = 0; i < next.length; i++) {   //遍历相邻的格子
                if(next[i]>=0&&next[i]<matrix[0].length*matrix.length){  //如果index处于正常范围
                    if(matrix[next[i]/matrix[0].length][next[i]%matrix[0].length]==-1){  //-1表示这里是墙壁 这里不能经过
                        continue;
                    }
                    //新的代价=原先格子花的代价+到现在格子要的代价
                    int new_cost = cost_so_far.get(currentindex) + matrix[next[i]/matrix[0].length][next[i]%matrix[0].length];
                    if (!cost_so_far.containsKey(next[i])||new_cost < cost_so_far.get(next[i])) {  //如果没有遍历这个格子或者这个格子的代价比之前遍历的时候记录的低
                        if(cost_so_far.containsKey(next[i])){    //如果之前有遍历 这次是因为代价更低来进去
                            cost_so_far.replace(next[i], new_cost);
                        }else {                                   //之前没有遍历过
                            cost_so_far.put(next[i],new_cost);
                        }
                        int priority = new_cost + heuristic(end, next[i], matrix[0].length);  //当前代价+预估代价=总代价
                        frontier.put(next[i],priority);          //放入index和预估代价
                        list.add(next[i]);                   //放入index
                        //came_from.put(next[i], currentindex);   //放入路径
                        if(came_from.containsKey(next[i])){
                            came_from.replace(next[i], currentindex);
                        }else {
                            came_from.put(next[i], currentindex);
                        }
                    }
                }
            }
        }

        if(tag==true){   //如果为true表示是找到了目标才结束了循环
            for(int i=0;i<path.size();i++){
                path1.put(path.get(i),came_from.get(path.get(i)));
            }
            return path;
        }else {          //否则表示是跑完了所以能跑的格子都没找到
            return null;
        }

    }

}
