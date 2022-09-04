import exceptions.IndexMoreSizeException;
import exceptions.IndexNotFoundException;
import exceptions.ValueNotFoundException;
import exceptions.ValueNullException;

import java.util.Arrays;
import java.util.Objects;

public class ArrayIntegerList implements IntegerList {
    private Integer[] elementData;
    private int size;

    public ArrayIntegerList() {
        this.elementData = new Integer[10];
    }

    private Integer[] grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1) + 1;
        return elementData = Arrays.copyOf(elementData, newCapacity);

    }

    private Integer[] sort() {
        Integer[] copy = toArray();
        for (int i = 1; i < copy.length; i++) {
            int temp = copy[i];
            int j = i;
            while (j > 0 && copy[j - 1] >= temp) {
                copy[j] = copy[j - 1];
                j--;
            }
            copy[j] = temp;
        }
        return copy;
    }

    private boolean binarySearch(int item, Integer[] arr) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == arr[mid]) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }

        }
        return false;
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new IndexMoreSizeException("Индекс: " + index + ", Size: " + size);
        }
    }

    private void checkNull(Integer item) {
        if (item == null) {
            throw new ValueNullException("Строка пустая");
        }
    }

    @Override
    public Integer add(Integer item) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
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
    public Integer set(int index, Integer item) {
        checkIndex(index);
        checkNull(item);
        Integer oldValue = elementData[index];
        elementData[index] = item;
        return oldValue;
    }

    @Override
    public Integer remove(Integer item) {
        int removeIndex = indexOf(item);
        if (removeIndex == -1) {
            throw new ValueNotFoundException("Элемент не найден");
        }
        remove(removeIndex);
        return item;
    }

    @Override
    public Integer remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexNotFoundException("Индекс не найден");
        }
        Integer oldValue = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[size - 1] = null;
        size--;
        return oldValue;
    }


    @Override
    public boolean contains(Integer item) {
        checkNull(item);
        Integer[] arraySort = sort();
        return binarySearch(item, arraySort);
    }

    @Override
    public int indexOf(Integer item) {
        checkNull(item);
        for (int i = 0; i < size; i++) {
            if (item.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkNull(item);
        for (int i = size - 1; i >= 0; i--) {
            if (item.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        boolean equal = size == otherList.size();
        if (equal) {
            final Object[] otherEs = otherList.toArray();
            final Object[] es = elementData;
            for (int i = 0; i < size; i++) {
                if (!Objects.equals(es[i], otherEs[i])) {
                    equal = false;
                    break;
                }
            }
        }
        return equal;
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
    public Integer[] toArray() {
        return Arrays.copyOf(elementData, size);
    }
}
