package application;

import java.lang.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Random;

public class Generowanie {
	
	int SN; //Support Number
	int RC; //Rows Columns support number
	int FINAL; //Final "if true"
	int N = 9;
	int SRN = 3;
	Random rand = new Random();
	private int Sudoku[][] = new int[9][9];
	public int Sud2[][] = new int[9][9];

	boolean unUsedInBox(int rowStart, int colStart, int num) 
    { 
        for (int i = 0; i<SRN; i++) 
            for (int j = 0; j<SRN; j++) 
                if (Sudoku[rowStart+i][colStart+j]==num) 
                    return false; 
  
        return true; 
    } 
	
	int randomGenerator(int num) 
    { 
        return (int) Math.floor((Math.random()*num+1)); 
    } 
	
	void fillBox(int row,int col) 
    { 
        int num; 
        for (int i=0; i<SRN; i++) 
        { 
            for (int j=0; j<SRN; j++) 
            { 
                do
                { 
                    num = randomGenerator(N); 
                } 
                while (!unUsedInBox(row, col, num)); 
  
                Sudoku[row+i][col+j] = num; 
            } 
        } 
    } 

	void fillDiagonal() 
    { 
  
        for (int i = 0; i<N; i=i+SRN) 
  
            // for diagonal box, start coordinates->i==j 
            fillBox(i, i); 
    } 

	boolean fillRemaining(int i, int j) 
    { 
        //  System.out.println(i+" "+j); 
        if (j>=N && i<N-1) 
        { 
            i = i + 1; 
            j = 0; 
        } 
        if (i>=N && j>=N) 
            return true; 
  
        if (i < SRN) 
        { 
            if (j < SRN) 
                j = SRN; 
        } 
        else if (i < N-SRN) 
        { 
            if (j==(int)(i/SRN)*SRN) 
                j =  j + SRN; 
        } 
        else
        { 
            if (j == N-SRN) 
            { 
                i = i + 1; 
                j = 0; 
                if (i>=N) 
                    return true; 
            } 
        } 
  
        for (int num = 1; num<=N; num++) 
        { 
            if (CheckIfSafe(i, j, num)) 
            { 
                Sudoku[i][j] = num; 
                if (fillRemaining(i, j+1)) 
                    return true; 
  
                Sudoku[i][j] = 0; 
            } 
        } 
        return false; 
    } 
	
	boolean CheckIfSafe(int i,int j,int num) 
    { 
        return (unUsedInRow(i, num) && 
                unUsedInCol(j, num) && 
                unUsedInBox(i-i%SRN, j-j%SRN, num)); 
    } 
	
	boolean unUsedInRow(int i,int num) 
	    { 
	        for (int j = 0; j<N; j++) 
	           if (Sudoku[i][j] == num) 
	                return false; 
	        return true; 
	    } 

	boolean unUsedInCol(int j,int num) 
	    { 
	        for (int i = 0; i<N; i++) 
	            if (Sudoku[i][j] == num) 
	                return false; 
	        return true; 
	    } 

	
	public void Randomize(int i, int j)
	{
		Sudoku[i][j] = rand.nextInt(9) + 1;
	}
	
	public int RowsColumns(int i, int j)
	{
		RC = 0;
		for(int k=1; k<=9; k++)
		{
			//dla i
			if(i+1-k > 0) 
			{
				if(Sudoku[i][j] == Sudoku[i-k][j])
					{
						if(Sudoku[i][j]!= 0 && Sudoku[i-k][j]!=0) RC = 1;
					}
					
			}
			
			if(i+1+k <= 9) 
			{
				if(Sudoku[i][j] == Sudoku[i+k][j])
					{
						if(Sudoku[i][j]!= 0 && Sudoku[i+k][j]!=0) RC = 1;
					}
					
			}
			
			if(j+1-k > 0) 
			{
				if(Sudoku[i][j] == Sudoku[i][j-k])
					{
						if(Sudoku[i][j]!= 0 && Sudoku[i][j-k]!=0) RC = 1;
					}
					
			}
			
			if(j+1+k <= 9) 
			{
				if(Sudoku[i][j] == Sudoku[i][j+k])
					{
						if(Sudoku[i][j]!= 0 && Sudoku[i][j+k]!=0) RC = 1;
					}
					
			}
			
		}
		return RC;
	}
	
	public void fillValues() 
    { 
        // Fill the diagonal of SRN x SRN matrices 
        fillDiagonal(); 
  
        // Fill remaining blocks 
        fillRemaining(0, SRN); 

    } 

	public void printSudoku() 
    { 
        for (int i = 0; i<N; i++) 
        { 
            for (int j = 0; j<N; j++) 
                System.out.print(Sud2[i][j] + " "); 
            System.out.println(); 
        } 
        System.out.println(); 
    } 
	
	public int CheckSquare(int i, int j)
	{
		SN = 0;

				
				//Teraz i, j musz¹ byæ od 1, nie od 0
				if(((i+1) % 3 == 0) || (i+1 == 3))
				{
				if(((j+1) % 3 == 0) || (j+1 == 3))
				{
					if(Sudoku[i][j] == Sudoku[i-1][j]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i-2][j]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i][j-1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i-1][j-1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i-2][j-1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i][j-2]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i-1][j-2]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i-2][j-2]) SN = 1;           //Patrzymy, czy w kwadracie siê nie powtarzaj¹.
				}
				if(((j+1) % 3 == 1) || (j+1 == 1))
				{
					if(Sudoku[i][j] == Sudoku[i-1][j]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i-2][j]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i][j+1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i-1][j+1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i-2][j+1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i][j+2]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i-1][j+2]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i-2][j+2]) SN = 1;           //Patrzymy, czy w kwadracie siê nie powtarzaj¹.
				}
				if(((j+1) % 3 == 2) || (j+1 == 2))
				{
					if(Sudoku[i][j] == Sudoku[i-1][j]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i-2][j]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i][j-1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i-1][j-1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i-2][j-1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i][j+1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i-1][j+1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i-2][j+1]) SN = 1;           //Patrzymy, czy w kwadracie siê nie powtarzaj¹.
				}
				}
				
				if(((i+1) % 3 == 1) || (i+1 == 1))
				{
				if(((j+1) % 3 == 0) || (j+1 == 3))
				{
					if(Sudoku[i][j] == Sudoku[i+1][j]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+2][j]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i][j-1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+1][j-1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+2][j-1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i][j-2]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+1][j-2]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+2][j-2]) SN = 1;           //Patrzymy, czy w kwadracie siê nie powtarzaj¹.
				}
				if(((j+1) % 3 == 1) || (j+1 == 1))
				{
					if(Sudoku[i][j] == Sudoku[i+1][j]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+2][j]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i][j+1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+1][j+1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+2][j+1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i][j+2]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+1][j+2]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+2][j+2]) SN = 1;           //Patrzymy, czy w kwadracie siê nie powtarzaj¹.
				}
				if(((j+1) % 3 == 2) || (j+1 == 2))
				{
					if(Sudoku[i][j] == Sudoku[i+1][j]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+2][j]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i][j-1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+1][j-1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+2][j-1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i][j+1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+1][j+1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+2][j+1]) SN = 1;           //Patrzymy, czy w kwadracie siê nie powtarzaj¹.
				}
				}
				
				
				if(((i+1) % 3 == 2) || (i+1 == 2))
				{
				if(((j+1) % 3 == 0) || (j+1 == 3))
				{
					if(Sudoku[i][j] == Sudoku[i-1][j]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+1][j]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i][j-1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i-1][j-1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+1][j-1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i][j-2]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i-1][j-2]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+1][j-2]) SN = 1;           //Patrzymy, czy w kwadracie siê nie powtarzaj¹.
				}
				if(((j+1) % 3 == 1) || (j+1 == 1))
				{
					if(Sudoku[i][j] == Sudoku[i-1][j]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+1][j]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i][j+1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i-1][j+1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+1][j+1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i][j+2]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i-1][j+2]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+1][j+2]) SN = 1;           //Patrzymy, czy w kwadracie siê nie powtarzaj¹.
				}
				if(((j+1) % 3 == 2) || (j+1 == 2))
				{
					if(Sudoku[i][j] == Sudoku[i-1][j]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+1][j]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i][j-1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i-1][j-1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+1][j-1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i][j+1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i-1][j+1]) SN = 1;
					if(Sudoku[i][j] == Sudoku[i+1][j+1]) SN = 1;           //Patrzymy, czy w kwadracie siê nie powtarzaj¹.
				}
				}
		return SN;
	}
	
	public int FinalCheck(int i, int j)
	{
		if(RowsColumns(i, j) == 0 && CheckSquare(i, j) == 0) FINAL = 0;
		else FINAL = 1;
		return FINAL;
	}

	public int[][] generate() {
		return Sudoku;
	}

	public void generateProper()
	{
		
		fillValues();
		Sud2 = Sudoku;
		printSudoku();
		
	}

}