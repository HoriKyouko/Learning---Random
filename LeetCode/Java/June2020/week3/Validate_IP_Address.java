public class Validate_IP_Address {
    public String validIPAddress(String IP) {
        if(IP.chars().filter(ch-> ch == '.').count() == 3)
            return validateIPv4(IP);
        else if(IP.chars().filter(ch-> ch == ':').count() == 7)
            return validateIPv6(IP);
        else
            return "Neither";
    }
    
    private String validateIPv4(String IP){
        String[] strs = IP.split("\\.", -1);
        
        for(String str : strs){
            if(str.length() == 0 || str.length() > 3)
                return "Neither";
            if(str.charAt(0) == '0' && str.length() != 1)
                return "Neither";
            for(char ch: str.toCharArray())
                if(!Character.isDigit(ch))
                    return "Neither";
            if(Integer.parseInt(str) > 255)
                return "Neither";
        }
        
        return "IPv4";
    }
    
    private String validateIPv6(String IP){
        String[] strs = IP.split(":", -1);
        String hex = "0123456789abcdefABCDEF";
        for(String str: strs){
            if(str.length() == 0 || str.length() > 4)
                return "Neither";
            for(char ch: str.toCharArray())
                if(hex.indexOf(ch) == -1)
                    return "Neither";
        }
        return "IPv6";
    }
}