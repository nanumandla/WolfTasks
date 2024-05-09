package edu.ncsu.csc216.wolf_tasks.model.util;

/**
 * Keeps objects in a sorted order. Duplicate objects are allowed. And
 * implements the ISortedList interface. Changes made.
 * 
 * @param <E> type for SortedList
 * @author An Mai
 * @author Nirvan Reddy Anumandla
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {

	/** Size of of the list */
	private int size;
	/** The front node that will be used */
	private ListNode front;

	/**
	 * Constructor of a SortedList
	 */
	public SortedList() {
		front = null;
		size = 0;
	}

	/**
	 * Adds the elements in a sorted order
	 * @param element the object that will be added to the list
	 * @throws IllegalArgumentException if the element is a duplicate
	 * @throws NullPointerException if the element is null
	 */
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		if (contains(element)) {
			throw new IllegalArgumentException("Cannot add duplicate element.");
		}
		if (front == null || front.data.compareTo(element) > 0) {
			front = new ListNode(element, front);
		} else {
			ListNode current = front;
			while (current.next != null && current.next.data.compareTo(element) < 0) {
				current = current.next;
			}
			current.next = new ListNode(element, current.next);
		}
		size++;
	}

	/**
	 * Returns the element to be removed. Removes the object from the given index.
	 * 
	 * @param idx the index of the element to be removed
	 * @return the value that is removed
	 */
	@Override
	public E remove(int idx) {
		E value = null;
		checkIndex(idx);
		if (idx == 0) {
			value = front.data;
			front = front.next;
		} else {
			ListNode current = front;
			for (int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			value = current.next.data;
			current.next = current.next.next;
		}
		size--;
		return value;
	}

	/**
	 * Checks if the index is valid
	 * 
	 * @param idx to be checked
	 * @throws IndexOutOfBoundsException if the idx is less than 0 or greater than size - 1
	 */
	private void checkIndex(int idx) {
		if (idx < 0 || idx > size - 1) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}

	/**
	 * Checks if the list contains the element
	 * 
	 * @param element to check if the list contains it
	 * @return false if the element is in the list and true if it is there.
	 */
	@Override
	public boolean contains(E element) {
		if(front == null) {
			return false;
		}
		ListNode check = front;
		while (check != null) {
			if (check.data.equals(element)) {
				return true;
			}
			check = check.next;
		}
		return false;
	}

	/**
	 * Retrieves the element at the specific index
	 * 
	 * @param idx of the element
	 * @return the value at the index
	 */
	@Override
	public E get(int idx) {
		checkIndex(idx);
		ListNode current = front;
		for (int i = 0; i < idx; i++) {
			current = current.next;
		}
		return current.data;
	}

	/**
	 * Retrieves the size of the list
	 * @return the size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Class ListNode constructs a list of nodes that are connected to each other
	 */
	private class ListNode {

		/** The information contained in that node */
		public E data;
		/** The next node in the list */
		public ListNode next;

		/**
		 * ListNode constructor
		 * 
		 * @param e The element of a ListNode
		 * @param n the next ListNode it points to
		 */
		public ListNode(E e, ListNode n) {
			this.data = e;
			this.next = n;
		}
	}
}
