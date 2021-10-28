package stru.stacks;
import exceptions.EmptyCollectionException;
import interfaces.DoubleStackADT;

/**
 * @author Iker Pintado
 *
 *         Represents an array implementation of a double stack.
 */

public class GenericDoubleArrayStack<T> implements DoubleStackADT<T> {
	/**
	 * constant to represent the default capacity of the array
	 */
	private final int DEFAULT_CAPACITY = 100;

	/**
	 * int that represents both the number of elements and the next available
	 * position in the array
	 */
	private int topF;
	/**
	 * Int that represents the next available position in the array
	 */
	private int topS;

	/**
	 * array of generic elements to represent the stack
	 */
	private T[] doubleStack;

	/**
	 * Creates an empty stack using the default capacity. WARNING (see the course
	 * slides): Generic array creation is disallowed in Java So here is the type
	 * cast. Forget the warning compiler error.
	 */
	@SuppressWarnings("unchecked")
	public GenericDoubleArrayStack() {
		topF = 0;
		topS = DEFAULT_CAPACITY - 1;
		doubleStack = (T[]) (new Object[DEFAULT_CAPACITY]);
	}

	/**
	 * Creates an empty stack using the specified capacity.
	 * 
	 * @param initialCapacity represents the specified capacity
	 */
	@SuppressWarnings("unchecked")
	public GenericDoubleArrayStack(int initialCapacity) {
		topF = 0;
		topS = initialCapacity - 1;
		doubleStack = (T[]) (new Object[initialCapacity]);
	}

	/**
	 * Adds the specified element to the top of the first stack, expanding the
	 * capacity of the stack array if necessary.
	 * 
	 * @param element generic element to be pushed onto stack
	 */
	@Override
	public void pushFirst(T element) {
		if (isFull())
			expandCapacity();

		doubleStack[topF] = element;
		topF++;
	}

	/**
	 * Adds the specified element to the top of the second stack, expanding the
	 * capacity of the stack array if necessary.
	 * 
	 * @param element generic element to be pushed onto stack
	 */
	@Override
	public void pushSecond(T element) {
		if (isFull())
			expandCapacity();

		doubleStack[topS] = element;
		topS--;
	}

	/**
	 * If the array is full returns true
	 * 
	 * @return if the both stacks are full
	 */
	public boolean isFull() {
		return topF == topS + 1;
	}

	/**
	 * Removes the element at the top of the first stack and returns a reference to
	 * it. Throws an EmptyCollectionException if the stack is empty.
	 * 
	 * @return T element removed from top of stack
	 * @throws EmptyCollectionException if a pop is attempted on empty stack
	 */
	@Override
	public T popFirst() throws EmptyCollectionException {
		if (isEmptyFirst())
			throw new EmptyCollectionException("DoubleStack");

		topF--;
		T result = doubleStack[topF];
		doubleStack[topF] = null;

		return result;
	}

	/**
	 * Removes the element at the top of the second and returns a reference to it.
	 * Throws an EmptyCollectionException if the stack is empty.
	 * 
	 * @return T element removed from top of stack
	 * @throws EmptyCollectionException if a pop is attempted on empty stack
	 */
	@Override
	public T popSecond() throws EmptyCollectionException {
		if (isEmptySecond())
			throw new EmptyCollectionException("DoubleStack");

		topS++;
		T result = doubleStack[topS];
		doubleStack[topS] = null;

		return result;
	}

	/**
	 * Returns a reference to the element at the top of the first stack. The element
	 * is not removed from the stack. Throws an EmptyCollectionException if the
	 * stack is empty.
	 * 
	 * @return T element on top of stack
	 * @throws EmptyCollectionException if a peek is attempted on empty stack
	 */
	@Override
	public T peekFirst() throws EmptyCollectionException {
		if (isEmptyFirst())
			throw new EmptyCollectionException("DoubleStack");

		return doubleStack[topF - 1];
	}

	/**
	 * Returns a reference to the element at the top of the second stack. The
	 * element is not removed from the stack. Throws an EmptyCollectionException if
	 * the stack is empty.
	 * 
	 * @return T element on top of stack
	 * @throws EmptyCollectionException if a peek is attempted on empty stack
	 */
	@Override
	public T peekSecond() throws EmptyCollectionException {
		if (isEmptySecond())
			throw new EmptyCollectionException("DoubleStack");

		return doubleStack[topS + 1];
	}

	/**
	 * Returns true if the first stack is empty and false otherwise.
	 * 
	 * @return boolean true if this stack is empty, false otherwise
	 */
	@Override
	public boolean isEmptyFirst() {
		return (topF == 0);
	}

	/**
	 * Returns true if the second is empty and false otherwise.
	 * 
	 * @return boolean true if this stack is empty, false otherwise
	 */
	@Override
	public boolean isEmptySecond() {
		return (topS == doubleStack.length - 1);
	}

	/**
	 * Returns the number of elements in the first stack.
	 * 
	 * @return int the number of elements in this stack
	 */
	@Override
	public int sizeFirst() {
		return topF;
	}

	/**
	 * Returns the number of elements in the second stack.
	 * 
	 * @return int the number of elements in this stack
	 */
	@Override
	public int sizeSecond() {
		return (doubleStack.length - 1) - topS;
	}

	/**
	 * Returns a string representation of the first stack.
	 * 
	 * @return String representation of this stack
	 */
	@Override
	public String toStringFirst() {
		String result = "";

		for (int scan = 0; scan < topF; scan++)
			result = result + doubleStack[scan].toString() + "\n";

		return result;
	}

	/**
	 * Returns a string representation of the second stack.
	 * 
	 * @return String representation of this stack
	 */
	@Override
	public String toStringSecond() {
		String result = "";

		for (int scan = doubleStack.length - 1; scan > topS; scan--)
			result = result + doubleStack[scan].toString() + "\n";

		return result;
	}

	/**
	 * Creates a new array to store the contents of these two stack with twice the
	 * capacity of the old ones.
	 */
	@SuppressWarnings("unchecked")
	private void expandCapacity() {
		T[] larger = (T[]) (new Object[doubleStack.length * 2]);

		for (int index = 0; index < topF; index++)
			larger[index] = doubleStack[index];
		for (int index = doubleStack.length - 1; index > topS; index--)
			larger[larger.length - doubleStack.length + index] = doubleStack[index];// so we fill it in order from the
																					// end
		doubleStack = larger;
	}
}