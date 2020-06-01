public class Odd_Even_Linked_List {
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode slow = head;
        ListNode fast = head.next;
        ListNode node = fast;
        while(fast != null && fast.next != null){
            slow.next = fast.next;
            slow = slow.next;
            fast.next = slow.next;
            fast = fast.next;
        }
        slow.next = node;
        
        return head;
    }
}