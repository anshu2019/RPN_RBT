/**
 * This class is for stack
 * @author Anshu Anand
 *
 */
public class Stack {
	private Object[] arr = null;

	private int top = -1;
	private int size = 0;
	private int stackSize = 5;

	/**
	 * This constructor is for Stack
	 * @author Anshu Anand
	 */
	public Stack() {
		this.arr = new Object[stackSize];
	}

	/**
	 * This method pops item out of stack
	 * @Precondition: Stack exist
	 * @Postcondition: last item removed
	 * @return
	 */
	public Object pop() {
		if (this.size == 0) {
			return null;
		}

		this.size--;
		Object result = this.arr[top];
		
		this.arr[top] = null;// prevent memory leaking
		this.top--;

		return result;
	}

	/**
	 * @Precondition:Stack exists
	 * @Postcondition: object pushed in stack
	 * This method pusg items in stack
	 * @comment: Best case - In best case we wil push a item in stack implemented with array ,it will be
	 *                       Theeta(1)
	 *           Worst case -In worst case , stack will overflow and we have to resiz ethe array to push , 
	 *           if numbers of item in stack is N. Worst case is Theeta(N).
	 * @param e
	 * @return
	 */
	public boolean push(Object e) {
		if (isFull()) {
			Object[] temp = arr;
			this.top = -1;
			this.size = 0;
			this.stackSize = 2 * this.stackSize;
			this.arr = new Object[this.stackSize];

			for (Object o : temp) {
				this.size++;
				arr[++top] = o;
			}
			return true;
		} else {
			this.size++;
			arr[++top] = e;
			//System.out.println( " Pushed into stack: " + e);
			return true;
		}

	}

	/**
	 * @Precondition:Stack exists
	 * @Postcondition: status of stack
	 * This method checks if , stack is full
	 * @return
	 */
	public boolean isFull() {
		if (this.size == this.stackSize) {
			return true;
		} else {
			return false;
		}

	}
	
	/**
	 * This method checks if stack si empty or not
	 * @Precondition:Stack exists
	 * @Postcondition: returns emptiness of stack
	 * This method checks if stack is empty
	 * @return
	 */
	public boolean isEmpty() {
		return top == -1;

	}

	/**
	 * @Precondition:Stack exists
	 * @Postcondition: run subroutine and print the objects from stack
	 * This is main subroutine 
	 * @param args
	 */
	public static void main(String[] args) {

		Stack s = new Stack();
		for (int i = 0; i < 500; i++) {
			s.push(i);
			System.out.println( " Pushed into stack: " + i);
		}
		System.out.println( "-------------------------------------------------");
		for (int i = 0; i < 500; i++) {
			Object o = s.pop();
			if (o != null) {
				System.out.println( " Popping out of stack: " + o);
			}
		}
	}
}
