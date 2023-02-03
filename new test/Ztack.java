public interface Ztack<T> {
    void push(T elem);

    T pop();

    T top();

    int size();

    boolean isEmpty();
}