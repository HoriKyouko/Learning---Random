use std::collections::HashMap;
impl Solution {
    pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
        /*
            Need to take the first element and add with second element if
            it doesn't work then first with third and so on...
            
            Should check that nums isn't null.
        */
        
        /*
         CRAPPY SLOW, BUT WORKING SOLUTION!
        
        let mut i = 0;
        let mut j = i + 1;
        
        while i < nums.len() - 1 {
            while j < nums.len() {
                let temp = nums[i] + nums[j];
                if temp == target {
                    let mut vec: Vec<i32> = Vec::new();
                    vec.push(i as i32);
                    vec.push(j as i32);
                    return vec;
                }
                j += 1;
            }
            i += 1;
            j = i + 1;
        }
        vec![0, 0]
        */
        
        // Faster version, but slightly more confusing so lets comment it up.
        
        // Create a hashmap of i32 as our key and another i32 as our value.
        let mut map: HashMap<i32, i32> = HashMap::new();
        
        // We need some form of counter for our position in the vector.
        let mut count = 0;
        
        // This is our loop over the vector and i is essentially num[count]
        for i in nums{
            // Need to get the complement of what were looking for minus our current value.
            let complement:i32 = target - i;
            // Check to see if our complement is already a key
            if map.contains_key(&complement) {
                /*
                    map.get returns a option which needs to be unwrapped into an i32 this is done with
                    unwrap_or(&0) which defaults to 0 if it can't unwrap for some reason.
                    
                    We then get an &i32 value, but need to dereference it hence the * in from of the map
                    
                    We then create an initialized array of the returned value from the HashMap and our current
                    position in the array.
                */
                return vec![*map.get(&complement).unwrap_or(&0), count]
            }
            // Inserts i as a key and count as the value.
            map.insert(i as i32, count);
            count += 1;
        }
        // Needed only for Rust reasons.
        vec![0, 0]
    }
}