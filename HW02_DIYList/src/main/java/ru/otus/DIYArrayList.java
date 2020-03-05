package ru.otus;

import java.util.*;



public class DIYArrayList<T> implements List<T> {
    private final int INIT_SIZE = 20;
    private int resize_fract=2;
    private static int max_array_size=Integer.MAX_VALUE-8;
    private int size=0;//pointer
    private String INVALID_SIZE = "Invalid list size";
    private Object[] myArray;

    public DIYArrayList() {
        Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA=new Object[INIT_SIZE]; //добавил создание массива дефолтного размера в конструктор, чтобы он только создавался при вызове данного конструктора, аналогично сдела в public DIYArrayList(Collection<? extends T> c)
        this.myArray= DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public DIYArrayList(int initCapacity){
        if (initCapacity<0||initCapacity>max_array_size)
            throw new RuntimeException(INVALID_SIZE);
        this.myArray = new Object[initCapacity];
    }


    public DIYArrayList(Collection<? extends T> c){
        myArray=c.toArray();
        if(myArray.length!=0){
            if (myArray.getClass() != Object[].class)
                myArray=Arrays.copyOf(myArray, size, Object[].class);
        }
        else {
            Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA=new Object[INIT_SIZE]; //Создаем объект, только при попадании в блок else
            this.myArray= DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
        }
    }

    private void indexCheck(int index, int maxIndex){
        if(index<0||index>maxIndex)
            throw new IndexOutOfBoundsException();
    }

    private void indexCheck(int index){
        indexCheck(index, size);
    }


    private int getNewArraySize(int capacity){
        int someRate=1/resize_fract;
        int newCapacity=myArray.length*(someRate+1);
        if(newCapacity<0)
            newCapacity=capacity;
        if(newCapacity - max_array_size>0)
            newCapacity=hugeCapacity(capacity);
        return newCapacity;
    }
    private static int hugeCapacity(int minCapacity){
        if(minCapacity<0)
            throw new OutOfMemoryError();
        return(minCapacity > max_array_size) ? Integer.MAX_VALUE : max_array_size;
    }

    private void ensureSize(int minCapacity){
        int capacity = (myArray.length == 0) ? Math.max(INIT_SIZE, minCapacity) : minCapacity;
        indexCheck(capacity, max_array_size);
        if(capacity>myArray.length){
            myArray=Arrays.copyOf(myArray,getNewArraySize(capacity));
        }
    }

    @Override
    public int size() { return  size;}

    @Override
    public boolean contains(Object o) {
        return indexOf(o)>=0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(myArray, size);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }


    @Override
    public void add(int index, T element) {
        indexCheck(index, size+1);
        if (index+1>size)
        ensureSize(size+1);

        myArray[index+1]=element;
        size++;
    }

    @Override
    public boolean add(T t) {
        add(size, t);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        indexCheck(index);
        Object[] arr=c.toArray();
        int newNum=arr.length;

        if(arr.length>size-index) //Добавил проверку, чтобы лишний раз не вызывать метод.
        ensureSize(size+newNum);

        int numMoved = size-index;
        if (numMoved>0)
            System.arraycopy(myArray, index, myArray, index+newNum, numMoved);

        System.arraycopy(arr,0,myArray, index, newNum);
        size+=newNum;

        return newNum!=0;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean success=false;
        for(T t:c){
            success|=add(t);
        }
        return success;
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return (T)myArray[index];
    }

    @Override
    public T set(int index, T element) {
        indexCheck(index);
        T oldValue=(T)myArray[index];
        myArray[index]=element;

        return oldValue;
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new MyListIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {return size==0; }


    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object elm:c)
            if(!contains(elm))
                return false;
            return true;
    }



    @Override
    public boolean removeAll(Collection<?> c) {
        throw new  UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new  UnsupportedOperationException();
    }

    @Override
    public void clear() {
        for(int i=0; i<size;i++)
            myArray[i]=null;
        size=0;
    }


    @Override
    public int indexOf(Object o) {
        for(int i=0; i<size; i++){
            if(Objects.equals(o, myArray[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new  UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new  UnsupportedOperationException();
    }

    private class MyIterator implements Iterator<T>{
        protected int position = 0;

        @Override
        public boolean hasNext() {
            return position<size;
        }

        @Override
        public T next() {
            if(position>size){
                throw new NoSuchElementException();
            }
            return (T)myArray[position++];
        }
    }

    private class MyListIterator extends MyIterator implements ListIterator<T>{
        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException();
        }

        @Override
        public T previous() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T t) {
            DIYArrayList.this.set(position-1, t);
        }

        @Override
        public void add(T t) {
            throw new UnsupportedOperationException();
        }
    }
}