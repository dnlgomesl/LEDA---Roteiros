package adt.avltree;


import adt.bst.BSTNode;

import java.util.*;

import static adt.bt.Util.leftRotation;
import static adt.bt.Util.rightRotation;


public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		if(array != null) {
			Arrays.sort(array);
			HashMap<Integer, ArrayList<T>> map = new HashMap<>();
			auxmethod(array, map, 0, array.length-1, 0);
			int i = 0;
			while(!map.isEmpty()){
				for (int j = 0; j < map.get(i).size(); j++) {
					this.insert(map.get(i).get(j));
				}
				map.remove(i);
				i++;
			}
		}
	}

	private void auxmethod(T[] array, HashMap<Integer, ArrayList<T>> map, int left, int right, int level) {
		if(left <= right) {
			int mid = (left + right) / 2;

			if (map.containsKey(level)) {
				map.get(level).add(array[mid]);
			} else {
				ArrayList<T> arr = new ArrayList<>();
				arr.add(array[mid]);
				map.put(level, arr);
			}
			auxmethod(array, map, left, mid-1, level+1);
			auxmethod(array, map,mid+1, right, level+1);
		}
	}

	@Override
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
			this.RRcounter++;
		} else {
			rightRotation((BSTNode<T>) node.getRight());
			aux = leftRotation(node);
			this.RLcounter++;
		}
		if(aux.getParent() == null){
			this.root = aux;
		}
	}

	private void heavyLeft(BSTNode<T> node) {
		BSTNode<T> aux;
		if (calculateBalance((BSTNode<T>) node.getLeft()) >= 0) {
			aux = rightRotation(node);
			this.LLcounter++;
		} else {
			leftRotation((BSTNode<T>) node.getLeft());
			aux = rightRotation(node);
			this.LRcounter++;
		}
		if (aux.getParent() == null) {
			this.root = aux;
		}
	}

}
