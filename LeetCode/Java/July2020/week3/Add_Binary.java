public class Add_Binary {
    public String addBinary(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        StringBuilder aStr = new StringBuilder(a);
        StringBuilder bStr = new StringBuilder(b);
        StringBuilder c = new StringBuilder();
        
        if(aLen > bLen) {
        	int temp = aLen-bLen;
        	for(int i = 0; i < temp; i++) {
        		bStr.insert(0, "0");
        		bLen++;
        	}
        }
        else if(aLen < bLen) {
        	int temp = bLen-aLen;
        	for(int i = 0; i < temp; i++) {
        		aStr.insert(0, "0");
        		aLen++;
        	}
        }
        boolean carryBit = false;
        while(aLen > 0 && bLen > 0){
            if(bStr.charAt(bLen-1) == '1' && aStr.charAt(aLen-1) == '1' && !carryBit){
                carryBit = true;
                c.append("0");
            }
            else if(bStr.charAt(bLen-1) == '1' && aStr.charAt(aLen-1) == '1' && carryBit){
                c.append("1");
            }
            else if(bStr.charAt(bLen-1) == '1' && aStr.charAt(aLen-1) == '0' && !carryBit){
                c.append("1");
            }
            else if(bStr.charAt(bLen-1) == '1' && aStr.charAt(aLen-1) == '0' && carryBit){
                c.append("0");
            }
            else if(bStr.charAt(bLen-1) == '0' && aStr.charAt(aLen-1) == '1' && !carryBit){
                c.append("1");
            }
            else if(bStr.charAt(bLen-1) == '0' && aStr.charAt(aLen-1) == '1' && carryBit){
                c.append("0");
            }
            else if(bStr.charAt(bLen-1) == '0' && aStr.charAt(aLen-1) == '0' && !carryBit){
                c.append("0");
            }
            else if(bStr.charAt(bLen-1) == '0' && aStr.charAt(aLen-1) == '0' && carryBit){
                carryBit = false;
                c.append("1");
            }
            aLen--;
            bLen--;
        }
        if(carryBit)
        	c.append("1");
        c.reverse();
        return c.toString();
    }
}