package fr.az.util.parsing.json.keys.commons;

import fr.az.util.parsing.json.JSONParsingException;
import fr.az.util.parsing.json.keys.Key;

/**
 * Essentially the same as {@linkplain KeyValue} for {@linkplain Number} based class, but use the {@literal Number#<rawType>Value} method
 * as parser
 * @author A~Z
 *
 * @param <N> a Number class
 */
public abstract class KeyNumber<N extends Number> implements Key<Number, N>
{
	private static final long serialVersionUID = -8207801395969223443L;
	public static final String DEFAULT_NAME = "value";

	public static final KeyNumber<Number> KEY = new KeyNumber<Number>()
	{
		private static final long serialVersionUID = 4201375031782760665L;

		@Override
		public Number parse(Number n) throws JSONParsingException
		{
			return this.check(n);
		}
	};

	protected String name;
	protected N min;
	protected N max;

	public KeyNumber() {}
	public KeyNumber(N min) { this(min, null); }
	public KeyNumber(N min, N max) { this(DEFAULT_NAME, min, max); }

	public KeyNumber(String name) { this(name, null, null); }
	public KeyNumber(String name, N min) { this(name, min, null); }
	public KeyNumber(String name, N min, N max)
	{
		this.name = name;
		this.min = min;
		this.max = max;
	}

	public Number check(Number n) throws JSONParsingException
	{
		if (this.min != null && n.doubleValue() < this.min.doubleValue())
			throw new JSONParsingException(this, "The number should be greater than "+ this.min);

		if (this.max!= null && n != null && n.doubleValue() > this.max.doubleValue())
			throw new JSONParsingException(this, "The number should be lesser than "+ this.max);

		return n;
	}

	@Override
	public String getKey() { return this.name; }
	public N getMin() { return this.min; }
	public N getMax() { return this.max; }

	@Override public Class<Number> expectedType() { return Number.class; }
}
