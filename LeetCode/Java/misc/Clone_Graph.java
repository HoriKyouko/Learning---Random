public class Clone_Graph{
    public Node cloneGraph(Node node) {
        if(node == null)
            return null;
        
        Node cloneNode = new Node(node.val);
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        
        HashMap<Integer, Node> map = new HashMap<>();
        map.put(cloneNode.val, cloneNode);
        
        while(!queue.isEmpty()){
            Node n = queue.remove();
            //System.out.println(n.val);
            for(Node nod : n.neighbors){
                if(!map.containsKey(nod.val)){
                    queue.add(nod);
                    map.put(nod.val, new Node(nod.val));
                }
                map.get(n.val).neighbors.add(map.get(nod.val));
            }
        }
        
        return cloneNode;
    }
}
