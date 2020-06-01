import java.util.HashMap;

public class First_Unique_Number {
    private class ListNode{
        ListNode next;
        ListNode prev;
        int val;
        public ListNode(int value){
            val = value;
            next = null;
            prev = null;
        }
    }

    private class MyDLL{
        private ListNode head;
        private ListNode tail;

        public MyDLL(){
            head = new ListNode(-1);
            tail = new ListNode(-1);

            head.next = tail;
            tail.next = head;
        }

        public ListNode getFirst(){
            return head.next;
        }

        public boolean isEmpty(){
            return head.next == tail;
        }

        public void remove(ListNode node){
            ListNode pre = node.prev;
            ListNode next = node.next;
            pre.next = next;
            next.prev = pre;

        }

        public ListNode append(int num){
            ListNode node = new ListNode(num);
            ListNode pre = tail.prev;
            pre.next = node;
            node.next = tail;
            tail.prev = node;
            node.prev = pre;

            return node;
        }
    }

    private MyDLL keys;
    private HashMap<Integer, ListNode> map;
    
    public firstUnique(int[] nums){
        map = new HashMap();
        keys = new MyDLL();

        for(int n : nums)
            add(n);
    }

    public int showFirstUnique(){
        if(keys.isEmpty())
            return -1;
        return keys.getFirst().val;
    }

    public void add(int value){
        if(map.containsKey(value)){
            if(map.get(value) != null){
                keys.remove(map.get(value));
                map.put(value, null);
            }
        }
        else{
            ListNode node = keys.append(value);
            map.put(value, node);
        }
    }
}