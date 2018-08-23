public class QuickSelect{

	private Node[] args;

	public QuickSelect(Node[] args){
		this.args = args;
	}

	private int partition(int left, int right){
		
		Node pivot = args[right];
		int trail = left;
		
		for (int i=0; i<right; i++){
			if (args[i].score <= pivot.score){
				Node temp = args[trail];
				args[trail] = args[i];
				args[i] = temp;
				trail++;				
			}
		}
		Node temp = args[trail];
		args[trail] = args[right];
		args[right] = temp;
		return trail;
	}

	public Node[] select(int k){
		
		if (k == 0) return args;

		int pivot = this.partition(0, args.length-1);
		//System.out.println(pivot);
		while (pivot < k){
			pivot += this.partition(pivot+1, args.length-1); // guaranteed to increase pivot
		}
		return args;
	}

	public void print(){
		for (int i=0; i<args.length; i++){
			System.out.println(args[i].score);		
		}
	}
	
	public static void main(String[] args){

		Node[] arse = new Node[15];
		for (int i=0; i<15; i++){
			Node n = new Node();
			n.score = (13-i);
			arse[i] = n;
		}
		QuickSelect qs = new QuickSelect(arse);
		//qs.print();
		qs.select(10);
		System.out.println("*******************");
		//qs.print();
	}
}
