package adt.linkedList;


public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {
	}

	@Override
	public void insert(T element) {
		if(element != null){
			if(isEmpty()){
				this.data = element;
				this.next = new RecursiveDoubleLinkedListImpl<>();
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this);
				if(this.previous == null){
					this.previous = new RecursiveDoubleLinkedListImpl<>();
				}
			}else {
				this.next.insert(element);
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if(element != null){
			if(isEmpty()){
				this.data = element;
				this.next = new RecursiveDoubleLinkedListImpl<>();
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this);
				if(this.previous == null){
					this.previous = new RecursiveDoubleLinkedListImpl<>();
				}
			}else {
				T cache =  this.data;
				this.data = element;
				((RecursiveDoubleLinkedListImpl<T>) this.next).setPrevious(this);
				((RecursiveDoubleLinkedListImpl<T>) this.next).insertFirst(cache);
			}
		}
	}

	@Override
	public void remove(T element) {
		if(!isEmpty() && element != null){
			if(this.data.equals(element)){
				if(this.previous.isEmpty() && this.next.isEmpty()){
					this.data = null;
					this.previous = null;
					this.next = null;
				}else {
					this.data = this.next.getData();
					this.next = this.next.getNext();
					if(this.next != null){
						((RecursiveDoubleLinkedListImpl<T>) this.next).setPrevious(this);
					}
				}
			}else {
				this.next.remove(element);
			}
		}
	}

	@Override
	public void removeFirst() {
		if(!isEmpty()){
			if(this.previous.isEmpty() && this.next.isEmpty()){
				this.data = null;
				this.previous = null;
				this.next = null;
			}else {
				this.data = this.next.getData();
				this.next = this.next.getNext();
				if(this.next != null){
					((RecursiveDoubleLinkedListImpl<T>) this.next).setPrevious(this);
				}
			}
		}
	}

	@Override
	public void removeLast() {
		if(!isEmpty()){
			if(this.next.isEmpty()){
				this.data = null;
				this.next = new RecursiveDoubleLinkedListImpl<T>();
			}else {
				((RecursiveDoubleLinkedListImpl<T>) this.next).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
