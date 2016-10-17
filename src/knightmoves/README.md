I don't have the problem set for this one but it is basically:

given a chess board where 0 is a free space and 1 is an occupied space, determine
the minimum number of jumps a knight needs to make to get from the upper left-hand
corner to the lower right-hand corner.

The code is pretty messy, but I included this to demonstrate the JUnit test cases
in the test directory.

There is also my initial try at this using recursion, which failed the performance criteria.
Then, I did it using a method to check each space and determine the minimum number of jumps
to get to each each (empty) space. 