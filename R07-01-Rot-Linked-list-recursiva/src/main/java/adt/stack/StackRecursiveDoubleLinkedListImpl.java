package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(isFull()){
			throw new StackOverflowException();
		}else {
			this.top.insertFirst(element);
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		T result = null;
		if(isEmpty()){
			throw new StackUnderflowException();
		}else {
			result = ((RecursiveDoubleLinkedListImpl<T>) this.top).getData();
			this.top.removeFirst();
		}
		return result;

	}

	@Override
	public T top() {
		T result = null;
		if(!isEmpty()){
			result = ((RecursiveDoubleLinkedListImpl<T>) this.top).getData();
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		return (this.top.isEmpty());
	}

	@Override
	public boolean isFull() {
		return (this.top.size() == size);
	}

}
