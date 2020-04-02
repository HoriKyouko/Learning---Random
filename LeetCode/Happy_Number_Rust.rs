use std::collections::HashSet;
use std::collections::LinkedList;

impl Solution {
    pub fn is_happy(n: i32) -> bool {
        let mut value : i32 = n;
        let mut set: HashSet<i32> = HashSet::new();
        while !set.contains(&value) {
            if value == 1 {
                return true;
            }
            set.insert(value);
            let mut list: LinkedList<i32> = LinkedList::new();
            let mut number : i32 = value;
            while number > 0 {
                list.push_front(number % 10);
                number = number / 10;
            }
            value = 0;
            while !list.is_empty() {
                let temp: i32 = list.pop_front().unwrap();
                value = value + temp.pow(2);
            }
        }
        false
    }
}