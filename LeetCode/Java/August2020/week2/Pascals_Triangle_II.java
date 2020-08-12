public class Pascals_Triangle_II{
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        
        if(rowIndex == 0){
            row.add(1);
            return row;
        }
        
        for(int i = 0; i <= rowIndex; i++){
            List<Integer> prevRow = row;
            row = new ArrayList<>();
            // always be a 1 at the beginning
            row.add(1);
            
            for(int j = 1; j < i; j++)
                row.add(prevRow.get(j-1) + prevRow.get(j));   
            
            // always be a 1 at the end
            row.add(1);
            
        }
        
        return row;
    }
}