package sep7_2025;

import java.util.*;

public class ReconstructItineary {
    //LC-332
    //Problem is valid DFS traversal (smallest lexical order)
    // I did via TreeSet (removed city, dfs(city), addback(city) not efficient
    //result - LinkedList so we can add at First postion in O(1) time
    private HashMap<String, PriorityQueue<String>> graph = new HashMap<>();
    private LinkedList<String> result = new LinkedList<>();


    public List<String> setGraph(List<List<String>> tickets){

        for(int i=0;i<tickets.size();i++){
            String from = tickets.get(i).get(0);
            String to = tickets.get(i).get(1);
            if(graph.get(from)==null){
                graph.put(from, new PriorityQueue<>());
            }
            graph.get(from).offer(to);
        }

        dfs("JFK");
        return result;
    }

    public void dfs(String source){

        while(graph.get(source)!=null && graph.get(source).size()>0){
            dfs(graph.get(source).poll());
        }

        result.addFirst(source);
    }
    public static  void main(String[] args){

        ReconstructItineary reconstructItineary = new ReconstructItineary();

        List<List<String>> tickets =  new ArrayList<>();
        tickets.add(Arrays.asList("JFK", "SFO"));
        tickets.add(Arrays.asList("JFK", "ATL"));
        tickets.add(Arrays.asList("SFO", "ATL"));
        tickets.add(Arrays.asList("ATL", "JFK"));
        tickets.add(Arrays.asList("ATL", "SFO"));

        reconstructItineary.result = new LinkedList<>();
        System.out.println(reconstructItineary.setGraph(tickets));



    }
}
