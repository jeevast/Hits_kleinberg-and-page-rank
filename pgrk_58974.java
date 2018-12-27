/* Jeeva Surulibommu Thudikarabommu cs610 58974 prp */
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class pgrk_58974 {

	
	static double matrix_vertices[][] = new double[0][0];
	static double page_rank_value[][] = new double[0][0];
	static int initial_value = 0;
	static int iteration_value = 0;
	static double d = 0.85;
	static double hub_value[][] = new double[0][0];
	static int no_of_pointing_value[][] = new int[0][0];

	
	

	public static double[][] transpose_matrix( double arr_val[][])
	{
		double transpose_matrix[][] = new double[arr_val[0].length][arr_val.length];
		
		for(int i = 0; i < arr_val.length; i++)
		{
			for(int j = 0; j < arr_val[0].length; j++)
			{
				
				
				transpose_matrix[j][i] = arr_val[i][j];		
			}
		}
		
			return transpose_matrix;
	}
	
	

	public static  double[][] vertices_array(String FileName) 
	{

		int vertices_size = 0;
		int edges_size = 0;
		String file_name = FileName;
		Scanner file = null;
		
		 
		try
		{
			 file = new Scanner(new File(file_name));//"C:\\Users\\S.T.Jeeva\\Documents\\project_text.txt"C:\\Users\\S.T.Jeeva\\Downloads\\large.txt
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
			
			if(file.hasNextInt())
			{
				vertices_size = file.nextInt();
			}
			
			double arr[][] = new double[vertices_size][vertices_size];
			edges_size = file.nextInt();
			for(int i = 0; i < edges_size; i++)
			{
				
					arr[file.nextInt()][file.nextInt()] = 1;
				}
			file.close();
			
			return arr;
		}
	
	
	
	
	
	
	public static void initiating_pgrk(double dup1_pgrk_value[][], int no_vertices, int initial_value)
	{
		if(initial_value == 0)
		{
		for(int i = 0; i < 1; i++)
		{
			for(int j = 0; j < no_vertices; j++)
			{
				dup1_pgrk_value[i][j] = 0;
				
			}
		}
		}
		else if(initial_value == 1)
		{
			for(int i = 0; i < 1; i++)
			{
				for(int j = 0; j < no_vertices; j++)
				{
					dup1_pgrk_value[i][j] = 1;
					
				}
			}
		}
		
		else if(initial_value == -1)
		{
			for(int i = 0; i < 1; i++)
			{
				for(int j = 0; j <  no_vertices; j++)
				{
					dup1_pgrk_value[i][j] = (double)1/ no_vertices;
				
					
				}
			}
		}
		
		else if(initial_value == -2)
		{

			for(int i = 0; i < 1; i++)
			{
				for(int j = 0; j < no_vertices; j++)
				{
					dup1_pgrk_value[i][j] = 1/Math.sqrt(no_vertices);
					
				}
			}
		}
		
		
		page_rank_value = dup1_pgrk_value;
		
	}
	
	public static double compute_authority_no(int p) {
		
		return (double) no_of_pointing_value[0][p];
	}
	
	public static double compute_sub_pgrk(int n)
	{
		double sum = 0;
		double array_value[][] = page_rank_value;
		for(int r = 0; r < hub_value[n].length; r++ )
		{
			if(hub_value[n][r] == 1)
			{
				
				sum = sum + ((array_value[0][r])/(compute_authority_no(r)));
				//System.out.println("paaaaaaaaage rannnnnk " + page_rank_value[0][i] ) ;
				//System.out.println("nooooooo offfffffffff authorityyyyyyy "  + compute_authority_no(i));
				//System.out.println("summmmmmmmmmmm valueeeeeeeeeeeeeee " + sum);
			}
			
		}
		
		return sum;
	}
	
		

	public static void iterate_pgrk(int iteration_value)
	{
		int h = 0;
		int iteration = 0;
		boolean compute_difference = false;
		boolean check = true;
		
		if(iteration_value == 0 || iteration_value == -1 || iteration_value == -2 || iteration_value == -3 || iteration_value == -4 || iteration_value == -5 || iteration_value == -6 )
		{
			iteration = 50;
			compute_difference = true;
		}
		else
		{
		iteration = iteration_value;
		}
		
		for(int i = 0; i < iteration; i++)
		{
			if(check)
			{
				
				h = h+1;
			double[][] prev_pgrk_value = page_rank_value;
	if(i==0)
			{
				System.out.printf("Base : %d :", i);
			
			for(int l = 0; l < page_rank_value.length; l++)
			{
				for(int m = 0; m < page_rank_value[0].length; m++)
				{
					if(page_rank_value[0].length > 10)
				{
		System.out.printf(" pgrk[%d] = %.7f\n ",  m, Math.round(page_rank_value[l][m]*10000000.0)/10000000.0);
			}
			else
			{
				System.out.printf(" pgrk[%d] = %.7f",  m, Math.round(page_rank_value[l][m]*10000000.0)/10000000.0);
			}
					}
					
					System.out.print(" ");
				}
			
				System.out.println();
			}
			for(int k = 0; k < page_rank_value[0].length; k++)
			{
				
				double value = (d*compute_sub_pgrk(k));
				page_rank_value[0][k] = (double) ( (1-d)/page_rank_value[0].length ) +  value;
				//System.out.println("1st valueeeeeeeeeeeee " +(double) ( (1-d)/page_rank_value[0].length ));
				//System.out.println("second valueeeeeeeeeeeee " + value);
				//System.out.println("page_rank valueeeeeeeeeeeee" +"["+ 0 + "]" + "["+ k + "]" + page_rank_value[0][k]);
				//System.out.println("k valueeeeee " + k);
				//System.out.println("I valueeeeeeeeeeee " + i);
				
			}
		
		
		if(compute_difference)
		{
		check = check_error_rate(page_rank_value, prev_pgrk_value, iteration_value );
	
	
		}
		
		if(i >= 0 && check)
		{
			System.out.printf("Iter : %d :", h);
		
		
		for(int l = 0; l < page_rank_value.length; l++)
		{
			for(int m = 0; m < page_rank_value[0].length; m++)
			{
				if(page_rank_value[0].length>10)
				{
	System.out.printf(" pgrk[%d] = %.7f\n ",  m, Math.round(page_rank_value[l][m]*10000000.0)/10000000.0);
				}
				else
				{
					System.out.printf(" pgrk[%d] = %.7f ",  m, Math.round(page_rank_value[l][m]*10000000.0)/10000000.0);
				}
				}
				
				System.out.print(" ");
			}
			System.out.println();
			
		}
			}
		
		}
		
		
		
	}
	
	
	public static   boolean check_error_rate(double[][] pgrk_value, double[][] prev_pgrk_value,  int iter_para_value)
	{
		double error_rate = 0;
		int a = 0;
		boolean return_checker_value = true;
		
		if(iter_para_value == 0)
		{
			error_rate = Math.pow(10, -5);
		}
		else if(iter_para_value == -1)
		{
			error_rate = Math.pow(10, -1);
		}
		else if(iter_para_value == -2)
		{
			error_rate = Math.pow(10, -2);
		}
		else if(iter_para_value == -3)
		{
			error_rate = Math.pow(10, -3);
		}
		else if(iter_para_value == -4)
		{
			error_rate = Math.pow(10, -4);
		}
		else if(iter_para_value == -5)
		{
			error_rate = Math.pow(10, -5);
		}
		else if(iter_para_value == -6)
		{
			error_rate = Math.pow(10, -6);
		}
		
		for(int i = 0; i < pgrk_value.length; i++)
		{
			for(int j = 0; j < pgrk_value[0].length; j++)
			{
				if(Math.abs(pgrk_value[i][j] - prev_pgrk_value[i][j]) < error_rate)
				{
					a++;
				}
			}
		}
		
		
		if(a == pgrk_value[0].length)
		{
			return_checker_value = false;
		}
		
		return return_checker_value;
	}
	
		
		
	
	



public static void main(String[] args)
{
	
	matrix_vertices = vertices_array(args[2]);
	double dup_pgrk[][] = new double[1][matrix_vertices.length];
	double dup_hub_value[][] = new double[matrix_vertices.length][matrix_vertices.length];
	int dup_no_of_pointing_value[][] = new int[1][matrix_vertices.length];
	initial_value = Integer.parseInt(args[1]);
	iteration_value = Integer.parseInt(args[0]);
	if(initial_value < -2 || initial_value > 1)
	{
		System.out.println(" initial value exceeds the limit");
		System.exit(0);
	}
	
	if(iteration_value < - 6 )
	{
		System.out.println("iteration value exceeds the limit");
		System.exit(0);
	}
	for(int i = 0; i < matrix_vertices.length; i++)
	{
		dup_no_of_pointing_value[0][i] = 0;
		for(int j = 0; j < matrix_vertices[0].length; j++)
		{
			dup_no_of_pointing_value[0][i] = dup_no_of_pointing_value[0][i] + (int) matrix_vertices[i][j];
		}
	}
	
	System.out.println();
	no_of_pointing_value = dup_no_of_pointing_value;
	hub_value = transpose_matrix(matrix_vertices);

	 if(matrix_vertices.length > 10)
	    {
	    	iteration_value = 0;
	    	initial_value = -1;
	    }
	initiating_pgrk(dup_pgrk, matrix_vertices.length, initial_value);	
	iterate_pgrk(iteration_value);
}

}
