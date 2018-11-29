package sxc180025;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import rbk.Graph;
import rbk.Graph.Vertex;

public class Diameter 
{
	public static final int INFINITY = Integer.MAX_VALUE;
    Vertex src;
	/**
	 * This method is used to randomly select a vertex.
	 * @param vertices - List of all the vertices.
	 * @return index - index of the vertex chosen randomly.
	 */
    public int assign_random_vertex(ArrayList<Vertex> vertices)
    {
    	Random randomvertex = new Random();
		int index = randomvertex.nextInt(vertices.size()-1);
		return index;
    }
    
    /**
     * This method is used to calculate the diameter of the tree.
     * @param g - graph 
     * @return diameter - Diameter of the tree.
     */
    public int find_diameter(Graph g)
    {
    	ArrayList<Vertex> vertices = new ArrayList<>();
		for(Vertex u: g)
		{
			vertices.add(u);
		}
		int index = assign_random_vertex(vertices);
		Vertex source = vertices.get(index);
		//System.out.println("Random vertex is "+src.getName());
		BFSOO result1 = BFSOO.breadthFirstSearch(g, source);
		g.printGraph(false);

		Vertex max = source;
		//Assigning max to the farthest node from the source.
		for(Vertex u: g) 
		{
		    if(result1.getDistance(max)<result1.getDistance(u) && result1.getDistance(u)!=INFINITY)
		    {
		    	max = u;
		    }    
		}
		
		//Running the BFS by making the farthest node from the previous source as the new source.
		BFSOO result2 = BFSOO.breadthFirstSearch(g, max);
		//Assigning max to the farthest node from the new source.
		for(Vertex u: g) 
		{
			if(result2.getDistance(max)<result2.getDistance(u) && result2.getDistance(u)!=INFINITY)
		    {
		    	max = u;
		    }    
		}
		//System.out.println("Diameter of the graph is "+result1.getDistance(max));
    	return result2.getDistance(max);
    }
	public static void main(String[] args) throws Exception 
	{
		String string = "10 9   1 2 2   1 3 3   2 4 5   2 5 4   3 6 1   3 7 1   4 8 1   7 9 1   7 10 1 1";
		Scanner in;
		// If there is a command line argument, use it as file from which
		// input is read, otherwise use input from string.
		in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(string);
		// Read graph from input
	    Graph g = Graph.readGraph(in);
		int s = in.nextInt();
		// Call breadth-first search
		BFSOO b = BFSOO.breadthFirstSearch(g, s);
		g.printGraph(false);
		System.out.println("Output of BFS:\nNode\tDist\tParent\n----------------------");
		for(Vertex u: g) 
		{
			if(b.getDistance(u) == INFINITY) 
			{
				System.out.println(u + "\tInf\t--");
			} 
			else 
			{
				System.out.println(u + "\t" + b.getDistance(u) + "\t" + b.getParent(u));
			}
		}
		//This calls the method which calculates the diameter.
		Diameter d = new Diameter();
		int Final_diameter= d.find_diameter(g);
		System.out.println("The final diameter of the tree is "+Final_diameter);
	}
}
