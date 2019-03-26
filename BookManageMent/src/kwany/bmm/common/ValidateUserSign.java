package kwany.bmm.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUserSign {
	protected final String FIELD_ID = "id";
	protected final String FIELD_PWD = "pwd";
	protected final String FIELD_NAME = "name";
	protected final String FIELD_BIRTH = "birth";
	protected final String FIELD_GENDER = "gender";
	protected final String FIELD_PHONE = "phone";
	protected final String FIELD_EMAIL = "email";
	protected String id;
	protected String pwd;
	protected String confirmPwd;
	protected String name;
	protected String birth;
	protected String gender;
	protected String phone;
	protected String email;
	protected Pattern patternId;
	protected Pattern patternPwd;
	protected Pattern patternName;
	protected Pattern patternBirth;
	protected Pattern patternPhone;
	protected Pattern patternEmail;
	protected Matcher matcherId;
	protected Matcher matcherPwd;
	protected Matcher matcherName;
	protected Matcher matcherBirth;
	protected Matcher matcherPhone;
	protected Matcher matcherEmail;
	protected Date birthDate;
	protected Date minDate;

	public ValidateUserSign() {
		// 공백이 아닌 문자가 5~20개가 있는지 확인
		patternId = Pattern.compile("^[\\S]{5,20}$");
		patternPwd = Pattern.compile("^[\\S]{5,20}$");
		// 숫자가 아닌 문자가 2~10개가 있는지 확인
		patternName = Pattern.compile("^(\\D){2,10}$");
		// (19xx또는20xx)-(0x또는1x)-(0x또는1x또는2x또는3x) = yyyy-MM-dd
		patternBirth = Pattern.compile("^(19[\\d]{2}|20[\\d]{2})-(0[1-9]|1[0-2])-(0[1-9]|1[\\d]|2[\\d]|3[0-1])$");
		// 01(016789중하나)(숫자 3~4자)(숫자4자) = 01012345678 or 0101234567
		patternPhone = Pattern.compile("^(01([016789]))([\\d]{3,4})([\\d]{4})$");
		// (문자)(문자_-)(문자)@(문자)(문자_-)(문자)(.문자)
		patternEmail = Pattern.compile("^[\\w][\\w\\_\\-\\.\\+]+[\\w]@[\\w][\\w\\_\\-]*[\\w](\\.[a-zA-Z]{2,6}){1,2}$");	
		try {
			minDate = new SimpleDateFormat("yyyy-DD-mm").parse("1900-01-01");
		} catch (ParseException e) {
			System.out.println("생년월일 유효성 세팅중 오류");
		}
	}
	
	public boolean validate(String field, String value) {
		switch(field) {
		case FIELD_ID:
			matcherId = patternId.matcher(value);
			return matcherId.find();
		case FIELD_PWD:
			matcherPwd = patternPwd.matcher(value);
			return matcherPwd.find();
		case FIELD_NAME:
			matcherName = patternName.matcher(value);
			return matcherName.find();
		case FIELD_BIRTH:
			try {
				birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
			} catch (ParseException e) {
				return false;
			}
			matcherBirth = patternBirth.matcher(value);
			return matcherBirth.find();
		case FIELD_PHONE:
			matcherPhone = patternPhone.matcher(value);
			return matcherPhone.find();
		case FIELD_EMAIL:
			matcherEmail = patternEmail.matcher(value);
			return matcherEmail.find();
		}
		return false;
	}
}
