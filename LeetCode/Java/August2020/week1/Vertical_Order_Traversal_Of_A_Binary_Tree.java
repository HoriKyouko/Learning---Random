public class Vertical_Order_Traversal_Of_A_Binary_Tree{
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> output = new ArrayList<>();
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        helper(root, 0, 0, map);
        
        for(Map.Entry<Integer, TreeMap<Integer, PriorityQueue<Integer>>> entry : map.entrySet()){
            List<Integer> line = new ArrayList<>();
            for(PriorityQueue<Integer> pq : entry.getValue().values()){
                while(pq.size() > 0)
                    line.add(pq.poll());
            }
            output.add(line);
        }
        
        return output;
    }
    
    public void helper(TreeNode root, int x, int y, TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map){
        if(root == null)
            return;
        TreeMap<Integer, PriorityQueue<Integer>> m = new TreeMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        if(map.containsKey(x)){
            m = map.get(x);
            if(m.containsKey(y)){
                pq = m.get(y);
            }
        }
        pq.add(root.val);
        m.put(y, pq);
        map.put(x, m);
        helper(root.left, x-1, y+1, map);
        helper(root.right, x+1, y+1, map);
    }
}