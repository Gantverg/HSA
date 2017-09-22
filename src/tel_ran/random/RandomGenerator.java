package tel_ran.random;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class RandomGenerator {
	private Random gen = new Random();
	
	public Integer getRandomInteger(Integer min, Integer max){
		return min + gen.nextInt(max-min);
	}
	
	public Long getRandomLong(Long min, Long max){//nextLong не дает все значения Long
		return getRandomDouble(min.doubleValue(), max.doubleValue()).longValue();
	}

	public Double getRandomDouble(Double min, Double max){
		return min + gen.nextDouble()*(max-min);
	}

	public Float getRandomFloat(Float min, Float max){
		return min + gen.nextFloat()*(max-min);
	}
	
	public String getRandomAsciiString(int minLength, int maxLength){
		//String contains lower case ascii letters
		if(maxLength<=minLength){
			return "";
		}
		int length = getRandomInteger(minLength, maxLength);
		byte[] arr = new byte[length];
		for(int i = 0; i < length; i++){
			arr[i] = getRandomInteger('a'+0, 'z'+1).byteValue();
		}
		return new String(arr);
	}
	
	public LocalDate getRandomDate(LocalDate minDate, LocalDate maxDate){
		int period = gen.nextInt((int)ChronoUnit.DAYS.between(minDate, maxDate));
		return minDate.plusDays(period);
	}
}
