package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

   public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
      super(size);
      hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
      this.initiateInternalTable(size);
   }

   private int getHash(T element, int probe) {
      return Math.abs(((HashFunctionQuadraticProbing<T>) super.hashFunction).hash(element, probe));
   }

   @Override
   public void insert(T element) {
      if (element != null && this.search(element) == null) {
         if (isFull()) {
            throw new HashtableOverflowException();
         } else {
            int i = 0;
            boolean flag = true;
            while (i < this.table.length && flag) {
               int j = getHash(element, i++);
               if (this.table[j] == null || this.table[j].equals(deletedElement)) {
                  this.table[j] = element;
                  this.elements++;
                  flag = false;
               } else {
                  this.COLLISIONS++;
               }
            }
         }
      }
   }

   @Override
   public void remove(T element) {
      if (element != null && !isEmpty() && this.search(element) != null) {
         int i = 0;
         boolean flag = true;
         while (i < this.table.length && flag) {
            int j = getHash(element, i++);
            if (this.table[j] == null) {
               flag = false;
            } else if (this.table[j].equals(element)) {
               this.table[j] = deletedElement;
               this.elements--;
               flag = false;
            } else {
               this.COLLISIONS--;
            }
         }
      }
   }

   @Override
   public T search(T element) {
      T result = null;
      if (element != null && !isEmpty()) {
         int i = 0;
         boolean flag = true;
         while (i < this.table.length && flag) {
            int j = getHash(element, i++);
            if (this.table[j] == null) {
               flag = false;
            } else if (this.table[j].equals(element)) {
               result = (T) this.table[j];
               flag = false;
            }
         }
      }
      return result;
   }

   @Override
   public int indexOf(T element) {
      int index = -1;
      if (element != null && !isEmpty()) {
         int i = 0;
         boolean flag = true;
         while (i < this.table.length && flag) {
            int j = getHash(element, i++);
            if (this.table[j] == null) {
               flag = false;
            } else if (this.table[j].equals(element)) {
               index = j;
               flag = false;
            }
         }
      }
      return index;
   }
}
