public class Face {

	public int[][] values;
	public int color;

	public Face(int[][] values){
		
		this.values = values;
		color = values[values.length/2][values.length/2];
	}

	public void print(){
		
		for (int i=0; i<values.length; i++){
			for (int j=0; j<values[0].length; j++){
				System.out.print(values[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void rotate(){
	// CLOCKWISE
		int len = values.length-1; // index of the last element

		for (int i=0; i<=len/2; i++){ // for each layer of the cube
			for (int j=i; j<len-i; j++){ // for the size of the layer
				int temp = values[i][j];
				values[i][j] = values[len-j][i];
				values[len-j][i] = values[len-i][len-j];
				values[len-i][len-j] = values[j][len-i];
				values[j][len-i] = temp;
			}
		}
	}

	public void rotateCC(){
	// COUNTER CLOCKWISE
		this.rotate();
		this.rotate();
		this.rotate();
		/*  only keep this code if calling rotate 3 times is too slow	
		int len = values.length-1;
		
		for (int i=0; i<=len/2; i++){
			for (int j=i; j<len-i; j++){
				int temp = values[i][j];
				values[i][j] = values[j][len-i];
				values[j][len-i] = values[len-i][len-j];
				values[len-i][len-j] = values[len-j][i];
				values[len-j][i] = temp;
			}
		}
		*/
	}

	public int eval1(){
	// for the love of all things good and pure, please optimize
		int error = 0;
		for (int i=0; i<values.length; i++){
			for (int j=0; j<values.length; j++){
				if (values[i][j] != color){
					error += 1;
				}
			}
		}
		return error;
	}

	public static void main(String[] args) {

	}
}
