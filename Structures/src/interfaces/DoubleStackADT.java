package interfaces;


public interface DoubleStackADT<T>
{
  /**  Adds one element to the top of the first stack. 
   *   @param element element to be pushed onto stack
   */
  public void pushFirst (T element);
  
  /**  Removes and returns the top element from the first stack. 
   *   @return T element removed from the top of the stack
   */
  public T popFirst();

  /**  Returns without removing the top element of the first stack. 
   *   @return T element on top of the stack
   */
  public T peekFirst();
  
  /**  Returns true if the first stack contains no elements. 
   *   @return boolean whether or not this stack is empty
   */
  public boolean isEmptyFirst();

  /**  Returns the number of elements in the first stack. 
   *   @return int number of elements in the first stack
   */
  public int sizeFirst();

  /**  Returns a string representation of the first stack. 
   *   @return String representation of the first stack
   */
  public String toStringFirst();
  /**  Adds one element to the top of the second stack. 
   *   @param element element to be pushed onto stack
   */
  public void pushSecond (T element);
  
  /**  Removes and returns the top element from the second stack. 
   *   @return T element removed from the top of the stack
   */
  public T popSecond();

  /**  Returns without removing the top element of the second stack. 
   *   @return T element on top of the stack
   */
  public T peekSecond();
  
  /**  Returns true if the second stack contains no elements. 
   *   @return boolean whether or not the second stack is empty
   */
  public boolean isEmptySecond();

  /**  Returns the number of elements in the second stack. 
   *   @return int number of elements in the second stack
   */
  public int sizeSecond();

  /**  Returns a string representation of the second stack. 
   *   @return String representation of the second stack
   */
  public String toStringSecond();
}