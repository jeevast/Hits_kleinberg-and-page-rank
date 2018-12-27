/* Jeeva Surulibommu Thudikarabommu cs610 58974 prp */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class hits_58974 {
	
	static double matrix_vertices[][] = new double[0][0];
	static double hub_value[][] = new double[0][0];
	static double authority_value[][] = new double[0][0];
	static int initial_value = 0;
	static int iteration_value = 0;
	
	
	
	public static  double[][] vertices_array(String FileName) 
	{

		int vertices_size = 0;
		int edges_size = 0;
		String file_name = FileName;
		Scanner file = null;
		
		 
		try
		{
			 file = new Scanner(new File(file_name));//\\Users\\S.T.Jeeva\\Documen"C:ts\\project_text.txt"C:\\Users\\S.T.Jeeva\\Downloads\\large.txt
			 
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
	
	
	public static double[][] matrix_mul(double[][] arr1, double[][]arr2)
	{
		double mul_matrix[][] = new double[arr1.length][arr2[0].length];
		for(int i = 0; i < arr1.length; i++)
		{
			for(int j = 0; j < arr2[0].length; j++)
			{
				for(int k = 0; k < arr2.length; k++)
				{
					mul_matrix[i][j] = mul_matrix[i][j] + arr1[i][k]*arr2[k][j];
				}
			}
		}
		
				return mul_matrix;
	}
	
	
	
	public static void initiating_hub_auth(double dup1_hub_value[][], double dup1_auth_value[][], int no_vertices, int initial_value)
	{
		
		if(initial_value == 0)
		{
		for(int i = 0; i < 1; i++)
		{
			for(int j = 0; j < no_vertices; j++)
			{
				dup1_hub_value[i][j] = 0;
				dup1_auth_value[i][j] = 0;
			}
		}
		}
		else if(initial_value == 1)
		{
			for(int i = 0; i < 1; i++)
			{
				for(int j = 0; j < no_vertices; j++)
				{
					dup1_hub_value[i][j] = 1;
					dup1_auth_value[i][j] = 1;
				}
			}
		}
		 
		else if(initial_value == -1)
		{
			for(int i = 0; i < 1; i++)
			{
				for(int j = 0; j <  no_vertices; j++)
				{
					dup1_hub_value[i][j] = (double)1/ no_vertices;
					dup1_auth_value[i][j] = (double)1/ no_vertices;
					
				}
			}
		}
		
		else if(initial_value == -2)
		{

			for(int i = 0; i < 1; i++)
			{
				for(int j = 0; j < no_vertices; j++)
				{
					dup1_hub_value[i][j] = 1/Math.sqrt(no_vertices);
					dup1_auth_value[i][j] = 1/Math.sqrt(no_vertices);
				}
			}
		}
		
		
		hub_value = dup1_hub_value;
		authority_value = dup1_auth_value;		
	}
	
	
	public static void iterating_hits(int iteration_value)
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
		
		for(int i = 0; i < iteration ; i++)
		{
			if(check)
			{
				
				h = h+1;

		double[][] prev_auth_value = authority_value;
		double[][] prev_hub_value = hub_value;
		
		double sum_hub_value = 0;
		double sum_authority_value = 0;
		if(i==0)
		{
			System.out.printf("Base : %d :", i);
		
		for(int l = 0; l < hub_value.length; l++)
		{
			for(int m = 0; m < hub_value[0].length; m++)
			{
				if(hub_value[0].length > 10)
			{
	System.out.printf("A/H[%d] = %.7f/%.7f\n ",  m, Math.round(authority_value[l][m]*10000000.0)/10000000.0, Math.round(hub_value[l][m]*10000000.0)/10000000.0);
		}
		else
		{
			System.out.printf("A/H[%d] = %.7f/%.7f",  m, Math.round(authority_value[l][m]*10000000.0)/10000000.0, Math.round(hub_value[l][m]*10000000.0)/10000000.0);
		}
				}
				
				System.out.print(" ");
			}
		
			System.out.println();
		}
		authority_value = matrix_mul(hub_value, transpose_matrix(matrix_vertices) );
		hub_value = matrix_mul(authority_value, matrix_vertices);
		
		
		
		for(int j = 0; j < hub_value[0].length; j++)
		{
			sum_authority_value = (double)(sum_authority_value + (authority_value[0][j]*authority_value[0][j]));
			sum_hub_value = (double)sum_hub_value + (hub_value[0][j]*hub_value[0][j]);
		}
	
		
		
		double auth_scaling_denom = (double) Math.sqrt(sum_authority_value);
		double hub_scaling_denom = (double) Math.sqrt(sum_hub_value);

		if(auth_scaling_denom > 0 && hub_scaling_denom > 0)
		{
		for(int k = 0; k < hub_value[0].length; k++)
		{
		
			authority_value[0][k] = authority_value[0][k] / auth_scaling_denom;
		
			hub_value[0][k] = hub_value[0][k] / hub_scaling_denom;
		}
		}
		if(compute_difference)
		{
		check = check_error_rate(hub_value, authority_value, prev_hub_value, prev_auth_value, iteration_value );
		}
	
		
		if(i >= 0 && check)
		{
			System.out.printf("Iter : %d :", h);
		
		
		for(int l = 0; l < hub_value.length; l++)
		{
			for(int m = 0; m < hub_value[0].length; m++)
			{
				if(hub_value[0].length>10)
				{
	System.out.printf("A/H[%d] = %.7f/%.7f\n ",  m, Math.round(authority_value[l][m]*10000000.0)/10000000.0, Math.round(hub_value[l][m]*10000000.0)/10000000.0);
				}
				else
				{
					System.out.printf("A/H[%d] = %.7f/%.7f ",  m, Math.round(authority_value[l][m]*10000000.0)/10000000.0, Math.round(hub_value[l][m]*10000000.0)/10000000.0);
				}
				}
				
				System.out.print(" ");
			}
			System.out.println();
			
		}
			}
			
		}	
		}
	
	
	
	public static   boolean check_error_rate(double[][] hub_value, double[][] authority_value, double[][] prev_hub_value, double[][] prev_authority_value, int iter_para_value)
	{
		double error_rate = 0;
		int a = 0;
		int b = 0;
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
		
		for(int i = 0; i < hub_value.length; i++)
		{
			for(int j = 0; j < hub_value[0].length; j++)
			{
				if(Math.abs(hub_value[i][j] - prev_hub_value[i][j]) < error_rate)
				{
					a++;
				}
			}
		}
		for(int i = 0; i < authority_value.length; i++)
		{
			for(int j = 0; j < authority_value[0].length; j++)
			{
				if(Math.abs(authority_value[i][j] - prev_authority_value[i][j]) < Math.pow(10, -5))
				{
					b++;
				}
			}
		}
		
		if(a == hub_value[0].length || b == authority_value[0].length)
		{
			return_checker_value = false;
		}
		
		return return_checker_value;
	}
	
		
		
	



public static void main(String[] args) throws FileNotFoundException
{
	matrix_vertices = vertices_array(args[2]);
	double dup_hub_val[][] = new double[1][matrix_vertices.length];
	double dup_authority_val[][] = new double[1][matrix_vertices.length];
	initial_value = Integer.parseInt(args[1]);
	iteration_value = Integer.parseInt(args[0]);


	
			if(initial_value < -2 || initial_value > 1 )
	{
		System.out.println(" initial value exceeds the limit");
		System.exit(0);
	}
if(matrix_vertices.length > 10)
	    {
	    	iteration_value = 0;
	    	initial_value = -1;
	    }
	
	if(iteration_value < - 6 )
	{
		System.out.println("iteration value exceeds the limit");
		System.exit(0);
	}
	
	  
	initiating_hub_auth(dup_hub_val, dup_authority_val, matrix_vertices.length, initial_value);
	iterating_hits(iteration_value);	
	}
}