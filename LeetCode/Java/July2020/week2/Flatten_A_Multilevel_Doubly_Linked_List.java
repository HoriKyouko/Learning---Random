public class Flatten_A_Multilevel_Doubly_Linked_List {
    public Node flatten(Node head) {
        Stack<Node> stack = new Stack<>();
        Node root = head;
        
        while(root != null){
            if(root.next == null && !stack.empty()){
                Node temp = stack.pop();
                if(temp != null){
                    root.next = temp;
                    temp.prev = root;
                }
            }
            if(root.child != null){
                stack.push(root.next);
                root.next = root.child;
                root.child.prev = root;
                root.child = null;
            }
            root = root.next;
        }
        return head;
    }
}