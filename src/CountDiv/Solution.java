package CountDiv;

public class Solution 
{
   public int solution(int A, int B, int K) 
   {
       return (B / K) - (A / K) + (0 == A % K ? 1 : 0); 
   }
}
