package recursion;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class US_elections {

private static int count=0;
  //private static int occurance=0;

  public static int solution(int num_states, int[] delegates, int[] votes_Biden, int[] votes_Trump, int[] votes_Undecided){
    // delegates[0] votes_Biden[0] votes_Trump[0] votes_Undecided[0] // one row
    //int total_needed = 3;
     // delegates[0] votes_Biden[0] votes_Trump[0] votes_Undecided[0] // one row
    //int total_needed = 3;
    int total_state_count = 0;
    int final_needed =0;
    
    final_needed = (total_state_count/2) +1;
    
    
    // TODO Auto-generated method stub
    int[] states = delegates;
    int[] biden = votes_Biden;
    int[] trump = votes_Trump;
    int[] undecided = votes_Undecided;
    int Trump_Num_States = 0;
    int Biden_Num_States =0;
    int total_undecided_needed = 0;
    
    // Total States
    for (int i=0;i< states.length; i++) {
      total_state_count += states[i];
    }
    
  //  System.out.println("Total Count is: " + total_state_count);
    
    final_needed = (total_state_count/2) +1;
    
    ArrayList<Integer> new_States = new ArrayList<Integer>();
	ArrayList<Integer> undecided_new = new ArrayList<Integer>();
	
	for (int i=0; i<states.length;i++) {
		if((undecided[i]+trump[i]) < biden[i]) { //this means Biden has already won that state
			Biden_Num_States +=states[i];
		}
		else if((undecided[i]+biden[i]) <= trump[i]) {
			Trump_Num_States += states[i];
		}
		else {
			double new_undecided = (double) undecided[i]/2;
			int final_new_undecided =0;
			int new_biden_votes = 0;
			int new_trump_votes =0;
			int bidenn_undecided =0;
			int trumpp_undecided=0;
			//System.out.println((new_undecided) % 2);
			if (undecided[i] % 2 == 0) {
				final_new_undecided = (int) new_undecided;
				new_biden_votes = biden[i] + (int) new_undecided;
				new_trump_votes = trump[i] + (int) new_undecided;
				bidenn_undecided = ((int) (new_undecided ));
				trumpp_undecided = ((int) (new_undecided));
				new_undecided = final_new_undecided;
			}
			else {
				final_new_undecided = (int) ((int) (new_undecided + 0.5));
				//System.out.println(final_new_undecided);
				new_biden_votes = (int) (biden[i] + ((int) (new_undecided + 0.5)));
				new_trump_votes = (int) (trump[i] + ((int) (new_undecided - 0.5)));
				bidenn_undecided = ((int) (new_undecided + 0.5));
				trumpp_undecided = ((int) (new_undecided - 0.5));
				
				
				new_undecided = final_new_undecided;
			}
			
			//System.out.println(new_undecided+1);
			
			
			
			if (new_biden_votes <= new_trump_votes) {
				for (int j=0; j<new_undecided+1; j++) {
					if (new_biden_votes <= new_trump_votes) {
						new_biden_votes = (int) (new_biden_votes +1);
						final_new_undecided++;
						new_trump_votes = (int) (new_trump_votes -1);   //this will continue untill Biden <= Trump
					}
					else {
						new_biden_votes = new_biden_votes +1;
						//final_new_undecided++;
						new_States.add(states[i]);
						undecided_new.add(final_new_undecided);
						break;
					}
				}
			}
			else {
				//if ((new_biden_votes - new_trump_votes) > 2) {
					for (int j=0; j<new_undecided; j++) {
						if ((new_biden_votes - new_trump_votes) > 2) {
							new_biden_votes = (int) (new_biden_votes -1);
							final_new_undecided--;
							new_trump_votes = (int) (new_trump_votes +1);
						}
						else {
							if((new_biden_votes-1) != new_trump_votes) {
								new_biden_votes = (int) (new_biden_votes -1);
								new_trump_votes = (int) (new_trump_votes +1);
								new_States.add(states[i]);
								undecided_new.add(final_new_undecided);
								break;
							}
							else {
								new_States.add(states[i]);
								undecided_new.add(final_new_undecided);
								break;
						//}
						}
					}
				}
			}
		}
	}
	
    
    // If trump has won more than 50% of the states
    if (Trump_Num_States >= final_needed) {
      
      return -1;
    }
    // If Biden won 50% of the states
    else {
    if (Biden_Num_States >= final_needed) {
      return 0;
    }
    // If there was a tie
    if(Biden_Num_States == Trump_Num_States && new_States.size()==0) {
      return -1;
    }
    
    
    int ff = final_needed-Biden_Num_States;
    ArrayList<Integer> K = minimum_sum_require(new_States, new_States.size(),ff);
    
  //  System.out.println(undecided_new);
    
    total_undecided_needed =Integer.MAX_VALUE;
  for(int i=0; i<K.size();i++) {
    int current_undecided = min_profit_knapSack(K.get(i), new_States, undecided_new, new_States.size());
    
    if(total_undecided_needed  > current_undecided) {
      total_undecided_needed  = current_undecided;
    }
  }
  
  
    
    return total_undecided_needed;
    }
  }
  
  private static int min(int first_index, int second_index) { 
      if(first_index < second_index) {
        return first_index;
      }
      else {
        return second_index;
      }
  } 

  private static int min_profit_knapSack(int max_weight, ArrayList<Integer> states, ArrayList<Integer> undecided, int n) 
  {  
    int profit_Knapsack[][] = new int[n + 1][max_weight + 1]; 

    for (int row = 0; row <= n; row++) 
    { 
      for (int coloumn = 0; coloumn <= max_weight; coloumn++) 
      { 
        if (coloumn == 0) 
          profit_Knapsack[row][coloumn] = 0; 
        else if(row==0 && coloumn!=0)
          profit_Knapsack[row][coloumn]=1000000000;
        else if (row==0 && coloumn==0)
          profit_Knapsack[row][coloumn]=0;
        else if (states.get(row - 1) <= coloumn) 
          if(undecided.get(row - 1) + profit_Knapsack[row - 1][coloumn - states.get(row - 1)] <0)
            profit_Knapsack[row][coloumn] = min((undecided.get(row - 1) + profit_Knapsack[row - 1][coloumn - states.get(row - 1)])*-1, profit_Knapsack[row - 1][coloumn]); 
          else if(undecided.get(row - 1) + profit_Knapsack[row - 1][coloumn - states.get(row - 1)] == 1) {
            profit_Knapsack[row][coloumn] = min((undecided.get(row - 1) + profit_Knapsack[row - 1][coloumn - states.get(row - 1)]), profit_Knapsack[row - 1][coloumn]); 
          }
          else  
            profit_Knapsack[row][coloumn] = min(undecided.get(row - 1) + profit_Knapsack[row - 1][coloumn - states.get(row - 1)], profit_Knapsack[row - 1][coloumn]); 
        else
          profit_Knapsack[row][coloumn] = profit_Knapsack[row - 1][coloumn]; 
      } 
    }
    

    return profit_Knapsack[n][max_weight]; 
  } 
  
  private static ArrayList<Integer> minimum_sum_require(ArrayList<Integer> arr, int n, int required_votes) {
    
    ArrayList<Integer> testing = new ArrayList<Integer>();
    
    int sum = 0; 
    int current_index=0;
    while(current_index <n) {
      sum += arr.get(current_index); 
      current_index++;
    }
    

    
    boolean[][] double_array = new boolean[n + 1][sum + 1]; 

  
    int assigning_zero=0;
    while(assigning_zero < n) {
      double_array[assigning_zero][0] = true; 
      assigning_zero++;
    }

    int current_i=1;
    while(current_i <=n) {
      double_array[current_i][arr.get(current_i-1)] = true; 
      for (int j = 1; j <= sum; j++) { 
        if (double_array[current_i - 1][j] == true) { 
          double_array[current_i][j] = true; 
          double_array[current_i][j + arr.get(current_i-1)] = true; 
        } 
      } 
        current_i++;
    } 
   
    for (int j = sum; j >-1; j--) { 
      if (double_array[n][j] == true) {
        if (j>= required_votes) {
          testing.add(j);
        }
        else {
          break;
        }
      }
    }
    return testing;
    
  } 
  
  


  
  
  
   

  public static void main(String[] args) {
   try {
    //  String path = args[0];
    // File myFile = new File(path);
	  final long startTime = System.currentTimeMillis();
      File myFile= new File("/Users/macuk/Documents/secret/063-roundingError-01.in");
      Scanner sc = new Scanner(myFile);
      int num_states = sc.nextInt();
      int[] delegates = new int[num_states];
      int[] votes_Biden = new int[num_states];
      int[] votes_Trump = new int[num_states];
      int[] votes_Undecided = new int[num_states];  
      for (int state = 0; state<num_states; state++){
        delegates[state] =sc.nextInt();
        votes_Biden[state] = sc.nextInt();
        votes_Trump[state] = sc.nextInt();
        votes_Undecided[state] = sc.nextInt();
      }
      sc.close();
      int answer = solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
        System.out.println(answer);
        final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime) + "ms");
	} catch (FileNotFoundException e) {
		System.out.println("An error occurred.");
		e.printStackTrace();
	}
    }

}
