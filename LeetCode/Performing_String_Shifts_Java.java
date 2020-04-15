class Solution {
    public String stringShift(String s, int[][] shift) {
        
        StringBuilder str = new StringBuilder();
        int count = 0;
        for(int [] shifts: shift){
            if(shifts[0] == 0)
                count += shifts[1];
            else
                count -= shifts[1];
        }
        
        boolean neg = false;
        if(count < 0) neg = true;
        
        count = Math.abs(count) % s.length();
        if(count == 0){
            return s;
        }
        else if(neg){
            str.append(s.substring(s.length() - Math.abs(count), s.length()));
            str.append(s.substring(0, s.length()- Math.abs(count)));
            return str.toString();
            
        }
        else{
            str.append(s.substring(count, s.length()));
            str.append(s.substring(0, count));
            return str.toString();
        }
        
        /*
            1. Build a stringbuilder off of s called str
            2. for loop through the number of shifts will need to do.
            3. get whether we need to do a right or left shift based on
               whether [i][0] is 0 or 1.
            4. While amountShift > 0 where amountShift is [i][1] value
            5. if [i][0] is 0 then take character at beginning of str
               and append it to the end.
               else if [i][0] is 1 then take character at end of str and
               place it at the beginning of the str.
            6. Once done shifting return str.toString();
            
            FIRST SOLUTION!
            for(int i = 0; i < shift.length; i++){
                int move = shift[i][0];
                int temp = shift[i][1];
                while(temp > 0){
                    if(move == 0){
                        char c = s.charAt(0);
                        str.append(c);
                        //System.out.println("Move == 0 before delete " + str.toString() + " " + s);
                        str.delete(0,1);
                        s = str.toString();
                        //System.out.println("Move == 0 after delete " + str.toString() + " " + s);
                    }
                    else{
                        char c = s.charAt(s.length()-1);
                        str.insert(0, c);
                        //System.out.println("Move == 1 before delete " + str.toString() + " " + s);
                        str.delete(str.length()-1,str.length());
                        s = str.toString();
                        //System.out.println("Move == 1 after delete " + str.toString() + " " + s);
                    }
                    temp--;
                }
            }

            return str.toString();
        */
    }
}