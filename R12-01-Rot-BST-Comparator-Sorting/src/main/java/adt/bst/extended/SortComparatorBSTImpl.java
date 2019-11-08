package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

import java.util.Comparator;


/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(this.root, element);
		}
	}

	private void insert (BSTNode<T> node, T element) {
		if(node.isEmpty()){
			node.setData(element);
			node.setLeft(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
			node.setRight(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
		}else{
			if (this.comparator.compare(element, node.getData()) < 0){
				insert((BSTNode<T>) node.getLeft(), element);
			} else if (this.comparator.compare(element, node.getData()) > 0){
				insert((BSTNode<T>) node.getRight(), element);
			}
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(element, this.root);
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		BSTNode<T> result = new BSTNode<>();
		if(element != null && !node.isEmpty()){
			if(element.equals(node.getData())){
				result = node;
			} else if (this.comparator.compare(element, node.getData()) > 0){
				result = search(element, (BSTNode<T>) node.getRight());
			} else {
				result = search(element, (BSTNode<T>) node.getLeft());
			}
		}
		return result;
	}

	@Override
	public T[] sort(T[] array) {
		this.root = new BSTNode<>();
		if(array !=  null){
			for (int i = 0; i < array.length; i++) {
				this.insert(array[i]);
			}
		}
		return this.order();
	}

	@Override
	public T[] reverseOrder() {
		T[] result = (T[]) new Comparable[this.size()];
		reverseOrder(this.root, result, 0);
		return result;
	}

	private int reverseOrder(BSTNode<T> root, T[] result, int i) {
		if(root !=  null && !root.isEmpty()){
			i = reverseOrder((BSTNode<T>) root.getRight(), result, i);
			result[i++] = (root.getData());
			i = reverseOrder((BSTNode<T>) root.getLeft(), result, i);
		}
		return i;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
