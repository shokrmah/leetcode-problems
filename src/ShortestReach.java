import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

import javax.management.Query;

public class ShortestReach {

	public static class GraphNode {

		public boolean startNode = false;
		public int nodeNumber = -1;
		public Set<Integer> connectedNodes;
		//public List<Integer> connectedNodes;
		public int shortest = -1;
		public boolean visited = false;
		
		GraphNode(int number) {
			nodeNumber = number;
			connectedNodes = new HashSet<Integer>();
			//connectedNodes = new ArrayList<Integer>();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<GraphNode[]> graphs = readData();

		for (int i = 0; i < graphs.size(); i++) {

			List<Integer> distances = calculateDistances(graphs.get(i));
			for (int j = 0; j < distances.size(); j++) {
				if(distances.get(j) != 0)
					System.out.print(distances.get(j) + " ");
			}
			System.out.println("");
		}

	}

	public static List<Integer> calculateDistances(GraphNode[] graph) {
		List<Integer> result = new ArrayList<Integer>();

		GraphNode startNode = null;
		for (int i = 0; i < graph.length; i++) {
			if (graph[i].startNode) {
				startNode = graph[i];
				break;
			}

		}
		startNode.shortest = 0;
		GraphNode currentNode = startNode;
		Queue<Integer> myQueue = new LinkedList<Integer>();
		myQueue.add(currentNode.nodeNumber);
		while (!myQueue.isEmpty()) {
			currentNode =graph[myQueue.poll() - 1];
			if(currentNode.visited)
				continue;
			currentNode.visited = true;
			for(Integer nodeNumber: currentNode.connectedNodes) {
				if(graph[nodeNumber - 1].shortest == -1 || currentNode.shortest + 6 < graph[nodeNumber - 1].shortest)
					graph[nodeNumber - 1].shortest = currentNode.shortest + 6;
				myQueue.add(nodeNumber);
			}
		}
		
		for (int i = 0; i < graph.length; i++) {
			result.add(graph[i].shortest);
		}

		return result;
	}

	public static List<GraphNode[]> readData() {
		Scanner scan = new Scanner(System.in);
		int allCases = Integer.parseInt(scan.nextLine());

		List<GraphNode[]> graphs = new ArrayList<GraphNode[]>();
		boolean newCase = true;
		while (allCases > 0) {
			String[] line = scan.nextLine().split(" ");
			if (line.length == 1) {
				// start node:
				graphs.get(graphs.size() - 1)[Integer.parseInt(line[0]) - 1].startNode = true;
				allCases--;
				newCase = true;

			} else if (newCase) {
				GraphNode[] myGraph = new GraphNode[Integer.parseInt(line[0])];
				for (int i = 0; i < myGraph.length; i++) {
					myGraph[i] = new GraphNode(i + 1);
				}
				newCase = false;
				graphs.add(myGraph);
			} else {
				int first = Integer.parseInt(line[0]);
				int second = Integer.parseInt(line[1]);
				graphs.get(graphs.size() - 1)[first - 1].connectedNodes.add(second);
				graphs.get(graphs.size() - 1)[second - 1].connectedNodes.add(first);
			}
		}

		scan.close();

		return graphs;
	}

}
