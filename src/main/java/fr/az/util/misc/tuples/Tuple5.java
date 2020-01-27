package fr.az.util.misc.tuples;

import java.util.Arrays;
import java.util.List;

/**
 * Provides a simple tuple implementation for 5 values, extends from {@linkplain Tuple4}
 * @author A~Z
 *
 * @param <A> first element's type
 * @param <B> second element's type
 * @param <C> third element's type
 * @param <D> fourth element's type
 * @param <E> fifth element's type
 */
public class Tuple5<A, B, C, D, E> extends Tuple4<A, B, C, D>
{
	/**
	 * The fifth element
	 */
	public E e;

	/**
	 * Initializes this tuple with null for each element
	 */
	public Tuple5() {}

	/**
	 * Initializes this tuple with the provided elements
	 * @param a the first element
	 * @param b the second element
	 * @param c the third element
	 * @param d the fourth element
	 * @param e the fifth element
	 */
	public Tuple5(A a, B b, C c, D d, E e)
	{
		super(a, b, c, d);
		this.e = e;
	}

	@Override public List<Object> toList() { return Arrays.asList(this.a, this.b, this.c, this.d, this.e); }

	/**
	 * @return the fifth element
	 */
	public E e() { return this.e; }

	@Override public String toString() { return "Tuple5["+ this.a +','+ this.b +','+ this.c +','+ this.d +','+ this.e +']'; }
}
