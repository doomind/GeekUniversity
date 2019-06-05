package Advanced.Lesson_2.Exceptions;

public class MyArraySizeException extends RuntimeException {
    /**
     * Constructs an <code>MyArraySizeException</code> with no
     * detail message.
     */
    MyArraySizeException() {
        super();
    }

    /**
     * Constructs an <code>MyArraySizeException</code> with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public MyArraySizeException(String s) {
        super(s);
    }
}
