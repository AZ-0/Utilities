package fr.az.util.misc.tuples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Provides a simple tuple implementation for 2 values.
 * <br>
 * Each tuple of n + 1 values, n > 1, is a tuple of n values as well.
 * This means a {@linkplain Tuple3} is a {@linkplain Tuple2}, a {@linkplain Tuple4} is a {@linkplain Tuple3} and a {@linkplain Tuple2}, et caetera
 *
 * @author A~Z
 *
 * @param <A>
 * @param <B>
 */
public class Tuple2<A, B>
{
	/**
	 * The first element
	 */
	public A a;

	/**
	 * The second element
	 */
	public B b;

	/**
	 * Initializes this tuple with null for each element
	 */
	public Tuple2() {}

	/**
	 * Initializes this tuple with the provided elements
	 * @param a the first element
	 * @param b the second element
	 */
	public Tuple2(A a, B b)
	{
		this.a = a;
		this.b = b;
	}

	/**
	 * @return a list containing this tuple's elements
	 */
	public List<Object> toList() { return Arrays.asList(this.a, this.b); }

	/**
	 * Get a list of each element of this tuple matching the provided type.
	 * If an element's class isn't a subtype of the provided class, it isn't added to the list.
	 * @param clazz the matcher {@link java.lang.Class}
	 * @return the generated list
	 */
	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> toGenericList(Class<? extends T> clazz)
	{
		ArrayList<T> list = new ArrayList<>();

		for (Object element : list)
			if (clazz.isInstance(element))
				list.add((T) element);

		return list;
	}

	/**
	 * @return the first element
	 */
	public A a() { return this.a; }

	/**
	 * @return the second element
	 */
	public B b() { return this.b; }

	@Override public String toString() { return "Tuple2["+ this.a +','+ this.b +']'; }
}
