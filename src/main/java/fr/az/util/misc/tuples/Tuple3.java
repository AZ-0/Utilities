package fr.az.util.misc.tuples;

import java.util.Arrays;
import java.util.List;

/**
 * Provides a simple tuple implementation for 3 values, extends from {@linkplain Tuple2}
 * @author A~Z
 *
 * @param <A> first element's type
 * @param <B> second element's type
 * @param <C> third element's type
 */
public class Tuple3<A, B, C> extends Tuple2<A, B>
{
	/**
	 * The third element
	 */
	public C c;

	/**
	 * Initializes this tuple with null for each element
	 */
	public Tuple3() {}

	/**
	 * Initializes this tuple with the provided elements
	 * @param a the first element
	 * @param b the second element
	 * @param c the third element
	 */
	public Tuple3(A a, B b, C c)
	{
		super(a, b);
	}

	@Override public List<Object> toList() { return Arrays.asList(this.a, this.b, this.c); }

	/**
	 * @return the third element
	 */
	public C c() { return this.c; }

	@Override public String toString() { return "Tuple3["+ this.a +','+ this.b +','+ this.c +']'; }
}
