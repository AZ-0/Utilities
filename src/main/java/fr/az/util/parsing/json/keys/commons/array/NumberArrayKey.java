package fr.az.util.parsing.json.keys.commons.array;

import java.util.function.BiFunction;

import fr.az.util.parsing.IParser;

public class NumberArrayKey<N extends Number> extends SimpleArrayKey<Number, N>
{
	private static final long serialVersionUID = -7389326377115865574L;

	public static final NumberArrayKey<Number> KEY = new NumberArrayKey<>("value", n -> n);

	public NumberArrayKey(String name, IParser<Number, N, ?> parser) { super(name, parser); }
	public NumberArrayKey(String name, BiFunction<N, N, IParser<Number, N, ?>> producer) { this(name, null, null, producer); }
	public NumberArrayKey(String name, N min, BiFunction<N, N, IParser<Number, N, ?>> producer) { this(name, null, null, producer); }
	public NumberArrayKey(String name, N min, N max, BiFunction<N, N, IParser<Number, N, ?>> producer)
	{
		super(name, producer.apply(min, max));
	}
}
