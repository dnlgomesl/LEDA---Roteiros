package adt.rbtree;

import adt.bst.BSTImpl;
import adt.rbtree.RBNode.Colour;

import java.util.ArrayList;
import java.util.List;

import static adt.bt.Util.leftRotation;
import static adt.bt.Util.rightRotation;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
		implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		return (blackHeightAuxMethod((RBNode<T>) this.root));
	}

	private int blackHeightAuxMethod(RBNode<T> node) {
		int result = 0;
		if (node != null && !node.isEmpty()) {
			if (node.getColour().equals(Colour.BLACK)) {
				result = 1 + Math.max(blackHeightAuxMethod((RBNode<T>) node.getLeft()), blackHeightAuxMethod((RBNode<T>) node.getRight()));
			} else {
				result = Math.max(blackHeightAuxMethod((RBNode<T>) node.getLeft()), blackHeightAuxMethod((RBNode<T>) node.getRight()));
			}
		}
		return result;
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour()
				&& verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
		// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}
	//

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		return (verifyChildrenOfRedNodesAuxMethod((RBNode<T>) this.root));
	}

	private boolean verifyChildrenOfRedNodesAuxMethod(RBNode<T> node) {
		if (!node.isEmpty() && node != null) {
			if (node.getColour().equals(Colour.RED)) {
				if(((RBNode<T>) node.getLeft()).getColour().equals(Colour.RED) ||
						((RBNode<T>) node.getRight()).getColour().equals(Colour.RED)){
					return false;
				}
			}
			verifyChildrenOfRedNodesAuxMethod((RBNode<T>) node.getLeft());
			verifyChildrenOfRedNodesAuxMethod((RBNode<T>) node.getRight());
		}
		return true;
	}

	/**
	 * Verifies the black-height property from the root.
	 */
	private boolean verifyBlackHeight() {
		return (blackHeightAuxMethod((RBNode<T>) root.getLeft()) == blackHeightAuxMethod((RBNode<T>) root.getRight()));
	}

	@Override
	public void insert(T value) {
		if (value != null) {
			this.insertAuxMethod((RBNode<T>) this.root, value);
		}
	}

	private void insertAuxMethod(RBNode<T> node, T value) {
		if (node.isEmpty()) {
			node.setData(value);
			node.setColour(Colour.RED);
			node.setLeft(new RBNode<>());
			node.getLeft().setParent(node);
			node.setRight(new RBNode<>());
			node.getRight().setParent(node);
			this.fixUpCase1(node);
		} else {
			if (value.compareTo(node.getData()) < 0) {
				insertAuxMethod((RBNode<T>) node.getLeft(), value);
			} else if (value.compareTo(node.getData()) > 0) {
				insertAuxMethod((RBNode<T>) node.getRight(), value);
			}
		}
	}

	private void fixUpCase1(RBNode<T> node) {
		if (node.getParent() == null) {
			node.setColour(Colour.BLACK);
		} else {
			this.fixUpCase2(node);
		}
	}

	private void fixUpCase2(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		if (parent != null && !parent.getColour().equals(Colour.BLACK)) {
			fixUpCase3(node);
		}
	}

	private void fixUpCase3(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandFather = (RBNode<T>) parent.getParent();
		RBNode<T> uncle;
		if (grandFather != null && !grandFather.isEmpty()) {
			if (grandFather.getLeft().equals(parent)) {
				uncle = (RBNode<T>) grandFather.getRight();
			} else {
				uncle = (RBNode<T>) grandFather.getLeft();
			}
			if (uncle != null && uncle.getColour().equals(Colour.RED)) {
				parent.setColour(Colour.BLACK);
				uncle.setColour(Colour.BLACK);
				grandFather.setColour(Colour.RED);
				fixUpCase1(grandFather);
			} else {
				fixUpCase4(node);
			}
		}
	}

	private void fixUpCase4(RBNode<T> node) {
		RBNode<T> next = node;
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandFather = (RBNode<T>) parent.getParent();
		if (parent.getRight().equals(next) && grandFather.getLeft().equals(parent)) {
			leftRotation(parent);
			next = (RBNode<T>) next.getLeft();
		} else if (parent.getLeft().equals(next) && grandFather.getRight().equals(parent)) {
			rightRotation(parent);
			next = (RBNode<T>) next.getRight();
		}
		fixUpCase5(next);
	}

	private void fixUpCase5(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandFather = (RBNode<T>) parent.getParent();
		parent.setColour(Colour.BLACK);
		grandFather.setColour(Colour.RED);
		RBNode<T> aux;
		if (node.equals(parent.getLeft())) {
			aux = (RBNode<T>) rightRotation(grandFather);
		} else {
			aux = (RBNode<T>) leftRotation(grandFather);
		}
		if (aux.getParent() == null) {
			this.root = aux;
		}
	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		RBNode<T>[] arr = new RBNode[super.size()];
		List<RBNode<T>> list = new ArrayList<>();
		rbPreOrder((RBNode<T>) root, list);
		return list.toArray(arr);
	}

	private void rbPreOrder(RBNode<T> node, List<RBNode<T>> list) {
		if (node != null && !node.isEmpty()) {
			list.add(node);
			rbPreOrder((RBNode<T>) node.getLeft(), list);
			rbPreOrder((RBNode<T>) node.getRight(), list);
		}
	}

}
