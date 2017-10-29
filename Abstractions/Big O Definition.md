## Big O
### definition
Big O is used to quantify efficiency of algorithms, usually by time complexity and space complexity. definition below is from wikipedia: 
> Big O notation is a mathematical notation that describes the limiting behavior of a function when the argument tends towards a particular value or infinity. It is a member of a family of notations invented by Paul Bachmann, Edmund Landau, and others, collectively called Bachmann–Landau notation or asymptotic notation.  
> In computer science, big O notation is used to classify algorithms according to how their running time or space requirements grow as the input size grows.  
What does that mean? That means big O of a algoritm is, by time complexity, to measure that rate of concumed time variaence when size of data varies.  

Here are several most used levels in big O notation:  
* O(1): constant  
* O(logn): logarithmetic  
* O(n): linear  
* O(n^2): quadratic  
* O(n^c):  algebraic  
### How caculate
If an algorithm takes multiple levels of orders, only highest order counts. In time complexity. For example, only biggest order is important when data increase greatly.  
Eg:
If the time consumed by a algorithm can be written as `2*n^2 + n + 1`, the big O for it is `n^2` because when data increase greatly, `n^2` will be much much important than other factors.
### Take the upper bound
Lower level big O is equal to better time complexity, but better time complexity does not mean the time consumed is less. Because time complexity takes upper bound of algorithm, take upper bound means best case is not considered.  
For example:
See this problem
>Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.  
You may assume no duplicates in the array.  
Here are few examples.  
[1,3,5,6], 5 → 2  
[1,3,5,6], 2 → 1  
[1,3,5,6], 7 → 4  
[1,3,5,6], 0 → 0  

Because array is sorted, I am consdering to use binary search to find the correct number and then return index. Codes are shown below:  
```
function serachInsert(nums, target){
  if(nums === null){
    return -1;
  }
  
  var left  = 0;
  var right = nums.length - 1;
  while(left <= right){
    var mid = Math.floor((left + right) / 2)
    if(nums[mid] == target){
      return mid;
    }else if(nums[mid] > target){
      right = mid - 1;
    }else{
      left = mid + 1;
    }
  }
  return left;
}
```
The time complexity of this algorithm is O(log n), and the time is usually between 100 - 105ms according to leetcode results.  
When I change to another method to find a index of number nearest to target.  
```
function searchInsert(nums, target){
  if(nums === null){
    return -1;
  }
  for(int i = 0; i < nums.length; i++){
    //if find a number eqaual or greater than target, return the index
    if(nums[i] >= target){
      return i;
    }
  }
  return nums.length - 1;
}
```
For this solution, time complexity is O(n) indead of O(log n) because big O wll take the upper bound. In the wrost case, the second solution will iterate all elements in the array so the time complexity O(n).   
While time commplexity of second solution is worse than the first one, time of the second is not ususally taken longer thane the first one, and time is usually 95 to 100 ms according to leetcode results. That is because algorithem will not always on the worst case.
Always care more about data itself rather than time complexity indicated by algorithms.
