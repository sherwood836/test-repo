package PassingCars;

public class Solution 
{
   public int solution(int[] carArr) 
   {
       boolean foundAnEastBoundCar = false;
       
       int numberOfCarsGoingDirectionEast = 0;
       int numberOfPassedCars = 0;
       
       for (int index = 0; index < carArr.length; index++)
       {
          if (0 == carArr[index]) foundAnEastBoundCar = true;
          
          if (foundAnEastBoundCar) 
          {
             if (0 == carArr[index]) numberOfCarsGoingDirectionEast++;
             else numberOfPassedCars += numberOfCarsGoingDirectionEast;
          }
          
           if (numberOfPassedCars > 1000000000) return -1;
       }
       
       return numberOfPassedCars;
   }
}
