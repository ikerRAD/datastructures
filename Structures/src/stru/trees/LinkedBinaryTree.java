package stru.trees;

/**
 * LinkedBinaryTree implements the BinaryTreeADT interface
 * 
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 8/19/08
 */

import java.util.Iterator;

import exceptions.ElementNotFoundException;
import exceptions.EmptyCollectionException;
import helpers.BinaryTreeNode;
import interfaces.BinaryTreeADT;
import stru.lists.LinkedList;

public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {
	protected int count;
	protected BinaryTreeNode<T> root;

	/**
	 * Creates an empty binary tree.
	 */
	public LinkedBinaryTree() {
		count = 0;
		root = null;
	}

	/**
	 * Creates a binary tree with the specified element as its root.
	 *
	 * @param element the element that will become the root of the new binary tree
	 */
	public LinkedBinaryTree(T element) {
		count = 1;
		root = new BinaryTreeNode<T>(element);
	}

	/**
	 * Returns a reference to the element at the root
	 *
	 * @return a reference to the specified target
	 * @throws EmptyCollectionException if the tree is empty
	 */
	@Override
	public T getRoot() throws EmptyCollectionException {
		if (root == null)
			throw new EmptyCollectionException("binary tree");

		return (root.getElement());
	}

	/**
	 * Returns true if this binary tree is empty and false otherwise.
	 *
	 * @return true if this binary tree is empty
	 */
	@Override
	public boolean isEmpty() {
		return (count == 0);
	}

	/**
	 * Returns the integer size of this tree.
	 *
	 * @return the integer size of this tree
	 */
	@Override
	public int size() {
		return count;
	}

	/**
	 * Returns true if this tree contains an element that matches the specified
	 * target element and false otherwise.
	 *
	 * @param targetElement the element being sought in this tree
	 * @return true if the element in is this tree
	 * @throws ElementNotFoundException if an element not found exception occurs
	 */
	@Override
	public boolean contains(T targetElement) {
		@SuppressWarnings("unused")
		T temp;
		boolean found = false;

		try {
			temp = find(targetElement);
			found = true;
		} catch (Exception ElementNotFoundException) {
			found = false;
		}

		return found;
	}

	/**
	 * Returns a reference to the specified target element if it is found in this
	 * binary tree. Throws a NoSuchElementException if the specified target element
	 * is not found in the binary tree.
	 *
	 * @param targetElement the element being sought in this tree
	 * @return a reference to the specified target
	 * @throws ElementNotFoundException if an element not found exception occurs
	 */
	@Override
	public T find(T targetElement) throws ElementNotFoundException {
		BinaryTreeNode<T> current = findAgain(targetElement, root);

		if (current == null)
			throw new ElementNotFoundException("binary tree");

		return (current.getElement());
	}

	/**
	 * Returns a reference to the specified target element if it is found in this
	 * binary tree.
	 *
	 * @param targetElement the element being sought in this tree
	 * @param next          the element to begin searching from
	 */
	private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {
		if (next == null)
			return null;

		if (next.getElement().equals(targetElement))
			return next;

		BinaryTreeNode<T> temp = findAgain(targetElement, next.getLeft());

		if (temp == null)
			temp = findAgain(targetElement, next.getRight());

		return temp;
	}

	/**
	 * Returns a string representation of this binary tree.
	 *
	 * @return a string representation of this binary tree
	 */
	@Override
	public String toString() {
		LinkedList<T> tempList = new LinkedList<T>();
		preorder(root, tempList);

		return tempList.toString();
	}

	/**
	 * Performs an inorder traversal on this binary tree by calling an overloaded,
	 * recursive inorder method that starts with the root.
	 *
	 * @return an in order iterator over this binary tree
	 */
	@Override
	public Iterator<T> iteratorInOrder() {
		LinkedList<T> tempList = new LinkedList<T>();
		inorder(root, tempList);

		return tempList.iterator();
	}

	/**
	 * Performs a recursive inorder traversal.
	 *
	 * @param node     the node to be used as the root for this traversal
	 * @param tempList the temporary list for use in this traversal
	 */
	protected void inorder(BinaryTreeNode<T> node, LinkedList<T> tempList) {
		if (node != null) {
			inorder(node.getLeft(), tempList);
			tempList.addToTail(node.getElement());
			inorder(node.getRight(), tempList);
		}
	}

	/**
	 * Performs an preorder traversal on this binary tree by calling an overloaded,
	 * recursive preorder method that starts with the root.
	 *
	 * @return an pre order iterator over this tree
	 */
	@Override
	public Iterator<T> iteratorPreOrder() {
		LinkedList<T> tempList = new LinkedList<T>();
		preorder(root, tempList);

		return tempList.iterator();
	}

	/**
	 * Performs a recursive preorder traversal.
	 *
	 * @param node     the node to be used as the root for this traversal
	 * @param tempList the temporary list for use in this traversal
	 */
	protected void preorder(BinaryTreeNode<T> node, LinkedList<T> tempList) {
		if (node != null) {
			tempList.addToTail(node.getElement());
			preorder(node.getLeft(), tempList);
			preorder(node.getRight(), tempList);
		}
	}

	/**
	 * Performs an postorder traversal on this binary tree by calling an overloaded,
	 * recursive postorder method that starts with the root.
	 *
	 * @return a post order iterator over this tree
	 */
	@Override
	public Iterator<T> iteratorPostOrder() {
		LinkedList<T> tempList = new LinkedList<T>();
		postorder(root, tempList);

		return tempList.iterator();
	}

	/**
	 * Performs a recursive postorder traversal.
	 *
	 * @param node     the node to be used as the root for this traversal
	 * @param tempList the temporary list for use in this traversal
	 */
	protected void postorder(BinaryTreeNode<T> node, LinkedList<T> tempList) {
		if (node != null) {
			postorder(node.getLeft(), tempList);
			postorder(node.getRight(), tempList);
			tempList.addToTail(node.getElement());
		}
	}

	/**
	 * Performs a levelorder traversal on this binary tree, using a templist.
	 *
	 * @return a levelorder iterator over this binary tree
	 */
	@Override
	public Iterator<T> iteratorLevelOrder() {
		LinkedList<BinaryTreeNode<T>> nodes = new LinkedList<BinaryTreeNode<T>>();
		LinkedList<T> tempList = new LinkedList<T>();
		BinaryTreeNode<T> current;

		nodes.addToTail(root);

		while (!nodes.isEmpty()) {
			current = (nodes.removeFirst());

			if (current != null) {
				tempList.addToTail(current.getElement());
				if (current.getLeft() != null)
					nodes.addToTail(current.getLeft());
				if (current.getRight() != null)
					nodes.addToTail(current.getRight());
			} else
				tempList.addToTail(null);
		}

		return tempList.iterator();
	}
}
