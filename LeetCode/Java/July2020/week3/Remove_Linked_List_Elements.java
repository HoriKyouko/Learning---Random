public class Remove_Linked_List_Elements {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null)
            return head;
        
        while(head != null && head.val == val)
            head = head.next;
        
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode trail = dummy;
        
        while(head != null){
            if(head.val == val){
                trail.next = head.next;
                head = head.next;
                continue;
            } 
            head = head.next;
            trail = trail.next;
        }
        
        return dummy.next;
    }
}