public class Reconstruct_Itenerary {
    private HashMap<String, List<String>> adjList = new HashMap<>();
    private LinkedList<String> route = new LinkedList<>();
    private int numTickets = 0;
    private int numTicketsUsed = 0;
    
    public List<String> findItinerary(List<List<String>> tickets) {
        if(tickets == null || tickets.size() == 0)
            return route;
        
        numTickets = tickets.size();
        for(int i = 0; i < tickets.size(); i++){
            if(!adjList.containsKey(tickets.get(i).get(0))){
                List<String> list = new ArrayList<>();
                list.add(tickets.get(i).get(1));
                adjList.put(tickets.get(i).get(0), list);
            }
            else{
                adjList.get(tickets.get(i).get(0)).add(tickets.get(i).get(1));
            }
        }
        
        for(Map.Entry<String, List<String>> entry : adjList.entrySet())
            Collections.sort(entry.getValue());
        
        route.add("JFK");
        dfsRoute("JFK");
        return route;
    }
    
    private void dfsRoute(String v){
        if(!adjList.containsKey(v))
            return;
        List<String> list = adjList.get(v);
        for(int i = 0; i < list.size(); i++){
            String neighbor = list.get(i);
            list.remove(i);
            route.add(neighbor);
            numTicketsUsed++;
            dfsRoute(neighbor);
            
            if(numTickets == numTicketsUsed)
                return;
            
            list.add(i, neighbor);
            route.removeLast();
            numTicketsUsed--;
        }
    }
}