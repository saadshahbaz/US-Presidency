# US-Presidency

## Objective:
The goal of this project was to find an  efficient algorithm to know the minimum number of people that Mr. Biden needs to convince in order to secure the presidency of the United States of America when the information regarding each state is given. Particularly, for each State, the few information regarding 
1. The expected number of people who are projected to vote for Mr. Biden
2. The expected number of people who are projected to vote for Mr. Trump
3. The number of people who have not made a voting decision yet.
 
The technical description of the polls makes it clear that the numbers of the first two categories are not likely to change because they are votes from people with long-time roots in a particular political party. On the other hand, people in the third category are the ones who expressed no preference and did not lean towards
either of the major parties.


## Data 
Following num states lines each contain four integers (separated by spaces) with the following information.
1. The number of delegates for a specific state.
2. The number of people who will vote for Mr. Biden in that state.
3. The number of people who will vote for Mr. Trump in that state.
4. The number of people who have not made a voting decision in that state yet.

For each provided file I calculate the minimum number of people that Mr. Biden would have to convince to earn the US presidency. If it is not possible for Mr. Biden to win the election, then an output of -1 is returned.

## Examples and Expected output
<br> Sample Input **1**:
<br> `3` 
<br> `7 2401 3299 0 `
<br> `6 2401 2399 0` 
<br> `2 750 750 99 `
<br> Sample *Output **1: 50**


<br>

<br>Sample Input **2**:
<br>`3`
<br>`7 100 200 200`
<br>`8 100 300 200`
<br>`9 100 400 200`
<br>Sample Output **2: -1**

<br>


<br> Sample Input **3**:
<br> `3` 
<br> `32 0 0 20 `
<br> `32 0 0 20` 
<br> `64 0 0 41 `
<br> Sample *Output **1: 50**
