impl Solution {
    pub fn reverse(x: i32) -> i32 {
        /*
            1. Check if negative.
                a. If negative mark as such and abs value
                b. move to next step
            2. Convert i32 to String
            3. Reverse String
                a. If check is negative add - to beginning of string
                b. move to next step
            4. Check if String is a valid i32 if not return 0.
        */
        
        let check_negative = x.signum();
        let toop = x.overflowing_abs();
        if toop.1 {
            return 0;
        }
        let mut s : String = toop.0.to_string().chars().rev().collect::<String>();
        if check_negative == -1 {
            s.insert(0, '-');
        }
        s.parse::<i32>().unwrap_or(0)
    }
}