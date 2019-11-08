package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

   protected DoubleLinkedListNode<T> last;

   public DoubleLinkedListImpl() {
      this.setHead(new DoubleLinkedListNode<>());
      this.setLast((DoubleLinkedListNode<T>) this.head);
   }

   @Override
   public void insert(T element) {
      if (element != null) {
         DoubleLinkedListNode<T> nil = new DoubleLinkedListNode<>();
         DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode(element, nil, this.last);
         this.last.setNext(newLast);
         if (this.last.isNIL()) {
            this.setHead(newLast);
         }
         this.setLast(newLast);
      }
   }

   @Override
   public void insertFirst(T element) {
      if (element != null) {
         DoubleLinkedListNode<T> nil = new DoubleLinkedListNode<>();
         DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode(element, (DoubleLinkedListNode<T>) this.head, nil);
         ((DoubleLinkedListNode<T>) this.head).setPrevious(newHead);
         if (this.getHead().isNIL()) {
            this.setLast(newHead);
         }
         this.setHead(newHead);
      }
   }

   @Override
   public void remove(T element) {
      if (element != null && !isEmpty()) {
         if (this.getHead().getData().equals(element)) {
            this.removeFirst();
         } else if (this.last.getData().equals(element)) {
            this.removeLast();
         } else {
            DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.head;
            while (!aux.isNIL() && !aux.data.equals(element)) {
               aux = (DoubleLinkedListNode<T>) aux.getNext();
            }
            if (!aux.isNIL()) {
               aux.getPrevious().setNext(aux.getNext());
               ((DoubleLinkedListNode<T>) aux.getNext()).setPrevious(aux.getPrevious());
            }
         }
      }
   }

   @Override
   public void removeFirst() {
      if (!this.head.isNIL()) {
         this.head = (this.head.getNext());
         if (this.head.isNIL()) {
            this.last = (DoubleLinkedListNode<T>) this.head;
         }
         ((DoubleLinkedListNode<T>) this.head).setPrevious(new DoubleLinkedListNode<>());
      }

   }

   @Override
   public void removeLast() {
      if (!this.last.isNIL()) {
         this.last = this.last.getPrevious();
         if (this.last.isNIL()) {
            this.head = this.last;
         }
         this.last.setNext(new DoubleLinkedListNode<T>());
      }
   }

   public DoubleLinkedListNode<T> getLast() {
      return this.last;
   }

   public void setLast(DoubleLinkedListNode<T> last) {
      this.last = last;
   }

}
