package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		T headResult = null;
		if(!isEmpty()){
			headResult = this.array[0];
		}
		return headResult;
	}

	@Override
	public boolean isEmpty() {
		return this.tail == -1;
	}

	@Override
	public boolean isFull() {
		return this.tail == this.array.length-1;
	}

	private void shiftLeft() {
		for (int i = 0; i <= this.tail; i++) {
			array[i] = array[i+1];
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(isFull()){
			throw new QueueOverflowException();
		}
		if(element != null) {
			this.array[++tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()){
			throw new QueueUnderflowException();
		}
		T result = this.array[0];
		shiftLeft();
		tail--;
		return result;
	}

	public T encontra(int i) throws QueueOverflowException, QueueUnderflowException {
		T result = null;
		if (!isEmpty() && ((i-1) >= 0 && (i-1) <= (this.tail))){
			i = i-1;
			Queue<T> aux = new QueueImpl<>(this.tail+1);
			if(i == 0){
				aux.enqueue(this.array[0]);
				result = aux.head();
			} else{
				for (int j = 0; j < this.tail ; j++) {
					aux.enqueue(this.array[i]);
				}
				for (int j = 0; j < i; j++) {
					result = aux.dequeue();
				}
			}
		}
		return result;
	}

	public static void main(String[] args) throws QueueOverflowException, QueueUnderflowException {
		QueueImpl<String> q = new QueueImpl<>(4);
		q.enqueue("a");
		q.enqueue("b");
		q.enqueue("c");
		q.enqueue("d");
		System.out.println(q.encontra(0));
	}

}
