public class Stack{
    private static class Node{
        private int data;
        private Node next;
        private Node(int data){
            this.data = data;
        }
    }

    private Node top;

    public static void main(String[] args){
        Stack stack = new Stack();
        stack.push(4);
        stack.push(8);
        stack.push(1);
        stack.push(109);
        stack.push(12312);

        for(int i = 0; i < 5; i++){
            System.out.println(stack.pop());
        }
    }

    public boolean isEmpty(){
        return top == null;
    }
    public int peek(){
        return top.data;
    }
    public void push(int data){
        Node node = new Node(data);
        node.next = top;
        top = node;
    }

    public int pop(){
        int data = top.data;
        top = top.next;
        return data;
    }
}