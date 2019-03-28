package kwany.bmm.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateBookInfo {
	protected final String FIELD_NUMBER = "number";
	protected final String FIELD_TITLE = "title";
	protected final String FIELD_AUTHOR = "author";
	protected final String FIELD_PUBLISHER = "publisher";
	protected final String FIELD_PUBDATE = "pubDate";
	protected final String FIELD_GENRE = "genre";
	protected String number;
	protected String title;
	protected String author;
	protected String publisher;
	protected String pubDate;
	protected String genre;
	protected Pattern patternNumber;
	protected Pattern patternTitle;
	protected Pattern patternAuthor;
	protected Pattern patternPublisher;
	protected Pattern patternPupDate;
	protected Matcher matcherNumber;
	protected Matcher matcherTitle;
	protected Matcher matcherAuthor;
	protected Matcher matcherPublisher;
	protected Matcher matcherPupDate;
	protected Date inputDate;
	protected ArrayList<String> genreList;

	public ValidateBookInfo() {
		// 6자리 숫자만 허용
		patternNumber = Pattern.compile("^[\\d]{6}$");
		// 공백이 아닌 1~20문자 
		patternTitle = Pattern.compile("^[\\S]{1,20}$");
		patternAuthor = Pattern.compile("^[\\S]{1,20}$");
		patternPublisher = Pattern.compile("^[\\S]{1,20}$");
		// yyyy-MM-dd
		patternPupDate = Pattern.compile("^(1[\\d]{3}|2[\\d]{3})-(0[1-9]|1[0-2])-(0[1-9]|1[\\d]|2[\\d]|3[0-1])$");
		genreList = new ArrayList<String>();
		genreList.add("일반");genreList.add("만화");genreList.add("소설");genreList.add("참고서");genreList.add("성인");
	}
	
	public boolean validate(String field, String value) {
		switch(field) {
		case FIELD_NUMBER:
			matcherNumber = patternNumber.matcher(value);
			return matcherNumber.find();
		case FIELD_TITLE:
			matcherTitle = patternTitle.matcher(value);
			return matcherTitle.find();
		case FIELD_AUTHOR:
			matcherAuthor = patternAuthor.matcher(value);
			return matcherAuthor.find();
		case FIELD_PUBLISHER:
			matcherPublisher = patternPublisher.matcher(value);
			return matcherPublisher.find();
		case FIELD_PUBDATE:
			try {
				inputDate = new SimpleDateFormat("yyyy-MM-dd").parse(value);
			} catch (ParseException e) {
				return false;
			}
			matcherPupDate = patternPupDate.matcher(value);
			return matcherPupDate.find();
		}
		return false;
	}
}
