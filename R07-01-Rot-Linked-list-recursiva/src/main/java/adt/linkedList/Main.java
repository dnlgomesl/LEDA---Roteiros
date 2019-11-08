package adt.linkedList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        RecursiveDoubleLinkedListImpl<Integer> ll = new RecursiveDoubleLinkedListImpl<>();
        ll.insert(10);
        System.out.println(ll.getData()); //10
        ll.insertFirst(9);
        System.out.println(ll.getData()); //9
        System.out.println(ll.getNext().getData()); //10
        System.out.println(ll.getPrevious().getData()); //null
        ll.removeLast();
        System.out.println(ll.getData()); // 9
        System.out.println(ll.getNext().getData()); //null
        ll.insertFirst(8);
        System.out.println(ll.getData()); // 8
        ll.removeFirst();
        System.out.println(ll.getData()); // 9

        RecursiveDoubleLinkedListImpl<Integer> lll = new RecursiveDoubleLinkedListImpl<>();
        lll.insert(23);
        lll.removeLast();
        System.out.println(Arrays.toString(lll.toArray()));
    }
}
