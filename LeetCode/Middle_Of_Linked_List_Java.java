/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode temp = head;
        int counter = 0;
        while(head != null){
            counter++;
            head = head.next;
        }
        counter/=2;
        
        for(int i = 0; i < counter; i++)
            temp = temp.next;
        return temp;
    }
}