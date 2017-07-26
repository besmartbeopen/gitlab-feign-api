package it.besmartbeopen.gitlab.pagination;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * @author marco
 *
 * @param <E> paginated type
 */
public class PaginatedList<E> implements List<E>, Serializable {

  private static final long serialVersionUID = -4363818739908955121L;

  private static final int FIRST_PAGE = 1;
  public static final int DEFAULT_PAGE_SIZE = 50;

  private final LoadingCache<Integer, Page<E>> data;

  public PaginatedList(Function<Integer, Page<E>> getPage) {

    data = CacheBuilder.newBuilder()
        .build(new CacheLoader<Integer, Page<E>>() {

          @Override
          public Page<E> load(Integer k) throws Exception {
            return getPage.apply(k);
          }
        });
  }

  @Override
  public int size() {
    return data.getUnchecked(FIRST_PAGE).getTotal();
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public Stream<E> stream() {
    return IntStream
        .rangeClosed(FIRST_PAGE, data.getUnchecked(FIRST_PAGE).getTotalPages())
        .mapToObj(i -> data.getUnchecked(i).getData())
        .flatMap(List::stream);
  }

  @Override
  public Iterator<E> iterator() {
    return stream().iterator();
  }

  @Override
  public Object[] toArray() {
    return stream().toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    throw new IllegalStateException("not implemented yet");
  }

  @Override
  public boolean contains(Object o) {
    throw new IllegalStateException("not implemented yet");
  }

  @Override
  public boolean add(E e) {
    throw new IllegalStateException("not supported");
  }

  @Override
  public boolean remove(Object o) {
    throw new IllegalStateException("not supported");
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    throw new IllegalStateException("not implemented yet");
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    throw new IllegalStateException("not supported");
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    throw new IllegalStateException("not supported");
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    throw new IllegalStateException("not supported");
  }

  @Override
  public void clear() {
    throw new IllegalStateException("not supported");
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    throw new IllegalStateException("not supported");
  }

  @Override
  public E get(int index) {
    final int perPage = data.getUnchecked(FIRST_PAGE).getPerPage();
    return data.getUnchecked(FIRST_PAGE + index / perPage)
        .getData().get(index % perPage);
  }

  @Override
  public E set(int index, E element) {
    throw new IllegalStateException("not supported");
  }

  @Override
  public void add(int index, E element) {
    throw new IllegalStateException("not supported");
  }

  @Override
  public E remove(int index) {
    throw new IllegalStateException("not supported");
  }

  @Override
  public int indexOf(Object o) {
    throw new IllegalStateException("not implemented yet");
  }

  @Override
  public int lastIndexOf(Object o) {
    throw new IllegalStateException("not implemented yet");
  }

  @Override
  public ListIterator<E> listIterator() {
    throw new IllegalStateException("not implemented yet");
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    throw new IllegalStateException("not implemented yet");
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    // simple
    return IntStream.range(fromIndex, toIndex)
      .mapToObj(i -> get(i))
      .collect(Collectors.toList());
  }
}
