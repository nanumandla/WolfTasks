package edu.ncsu.csc216.wolf_tasks.model.util;

/**
 * Class that constructs a list that can be swapped through operations.
 * 
 * @param <E> type for SwapList\
 * @author An Mai
 * @author Nirvan Reddy Anumandla
 */
public class SwapList<E> implements ISwapList<E> {

	/** The initail capacity of the list */
	private static final int INITIAL_CAPACITY = 10;
	/** A list array of type E */
	private E[] list;
	/** Length of the list */
	private int size;

	/**
	 * Constructs the list to be swapped
	 */
	@SuppressWarnings("unchecked")
	public SwapList() {
		list = (E[]) new Object[INITIAL_CAPACITY];
		size = 0;
	}

	/**
	 * Adds the element to the end of the list
	 * 
	 * @param element to be added to the list
	 * @throws NullPointerException if the elemnt is null
	 */
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}

		checkCapacity(size());
		list[size()] = element;
		size++;

	}

	/**
	 * Checks the capacity of the list and increases it if it is necessary
	 * 
	 * @param size of the list
	 */
	@SuppressWarnings({ "unchecked" })
	private void checkCapacity(int size) {
		if (size == list.length - 1) {
			E[] list2 = (E[]) new Object[2 * list.length];
			for (int i = 0; i < size; i++) {
				list2[i] = list[i];
			}
			list = list2;
		}
	}

	/**
	 * Returns the element that was removed. Removes the element at the given index.
	 * 
	 * @param idx the index of the element to be removed
	 * @return the element removed
	 */
	@Override
	public E remove(int idx) {
		checkIndex(idx);
		E temp = list[idx];
		for (int i = idx; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		list[size - 1] = null;
		--size;
		return temp;
	}

	/**
	 * Checks that a valid index was given
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
	 * Moves the priority of the task up
	 * 
	 * @param idx how much to be moved
	 */
	@Override
	public void moveUp(int idx) {
		checkIndex(idx);		
		if(idx == 0) {
			return;
		}
		E temp = get(idx);
		list[idx] = get(idx - 1);
		list[idx - 1] = temp;
		
	}

	/**
	 * Moves the priority of the task down
	 * 
	 * @param idx how much to be moved
	 */
	@Override
	public void moveDown(int idx) {
		checkIndex(idx);
		if(idx == size() - 1) {
			return;
		}
		E temp = get(idx);
		list[idx] = get(idx + 1);
		list[idx + 1] = temp;
	}

	/**
	 * Moves the priority of the task to the front
	 * 
	 * @param idx how much to be moved
	 */
	@Override
	public void moveToFront(int idx) {
		checkIndex(idx);
		if(idx == 0) {
			return;
		}
		
		E temp = get(idx);
		for(int i = idx; i > 0; i--) {
			list[i] = list[i - 1];
		}
		list[0] = temp;
		
	}

	/**
	 * Moves the priority of the task to the back
	 * 
	 * @param idx how much to be moved
	 */
	@Override
	public void moveToBack(int idx) {
		checkIndex(idx);
		if(idx == size() - 1) {
			return;
		}
		E temp = get(idx);
		for(int i = idx; i < size() - 1; i++) {
			list[i] = list[i + 1];
		}
		list[size() - 1] = temp;
	}

	/**
	 * Retrieves the element at the specific index
	 * 
	 * @param idx index of the element
	 * @return the element at the index
	 */
	@Override
	public E get(int idx) {
		checkIndex(idx);
		return list[idx];
	}

	/**
	 * Returns the size of the list
	 * 
	 * @return list size
	 */
	@Override
	public int size() {
		return size;
	}

}
