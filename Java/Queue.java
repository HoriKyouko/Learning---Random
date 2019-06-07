public class Queue{
    public static class Node{
        private int data;
        private Node next;
        private Node(int data){
            this.data = data;
        }
    }
    private Node head;
    private Node tail;
    public static void main(String[] args){
        Queue queue = new Queue();
        queue.add(4);
        queue.add(8);
        queue.add(1);
        queue.add(109);
        queue.add(12312);

        for(int i = 0; i < 5; i++){
            System.out.println(queue.remove());
        }
    }

    public boolean isEmpty(){
        return head == null;
    }
    public int peek(){
        return head.data;
    }
    public void add(int data){
        Node node = new Node(data);
        if(tail != null){
            tail.next = node;
        }
        tail = node;
        if(head == null){
            head = node;
        }
    }
    public int remove(){
        int data = head.data;
        head = head.next;
        if(head == null){
            tail = null;
        }
        return data;
    }
}