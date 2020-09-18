public class Insertion_Sort_List {
    public ListNode insertionSortList(ListNode head) {
        if( head == null )
			return head;
		
		ListNode dummy = new ListNode(0); //new starter of the sorted list
		ListNode pre = dummy; //insert node between pre and pre.next
        
		while( head != null ){
            ListNode temp = head.next;
            if(pre.val >= head.val)
                pre = dummy;
			//find the right place to insert
			while( pre.next != null && pre.next.val < head.val )
				pre = pre.next;
            
			//insert between pre and pre.next
			head.next = pre.next;
			pre.next = head;
			head = temp;
		}
		
		return dummy.next;
    }
}
