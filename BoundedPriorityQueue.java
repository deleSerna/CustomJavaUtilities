import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * It's a bounded PriorityQueue on top of {@link PriorityQueue}. Except
 * {@link #add(Object)}, all other behaviors are similar to PriorityQueue. Since
 * it's a bounded PriorityQueue, it will only store the top {@link #maxCapacity}
 * items. If a new item come with a higher priority comes then the current
 * lowest priority item will be removed and new higher priority item will be
 * added.
 *
 * @param <E>
 */
public class BoundedPriorityQueue<E> extends PriorityQueue<E> {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final int maxCapacity;
	private int currentCapacity = 0;

	/**
	 * If the maxCapacity reaches then it will remove the lowest priority item.
	 *
	 * @param maxCapacity
	 *            maximum capacity
	 * @param comparator
	 *            the comparator
	 */
	public BoundedPriorityQueue(int maxCapacity, Comparator<E> comparator) {
		super(comparator);
		this.maxCapacity = maxCapacity;
	}

	@Override
	public boolean add(E element) {
		boolean result = offer(element);// super.add() is an extra indirection to offer
		if (result) {
			currentCapacity++;
		}
		if (currentCapacity > maxCapacity) {
			// remove has an extra indirection.Plus, in this location queue will not be empty. Hence NoSUCHEx is pointless here.
			poll();
			currentCapacity--;
		}
		return result;
	}
}
