impl Solution {
    pub fn single_number(nums: Vec<i32>) -> i32 {
        let mut map : HashMap<i32, i32> = HashMap::new();

        for i in &nums {
            *map.entry(*i).or_insert(0) += 1;
        }

        let a :i32 = 1;
        for i in &nums{
            if map.get(&i).unwrap() == &a {
                return *i;
            }
        }
       0
    }
}