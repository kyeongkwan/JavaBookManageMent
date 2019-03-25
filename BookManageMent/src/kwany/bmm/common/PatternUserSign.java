package kwany.bmm.common;

import java.util.regex.Pattern;

public class PatternUserSign {
	protected Pattern patternId;
	protected Pattern patternPwd;
	protected Pattern patternName;
	protected Pattern patternBirth;
	protected Pattern patternPhone;
	protected Pattern patternEmail;

	public PatternUserSign() {
		// 공백이 아닌 문자가 5~20개가 있는지 확인
		patternId = Pattern.compile("^[\\S]{5,20}$");
		patternPwd = Pattern.compile("^[\\S]{5,20}$");
		// 숫자가 아닌 문자가 2~10개가 있는지 확인
		patternName = Pattern.compile("^(\\D){2,10}$");
		// (19xx또는20xx)-(0x또는1x)-(0x또는1x또는2x또는3x) = yyyy-MM-dd
		patternBirth = Pattern.compile("^(19[\\d]{2}|20[\\d]{2})-(0[1-9]|1[0-2])-(0[1-9]|1[\\d]|2[\\d]|3[0-1])$");
		// 01(016789중하나)(숫자 3~4자)(숫자4자) = 01012345678 or 0101234567
		patternPhone = Pattern.compile("^(01([016789]))([\\d]{3,4})([\\d]{4})$");
		patternEmail = Pattern.compile("^[\\w][\\w\\_\\-\\.\\+]+[\\w]@[\\w][\\w\\_\\-]*[\\w](\\.[a-zA-Z]{2,6}){1,2}$");
	}
}
