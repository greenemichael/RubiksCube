import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BeamSearch{

	private int beamSize;
	
	public BeamSearch(int beamSize){

		this.beamSize = beamSize;
	}

	public void search(Node node){
	// use Node to represent a Tree node and FringeNode to represent a LinkedList node
		Queue<Node> toSearch = new LinkedList<>(); // this might be fast enough, examine later
		// add the starting point to the queue
		toSearch.add(node);
		LinkedList<Node> fringe = new LinkedList<>(); // mess around with this too
		while (true){
			try{ // if there is a node left in the queue, we are not done at this level of the tree yet
				//remove the first item in the queue				
				Node searching = toSearch.remove(); // throws an exception if the queue is empty
				//check for goal state
				if (searching.score == 0){
					System.out.println("what, we did it...?!");
					return;
				}
				else{
				// expand the node and add the new options to the fringe
					searching.expand();
					for (int i=0; i<searching.options.length; i++){
						fringe.add(searching.options[i]);
					}					
				}
			}catch(Exception e) { // finished with this level of the tree
				// remove the K best items from the fringe (leave leftovers in the fringe as a way to backtrack)
				Node[] best = extractBestNodes(fringe);				
				// add the K best nodes to the queue (add a new level of the tree to the queue to be processed)
				for (int i=0; i<beamSize; i++){
					toSearch.add(best[i]);
				}			
			}
		}
	}

	public Node[] extractBestNodes(LinkedList<Node> fringe){
	// this performs quick select to select the Kth largest element in O(n) and returns the partitioned list
		Node[] fringearr = fringe.toArray(new Node[fringe.size()]);
	// remove elements 0 - beamSize from partition and add to result
		return fringearr;
	// set fringe to the rest of partition
	}

	public static void main(String[] args){
		
	}
}
