package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(isFull()){
			throw new QueueOverflowException();
		}
		if(element != null){
			if(isEmpty()) {
				this.head++;
			}
			this.tail = (tail+1) % array.length;
			this.array[tail] = element;
			elements++;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()){
			throw new QueueUnderflowException();
		}
		T result = array[head];
		this.head = (this.head + 1) % array.length;
		this.elements--;
		return result;
	}

	@Override
	public T head() {
		T result = null;
		if(!isEmpty()){
			result = this.array[head];
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return (this.elements == 0);
	}

	@Override
	public boolean isFull() {
		return (this.elements == this.array.length);
	}

}
