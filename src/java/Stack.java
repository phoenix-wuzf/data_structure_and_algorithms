public class Stack<T> extends Vector<T>
{
	 // We could use Vector methods internally for the following methods,
	 // but have used Vector fields directly for efficiency (i.e. this
	 // often reduces out duplicate bounds checking).

	 /**
	  * Compatible with JDK 1.0+.
	  */
	 private static final long serialVersionUID = 1224463164541339165L;

	 /**
	  * This constructor creates a new Stack, initially empty
	  */
	 public Stack()
	 {
	 }

	 /**
	  * Pushes an Object onto the top of the stack.  This method is effectively
	  * the same as addElement(item).
	  *
	  * @param item the Object to push onto the stack
	  * @return the Object pushed onto the stack
	  * @see Vector#addElement(Object)
	  */
	 public T push(T item)
	 {
	   // When growing the Stack, use the Vector routines in case more
	   // memory is needed.
	   // No spec indicates that this method *always* returns obj passed in!

	   addElement(item);
	   return item;
	 }

	 /**
	  * Pops an item from the stack and returns it.  The item popped is
	 * removed from the Stack.
	 *
	 * @return the Object popped from the stack
	 * @throws EmptyStackException if the stack is empty
	 */
	public synchronized T pop()
	{
	  if (elementCount == 0)
		throw new EmptyStackException();

	  modCount++;
	  T obj = elementData[--elementCount];

	  // Set topmost element to null to assist the gc in cleanup.
	  elementData[elementCount] = null;
	  return obj;
	}

	/**
	 * Returns the top Object on the stack without removing it.
	 *
	 * @return the top Object on the stack
	 * @throws EmptyStackException if the stack is empty
	 */
	public synchronized T peek()
	{
	  if (elementCount == 0)
		throw new EmptyStackException();

	  return elementData[elementCount - 1];
	}

	/**
	 * Tests if the stack is empty.
	 *
	 * @return true if the stack contains no items, false otherwise
	 */
	public synchronized boolean empty()
	{
	  return elementCount == 0;
	}

	/**
	 * Returns the position of an Object on the stack, with the top
	 * most Object being at position 1, and each Object deeper in the
	 * stack at depth + 1.
	 *
	 * @param o The object to search for
	 * @return The 1 based depth of the Object, or -1 if the Object
	 *         is not on the stack
	 */
	public synchronized int search(Object o)
	{
	  int i = elementCount;
	  while (--i >= 0)
		if (equals(o, elementData[i]))
		  return elementCount - i;
	  return -1;
	}
}