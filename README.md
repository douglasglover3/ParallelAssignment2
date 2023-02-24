To start the first problem's code run this command:

java ProblemOne

To start the second problem's code run this command:

java ProblemTwo




My interpretation of the first problem meant that guests could signal that they have all entered the maze based on how many cupcakes were requested. I set it so that each guest ate a cupcake the first time they entered the labyrinth, requesting a cupcake if there was none. We know that there is a cupcake at the start, so once N - 1 cupcakes were requested, then all the guests had entered the labyrinth and eaten a cupcake.

For the second problem, we had the option of three different strategies to solve the problem. Each one had its benefits and drawbacks. 

I chose the second strategy in my implementation.

The first solution's efficiency depends on how long it takes for the guest to leave the showroom. If a guest takes more time in the showroom, then it is more likely that another guest will waste their time checking if the showroom is empty. In a scenario where guests spend very little time in the showroom, this method is very effective.

The second solution is similar to the first, but it lessens the effort to check if the showroom is full by placing more responsibility on teh guests entering. This strategy is more efficient if the guests are checking if the showroom is open often. However, if guests are not checking often it is slightly less efficient as each guest has to take the time to flip the sign.

The third solution provides a queue. This guarantees the guest will enter if they wait long enough, but if the queue gets extremely long this is highly inefficient as the guests will be doing nothing for a long time. This strategy is more "fair", since the other scenarios could lead to some guests waiting much longer than others purely by chance. But in this case, the guest's time spent waiting is appropriate to when they first arrived.