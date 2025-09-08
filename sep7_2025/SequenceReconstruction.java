package sep7_2025;

import java.util.*;

public class SequenceReconstruction {
    //permuatation for [1,N]
    //permuatation should be the only and shortest topological sort of sequences
    //[1,3,5] [2,4,5]
    // 1) Generate adj list
    // Kahn Algorithum (each time only 1 node should have 0 degree
    // At last all nodes should be visted


    //Algo is right - but edge cases - duplicacy handling via hashset + indices mistake

    HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

    public boolean kahn(int[] nums, List<List<Integer>> sequences){

        boolean[] vis = new boolean[nums.length+1];
        int[] deg = new int[nums.length+1];

        for(int i=0;i<sequences.size();i++){
            for(int j=1;j<sequences.get(i).size();j++){
                int source = sequences.get(i).get(j-1);
                int destination = sequences.get(i).get(j);
                if(graph.get(source)==null)
                    graph.put(source, new HashSet<>());

                if(graph.get(source).contains(destination)==false){
                    graph.get(source).add(destination);
                    deg[destination]++;
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=1;i<vis.length;i++){
            if(deg[i]==0){
                queue.offer(i);
                vis[i] = true;
            }
        }


        for(int i=0;i<nums.length;i++){
            if(queue.size()==1 && queue.peek()==nums[i]){
                queue.poll();
                for(int node: graph.getOrDefault(nums[i],new HashSet<>())){
                    deg[node]--;
                    if(deg[node]==0){
                        vis[node]=true;
                        queue.offer(node);
                    }
                }
            }
        }
        for(int i=1;i<vis.length;i++){
            if(vis[i]==false)
                return false;
        }
        return queue.size()==0;
    }

    public static void main(String[] args){
        SequenceReconstruction sequenceReconstruction = new SequenceReconstruction();
        List<List<Integer>> seq = new ArrayList<>();
        seq.add(Arrays.asList(1,2));
        seq.add(Arrays.asList(1,3));
        seq.add(Arrays.asList(2,3));
        System.out.println(sequenceReconstruction.kahn(new int[]{1,2,3}, seq ));
    }
}
