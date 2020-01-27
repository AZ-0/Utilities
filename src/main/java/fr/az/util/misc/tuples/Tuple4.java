package fr.az.util.misc.tuples;

import java.util.Arrays;
import java.util.List;

/**
 * Provides a simple tuple implementation for 4 values, extends from {@linkplain Tuple3}
 * @author A~Z
 *
 * @param <A> first element's type
 * @param <B> second element's type
 * @param <C> third element's type
 * @param <D> fourth element's type
 */
public class Tuple4<A, B, C, D> extends Tuple3<A, B, C>
{
	/**
	 * The third element
	 */
	public D d;

	/**
	 * Initializes this tuple with null for each element
	 */
	public Tuple4() {}

	/**
	 * Initializes this tuple with the provided elements
	 * @param a the first element
	 * @param b the second element
	 * @param c the third element
	 * @param d the fourth element
	 */
	public Tuple4(A a, B b, C c, D d)
	{
		super(a, b, c);
		this.d = d;
	}

	@Override public List<Object> toList() { return Arrays.asList(this.a, this.b, this.c, this.d); }

	/**
	 * @return the fourth element
	 */
	public D d() { return this.d; }

	@Override public String toString() { return "Tuple4["+ this.a +','+ this.b +','+ this.c +','+ this.d +']'; }
}
