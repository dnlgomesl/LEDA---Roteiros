package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

   @Override
   public void removeDuplicates() {
      SetLinkedListImpl<T> newSet = new SetLinkedListImpl<>();
      T[] arr = this.toArray();
      for (int i = 0; i < arr.length; i++) {
         if (newSet.search(arr[i]) == null) {
            newSet.insert(arr[i]);
         }
      }
      this.setHead(newSet.getHead());
   }

   @Override
   public SetLinkedList<T> union(SetLinkedList<T> otherSet) {
      SetLinkedListImpl<T> newSet = new SetLinkedListImpl<>();
      if (!isEmpty()) {
         T[] arr = this.toArray();
         for (int i = 0; i < arr.length; i++) {
            newSet.insert(arr[i]);
         }
      }
      if (otherSet != null && !otherSet.isEmpty()) {
         T[] arr2 = otherSet.toArray();
         for (int i = 0; i < arr2.length; i++) {
            newSet.insert(arr2[i]);
         }
      }
      newSet.removeDuplicates();
      return newSet;
   }

   @Override
   public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
      SetLinkedListImpl<T> newSet = new SetLinkedListImpl<>();
      if (!this.isEmpty() && otherSet != null && !otherSet.isEmpty()) {
         T[] arr = this.toArray();
         for (T element : arr) {
            if (otherSet.search(element) != null && newSet.search(element) == null) {
               newSet.insert(element);
            }
         }
      }
      return newSet;
   }

   @Override
   public void concatenate(SetLinkedList<T> otherSet) {
      if (!otherSet.isEmpty()) {
         SingleLinkedListNode<T> aux = this.getHead();
         while (!aux.isNIL()) {
            aux = aux.getNext();
         }
         aux.setData(((SingleLinkedListImpl<T>) otherSet).getHead().getData());
         aux.setNext(((SingleLinkedListImpl<T>) otherSet).getHead().getNext());
      }
      this.removeDuplicates();
   }

}
