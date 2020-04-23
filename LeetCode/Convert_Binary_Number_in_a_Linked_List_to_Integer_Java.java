/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int getDecimalValue(ListNode head) {
        int output = 0;
        while(head != null){
            output <<= 1;
            output |= head.val;
            head = head.next;
        }
        
        return output;
    }
}