// Definition for singly-linked list.
// #[derive(PartialEq, Eq, Clone, Debug)]
// pub struct ListNode {
//   pub val: i32,
//   pub next: Option<Box<ListNode>>
// }
// 
// impl ListNode {
//   #[inline]
//   fn new(val: i32) -> Self {
//     ListNode {
//       next: None,
//       val
//     }
//   }
// }
impl Solution {
    pub fn add_two_numbers(l1: Option<Box<ListNode>>, l2: Option<Box<ListNode>>) -> Option<Box<ListNode>> {
        let mut head = ListNode::new(0);
        let mut current_node = &mut head;
        let mut p = l1.as_ref();
        let mut q = l2.as_ref();
        
        let mut carry = 0;
        
        while p.is_some() || q.is_some() {
            
            let x = p.map_or(0, |z| z.val);
            let y = q.map_or(0, |z| z.val);
            let sum = carry + x + y;
            
            carry = sum / 10;
            current_node.next = Some(Box::new(ListNode::new(sum % 10)));
            current_node = current_node.next.as_mut().unwrap();
            
            p = p.and_then(|z| z.next.as_ref());
            q = q.and_then(|z| z.next.as_ref());
        }
        
        if carry > 0 {
            current_node.next = Some(Box::new(ListNode::new(carry)));
        }
        
        head.next
    }
}