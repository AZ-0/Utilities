package fr.az.util.misc.tuples;

import java.util.Arrays;
import java.util.List;

/**
 * Provides a simple tuple implementation for 6 values, extends from {@linkplain Tuple5}
 * @author A~Z
 *
 * @param <A> first element's type
 * @param <B> second element's type
 * @param <C> third element's type
 * @param <D> fourth element's type
 * @param <E> fifth element's type
 * @param <F> sixth element's type
 */
public class Tuple6<A, B, C, D, E, F> extends Tuple5<A, B, C, D, E>
{
	/**
	 * The sixth element
	 */
	public F f;

	/**
	 * Initializes this tuple with null for each element
	 */
	public Tuple6() {}

	/**
	 * Initializes this tuple with the provided elements
	 * @param a the first element
	 * @param b the second element
	 * @param c the third element
	 * @param d the fourth element
	 * @param e the fifth element
	 * @param f the sixth element
	 */
	public Tuple6(A a, B b, C c, D d, E e, F f)
	{
		super(a, b, c, d, e);
		this.f = f;
	}

	@Override public List<Object> toList() { return Arrays.asList(this.a, this.b, this.c, this.d, this.e, this.f); }

	/**
	 * @return the sixth element
	 */
	public F f() { return this.f; }

	@Override public String toString() { return "Tuple6["+ this.a +','+ this.b +','+ this.c +','+ this.d +','+ this.e +','+ this.f +']'; }
}
