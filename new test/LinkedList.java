import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A singly linked list.
 * 
 * @author Naif Hagatulah
 * @version 2023-01-23
 */
public class LinkedList<T> implements Ztack<T> { 
    private ListElement<T> first;   // First element in list.
    private ListElement<T> last;    // Last element in list.
    private int size;               // Number of elements in list.
    
    /**
     * A list element.
     */
    private static class ListElement<T> {
        public T data;
        public ListElement<T> next;
        
        public ListElement(T data) {
            this.data = data;
            this.next = null;
        }
    }
    
    /**
     * Creates an empty list.
     */
    public LinkedList() {

    }

    /**
     * Inserts the given element at the beginning of this list.
     *
     * @param element An element to insert into the list.
     */
    @Override
    public void push(T element) {
        ListElement newFirst = new ListElement<T>(element);
        if(this.first != null){
            newFirst.next = this.first;
        }else{
            newFirst.next = null;
            this.last = newFirst;
        }
        this.first = newFirst;
        size++;
    }

    /**
     * @return The head of the list.
     * @throws NoSuchElementException if the list is empty.
     */
    @Override
    public T top() {
        if(size == 0)
            throw new   EmptyStackException();
        return first.data;
    }

    /**
     * Removes the first element from the list.
     *
     * @return The removed element.
     * @throws NoSuchElementException if the list is empty.
     */
    @Override
    public T pop() {
        if(size == 0)
            throw new  EmptyStackException();
        ListElement<T> saved = first;
        first = first.next;
        size--;
        if(size == 0)
            last = null;
        return saved.data;
    }

    /**
     * Removes all of the elements from the list.
     */
    public void clear() {
        ListElement<T> currentValue = first;
        while(currentValue != null){
            pop();
            currentValue = currentValue.next;
        }
    }

    /**
     * @return The number of elements in the list.
     */
    @Override
    public int size() {
        int value = 1;
        if(first == null)
            return 0;
        ListElement values = first;
        while(values.next != null){
            value++;
            values = values.next;
        }

        return size;
    }

    /**
     * Note that by definition, the list is empty if both first and last
     * are null, regardless of what value the size field holds (it should
     * be 0, otherwise something is wrong).
     *
     * @return <code>true</code> if this list contains no elements.
     */
    @Override
    public boolean isEmpty() {
        return first == null && last == null;
    }

    /**
     * Creates a string representation of this list. The string
     * representation consists of a list of the elements enclosed in
     * square brackets ("[]"). Adjacent elements are separated by the
     * characters ", " (comma and space). Elements are converted to
     * strings by the method toString() inherited from Object.
     *
     * Examples:
     *  "[1, 4, 2, 3, 44]"
     *  "[]"
     *
     * @return A string representing the list.
     */
    public String toString() {
        String value = "[";
        if(first == null && last == null)
            return "[]";
        ListElement<T> elements = first;
        while(elements != null){
            value += elements.data.toString();
            if(elements.next != null)
                value += ", ";
            elements = elements.next;
        }
        value +=  "]";
        return value;
    }

}
