public class LRU_Cache {
    Node head = new Node();
    Node tail = new Node();
    HashMap<Integer, Node> map;
    int cap;
    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>(capacity);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if(node != null){
            remove(node);
            add(node);
            return node.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        Node node = map.get(key);
        if(node != null){
            node.value = value;
            remove(node);
            add(node);
        }
        else{
            if(map.size() == cap){
                map.remove(tail.prev.key);
                remove(tail.prev);
            }
            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;
            
            map.put(key, newNode);
            add(newNode);
        }
    }
    
    public void add(Node node){
        Node headNext = head.next;
        node.next = headNext;
        headNext.prev = node;
        head.next = node;
        node.prev = head;
    }
    
    public void remove(Node node){
        Node nextNode = node.next;
        Node prevNode = node.prev;
        
        nextNode.prev = prevNode;
        prevNode.next = nextNode;
    }
    
    class Node{
        Node next;
        Node prev;
        int value;
        int key;
    }
}