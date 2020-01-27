package fr.az.util.misc.tuples;

import java.util.Arrays;
import java.util.List;

/**
 * Provides a simple tuple implementation for 7 values, extends from {@linkplain Tuple6}
 * @author A~Z
 *
 * @param <A> first element's type
 * @param <B> second element's type
 * @param <C> third element's type
 * @param <D> fourth element's type
 * @param <E> fifth element's type
 * @param <F> sixth element's type
 * @param <G> seventh element's type
 */
public class Tuple7<A, B, C, D, E, F, G> extends Tuple6<A, B, C, D, E, F>
{
	/**
	 * The seventh element
	 */
	public G g;

	/**
	 * Initializes this tuple with null for each element
	 */
	public Tuple7() {}

	/**
	 * Initializes this tuple with the provided elements
	 * @param a the first element
	 * @param b the second element
	 * @param c the third element
	 * @param d the fourth element
	 * @param e the fifth element
	 * @param f the sixth element
	 * @param g the seventh element
	 */
	public Tuple7(A a, B b, C c, D d, E e, F f, G g)
	{
		super(a, b, c, d, e, f);
		this.g = g;
	}

	@Override public List<Object> toList() { return Arrays.asList(this.a, this.b, this.c, this.d, this.e, this.f, this.g); }

	/**
	 * @return the seventh element
	 */
	public G g() { return this.g; }

	@Override
	public String toString()
	{
		return "Tuple7["
				+ this.a +','
				+ this.b +','
				+ this.c +','
				+ this.d +','
				+ this.e +','
				+ this.f +','
				+ this.g +']';
	}
}
