package Advanced.Lesson_2.Exceptions;

class MyArrayDataException extends RuntimeException {
    /**
     * Constructs an <code>MyArrayDataException</code> with no
     * detail message.
     */
    MyArrayDataException() {
        super();
    }

    /**
     * Constructs a new <code>MyArrayDataException</code>
     * class with an argument indicating the illegal index.
     *
     * @param   i   the first illegal index.
     * @param   j   the second illegal index.
     */
    MyArrayDataException(int i, int j) {
        super("Wrong data at [" + i + "][" + j + "] cell.");
    }

    /**
     * Constructs an <code>MyArrayDataException</code> with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    MyArrayDataException(String s) {
        super(s);
    }
}
