public class Reorder_List {
    /*public void reorderList(ListNode head) {
        HashMap<Integer, ListNode> map = new HashMap<>();
        int i = 1;
        while(head != null){
            map.put(i, head);
            head = head.next;
            i++;
        }
        
        ListNode temp = new ListNode();
        ListNode current = temp;
        i = 1;
        int j = map.size();
        while(i <= j){                          // 1,2,3,4
            current.next = map.get(i);          // curr -> 1
            if(i != j)
                map.get(i).next = map.get(j);    // 1->4
            map.get(j).next = null;             // 4->null
            current = map.get(j);
            i++;
            j--;
        }
    }*/
    public void reorderList(ListNode head) {
        if (head == null) 
            return;
        
        // find the middle of linked list [Problem 876]
        // in 1->2->3->4->5->6 find 4 
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse the second part of the list [Problem 206]
        // convert 1->2->3->4->5->6 into 1->2->3->4 and 6->5->4
        // reverse the second half in-place
        ListNode prev = null, curr = slow, tmp;
        while (curr != null) {
            tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }

        // merge two sorted linked lists [Problem 21]
        // merge 1->2->3->4 and 6->5->4 into 1->6->2->5->3->4
        ListNode first = head, second = prev;
        while(second.next != null) {
            tmp = first.next;
            first.next = second;
            first = tmp;

            tmp = second.next;
            second.next = first;
            second = tmp;
        }
    }
}