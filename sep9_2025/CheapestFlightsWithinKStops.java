package sep9_2025;

import helper.Pair;

import java.util.*;


public class CheapestFlightsWithinKStops {
    //LC 787
    //Brute Force Way is BFS but Level order so at max I should go from 0 to K+1 levels
    //Maintain distance array
    //1) BFS with level order traversal
    //2) Bellman Ford (E*K)
    public int bfs(int n, int[][] flights, int src, int dst, int k) {
        Queue<Pair<Integer,Integer>> queue = new LinkedList<>();
        List<List<Pair<Integer,Integer>>> adj = new ArrayList<>();

        //Adjacency List
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<flights.length;i++){
            int source = flights[i][0];
            int destination = flights[i][1];
            int cost = flights[i][2];
            adj.get(source).add(new Pair<>(destination,cost));
        }
        queue.offer(new Pair<>(src,0));

        //distance Array
        int[] dist = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src] = 0;

        for(int stop=0;stop<k+1;stop++){
            int size = queue.size();
            for(int i=1;i<=size;i++){
                Pair<Integer,Integer> node = queue.poll();
                for(Pair<Integer,Integer> child: adj.get(node.getKey())){
                    if(dist[child.getKey()]>node.getValue()+child.getValue()){
                        queue.offer(new Pair<>(child.getKey(), node.getValue()+ child.getValue()));
                        dist[child.getKey()] = node.getValue() + child.getValue();
                    }
                }
            }
        }
        if(dist[dst]==Integer.MAX_VALUE)
            return -1;
        else
            return dist[dst];
    }
    //k=1
    //0 -> 1, 0->2
    //issue is we cannot use the standard bellman we need to create temp array
    private int bellmanFord(int n, int[][] flights, int src, int dest, int k){
        int[] dist = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src] = 0;

        for(int i=0;i<k+1;i++){
            int[] temp = Arrays.copyOf(dist, n);
            for(int j=0;j<flights.length;j++){
                int source = flights[j][0];
                int destination = flights[j][1];
                int cost = flights[j][2];
                if(dist[source]!=Integer.MAX_VALUE && temp[destination]>(dist[source]+cost)){
                    temp[destination] = dist[source] + cost;
                }
            }
            dist = temp;
        }

        return dist[dest]!=Integer.MAX_VALUE?dist[dest]: -1;
    }
}
