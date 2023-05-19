import exceptions.IndexMoreSizeException;
import exceptions.IndexNotFoundException;
import exceptions.ValueNotFoundException;
import exceptions.ValueNullException;

import java.util.Arrays;
import java.util.Objects;

public class ArrayStringList implements StringList {
    private String[] elementData;
    private int size;

    public ArrayStringList() {
        this.elementData = new String[10];
    }

    private String[] grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1) + 1;
        return elementData = Arrays.copyOf(elementData, newCapacity);

    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new IndexMoreSizeException("Индекс: " + index + ", Size: " + size);
        }
    }

    private void checkNull(String item) {
        if (item == null) {
            throw new ValueNullException("Строка пустая");
        }
    }

    @Override
    public String add(String item) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        checkIndex(index);
        checkNull(item);
        if (size == elementData.length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        checkIndex(index);
        checkNull(item);
        String oldValue = elementData[index];
        elementData[index] = item;
        return oldValue;
    }

    @Override
    public String remove(String item) {
        checkNull(item);
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(item)) {
                System.arraycopy(elementData, i + 1, elementData, i, size - i - 1);
                elementData[size - 1] = null;
                size--;
                return item;
            }
        }
        throw new ValueNotFoundException("Элемент не найден");
    }

    @Override
    public String remove(int index) {
        for (int i = 0; i < size; i++) {
            if (i == index) {
                String oldValue = elementData[i];
                System.arraycopy(elementData, i + 1, elementData, i, size - i - 1);
                elementData[size - 1] = null;
                size--;
                return oldValue;
            }
        }
        throw new IndexNotFoundException("Индекс не найден");
    }


    @Override
    public boolean contains(String item) {
        checkNull(item);
        return indexOf(item) >= 0;
    }

    @Override
    public int indexOf(String item) {
        checkNull(item);
        for (int i = 0; i < size; i++) {
            if (item.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        checkNull(item);
        for (int i = size - 1; i >= 0; i--) {
            if (item.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if(otherList == null || size() != otherList.size()){
            return false;
        }
        for (int i = 0; i < size; i++) {
            if(!get(i).equals(otherList.get(i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int to = size, i = size = 0; i < to; i++)
            elementData[i] = null;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(elementData, size);
    }
}
