public class Task_Scheduler {
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        int max = 0;
        int maxCount = 0;
        
        for(char t : tasks){
            count[t -'A']++;
            if(max == count[t-'A']){
                maxCount++;
            }
            else if(max < count[t-'A']){
                max = count[t-'A'];
                maxCount = 1;
            }
        }
        
        int emptySlots = (max-1) * (n - (maxCount - 1));
        int availableTasks = tasks.length - max * maxCount;
        int idles = Math.max(0, emptySlots - availableTasks);
        return tasks.length + idles;
    }
}