public class Longest_Duplicate_Substring {
    long q = (1<<31)-1;
    long d = 26;
    public String longestDupSubstring(String S) {
        int left = 2;
        int right = S.length()-1;
        int maxLen = 0;
        int start = 0;
        
        while(left <= right){
            int len = left + (right-left) / 2;
            boolean found = false;
            
            HashMap<Long, List<Integer>> map = new HashMap<>();
            long hash = hash(S, len);
            map.put(hash, new ArrayList<>());
            map.get(hash).add(0);
            long RM = 1l;
            for(int i = 1; i < len; i++)
                RM = (d * RM) % q;
            loop:
            for(int i = 1; i + len <= S.length(); i++){
                hash = (hash + q - RM * S.charAt(i-1) % q) % q;
                hash = (hash * d + S.charAt(i + len - 1 )) % q;
                if(!map.containsKey(hash)){
                    map.put(hash, new ArrayList<>());
                }
                else{
                    for(int j: map.get(hash)){
                        if(compare(S, i, j, len)){
                            found = true;
                            start = i;
                            maxLen = len;
                            break loop;
                        }
                    }
                }
                map.get(hash).add(i);
            }
            if(found)
                left = len+1;
            else
                right = len-1;
        }
        return S.substring(start, start + maxLen);
    }
    private long hash(String S, int len){
        long h = 0;
        for(int j = 0; j < len; j++)
            h = (d * h + S.charAt(j)) % q;
        return h;
    }
    private boolean compare(String S, int i, int j, int len){
        for(int count = 0; count < len; count++)
            if(S.charAt(i++) != S.charAt(j++))
                return false;
        return true;
    }
}