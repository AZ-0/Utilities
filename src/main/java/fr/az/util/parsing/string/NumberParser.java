package fr.az.util.parsing.string;

import fr.az.util.parsing.ParsingException;

public class NumberParser<N extends Number> implements Parser<N>
{
	private static final long serialVersionUID = 3405904486589299589L;

	private final Parser<N> parser;
	private final N min;
	private final N max;

	public NumberParser(Parser<N> parser) { this(parser, null, null); }
	public NumberParser(Parser<N> parser, N min) { this(parser, min, null); }
	public NumberParser(Parser<N> parser, N min, N max)
	{
		assert min.doubleValue() <= max.doubleValue();
		this.parser = parser;
		this.min = min;
		this.max = max;
	}

	@Override
	public N parse(String from) throws ParsingException
	{
		try
		{
			N number = this.parser.parse(from);

			if (number.doubleValue() < this.min.doubleValue())
				throw Parser.produceException("The number '"+ INPUT +"' should be lesser than "+ this.min, from);

			if (number.doubleValue() > this.max.doubleValue())
				throw Parser.produceException("The number '"+ INPUT +"' should be greater than "+ this.max, from);

			return number;
		}
		catch (NumberFormatException e) { throw Parser.produceException(DEFAULT_PARSING_ERROR_MESSAGE, from, e); }
	}

	public N getMin() { return this.min; }
	public N getMax() { return this.max; }
}
