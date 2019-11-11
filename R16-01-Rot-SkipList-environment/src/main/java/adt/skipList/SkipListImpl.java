package adt.skipList;

import java.util.ArrayList;
import java.util.List;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}


	@Override
	public void insert(int key, T newValue, int height) {
		if(height > maxHeight){
			throw new RuntimeException();
		}
		SkipListNode<T>[] update = new SkipListNode[maxHeight];
		SkipListNode<T> aux = this.root;
		for (int i = maxHeight-1; i >= 0 ; i--) {
			while(aux.getForward(i).getKey() < key){
				aux = aux.getForward(i);
			}
			update[i] = aux;
		}
		aux = aux.getForward(0);
		if(aux.getKey() == key){
			aux.setValue(newValue);
		} else {
			aux = new SkipListNode<>(key, height, newValue);
			for (int i = 0; i < height; i++) {
				aux.getForward()[i] = update[i].getForward(i);
				update[i].getForward()[i] = aux;
			}
		}

	}

	@Override
	public void remove(int key) {
		SkipListNode<T>[] update = new SkipListNode[maxHeight];
		SkipListNode<T> aux = this.root;
		for (int i = maxHeight-1; i >= 0 ; i--) {
			while(aux.getForward(i).getKey() < key){
				aux = aux.getForward(i);
			}
			update[i] = aux;
		}
		aux = aux.getForward(0);
		if(aux.getKey() == key){
			for (int i = 0; i < maxHeight; i++) {
				if(update[i].getForward(i).equals(aux)){
					update[i].getForward()[i] = aux.getForward(i);
				}
			}
		}
	}

	@Override
	public int height() {
		return this.maxHeight;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> result = this.root;
		for (int i = maxHeight-1; i >= 0 ; i--) {
			while(result.getForward(i).getKey() < key){
				result = result.getForward(i);
			}
		}
		result = result.getForward(0);
		if(result.getKey() != key){
			result = null;
		}
		return result;
	}

	@Override
	public int size() {
		int size = 0;
		SkipListNode<T> aux = this.root;
		while (aux.getKey() < Integer.MAX_VALUE) {
			size++;
			aux = aux.getForward(0);
		}
		return (size-1);
	}

	@Override
	public SkipListNode<T>[] toArray() {
		List<SkipListNode<T>> list = new ArrayList<>();
		SkipListNode<T> aux = this.root;
		while(aux.getKey() < Integer.MAX_VALUE) {
			list.add(aux);
			aux = aux.getForward(0);
		}
		list.add(aux);
		SkipListNode<T>[] array = new SkipListNode[list.size()];
		return list.toArray(array);
	}

}