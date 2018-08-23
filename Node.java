public class Node{
	public Cube current;
	public Node parent;
	public String lastMove;
	public int score;
	public Node[] options;

	public Node(){
		current = null;
		parent = null;
		lastMove = null;
		score = 0;
		options = null;
	}

	public Node(Cube current, Node parent, String lastMove){

		this.current = current;
		this.parent = parent;
		this.lastMove = lastMove;
		score = current.eval1(); // leave this here, eval will be configurable once multiple heuristics are thought of
		options = new Node[10];
		/**************************************************
			options[0] = top
			options[1] = topInverted
			options[2] = bottom
			options[3] = bottomInverted
			options[4] = right
			options[5] = rightInverted
			options[6] = left
			options[7] = leftInverted
			options[8] = front
			options[9] = frontInverted
		***************************************************/
	}

	public Node(Cube current){
	// makes it more intuitive to start search
		this.current = current;
		this.parent = null;
		this.lastMove = null;
		score = current.eval1();
		options = new Node[10];
	}

	public void expand(){
	// any 6 faced cube has these and only these ops (ignore back and backInverted, too unclear for the user)
	// if this is too slow: create peek() methods in the cube class
		Cube temp = current;
		current.top();
		options[0] = new Node(current, this, "top");
		current.topInverted(); // undo side effects (this is why peek methods would be faster)
		current.topInverted();
		options[1] = new Node(current, this, "top inverted");
		current.top();
		current.bottom();
		options[2] = new Node(current, this, "bottom");
		current.bottomInverted();
		current.bottomInverted();
		options[3] = new Node(current, this, "bottom inverted");
		current.bottom();
		current.right();
		options[4] = new Node(current, this, "right");
		current.rightInverted();
		current.rightInverted();
		options[5] = new Node(current, this, "right inverted");
		current.right();
		current.left();
		options[6] = new Node(current, this, "left");
		current.leftInverted();
		current.leftInverted();
		options[7] = new Node(current, this, "left inverted");
		current.left();
		current.front();
		options[8] = new Node(current, this, "front");
		current.frontInverted();
		current.frontInverted();
		options[9] = new Node(current, this, "front inverted");
		current.front();
	}

	public static void main(String[] args){
		Cube cube = new Cube( // should score a 24 on eval1
			new Face (new int[][] {{5,5,5},{4,4,4},{5,5,5}}),
			new Face (new int[][] {{2,2,2},{0,0,0},{2,2,2}}),
			new Face (new int[][] {{0,0,0},{2,2,2},{0,0,0}}),
			new Face (new int[][] {{3,3,3},{3,3,3},{3,3,3}}),
			new Face (new int[][] {{4,4,4},{5,5,5},{4,4,4}}),
			new Face (new int[][] {{1,1,1},{1,1,1},{1,1,1}})
		);

		Node n = new Node(cube);
		n.expand();
		Node m = n.options[6];
		m.expand();
		for (int i=0; i<m.options.length; i++){
			//n.options[i].current.print();
			System.out.println(m.options[i].lastMove + "- score: " + m.options[i].score);
			System.out.println(m.options[i].lastMove);
		}
	}
}
