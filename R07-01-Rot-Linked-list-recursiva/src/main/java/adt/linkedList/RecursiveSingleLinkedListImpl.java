package adt.linkedList;

import java.util.ArrayList;
import java.util.List;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {
	}


	@Override
	public boolean isEmpty() {
		return (this.getData() == null);
	}

	@Override
	public int size() {
		int length = 0;
		if(!isEmpty()){
			length = 1 + this.next.size();
		}
		return length;
	}

	@Override
	public T search(T element) {
		T result = null;
		if(!isEmpty() && element != null){
			if(data.equals(element)){
				result = element;
			}
			else {
				result = this.next.search(element);
			}
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if(element != null){
			if (isEmpty()){
				this.data = element;
				this.next = new RecursiveSingleLinkedListImpl<>();
			}else {
				this.next.insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if(!isEmpty() && element != null){
			if(this.data.equals(element)){
				this.setData(this.next.getData());
				this.setNext(this.next.getNext());
			}else {
				this.next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		List<T> result = new ArrayList<>();
		toArray(result);
		return (T[]) result.toArray();
	}

	private void toArray(List<T> list){
		if(!isEmpty()){
			list.add(this.data);
			if(this.next != null){
				this.next.toArray(list);
			}
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
