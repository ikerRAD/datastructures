package stru.stacks;
import exceptions.EmptyCollectionException;
import interfaces.StackADT;

/**
 * @author Lewis and Chase
 * 
 *         WITH CHANGES FOR THE SUBJECT DSA AT THE UPV/EHU
 *
 *         Represents an array implementation of a stack.
 */

public class GenericArrayStack<T> implements StackADT<T> {
	/**
	 * constant to represent the default capacity of the array
	 */
	private final int DEFAULT_CAPACITY = 100;

	/**
	 * int that represents both the number of elements and the next available
	 * position in the array
	 */
	private int top;

	/**
	 * array of generic elements to represent the stack
	 */
	private T[] stack;

	/**
	 * Creates an empty stack using the default capacity. WARNING (see the course
	 * slides): Generic array creation is disallowed in Java So here is the type
	 * cast. Forget the warning compiler error.
	 */
	@SuppressWarnings("unchecked")
	public GenericArrayStack() {
		top = 0;
		stack = (T[]) (new Object[DEFAULT_CAPACITY]);
	}

	/**
	 * Creates an empty stack using the specified capacity.
	 * 
	 * @param initialCapacity represents the specified capacity
	 */
	@SuppressWarnings("unchecked")
	public GenericArrayStack(int initialCapacity) {
		top = 0;
		stack = (T[]) (new Object[initialCapacity]);
	}

	/**
	 * Adds the specified element to the top of this stack, expanding the capacity
	 * of the stack array if necessary.
	 * 
	 * @param element generic element to be pushed onto stack
	 */
	@Override
	public void push(T element) {
		if (size() == stack.length)
			expandCapacity();

		stack[top] = element;
		top++;
	}

	/**
	 * Removes the element at the top of this stack and returns a reference to it.
	 * Throws an EmptyCollectionException if the stack is empty.
	 * 
	 * @return T element removed from top of stack
	 * @throws EmptyCollectionException if a pop is attempted on empty stack
	 */
	@Override
	public T pop() throws EmptyCollectionException {
		if (isEmpty())
			throw new EmptyCollectionException("Stack");

		top--;
		T result = stack[top];
		stack[top] = null;

		return result;
	}

	/**
	 * Returns a reference to the element at the top of this stack. The element is
	 * not removed from the stack. Throws an EmptyCollectionException if the stack
	 * is empty.
	 * 
	 * @return T element on top of stack
	 * @throws EmptyCollectionException if a peek is attempted on empty stack
	 */
	@Override
	public T peek() throws EmptyCollectionException {
		if (isEmpty())
			throw new EmptyCollectionException("Stack");

		return stack[top - 1];
	}

	/**
	 * Returns true if this stack is empty and false otherwise.
	 * 
	 * @return boolean true if this stack is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return (top == 0);
	}

	/**
	 * Returns the number of elements in this stack.
	 * 
	 * @return int the number of elements in this stack
	 */
	@Override
	public int size() {
		return top;
	}

	/**
	 * Returns a string representation of this stack.
	 * 
	 * @return String representation of this stack
	 */
	@Override
	public String toString() {
		String result = "";

		for (int scan = 0; scan < top; scan++)
			result = result + stack[scan].toString() + "\n";

		return result;
	}

	/**
	 * Creates a new array to store the contents of this stack with twice the
	 * capacity of the old one.
	 */
	@SuppressWarnings("unchecked")
	private void expandCapacity() {
		T[] larger = (T[]) (new Object[stack.length * 2]);

		for (int index = 0; index < stack.length; index++)
			larger[index] = stack[index];

		stack = larger;
	}
}