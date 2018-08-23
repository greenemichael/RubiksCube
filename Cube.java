public class Cube {
	
	public Face front;
	public Face back;
	public Face right;
	public Face left;
	public Face top;	
	public Face bottom;
	private int len; // this represents the index of the rightmost column

	public Cube (Face front, Face top, Face bottom, Face right, Face back, Face left) {
		
		this.front = front;
		this.top = top;
		this.bottom = bottom;
		this.right = right;
		this.back = back;
		this.left = left;
		len = front.values.length-1;	
	}

	public void print(){
		
		System.out.println("Front");
		front.print();
		System.out.println("Top");
		top.print();
		System.out.println("Bottom");
		bottom.print();
		System.out.println("Right");
		right.print();
		System.out.println("Back");
		back.print();
		System.out.println("Left");
		left.print();
	}

	public void top(){

		top.rotate(); // clockwise		

		int[] temp = front.values[0];
		front.values[0] = right.values[0];
		right.values[0] = back.values[0];
		back.values[0] = left.values[0];
		left.values[0] = temp;		
	}

	public void topInverted(){

		for (int i=0; i<3; i++) this.top();
	}

	public void bottom(){
		
		bottom.rotateCC(); // this is counterintuitive but works

		int[] temp = front.values[len];
		front.values[len] = right.values[len];
		right.values[len] = back.values[len];
		back.values[len] = left.values[len];
		left.values[len] = temp;
	}

	public void bottomInverted(){
		
		for (int i=0; i<3; i++) this.bottom();
	}

	public void right(){

		right.rotate();
		
		//get a temp array of values at front		
		int[] temp = new int[len+1];
		for (int i=0; i<=len; i++){
			temp[i] = front.values[i][len];
		}

		// the next four loops represent one hoisted loop, save cache
		for (int i=0; i<=len; i++){
			front.values[i][len] = bottom.values[i][len];
		}
		for (int i=0; i<=len; i++){
			bottom.values[i][len] = back.values[len-i][0];
		}
		for (int i=0; i<=len; i++){
			back.values[len-i][0] = top.values[i][len];
		}
		for (int i=0; i<=len; i++){
			top.values[i][len] = temp[i];
		}
	}

	public void rightInverted(){
		
		for (int i=0; i<3; i++) this.right();
	}

	public void left(){

		left.rotate();

		int[] temp = new int[len+1];
		for (int i=0; i<=len; i++){
			temp[i] = front.values[i][0]; // leftmost column
		}

		// hoisting to save cache		
		for (int i=0; i<=len; i++){
			front.values[i][0] = top.values[i][0];
		}
		for (int i=0; i<=len; i++){
			top.values[i][0] = back.values[len-i][len];
		}
		for (int i=0; i<=len; i++){
			back.values[len-i][len] = bottom.values[i][0];
		}
		for (int i=0; i<=len; i++){
			bottom.values[i][0] = temp[i];
		}
	}

	public void leftInverted(){

		for (int i=0; i<3; i++) this.left();	
	}

	public void front(){

		front.rotate(); // clockwise

		int[] temp = new int[len+1];
		for (int i=0; i<=len; i++){
			temp[i] = top.values[len][i];
		}

		// hoisting		
		for (int i=0; i<=len; i++){
			top.values[len][i] = left.values[len-i][len];
		}
		for (int i=0; i<=len; i++){
			left.values[len-i][len] = bottom.values[0][i];
		}
		for (int i=0; i<=len; i++){
			bottom.values[0][i] = right.values[i][0];
		}
		for (int i=0; i<=len; i++){
			right.values[i][0] = temp[i];
		}		
	}

	public void frontInverted(){
		for (int i=0; i<3; i++) this.front();
	}

	public int eval1(){
		return front.eval1() + top.eval1() + bottom.eval1() + right.eval1() + back.eval1() + left.eval1();
	}

	public static void main(String[] args){
		
		// Initialization is specific because orientation must be agreed upon
		Cube cube = new Cube( // should score a 24 on eval1
			new Face (new int[][] {{5,5,5},{4,4,4},{5,5,5}}),
			new Face (new int[][] {{2,2,2},{0,0,0},{2,2,2}}),
			new Face (new int[][] {{0,0,0},{2,2,2},{0,0,0}}),
			new Face (new int[][] {{3,3,3},{3,3,3},{3,3,3}}),
			new Face (new int[][] {{4,4,4},{5,5,5},{4,4,4}}),
			new Face (new int[][] {{1,1,1},{1,1,1},{1,1,1}})
		);

		int[][] array4 = {{1,1,1,1},{1,1,1,1},{0,0,0,0},{0,0,0,0}};
		Face face4 = new Face(array4);
		int[][] array5 = {{1,2,3,4,5},{5,4,3,2,1},{1,2,3,4,5},{5,4,3,2,1},{1,2,3,4,9}};
		Face face5 = new Face(array5);

		cube.print();
		System.out.println(cube.eval1());
		for (int i=0; i<5; i++) System.out.println("*");		
		cube.front();
		cube.right();
		cube.bottom();
		cube.left();
		cube.bottomInverted();
		//cube.print();
	}
}
