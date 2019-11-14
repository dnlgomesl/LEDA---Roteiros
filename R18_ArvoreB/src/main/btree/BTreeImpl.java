package main.btree;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

    protected BNode<T> root;
    protected int order;

    public BTreeImpl(int order) {
        this.order = order;
        this.root = new BNode<T>(order);
    }

    @Override
    public BNode<T> getRoot() {
        return this.root;
    }

    @Override
    public boolean isEmpty() {
        return this.root.isEmpty();
    }

    @Override
    public int height() {
        return height(this.root);
    }

    private int height(BNode<T> node) {
        int height = -1;

        if (node != null && !node.isEmpty()) {
            if (node.isLeaf()) {
                height = 0;
            } else {
                height = 1 + this.height(node.getChildren().get(0));
            }
        }

        return height;
    }

    @Override
    public BNode<T>[] depthLeftOrder() {
        List<BNode<T>> alist = new ArrayList<>();
        depthLeftOrder(alist, this.root);
        return alist.toArray(new BNode[alist.size()]);
    }

    private void depthLeftOrder(List<BNode<T>> alist, BNode<T> node) {
        if(!node.isEmpty()) {
            alist.add(node);
            for (BNode<T> b : node.getChildren()) {
                depthLeftOrder(alist, b);
            }
        }
    }

    @Override
    public int size() {
        return this.size(this.root);
    }

    private int size(BNode<T> node) {
        int size = 0;

        if (!node.isEmpty()) {
            size += node.size();
            for (int i = 0; i < node.getChildren().size(); i++) {
                size += this.size(node.getChildren().get(i));
            }
        }

        return size;
    }

    @Override
    public BNodePosition<T> search(T element) {
        BNodePosition<T> result = new BNodePosition<>();
        if(element != null){
            result = seachAuxMethod(this.root, element);
        }
        return result;
    }

    private BNodePosition<T> seachAuxMethod(BNode<T> node, T element) {
        int i = 0;
        BNodePosition<T> result;
        while (i <= node.size() && element.compareTo(node.getElementAt(i)) > 0) {
            i++;
        }if(i < node.size() && element.equals(node.getElementAt(i))){
                result = new BNodePosition<>(node, i);
        } else if (node.isLeaf()){
                result = new BNodePosition<>();
        } else {
                result = seachAuxMethod(node.getChildren().get(i), element);
        }
        result = new BNodePosition<>();
        return result;
    }

    @Override
    public void insert(T element) {
        if (element != null) {
            this.insert(element, this.root);
        }
    }

    private void insert(T element, BNode<T> node) {
        if(node.isLeaf()) {
            LinkedList<T> list = new LinkedList<>();
            for (int i = 0; i < node.getElements().size(); i++) {
                if(element.compareTo(node.getElementAt(i)) < 0){
                    list.add(i, element);
                }
                list.add(element);
            }
            node.setElements(list);
            if(node.getElements().size() > order-1){
                split(node);
            }
            if(node.getParent() != null && node.getParent().getElements().size() > order-1){
                split(node.getParent());
            }
        } else {
            int j = -1;
            for (int i = 0; i < node.getElements().size(); i++) {
                if(element.compareTo(node.getElementAt(i)) < 0 && j == -1){
                    j++;
                }
            }
            insert(element, node.getChildren().get(j));
        }
    }

    private void split(BNode<T> node) {
    }

    private void promote(BNode<T> node) {

    }

    // NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
    @Override
    public BNode<T> maximum(BNode<T> node) {
        // NAO PRECISA IMPLEMENTAR
        throw new UnsupportedOperationException("Not Implemented yet!");
    }

    @Override
    public BNode<T> minimum(BNode<T> node) {
        // NAO PRECISA IMPLEMENTAR
        throw new UnsupportedOperationException("Not Implemented yet!");
    }

    @Override
    public void remove(T element) {
        // NAO PRECISA IMPLEMENTAR
        throw new UnsupportedOperationException("Not Implemented yet!");
    }

}
