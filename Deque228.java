kage cs228hw2;

import java.util.Collection;

import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * implementation of the deque interface
 * a data structure that is both a queue and a stack, also known as a double ended queue
 * uses the AmusingLinkedList class as the backing structure
 * @author Zach Eisele
 *
 * @param <E>
 */
public class Deque228<E> implements Deque<E>
{

	AmusingLinkedList<E> deque= new AmusingLinkedList<E>();
	
	/*
	 * default constructor
	 */
	public Deque228()
	{	
	}

	/*
	 * adds all data from the collection into the deque(non-Javadoc)
	 * @return if successful
	 * @param collection
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) 
	{
		return deque.addAll(c);
	}
	
	/*
	 * clears the deque(non-Javadoc)
	 * @see java.util.Collection#clear()
	 */
	@Override
	public void clear() 
	{
		deque.clear();
	}
	
	/*
	 * checks if all the data in the collection is contained in the deque(non-Javadoc)
	 * @return if successful
	 * @param collection
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> c) 
	{
		return deque.containsAll(c);
	}
	
	/*
	 * returns if the deque is empty(non-Javadoc)
	 * @return if empty
	 * @see java.util.Collection#isEmpty()
	 */
	@Override
	public boolean isEmpty() 
	{
		if(deque.isEmpty())
		{
			return true;
		}
		return false;
	}
	
	/*
	 * returns if all the items in the collection are removed(non-Javadoc)
	 * @return if successful
	 * @param collection
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> c) 
	{
		return deque.removeAll(c);
	}
	
	/*
	 * keeps only the data int the collection in the deque(non-Javadoc)
	 * @return if successful
	 * @param collection
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> c) 
	{
		return deque.retainAll(c);
	}
	
	/*
	 * returns an array of the deque(non-Javadoc)
	 * @return array of deque
	 * @see java.util.Collection#toArray()
	 */
	@Override
	public Object[] toArray() 
	{
		return deque.toArray();
	}
	
	/*
	 * returns the deque in the given array(non-Javadoc)
	 * @return given array of deque
	 * @param given array
	 * @see java.util.Collection#toArray(java.lang.Object[])
	 */
	@Override
	public <T> T[] toArray(T[] a) 
	{
		return deque.toArray(a);
	}
	
	/*
	 * adds a item into the deque at the end, throws an exception if it cannot be done(non-Javadoc)
	 * @return if successful
	 * @param item to be added
	 * @see java.util.Deque#add(java.lang.Object)
	 */
	@Override
	public boolean add(E item) 
	{
		return deque.add(item);
	}
	
	/*
	 * adds an item into the deque at the beginning, throws an exception if it cannot be done(non-Javadoc)
	 * @param item to be added
	 * @see java.util.Deque#addFirst(java.lang.Object)
	 */
	@Override
	public void addFirst(E item) 
	{
		deque.add(0, item);
	}
	
	/*
	 * adds an item at the end of the deque, throws an exception if it cannot be done(non-Javadoc)
	 * @param item to be added
	 * @see java.util.Deque#addLast(java.lang.Object)
	 */
	@Override
	public void addLast(E item) 
	{
		deque.add(size()-1, item);
	}
	
	/*
	 * returns if the deque contains the given object(non-Javadoc)
	 * @return if contains
	 * @param item
	 * @see java.util.Deque#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object item) 
	{
		return deque.contains(item);
	}
	
	/*
	 * returns an iterator that starts at the tail and moves towards the front in reverse order(non-Javadoc)
	 * @return descending iterator
	 * @see java.util.Deque#descendingIterator()
	 */
	@Override
	public Iterator<E> descendingIterator() 
	{
		return deque.listDescendingIterator();
	}
	
	/*
	 * retrieves but does not remove the head of the deque, throws an exception if the deque is empty(non-Javadoc)
	 * @return element at head of deque
	 * @see java.util.Deque#element()
	 */
	@Override
	public E element() 
	{
		if(deque.isEmpty())
		{
			throw new NoSuchElementException("The deque is empty");
		}
		return deque.get(0);
	}
	
	/*
	 * retrieves but does not remove the head of the deque, throws an exception if the deque is empty(non-Javadoc)
	 * @return item at head of deque
	 * @see java.util.Deque#getFirst()
	 */
	@Override
	public E getFirst() 
	{
		if(deque.isEmpty())
		{
			throw new NoSuchElementException("The deque is empty");
		}
		return deque.get(0);
	}
	
	/*
	 * returns the tail of the deque, throws an exception if the deque is empty(non-Javadoc)
	 * @return item at tail
	 * @see java.util.Deque#getLast()
	 */
	@Override
	public E getLast() 
	{
		if(deque.isEmpty())
		{
			throw new NoSuchElementException("The deque is empty");
		}
		return deque.get(deque.size()-1);
	}
	
	/*
	 * returns an iterator over the deque(non-Javadoc)
	 * @return iterator over deque
	 * @see java.util.Deque#iterator()
	 */
	@Override
	public Iterator<E> iterator() 
	{
		return deque.iterator();
	}
	
	/*
	 * adds the given item at the tail of the deque(non-Javadoc)
	 * @return if successful
	 * @param item to offer
	 * @see java.util.Deque#offer(java.lang.Object)
	 */
	@Override
	public boolean offer(E item) 
	{
		deque.add(item);
		return true;
	}
	
	/*
	 * adds the given item at the front of the deque(non-Javadoc)
	 * @return if successful
	 * @param item to offer
	 * @see java.util.Deque#offerFirst(java.lang.Object)
	 */
	@Override
	public boolean offerFirst(E item) 
	{
		deque.add(item);
		return true;
	}
	
	/*
	 * adds the given item at the tail of the deque(non-Javadoc)
	 * @return if successful
	 * @param item
	 * @see java.util.Deque#offerLast(java.lang.Object)
	 */
	@Override
	public boolean offerLast(E item) 
	{
		deque.add(item);
		return true;
	}
	
	/*
	 * returns but does not return the head of the deque, returns null if the deque is empty(non-Javadoc)
	 * @return head of deque
	 * @see java.util.Deque#peek()
	 */
	@Override
	public E peek() 
	{
		if(deque.isEmpty())
		{
			return null;
		}
		return deque.get(0);
	}
	
	/*
	 * returns but does not remove the head of the deque, returns null if the deque is empty(non-Javadoc)
	 * @return head of deque
	 * @see java.util.Deque#peekFirst()
	 */
	@Override
	public E peekFirst() 
	{
		if(deque.isEmpty())
		{
			return null;
		}
		return deque.get(0);
	}
	
	/*
	 * returns but does not remove the tail of the deque, returns null if the deque is empty(non-Javadoc)
	 * @return tail of deque
	 * @see java.util.Deque#peekLast()
	 */
	@Override
	public E peekLast() 
	{
		if(deque.isEmpty())
		{
			return null;
		}
		return deque.get(deque.size()-1);
	}
	
	/*
	 * returns and removes the head of the deque, returning null if the deque is empty(non-Javadoc)
	 * @return item removed
	 * @see java.util.Deque#poll()
	 */
	@Override
	public E poll() 
	{
		if(deque.isEmpty())
		{
			return null;
		}
		return deque.remove(0);
	}
	
	/*
	 * returns and removes the head of the deque, returns null if the deque is empty(non-Javadoc)
	 * @return item removed
	 * @see java.util.Deque#pollFirst()
	 */
	@Override
	public E pollFirst() 
	{
		if(deque.isEmpty())
		{
			return null;
		}
		return deque.remove(0);
	}
	
	/*
	 * returns and removes the tail of the deque, returns null if the deque is empty(non-Javadoc)
	 * @return item removed
	 * @see java.util.Deque#pollLast()
	 */
	@Override
	public E pollLast() 
	{
		if(deque.isEmpty())
		{
			return null;
		}
		return deque.remove(deque.size()-1);
	}
	
	/*
	 * returns and removes the head of the deque, throws an exception if the deque is empty(non-Javadoc)
	 * @return item removed
	 * @see java.util.Deque#pop()
	 */
	@Override
	public E pop() 
	{
		if(deque.isEmpty())
		{
			throw new NoSuchElementException("The deque is empty");
		}
		return deque.remove(0);
	}
	
	/*
	 * adds the item at the head of the deque(non-Javadoc)
	 * @param item being added
	 * @see java.util.Deque#push(java.lang.Object)
	 */
	@Override
	public void push(E item) 
	{
		deque.add(0, item);
	}
	
	/*
	 * returns and removes the head of the deque, gives an exception if the deque is empty(non-Javadoc)
	 * @return item removed
	 * @see java.util.Deque#remove()
	 */
	@Override
	public E remove() 
	{
		if(deque.isEmpty())
		{
			throw new NoSuchElementException("The deque is empty");
		}
		return deque.remove(0);
	}
	
	/*
	 * removes the first instance of the item from the deque, returns false if the item is not contained in the deque(non-Javadoc)
	 * @return if successful
	 * @param item being removed
	 * @see java.util.Deque#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object item) 
	{
		if(deque.contains(item))
		{
			deque.remove(item);
			return true;
		}
		return false;
	}
	
	/*
	 * returns and removes the head of this deque, throws an excepton if the deque is empty(non-Javadoc)
	 * @return item removed
	 * @see java.util.Deque#removeFirst()
	 */
	@Override
	public E removeFirst() 
	{
		if(deque.isEmpty())
		{
			throw new NoSuchElementException("The deque is empty");
		}
		return deque.remove(0);
	}
	
	/*
	 * removes the first occurrence of the item in the deque, returns false and the deque is unchanged if the item was not contained in the deque(non-Javadoc)
	 * @return if element was removed
	 * @see java.util.Deque#removeFirstOccurrence(java.lang.Object)
	 */
	@Override
	public boolean removeFirstOccurrence(Object item) 
	{
		if(deque.contains(item))
		{
			deque.remove(item);
			return true;
		}
		return false;
	}
	
	/*
	 * returns and removes the tail of the deque, throws an exception if the deque is empty(non-Javadoc)
	 * @return item removed
	 * @see java.util.Deque#removeLast()
	 */
	@Override
	public E removeLast() 
	{
		if(deque.isEmpty())
		{
			throw new NoSuchElementException("The deque is empty");
		}
		return deque.remove(size()-1);
	}
	
	/*
	 * removes the last occurrence of an item from the deque, returns false and the deque is unchanged if the item was not contained in the deque(non-Javadoc)
	 * @return if item was removed
	 * @param item
	 * @see java.util.Deque#removeLastOccurrence(java.lang.Object)
	 */
	@Override
	public boolean removeLastOccurrence(Object item) 
	{
		Object curData;
		int firstOcur=deque.indexOf(item);
		int idxLast=firstOcur;
		if(firstOcur>0)
		{
			for(int i=firstOcur+1; i<size(); i++)
			{
				curData=deque.get(i);
				if(curData.equals(item))
				{
					idxLast=i;
				}
			}
		}
		else
		{
			return false;
		}
		deque.remove(idxLast);
		return true;	
	}
	
	/*
	 * returns the size of the deque(non-Javadoc)
	 * @return size of deque
	 * @see java.util.Deque#size()
	 */
	@Override
	public int size() 
	{
		return deque.size();
	}
	
	
	
}