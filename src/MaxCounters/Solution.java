package MaxCounters;

public class Solution 
{
   public int[] solution(int maxN, int[] movesArr) 
   {
       int [] counterArr = new int[maxN];
         
       int maxValue = 0;
       int startValue = 0;
               
       for (int index = 0; index < movesArr.length; index++)
       {
           if (movesArr[index] >= 1 && movesArr[index] <= maxN)
           {
              if (counterArr[movesArr[index]-1] < startValue) counterArr[movesArr[index]-1] = startValue;
              
              counterArr[movesArr[index]-1]++;
              
              if (counterArr[movesArr[index]-1] > maxValue) maxValue = counterArr[movesArr[index]-1];
           }
           
           if (maxN + 1 == movesArr[index])
           {                    
               startValue = maxValue;
           }
       }
   
       for (int counterIndex = 0; counterIndex < counterArr.length; counterIndex++)
           if (counterArr[counterIndex] < startValue) counterArr[counterIndex] = startValue;

       return counterArr;
   }
}

