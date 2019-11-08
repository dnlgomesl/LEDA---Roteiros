package adt.bst;

import java.util.Arrays;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height (BSTNode<T> node) {
		int result = -1;
		if(!node.isEmpty()) {
			int left = height((BSTNode<T>) node.getLeft());
			int rigth = height((BSTNode<T>) node.getRight());
			if(left >= rigth){
				result = 1 + left;
			} else {
				result =  1 + rigth;
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(element, this.root);
	}

	private BSTNode<T> search(T element, BSTNode<T> root) {
		BSTNode<T> result = new BSTNode<>();
		if(element != null && !root.isEmpty()){
			if(element.equals(root.getData())){
				result = root;
			} else if (element.compareTo(root.getData()) > 0){
				result = search(element, (BSTNode<T>) root.getRight());
			} else {
				result = search(element, (BSTNode<T>) root.getLeft());
			}
		}
		return result;
	}


	@Override
	public void insert(T element) {
		if(element != null){
			insert(element, this.root);
		}
	}

	private void insert(T element, BSTNode<T> node) {
		if(node.isEmpty()){
			node.setData(element);
			node.setLeft(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
			node.setRight(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
		}else{
			if (element.compareTo(node.getData()) < 0){
				insert(element, (BSTNode<T>) node.getLeft());
			} else if (element.compareTo(node.getData()) > 0){
				insert(element, (BSTNode<T>) node.getRight());
			}
		}
	}


	@Override
	public BSTNode<T> maximum() {
		return maximum(this.root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> result = null;
		if(!node.isEmpty()){
			while(!node.getRight().isEmpty()){
				node = (BSTNode<T>) node.getRight();
			}
			result = node;
		}
		return result;
	}


	@Override
	public BSTNode<T> minimum() {
		return minimum(this.root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> result = null;
		if(node != null){
			if(node.isEmpty()){
				result = (BSTNode<T>) node.getParent();
			} else {
				minimum((BSTNode<T>) node.getLeft());
			}
		}
		return result;
	}


	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = this.search(element);
		BSTNode<T> result = null;
		if(!node.isEmpty()){
			if(!node.getRight().isEmpty()){
				result = minimum((BSTNode<T>) node.getRight());
			} else {
				BSTNode<T> pai = (BSTNode<T>) node.getParent();
				while (pai != null && !pai.isEmpty() && node.equals(pai.getRight())){
					node = pai;
					pai = (BSTNode<T>) pai.getParent();
				}
				result = pai;
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = this.search(element);
		BSTNode<T> result = null;
		if(!node.isEmpty()){
			if(!node.getLeft().isEmpty()){
				result = maximum((BSTNode<T>) node.getLeft());
			} else {
				BSTNode<T> pai = (BSTNode<T>) node.getParent();
				while (pai != null && !pai.isEmpty() && node.equals(pai.getLeft())){
					node = pai;
					pai = (BSTNode<T>) pai.getParent();
				}
				result = pai;
			}
		}
		return result;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if(!node.isEmpty()){
			if(node.isLeaf()){
				node.setData(null);
				node.setRight(null);
				node.setLeft(null);
			} else if(node.getRight().isEmpty() || node.getLeft().isEmpty()) {
				BSTNode<T> pai = (BSTNode<T>) node.getParent();
				if(pai != null) { //se nao for root
					if(node.equals(pai.getLeft())){
						if (!node.getLeft().isEmpty()) {
							pai.setLeft(node.getLeft());
							node.getLeft().setParent(pai);
						} else {
							pai.setLeft(node.getRight());
							node.getRight().setParent(pai);
						}
					} else {
						if(!node.getLeft().isEmpty()){
							pai.setRight(node.getLeft());
							node.getLeft().setParent(pai);
						} else {
							pai.setRight(node.getRight());
							node.getRight().setParent(pai);
						}
					}
				} else{
					if (node.getLeft().isEmpty()) {
						this.root = (BSTNode<T>) node.getRight();
					} else {
						this.root = (BSTNode<T>) node.getLeft();
					}
					this.root.setParent(null);
				}
			} else {
				T sucessor = sucessor(node.getData()).getData();
				remove(sucessor);
				node.setData(sucessor);
			}
		}
	}

	@Override
	public T[] preOrder() {
		T[] result = (T[]) new Comparable[this.size()];
		preOrder(this.root, result, 0);
		return result;
	}

	private void preOrder(BSTNode<T> root, T[] result, int i) {
		if(root !=  null && !root.isEmpty()){
			result[i] = root.getData();
			preOrder((BSTNode<T>) root.getLeft(), result, i+1);
			preOrder((BSTNode<T>) root.getRight(), result, i + 1 + size((BSTNode<T>) root.getLeft()));
		}
	}

	@Override
	public T[] order() {
		T[] result = (T[]) new Comparable[this.size()];
		order(this.root, result, 0);
		return result;
	}

	private int order(BSTNode<T> root, T[] result, int i) {
		if(root !=  null && !root.isEmpty()){
			i = order((BSTNode<T>) root.getLeft(), result, i);
			result[i++] = (root.getData());
			i = order((BSTNode<T>) root.getRight(), result, i);
		}
		return i;
	}

	@Override
	public T[] postOrder() {
		T[] result = (T[]) new Comparable[this.size()];
		postOrder(this.root, result, 0);
		return result;
	}

	private int postOrder(BSTNode<T> root, T[] result, int i) {
		if(root !=  null && !root.isEmpty()){
			i = postOrder((BSTNode<T>) root.getLeft(), result, i);
			i = postOrder((BSTNode<T>) root.getRight(), result, i);
			result[i++] = (root.getData());
		}
		return i;
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

	public boolean isDecendent (T d, T p){
		BSTNode<T> nodeP = this.search(p);
		boolean result = false;
		if(d != null && !nodeP.isEmpty()){
			if(d.equals(nodeP.getData()) || d.equals(nodeP.getLeft().getData()) || d.equals(nodeP.getRight().getData())){
				result = true;
			} else {
				if(d.compareTo(nodeP.getData()) > 0){
					result = isDecendent(d, nodeP.getRight().getData());
				} else {
					result = isDecendent(d, nodeP.getLeft().getData());
				}
			}
		}
		return result;
	}

	public int distance(T x1, T x2){
		int dist = -1;
		if(isDecendent(x2, x1)){
			if(x1.equals(x2)){
				dist = 0;
			} else {
				BSTNode<T> aux = this.search(x1);
				if(x2.equals(aux.getLeft().getData()) || (x2.equals(aux.getRight().getData()))){
					dist = 1;
				} else {
					if(x2.compareTo(aux.getData()) > 0){
						dist = 1 + distance(aux.getRight().getData(), x2);
					} else {
						dist = 1 + distance(aux.getLeft().getData(), x2);
					}
				}
			}
		} else {
			throw new IllegalArgumentException();
		}
		return dist;
	}

	public static void main(String[] args) {
		BSTImpl<Integer> bst = new BSTImpl<>();
		bst.insert(10);
		bst.insert(15);
		bst.insert(5);
		bst.insert(8);
		bst.insert(3);
		bst.insert(12);
		bst.insert(17);

		System.out.println(bst.distance(5, 5));
	}
}
