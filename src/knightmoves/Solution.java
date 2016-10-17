package knightmoves;

import java.util.ArrayList;
import java.util.List;

public class Solution 
{

   public class Position
   {
       int x;
       int y;
       
       public Position(int x, int y)
       {
          this.x = x;
          this.y = y;
       }
       
       public String toString()
       {
           return "x:[" + x + "] y:[" + y + "]";
       }
       
       public boolean equals(Object other)
       {
         if (other instanceof Position)
            if (((Position)other).x == this.x && ((Position)other).y == this.y) return true;
         
         return false;
       }
       
   }
   
   public int solutionRecursive(int[][] board) 
   {
       return moveKnight(board, new Position(0, 0), new ArrayList<Position> ()); 
   }
   
   public int moveKnight(int[][] board, Position now, List<Position> lastMoveList)
   {
       if (now.x == board.length - 1 && now.y == board[0].length - 1)
          return 0;
       
       lastMoveList.add(now);
       
       List<Position> nextMoveList = findNextMoves(now.x, now.y, board, lastMoveList);
       
       if (0 == nextMoveList.size()) return -1;
       
       int lowestNumberOfMoves = -1;
       
       for (Position nextMove : nextMoveList)
       {
          int numberOfMoves = moveKnight(board, nextMove, lastMoveList);
          
          if (-1 == lowestNumberOfMoves && numberOfMoves > -1) lowestNumberOfMoves = numberOfMoves;
          else if (numberOfMoves <  lowestNumberOfMoves && numberOfMoves > -1) lowestNumberOfMoves = numberOfMoves;
       }
       
       if (-1 < lowestNumberOfMoves) return lowestNumberOfMoves + 1;
       
       return -1;
   }
   
   public List<Position> findNextMoves(int x, int y, int[][] board, List<Position> lastMoveList)
   {
       List<Position> nextMoveList = new ArrayList();
       
       if (validKnightMove(x + 2, y + 1, board, lastMoveList))
       {
           nextMoveList.add(new Position(x + 2, y + 1));
       }
       
       if (validKnightMove(x + 2, y - 1, board, lastMoveList))
       {
           nextMoveList.add(new Position(x + 2, y - 1));
       }
       
       if (validKnightMove(x - 2, y + 1, board, lastMoveList))
       {
           nextMoveList.add(new Position(x - 2, y + 1));
       }
       
       if (validKnightMove(x - 2, y - 1, board, lastMoveList))
       {
           nextMoveList.add(new Position(x - 2, y - 1));
       }
       
       if (validKnightMove(x + 1, y + 2, board, lastMoveList))
       {
           nextMoveList.add(new Position(x + 1, y + 2));
       }
       
       if (validKnightMove(x + 1, y - 2, board, lastMoveList))
       {
           nextMoveList.add(new Position(x + 1, y - 2));
       }
       
       if (validKnightMove(x - 1, y + 2, board, lastMoveList))
       {
           nextMoveList.add(new Position(x - 1, y + 2));
       }
       
       if (validKnightMove(x - 1, y - 2, board, lastMoveList))
       {
           nextMoveList.add(new Position(x - 1, y - 2));
       }
       
       return nextMoveList;
   }
   
   public boolean validKnightMove(int x, int y, int[][] board, List<Position> lastMoveList)
   {
       Position here = new Position(x, y);
       
       if (x >= 0 && y >= 0 
           && x < board.length && y < board[0].length)
       {
           if (!lastMoveList.contains(here) && 0 == board[x][y]) return true;
       }
       
       return false;
   }
   
   public int solution(int[][] board) 
   {
      int [][] numberOfMovesBoard = new int [board.length][board[0].length];
      
      for (int rowIndex = 0; rowIndex < numberOfMovesBoard.length; rowIndex++)
      {
         for (int colIndex = 0; colIndex < numberOfMovesBoard[rowIndex].length; colIndex++)
         {
            numberOfMovesBoard[rowIndex][colIndex] = -1;
         }
         
      }
      
      numberOfMovesBoard[0][0] = 0;

      for (int rowIndex = 0; rowIndex < numberOfMovesBoard.length; rowIndex++)
      {
         for (int colIndex = 0; colIndex < numberOfMovesBoard[rowIndex].length; colIndex++)
         {
            if (0 == board[rowIndex][colIndex]) 
            {
               int lookBackValue = lookBack(rowIndex, colIndex, board, numberOfMovesBoard);
               
               if (-1 != lookBackValue) numberOfMovesBoard[rowIndex][colIndex] = lookBackValue + 1;
            }
            
            if (-1 < numberOfMovesBoard[rowIndex][colIndex]) fillBack(rowIndex, colIndex, board, numberOfMovesBoard);
         }
         
      }
      
      for (int rowIndex = 0; rowIndex < numberOfMovesBoard.length; rowIndex++)
      {
         System.out.print("[");

         for (int colIndex = 0; colIndex < numberOfMovesBoard[rowIndex].length; colIndex++)
         {
            System.out.print(numberOfMovesBoard[rowIndex][colIndex] + ", ");
         }
         
         System.out.println("]");
         
      }
      
      return numberOfMovesBoard[numberOfMovesBoard.length - 1][numberOfMovesBoard[numberOfMovesBoard.length - 1].length - 1];
      
   }

   public void fillBack(int x, int y, int[][] board, int[][] numberOfMovesBoard)
   {
      int numberToFillBack = numberOfMovesBoard[x][y] + 1;
      
      checkKnightBack(numberOfMovesBoard,
                                             numberToFillBack,
                                             x+2,
                                             y+1,
                                             board);
       
      checkKnightBack(numberOfMovesBoard,
                      numberToFillBack,
                                             x+2,
                                             y-1,
                                             board);
       
      checkKnightBack(numberOfMovesBoard,
                      numberToFillBack,
                                             x-2,
                                             y+1,
                                             board);
       
      checkKnightBack(numberOfMovesBoard,
                      numberToFillBack,
                                             x-2,
                                             y-1,
                                             board);
       
      checkKnightBack(numberOfMovesBoard,
                      numberToFillBack,
                                             x-1,
                                             y-2,
                                             board);
       
      checkKnightBack(numberOfMovesBoard,
                      numberToFillBack,
                                             x-1,
                                             y+2,
                                             board);
       
      checkKnightBack(numberOfMovesBoard,
                      numberToFillBack,
                                             x+1,
                                             y-2,
                                             board);
       
      checkKnightBack(numberOfMovesBoard,
                      numberToFillBack,
                                             x+1,
                                             y+2,
                                             board);
   }
   
   public int lookBack(int x, int y, int[][] board, int[][] numberOfMovesBoard)
   {
       int minimumNumberOfMoves = -1;
       
       minimumNumberOfMoves = checkKnightMove(numberOfMovesBoard,
                                              minimumNumberOfMoves,
                                              x+2,
                                              y+1);
        
       minimumNumberOfMoves = checkKnightMove(numberOfMovesBoard,
                                              minimumNumberOfMoves,
                                              x+2,
                                              y-1);
        
       minimumNumberOfMoves = checkKnightMove(numberOfMovesBoard,
                                              minimumNumberOfMoves,
                                              x-2,
                                              y+1);
        
       minimumNumberOfMoves = checkKnightMove(numberOfMovesBoard,
                                              minimumNumberOfMoves,
                                              x-2,
                                              y-1);
        
       minimumNumberOfMoves = checkKnightMove(numberOfMovesBoard,
                                              minimumNumberOfMoves,
                                              x-1,
                                              y-2);
        
       minimumNumberOfMoves = checkKnightMove(numberOfMovesBoard,
                                              minimumNumberOfMoves,
                                              x-1,
                                              y+2);
        
       minimumNumberOfMoves = checkKnightMove(numberOfMovesBoard,
                                              minimumNumberOfMoves,
                                              x+1,
                                              y-2);
        
       minimumNumberOfMoves = checkKnightMove(numberOfMovesBoard,
                                              minimumNumberOfMoves,
                                              x+1,
                                              y+2);
               
       return minimumNumberOfMoves;
   }

   private int checkKnightMove(int[][] numberOfMovesBoard,
                               int minimumNumberOfMoves,
                               int newX,
                               int newY)
   {
      if (newX >= 0 && newY >= 0 
            && newX < numberOfMovesBoard.length && newY < numberOfMovesBoard[0].length)
      {
      if (-1 < numberOfMovesBoard[newX][newY])
       {
           if (-1 == minimumNumberOfMoves) minimumNumberOfMoves = numberOfMovesBoard[newX][newY];
           else if (minimumNumberOfMoves > numberOfMovesBoard[newX][newY]) minimumNumberOfMoves = numberOfMovesBoard[newX][newY];           
       }
      }
      return minimumNumberOfMoves;
   }

   private void checkKnightBack(int[][] numberOfMovesBoard,
                               int numberOfMoves,
                               int newX,
                               int newY,
                               int [][] board)
   {
      if (newX >= 0 && newY >= 0 
            && newX < numberOfMovesBoard.length && newY < numberOfMovesBoard[0].length)
      {
      if (0 == board[newX][newY] && (-1 == numberOfMovesBoard[newX][newY] || numberOfMovesBoard[newX][newY] > numberOfMoves))
       {
           numberOfMovesBoard[newX][newY] = numberOfMoves;
           
           fillBack(newX, newY, board, numberOfMovesBoard);       
       }
      }
   }
}

