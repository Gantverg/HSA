package tel_ran.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public interface InputOutput {
String WRONG_INPUT = "Wrong Input: ";
String getString(String prompt);
default String getString(String prompt,int minLength, int maxLength){
	int length;
	String res="";
	do {
		res=getString(prompt);
		length=res.length();
		prompt="Wrong string length";
		
	}while(length < minLength || length >= maxLength);
	return res;
}
default Integer getInteger(String prompt){
	do {
		String resStr=getString(prompt);
		try {
			Integer res=Integer.parseInt(resStr);
			return res;
		}catch (Exception e){
			prompt=WRONG_INPUT+"no number";
		}
	}while(true);
}
default String getString(String prompt, Predicate<String>predicate){
	String res="";
	do {
		res=getString(prompt);
		prompt=WRONG_INPUT;
	}while(!predicate.test(res));
	return res;
}
default String getString(String prompt, String[]from){
	List<String> strings=Arrays.asList(from);
	return getString(prompt,new PredicateStrings(strings));
}
default Integer getInteger(String prompt, Integer minValue,Integer maxValue){
	Integer res=0;
	do {
		res=getInteger(prompt);
		prompt=WRONG_INPUT+"no in range";
	}while(res<minValue || res >= maxValue);
	return res;
}
default Integer getInteger(String prompt,Predicate<Integer>predicate){
	Integer res=0;
	do {
		res=getInteger(prompt);
		prompt=WRONG_INPUT;
	}while(!predicate.test(res));
	return res;
}
default Number getNumber(String prompt){
	do {
		String resStr=getString(prompt);
		try {
			Double res=Double.parseDouble(resStr);
			return res;
		}catch (Exception e){
			prompt=WRONG_INPUT+"no number";
		}
	}while(true);
}
default Number getNumber(String prompt, Number minValue,Number maxValue){
	Double res=0.0;
	do {
		res=getNumber(prompt).doubleValue();
		prompt=WRONG_INPUT+"no in range";
	}while(res<minValue.doubleValue() || res >= maxValue.doubleValue());
	return res;
}
default Number getNumber(String prompt,Predicate<Number>predicate){
	Number res=0.0;
	do {
		res=getNumber(prompt);
		prompt=WRONG_INPUT;
	}while(!predicate.test(res));
	return res;
}
default LocalDate getDate(String prompt,String format){
	String resStr="";
	DateTimeFormatter df=DateTimeFormatter.ofPattern(format);
	do {
		resStr=getString(prompt);
		try {
			LocalDate resDate=LocalDate.parse(resStr, df);
			return resDate;
		} catch (Exception e) {
			prompt=WRONG_INPUT+"no mathces to the format "+format;
		}
		
	}while(true);
}
default LocalDate getDate(String prompt,String format,LocalDate fromInclusive,LocalDate toExclusive){
	LocalDate res=null;
	do {
		res=getDate(prompt,format);
		prompt=WRONG_INPUT;
	}while(res.isBefore(fromInclusive)||res.isAfter(toExclusive.minusDays(1)));
	return res;
}
void put(Object object);
}
