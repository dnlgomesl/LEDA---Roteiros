package adt.avltree;


import adt.bst.BSTImpl;
import adt.bst.BSTNode;

import static adt.bt.Util.leftRotation;
import static adt.bt.Util.rightRotation;

/**
 *
 * Performs consistency validations within a AVL Tree instance
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	public int calculateBalance(BSTNode<T> node) {
		int balance = 0;

		if (!node.isEmpty() && node != null) {
			balance = this.height((BSTNode<T>) node.getLeft()) - this.height((BSTNode<T>) node.getRight());
		}

		return balance;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (balance > 1) {
			this.heavyLeft(node);
		} else if (balance < -1) {
			this.heavyRight(node);
		}
	}

	private void heavyRight(BSTNode<T> node) {
		BSTNode<T> aux;
		if(calculateBalance((BSTNode<T>) node.getRight()) <= 0){
			aux = leftRotation(node);
		} else {
			rightRotation((BSTNode<T>) node.getRight());
			aux = leftRotation(node);
		}
		if(aux.getParent() == null){
			this.root = aux;
		}
	}

	private void heavyLeft(BSTNode<T> node) {
		BSTNode<T> aux;
		if(calculateBalance((BSTNode<T>) node.getLeft()) >= 0){
			aux = rightRotation(node);
		} else {
			leftRotation((BSTNode<T>) node.getLeft());
			aux = rightRotation(node);
		}
		if(aux.getParent() == null){
			this.root = aux;
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while (parent != null) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(this.root, element);
		}
	}

	private void insert(BSTNode<T> node, T element) {
		if(node.isEmpty()){
			node.setData(element);
			node.setLeft(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
			node.setRight(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
		}else{
			if (element.compareTo(node.getData()) < 0){
				insert((BSTNode<T>) node.getLeft(), element);
			} else if (element.compareTo(node.getData()) > 0){
				insert((BSTNode<T>) node.getRight(), element);
			}
			rebalance(node);
		}
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if(!node.isEmpty()){
			if(node.isLeaf()){
				node.setData(null);
				node.setRight(null);
				node.setLeft(null);
				rebalanceUp(node);
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
				rebalanceUp(node);
			} else {
				T sucessor = sucessor(node.getData()).getData();
				remove(sucessor);
				node.setData(sucessor);
			}
		}
	}
}
