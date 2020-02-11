package cs228hw2;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * The implementation of a type of linked list. The list is circular and the .next references 
 * the Node in front of it, however .prev is null for odd indexes and referenes the last even
 * index Node for all even index Nodes(including 0)
 * @author Zach Eisele
 * @param <E>
 */
public class AmusingLinkedList<E> implements List<E>
{
	//first node of the list
	private Node head;
	
	//size of the linked list
	private int size;
	
	//last node of the list
	private Node tail;
	
	/*
	 * default constructor
	 */
	public AmusingLinkedList()
	{
	}
	
	/**
	 * Class that implements a Node, an object that holds data and references to other Nodes
	 * @author Zach Eisele
	 */
	public class Node
	{
		private Node prev;
		private Node next;
		private E data; 
		
		/*
		 * default constructor
		 */
		public Node()
		{
		}
		
		public E getData()
		{
			return data;
		}
		
		public Node getPrev()
		{
			return prev;
		}
		
		public Node getNext()
		{
			return next;
		}
		
	}
	
	/**
	 *  An iterator for the linked list
	 * @author Zach Eisele
	 *
	 */
	public class AmusingIterator implements Iterator<E>
	{
		// the cursor or current Node
		Node cur=null;
		
		//the current index
		int index=-1;
		
		/*
		 * constructor that starts the iterator at the beginning of the list
		 */
		public AmusingIterator()
		{
		}
		
		/*
		 * returns true if there is another item in the list, false if it is the end of the list(non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() 
		{
			if(index!=size()-1)
			{
				return true;
			}
			return false;
		}

		/*
		 * returns the next Node in the list or throws an exception if there is none (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public E next() 
		{
			if(index==size()-1)
			{
				throw new NoSuchElementException("The iteration is at the end");
			}
			if(index==-1)
			{
				cur=head;
				index++;
				return cur.data;
			}
			index++;
			cur = cur.next;
			return cur.data;
		}
		
	}
	
	/**
	 * implementation of an iterator that travels through the list backwards
	 * @author Zach Eisele
	 *
	 */
	public class AmusingListDescendingIterator implements ListIterator<E>
	{

		Node current; 
		
		//start index at the end
		int index=size();
		
		/*
		 * default constructor that starts at the end of the list
		 */
		public AmusingListDescendingIterator()
		{
			
		}
		
		
		@Override
		public void add(E arg0) 
		{
		}

		/*
		 * returns true if there are more elements in the list(non-Javadoc)
		 * @return if there is a next
		 * @see java.util.ListIterator#hasNext()
		 */
		@Override
		public boolean hasNext() 
		{
			if (index!=0)
			{
				return true;
			}
			return false;	
		}

		/*
		 * returns true if there are elements behind the iterator(non-Javadoc)
		 * @return if there is a previous
		 * @see java.util.ListIterator#hasPrevious()
		 */
		@Override
		public boolean hasPrevious() 
		{
			
			if (index!=size()-1);
			{
				return true;
			}
		}

		/*
		 * returns the next value in the list and moves the cursor(non-Javadoc)
		 * @returns the next data
		 * @see java.util.ListIterator#next()
		 */
		@Override
		public E next() 
		{
			/*
			 * if the index is even than to go forwards(backwards in the list), the cursor needs
			 * to go to the last even index and then go forward one
			 */
			
			if(index==-1)
			{
				current=tail;
			}
			if(index%2==0)
			{
				E data=current.prev.next.data;
				current=current.prev.next;
				index--;
				return data;
			}
			
			//if the index is odd, the cursor needs to go forward to the next even index than backwards to the last even index
			else 
			{
				E data=current.next.prev.data;
				current=current.next.prev;
				index--;
				return data;
			}
		}

		/*
		 * returns the next index in the list or -1 if there is none(non-Javadoc)
		 * @returns the next index
		 * @see java.util.ListIterator#nextIndex()
		 */
		@Override
		public int nextIndex() 
		{
			if(index==0)
			{
				return -1;
			}
			return index-1;
		}

		/*
		 * returns the previous value in the list(non-Javadoc)
		 * @return previous value
		 * @see java.util.ListIterator#previous()
		 */
		@Override
		public E previous() 
		{
			
			if(index==size()-1)
			{
				throw new NoSuchElementException("this is the end of the list");
			}
			current=current.next;
			index++;
			return current.getData();
			
		}

		/*
		 * returns the previous index or -1 if the beginning of the list has been reached(non-Javadoc)
		 * @return previous index
		 * @see java.util.ListIterator#previousIndex()
		 */
		@Override
		public int previousIndex() 
		{
			if(index==size()-1)
			{
				return 0;
			}
			return index+1;
			
		}

		@Override
		public void remove() 
		{
		}

		@Override
		public void set(E e) 
		{
		}
	}

	/**
	 * A list iterator for the given list
	 * @author Zach Eisele
	 *
	 */
	public class AmusingListIterator implements ListIterator<E>
	{

		Node current=null; 
		int index=-1;
		
		/*
		 * default constructor that starts at the beginning of the list
		 */
		public AmusingListIterator()
		{
		}
		
		@Override
		public void add(E arg0) 
		{
		}

		/*
		 * returns true if there is another node in the list and it is not the end of the list(non-Javadoc)
		 * @return if there is a next
		 * @see java.util.ListIterator#hasNext()
		 */
		@Override
		public boolean hasNext() 
		{
			if(index!=size()-1)
			{
				return true;
			}
			else
			{
				return false;
			}
		}

		/*
		 * returns true if there is a previous node and it is not the beginning of the list(non-Javadoc)
		 * @return if there is a previous
		 * @see java.util.ListIterator#hasPrevious()
		 */
		@Override
		public boolean hasPrevious() 
		{
			if (current.prev!=null)
			{
				return true;
			}
			return false;
		}

		/*
		 * returns the next data in the list and moves the cursor forward(non-Javadoc)
		 * @return next value
		 * @see java.util.ListIterator#next()
		 */
		@Override
		public E next() 
		{
			if(index==size()-1)
			{
				throw new NoSuchElementException("The iteration is at the end");
			}
			if(index==-1)
			{
				current=head;
				index++;
				return current.data;
			}
			current=current.next;
			index++;
			return current.data;
		}

		/*
		 * returns the next index in the list or -1 if it is the end of the list(non-Javadoc)
		 * @return next index
		 * @see java.util.ListIterator#nextIndex()
		 */
		@Override
		public int nextIndex() 
		{
			if(index==size()-1)
			{
				return 0;
			}
			return index+1;
		}

		/*
		 * returns the previous data in the list and moves the cursor backwards(non-Javadoc)
		 * @return previous value
		 * @see java.util.ListIterator#previous()
		 */
		@Override
		public E previous() 
		{
			if(index==0)
			{
				throw new NoSuchElementException("The iteration is at the beginning");
			}
			
			//to go backwards for even indexes the last even index needs to be reached then its next
			if(index%2==0)
			{
				E data=current.prev.next.data;
				current=current.prev.next;
				index--;
				return data;
			}
			
			//to go backwards for odd indexes need to go forward to an even index than back to the last even index
			else 
			{
				E data=current.next.prev.data;
				current=current.next.prev;
				index--;
				return data;
			}
			
		}

		/*
		 * returns the previous index or -1 if the beginning has been reached(non-Javadoc)
		 * @return previous index
		 * @see java.util.ListIterator#previousIndex()
		 */
		@Override
		public int previousIndex() 
		{
			if(index==0)
			{
				return -1;
			}
			return index-1;
		}

		@Override
		public void remove() 
		{
		}

		@Override
		public void set(E e) 
		{
		}
	}

	/*
	 * adds a node to the end of the list(non-Javadoc)
	 * @param item to be added
	 * @return if successful
	 * @see java.util.List#add(java.lang.Object)
	 */
	@Override
	public boolean add(E item) 
	{
		
		//sets up a new node
		Node n=new Node();
		n.data=item;
		n.next=null;
		
		//if list is empty, new node's prev and next will remain empty and the new node will be the head
		if (head==null)
		{
			n.prev=n;
			n.next=n;
			head=n;
			size++;
			tail=n;
			return true;
		}
		
		
		//the new node is connected into the loop
		tail.next=n;		
		n.next=head;
		size++;
		

		//sets the new node's and head's prev according to if its index is even or odd
		if ((size-1)%2==0)
		{	
			n.prev=head.prev;
			head.prev=n;
		}
		
		//the new node's prev is set to null if its index is odd
		else
		{
			
			n.prev=null;
			head.prev=tail;
	
		}	
		tail=n;
		return true;
	}

	/*
	 * adds a Node into the list at the given index(non-Javadoc)
	 * @param index
	 * @param item to be added
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	@Override
	public void add(int index, E item) 
	{
		
		Node n= new Node();
		n.data=item;
		
		//cursor used to find the node currently at the given index
		Node cur=head;
		
		//the node before the new node(not the .prev)
		Node previous;
		
		if(size()==0)
		{
			add(item);
			return;
		}
		
		if(index<0||index>=size())
		{
			throw new IndexOutOfBoundsException("The index is out of bounds");
		}
		
		
		
		//sets the previous to the last node in the list
		if(size()%2==0) 
		{
			previous = head.prev.next;
		}
		else
		{
			previous=head.prev;
		}
		
		//moves each node up until the given index is found
		for (int i=0; i<index; i++)
		{
			previous=cur;
			cur=cur.next;
		}
		
		n.next=cur;
		previous.next=n;
		
		
		if(index%2==0)
		{
			n.prev=cur.prev;
		}
		else 
		{
			n.prev=null;
		}
		
		//need to set the new node as the head if it is added at 0
		if(index==0)
		{
			head=n;
			head.prev=head.prev.next;
		}
		
		size++;
		
		//Node currently being fixed
		Node fix=n;
		
		//last even index for the fix node
		Node prevForFix=n;
		
		//every node needs to be fixed after the added node
		for (int i=index+1; i<size; i++)
		{
			fix=fix.next;
			
			
			if (i%2==0)
			{	
					fix.prev=prevForFix;
					prevForFix=prevForFix.next.next;
			}
			else 
			{
				fix.prev=null;
			}
		}
		

		//sets the head's new prev
		if (size%2==0)
		{
			head.prev=prevForFix;
		}
		else
		{
			head.prev=tail;
		}
		
			
	}

	/*
	 * adds all items in the collection to the end of the list(non-Javadoc)
	 * @return if successful
	 * @param collection to be added
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends E> items) 
	{
		Iterator<? extends E> iter=items.iterator();
		while(iter.hasNext())
		{
			E addition=iter.next();
			add(addition);
		}
		return true;
	}

	/*
	 * adds all items in the collection to the list at the given index(non-Javadoc)
	 * @return if successful
	 * @param index
	 * @param collection to be added
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<? extends E> items) 
	{
		if(index<0||index>=size())
		{
			throw new IndexOutOfBoundsException("The index is out of bounds");
		}
		Iterator<? extends E> iter=items.iterator();
		while(iter.hasNext())
		{
			E addition=iter.next();
			add(index, addition);
		}
		return true;
	}

	/*
	 * clears the list(non-Javadoc)
	 * @see java.util.List#clear()
	 */
	@Override
	public void clear() 
	{
		head=null;
		size=0;
	}

	/*
	 * returns true if the given object is in the list(non-Javadoc)
	 * @return if contains
	 * @see java.util.List#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object item) 
	{
		Node cur=head;
		
		for (int i=0; i<size; i++)
		{
			if (cur.data.equals(item))
			{
				return true;
			}
			cur=cur.next;
		}
		return false;
	}

	/*
	 * returns true if all items in the collection are contained in the list(non-Javadoc)
	 * @return if contains
	 * @param collection to be checked
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> items) 
	{
		Iterator<?> iter=items.iterator();
		for(int i=0; i<size; i++)
		while(iter.hasNext())
		{
			Object tmp=iter.next();
			if (!contains(tmp))
			{
				return false;
			}
		}
		return true;
	}

	/*
	 * returns the data at the given index(non-Javadoc)
	 * @return value at index
	 * @param index
	 * @see java.util.List#get(int)
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException
	{
		Node cur=head;
		if(index<0||index>=size())
		{
			throw new IndexOutOfBoundsException("Index is out of Range");
		}
		if(index==0)
		{
			return cur.data;
		}
		for (int i=0; i<index; i++)
		{
			cur=cur.next;
		}
		return cur.data;
	}
	
	/*
	 * returns the Node at the given index or throws an exception if the index is out of range
	 * @return Node at index
	 * @param index
	 */
	public Node getNodeAtIndex(int index)
	{
		Node cur=head;
		if(index<0||index>=size())
		{
			throw new IndexOutOfBoundsException("Index is out of Range");
		}
		for (int i=0; i<index; i++)
		{
			cur=cur.next;
		}
		return cur;
	}

	/*
	 * returns the index of the given object or returns -1 if it is not in the list(non-Javadoc)
	 * @return index
	 * @param item 
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(Object item) 
	{
		int index=0;
		Node cur=head;
		
		for (int i=0; i<size; i++)
		{
			if(cur.data==null)
			{
				cur=cur.next;
				index++;
				continue;
			}
			if (cur.data.equals(item))
			{
				return index;
			}
			cur=cur.next;
			index++;
		}
		return -1;
	}

	/*
	 * returns true if the list is empty(non-Javadoc)
	 * @return if Empty
	 * @see java.util.List#isEmpty()
	 */
	@Override
	public boolean isEmpty() 
	{
		if (head!=null)
		{
			return false;
		}
		return true;
	}

	/*
	 * returns an iterator over this list(non-Javadoc)
	 * @return iterator
	 * @see java.util.List#iterator()
	 */
	@Override
	public Iterator<E> iterator() 
	{
		return new AmusingIterator();
	}

	/*
	 * returns the last occurrence of the given object(non-Javadoc)
	 * @return last index of item
	 * @param item
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object item) 
	{
		Node cur=head;
		
		//last index
		int lasti=-1;
		for (int i=0; i<size; i++)
		{
			if(cur.data.equals(item))
			{
				lasti=i;
			}
			cur=cur.next;
		}
		return lasti;
	}

	/*
	 * returns a list iterator over the given list(non-Javadoc)
	 * @return listIterator
	 * @see java.util.List#listIterator()
	 */
	@Override
	public ListIterator<E> listIterator() 
	{
		return new AmusingListIterator();
	}
	
	/*
	 * returns a descending iterator over the given list
	 * @return descending list iterator
	 */
	public ListIterator<E> listDescendingIterator()
	{
		return new AmusingListDescendingIterator();
	}

	/*
	 * returns an iterator over the given list starting at a given index(non-Javadoc)
	 * @return iterator starting at index
	 * @param index
	 * @see java.util.List#listIterator(int)
	 */
	@Override
	public ListIterator<E> listIterator(int index) 
	{
		if(index<0||index>=size())
		{
			throw new IndexOutOfBoundsException("Index is out of Range");
		}
		AmusingListIterator all=new AmusingListIterator();
		
		//moves forward until the given index is reached
		for(int i=0; i<index; i++)
		{
			all.next();
		}
		return all;
	}

	/*
	 * removes data from the list if it is present, does nothing if it is not
	 * @return if successful (non-Javadoc)
	 * @param target data
	 * @see java.util.List#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object tNode)
	{

		int index = 0;
		Node cur = head;
		
		//The Node right before current, not its prev, but the Node prior to it. 
		Node prevr;

		/*
		 * if the target node is the last node in the list(and the tail was an even numbered index, all that needs to be done
		 * is setting the head's previous as the old tail's previous, reassigning the tail, 
		 * and setting the Node prior to the tail's next as the head. 
		 */
		if (tNode.equals(tail.data)&&size%2==1)
		{
			head.prev=tail.prev;
			tail.prev.next.next=head;
			tail=tail.prev.next;
			tail.next=head;
			size--;
			return true;
		}
		
		
		/**
		 * if the target node is the last node in the list(and the tail was an odd numbered index)
		 * the Node prior to the old tail's next is set to the head, and set the new tail
		 */
		if (tNode.equals(tail.data)&&size%2==0)
		{
			tail.next.prev.next=head;
			tail=tail.next.prev;
			size--;
			return true;
		}
		
		 
		//finds the index of the target node and puts it in a variable 
		for (int i=0; i<size; i++)
		{
			if (cur.data.equals(tNode))
			{
				break;
			}
			index++;
			cur=cur.next;
		}
		
		//finds the node prior to the current node(not the prev, but the Node that has cur as its next) 
		prevr=cur.prev.next;
		prevr.next=cur.next;
		
		//the Node that is currently being fixed
		Node fix=cur;			
		Node prevForFix=cur.prev;
		
			
		//goes through each Node after the removed Node and reassigns the nexts and prevs
		for (int i=index+1; i<size; i++)
		{
			fix=fix.next;
			
			//indexes with even numbers now will turn to odd once the tnode is removed 
			if (i%2==0)
			{	
					fix.prev=null;
			}
			
			//the prev for even numbered indexes is used and changes
			else 
			{
				fix.prev=prevForFix;
				prevForFix=prevForFix.next.next;
				if (prevForFix==cur)
				{
					prevForFix=prevForFix.next;
				}
			}
		}
			
		//special fix for if the target node was the head
		if (tNode.equals(head.data))
		{
			tail.next=head.next;
			head=head.next;
		}
			
		size--;
		
		//finding the head's prev
		if (size%2==0)
		{
			head.prev=prevForFix;
		}
		else
		{
			head.prev=tail;
		}
		
		//nulls the removed node for the trash collector
		cur.data=null;
		cur.next=null;
		cur.prev=null;
	
		return true;
		
	}

	/*
	 * removes the node at the given index, throws exception if the index is out of range
	 * @return the value removed(non-Javadoc)
	 * @param index
	 * @see java.util.List#remove(int)
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException
	{
		//target index
		int targeti = index;
		
		Node cur = head;
		
		//Node prior to the cursor
		Node prevr;
		
		//the data at the removal point
		E answer;

		if (index>size-1)
		{
			throw new IndexOutOfBoundsException("index does not exist");
		}
		
		if(size()==1)
		{
			answer=get(0);
			clear();
			return answer;
		}
		
		/*
		 * if the target index is the last node in the list(and the tail was an even numbered index, all that needs to be done
		 * is setting the head's previous as the old tail's previous, reassigning the tail, 
		 * and setting the Node prior to the tail's next as the head. 
		 */
		if (targeti==size-1&&size%2==1)
		{
			answer=tail.data;
			head.prev=tail.prev;
			tail.prev.next.next=head;
			tail=tail.prev.next;
			tail.next=head;
			return answer;
		}
		
		/**
		 * if the target index is the last node in the list(and the tail was an odd numbered index)
		 * the Node prior to the old tail's next is set to the head, and set the new tail
		 */
		if (targeti==size-1&&size%2==0)
		{
			answer=tail.data;
			tail.next.prev.next=head;
			tail=tail.next.prev;
			return answer;
		}
		
		//finds the index of the target node and puts it in a variable 
		for (int i=0; i<targeti; i++)
		{
			cur=cur.next;
		}
		
			answer=cur.data;
			prevr=cur.prev.next;
			prevr.next=cur.next;
			
			//node that is being fixed
			Node fix=cur;
			
			//the prev for a fix node
			Node prevForFix=cur.prev;
			
			//goes through each node after the removed one and fixes them
			for (int i=index+1; i<size; i++)
			{
				fix=fix.next;
				
				//the new index will be odd
				if (i%2==0)
				{	
						fix.prev=null;
				}
				
				//the new index will be even and needs the last even index node
				else 
				{
					fix.prev=prevForFix;
					
					//sets up the variable for the next use
					prevForFix=prevForFix.next.next;
					
					//special fix for when the variable becomes the removed node
					if (prevForFix==cur)
					{
						prevForFix=prevForFix.next;
					}
				}
			}
			
			//special fix for if the removed node was the head
			if (targeti==0)
			{
				tail.next=head.next;
				head=head.next;
			}
			
			size--;
			
			//fixes the head's prevs
			if (size%2==0)
			{
				head.prev=prevForFix;
			}
			else
			{
				head.prev=tail;
			}
			
			//nulls the removed node for the trash collector
			cur.data=null;
			cur.next=null;
			cur.prev=null;
			
			return answer;
	}

	/*
	 * removes all items in the collection(non-Javadoc)
	 * @return if successful
	 * @param collection
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> items) 
	{
		Iterator<?> iter= items.iterator();
		while(iter.hasNext())
		{
			Object removal=iter.next();
			remove(removal);
		}
		return true;
	}

	/*
	 * keeps only the values that are in the collection(non-Javadoc)
	 * @return if successful
	 * @param collection
	 * @see java.util.List#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> items) 
	{
		ListIterator<?> iter= listIterator();
		while(iter.hasNext())
		{
			Object tmp= iter.next();
			if(items.contains(tmp))	
			{
				continue;
			}
			remove(tmp);		
		}
		return true;
	}
	
	/*
	 * sets the node at the given index data as the given data (non-Javadoc)
	 * @return element previous there
	 * @param index
	 * @param item
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	@Override
	public E set(int index, E item) 
	{
		Node cur=head;
		if(index<0||index>=size())
		{
			throw new IndexOutOfBoundsException("Index is out of Range");
		}
		for(int i=0; i<index; i++)
		{
			cur=cur.next;
		}
		E retval= cur.data;
		cur.data=item;
		return retval;
	}

	/*
	 * returns the size of the list(non-Javadoc)
	 * @return size
	 * @see java.util.List#size()
	 */
	@Override
	public int size() 
	{
		if(size>=Integer.MAX_VALUE)
		{
			return Integer.MAX_VALUE;
		}
		return size;
	}

	/*
	 * creates a sublist starting and ending at the given indexes(non-Javadoc)
	 * @return sublist
	 * @param index to start
	 * @param index to end
	 * @see java.util.List#subList(int, int)
	 */
	@Override
	public List<E> subList(int i1, int i2) 
	{
		
		if(i1<0||i1>=size()&&i2<0||i2>=size())
		{
			throw new IndexOutOfBoundsException("Index is out of Range");
		}
		AmusingLinkedList<E> allSub= new AmusingLinkedList<E>();
		ListIterator<E> litter= listIterator(i1);
		for(int i=i1; i<=i2; i++)
		{
			allSub.add(litter.next());
		}
		return allSub;
	}

	/*
	 * sets the list as an array(non-Javadoc)
	 * @return array of list
	 * @see java.util.List#toArray()
	 */
	@Override
	public Object[] toArray() 
	{
		Object[] result=new Object[size];
		Node cur=head;
		for (int i=0; i<size; i++)
		{
			result[i]=cur.data;
			if(i!=size-1)
			{
				cur=cur.next;
			}
		}
		
		return result;
	}

	/*
	 * sets the list in the given array(non-Javadoc)
	 * @return given array of list
	 * @param array
	 * @see java.util.List#toArray(java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] item) 
	{
		Node cur=head;
		if(item==null)
		{
			throw new NullPointerException("The given array is null");
		}
		if (item.length>size)
		{
			for (int i=0; i<size; i++)
			{
				item[i]=(T) cur.data;
				cur=cur.next;
			}
			
			for (int i=size; i<item.length; i++)
			{
				item[i]=null;
			}
		}
		
		else if(item.length==size)
		{
			for (int i=0; i<size; i++)
			{
				item[i]=(T) cur.data;
				cur=cur.next;
			}
		}
		
		else 
		{
			T[] newItem= (T[]) new Object[size];
			for (int i=0; i<size; i++)
			{
				newItem[i]=(T) cur.data;
				cur=cur.next;
			}
			return newItem;
		}
		return item;
	}
	
	/*
	 * returns a String representation of the list, listing each node's index, index of its .prev,
	 * index of its .next, and its data in that order on its own line
	 * @return list of index, .prev, .next, and data
	 */
	@Override
	public String toString()
	{
		String result="";
		ListIterator<E> litter= listIterator();
		
		//will be used to track the data, starts at 0
		E data=get(0);
		
		//index of .prev
		int previousIdx=indexOf(head.prev.data);
		litter.next();
		//index of .next
		int nextIdx=litter.nextIndex();

		//prints out the indexes information and then sets up the variables for the next loop
		for(int i=0; i<size(); i++)
		{
			if(i!=size()-1)
			{
				result=result+i+" "+previousIdx+" "+nextIdx+" "+data+"\n";
			}
			else
			{
				result=result+i+" "+previousIdx+" "+nextIdx+" "+data;
			}
			
			//the next node has to be odd
			if(i==0)
			{
				previousIdx=-1;
			}
			else
			{
				//the end of the list, so the .next must be 0, used to avoid iterator errors
				if(i==size()-1)
				{
					nextIdx=0;
				}
				
				//the previous index for 2 will be 0, used to avoid iterator errors
				else if(i==1)
				{
					previousIdx=0;
				}
				
				//the next index will be even, but the iterator is still on an odd index so one call to previous index gives correct index
				else if(i%2==1)
				{
					previousIdx=litter.previousIndex();
				}
				
				//next index will be odd
				else 
				{
					previousIdx=-1;
				}
			}
			
			//if the end of the list is not reached, iterate forward
			if(i!=size()-1)
			{
				data=litter.next();
				nextIdx=litter.nextIndex();
			}
			
		}
		return result;
	}
	
}
