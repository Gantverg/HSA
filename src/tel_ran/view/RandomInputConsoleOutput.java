package tel_ran.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import tel_ran.random.RandomGenerator;

public class RandomInputConsoleOutput implements InputOutput {
	private static final int MIN_STRING = 5;
	private static final int MAX_STRING = 10;
	private static final LocalDate MIN_DATE = LocalDate.ofYearDay(1900, 1);
	private static final LocalDate MAX_DATE = LocalDate.now();
	LocalDate minDate = MIN_DATE;
	LocalDate maxDate = MAX_DATE;
	int minStrLength = MIN_STRING;
	int maxStrLength = MAX_STRING;
	RandomGenerator generator = new RandomGenerator();
	boolean fl_print = true;

	public boolean isFl_print() {
		return fl_print;
	}

	public LocalDate getMinDate() {
		return minDate;
	}

	public void setMinDate(LocalDate minDate) {
		this.minDate = minDate;
	}

	public LocalDate getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(LocalDate maxDate) {
		this.maxDate = maxDate;
	}

	public void setFl_print(boolean fl_print) {
		this.fl_print = fl_print;
	}

	public int getMinStrLength() {
		return minStrLength;
	}

	public void setMinStrLength(int minStrLength) {
		this.minStrLength = minStrLength;
	}

	public int getMaxStrLength() {
		return maxStrLength;
	}

	public void setMaxStrLength(int maxStrLength) {
		this.maxStrLength = maxStrLength;
	}

	@Override
	public String getString(String prompt) {

		return getString(prompt, minStrLength, maxStrLength);
	}

	@Override
	public String getString(String prompt, int minLength, int maxLength) {
		put(prompt);
		String res = generator.getRandomAsciiString(minLength, maxLength);
		put(res);
		return res;
	}

	@Override
	public Integer getInteger(String prompt) {

		return getInteger(prompt, 0, Integer.MAX_VALUE);

	}

	@Override
	public Integer getInteger(String prompt, Integer min, Integer max) {
		put(prompt);
		Integer res = generator.getRandomInteger(min, max);
		put(res);
		return res;
	}

	@Override
	public Number getNumber(String prompt, Number min, Number max) {
		put(prompt);
		Number res = generator.getRandomDouble(min.doubleValue(), max.doubleValue());
		put(res);
		return res;
	}

	@Override
	public Number getNumber(String prompt) {
		return getNumber(prompt, Double.MIN_VALUE, Double.MAX_VALUE);
	}

	@Override
	public void put(Object object) {
		if (fl_print)
			System.out.println(object);

	}

	@Override
	public LocalDate getDate(String prompt, String format, LocalDate from, LocalDate to) {
		put(prompt);
		LocalDate res = generator.getRandomDate(from, to);
		put(res.format(DateTimeFormatter.ofPattern(format)));
		return res;
	}

	@Override
	public LocalDate getDate(String prompt, String format) {
		return getDate(prompt, format, minDate, maxDate);
	}

	@Override
	public String getString(String prompt, String[] from) {
		put(prompt);
		int ind = generator.getRandomInteger(0, from.length);
		put(from[ind]);
		return from[ind];
	}

}
