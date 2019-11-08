package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		T topResult = null;
		if(!isEmpty()){
			topResult = this.array[this.top];
		}
		return topResult;
	}

	@Override
	public boolean isEmpty() {
		return this.top == -1;
	}

	@Override
	public boolean isFull() {
		return this.top == this.array.length-1;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull()){
			throw new StackOverflowException();
		}
		if(element != null){
			this.array[++this.top] = element;
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty()){
			throw new StackUnderflowException();
		}
		return this.array[this.top--];
	}

	public T searchIndex(int i) throws StackOverflowException {
		T result = null;
		i--;
		if(i >= 0 && !isEmpty() && i <= this.top){
			StackImpl<T> aux = new StackImpl<>(top+1);
			for (int j = 0; j <= i; j++) {
				aux.push(this.array[i]);
			}
			result = aux.top();
		}
		return result;
	}

	public static void main(String[] args) throws StackOverflowException {
		StackImpl<String> s = new StackImpl<>(4);
		s.push("a");
		s.push("b");
		s.push("c");
		s.push("d");
		System.out.println(s.searchIndex(0));
	}

}
