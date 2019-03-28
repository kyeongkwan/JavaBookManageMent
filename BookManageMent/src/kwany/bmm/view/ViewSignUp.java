package kwany.bmm.view;

import java.util.Date;
import java.util.Scanner;

import kwany.bmm.common.ValidateUserSign;
import kwany.bmm.dao.DaoUser;
import kwany.bmm.model.ModelUser;

public class ViewSignUp extends ValidateUserSign {
	private DaoUser daoUser;
	private ModelUser modelUser;
	private Scanner scan;
	private boolean flagSignUp;
	private boolean flagConfirm;
	private int flagCase;

	public ViewSignUp() {
		daoUser = new DaoUser();
		modelUser = new ModelUser();
		scan = new Scanner(System.in);
	}

	// 회원가입 화면
	public void viewSignUp() {
		flagCase = 1;
		flagSignUp = true;
		System.out.println("─────────────────────────────────────────");
		System.out.println("\t회원가입 화면");
		System.out.println("─────────────────────────────────────────");
		while (flagSignUp) {
			switch (flagCase) {
			case 1:
				System.out.print("\t아  이 디 : ");
				id = scan.next();
				if (validate(FIELD_ID, id)) {
					if (!daoUser.confirmUserId(id)) {
						System.out.println("\t\t\t✅");
						modelUser.setId(id);
						flagCase = 2;
						continue;
					} else {
						System.out.println("\t\t\t중복된 아이디가 있습니다. 다른 아이디를 사용하세요.");
						flagCase = 1;
						continue;
					}
				} else {
					System.out.println("\t\t\t아이디는 5~20글자로만 사용가능합니다.");
					flagCase = 1;
					continue;
				}

			case 2:
				System.out.print("\t비밀번호 : ");
				pwd = scan.next();
				if (validate(FIELD_PWD, pwd)) {
					System.out.println("\t\t\t✅");
					modelUser.setPwd(pwd);
					flagCase = 3;
					continue;
				} else {
					System.out.println("\t\t\t비밀번호는 5~20글자로만 사용가능합니다.");
					flagCase = 2;
					continue;
				}

			case 3:
				System.out.print("　　확인용 비밀번호 : ");
				confirmPwd = scan.next();
				if (confirmPwd.equals(modelUser.getPwd())) {
					System.out.println("\t\t\t✅");
					flagCase = 4;
					continue;
				} else {
					System.out.println("\t\t\t비밀번호가 일치하지 않습니다.");
					flagCase = 3;
					continue;
				}

			case 4:
				System.out.print("\t이　　름 : ");
				name = scan.next();
				if (validate(FIELD_NAME, name)) {
					System.out.println("\t\t\t✅");
					modelUser.setName(name);
					flagCase = 5;
					continue;
				} else {
					System.out.println("\t\t\t정확한 이름을 입력하세요.");
					flagCase = 4;
					continue;
				}

			case 5:
				System.out.print("\t생년월일 : ");
				birth = scan.next();
				if (validate(FIELD_BIRTH, birth)) {
					if (birthDate.getTime() - new Date().getTime() > 0) {
						System.out.println("\t\t\t정확한 생년월일을 입력하세요.");
						flagCase = 5;
						continue;
					} else if (minDate.getTime() - birthDate.getTime() > 0) {
						System.out.println("\t\t\t정확한 생년월일을 입력하세요.");
						flagCase = 5;
						continue;
					} else {
						System.out.println("\t\t\t✅");
						modelUser.setBirth(birth);
						flagCase = 6;
						continue;
					}
				} else {
					System.out.println("\t\t\t정확한 생년월일을 입력하세요.");
					flagCase = 5;
					continue;
				}

			case 6:
				System.out.print("\t성　　별 : ");
				gender = scan.next();
				if (gender.equals("남자") || gender.equals("여자")) {
					System.out.println("\t\t\t✅");
					modelUser.setGender(gender);
					flagCase = 7;
					continue;
				} else {
					System.out.println("\t\t\t'남자' or '여자'만 입력하세요.");
					flagCase = 6;
					continue;
				}

			case 7:
				System.out.print("\t연  락 처 : ");
				phone = scan.next();
				if (validate(FIELD_PHONE, phone)) {
					System.out.println("\t\t\t✅");
					modelUser.setPhone(phone);
					flagCase = 8;
					continue;
				} else {
					System.out.println("\t\t\t'01012345678'형식으로 입력하세요.");
					flagCase = 7;
					continue;
				}

			case 8:
				System.out.print("\t이  메 일 : ");
				email = scan.next();
				if (validate(FIELD_EMAIL, email)) {
					System.out.println("\t\t\t✅");
					modelUser.setEmail(email);
					confirmSignUp();
					break;
				} else {
					System.out.println("\t\t\t정확한 이메일을 입력하세요.");
					flagCase = 8;
					continue;
				}
			}
		}
	}

	// 회원가입 내용확인화면
	private void confirmSignUp() {
		flagConfirm = true;
		while (flagConfirm) {
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t입력된 내용을 확인해주세요.");
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t아  이 디 : " + modelUser.getId());
			System.out.println("\t비밀번호 : " + modelUser.getPwd());
			System.out.println("\t이　　름 : " + modelUser.getName());
			System.out.println("\t생년월일 : " + modelUser.getBirth());
			System.out.println("\t연  락 처 : " + modelUser.getPhone());
			System.out.println("\t성　　별 : " + modelUser.getGender());
			System.out.println("\t이  메 일 : " + modelUser.getEmail());
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t입력된 내용으로 회원가입을 완료하시겠습니까?");
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t0. 아니오\t1. 예");
			System.out.println("─────────────────────────────────────────");
			System.out.print("메뉴 선택 : ");
			switch (scan.next()) {
			case "0":
				resultSignUp(null);
				flagConfirm = false;
				flagSignUp = false;
				break;

			case "1":
				resultSignUp(daoUser.doSignUp(modelUser));
				flagConfirm = false;
				flagSignUp = false;
				break;

			default:
				System.out.println("메뉴에 해당된 번호만 입력하세요.");
				continue;
			}
		}
	}

	// 회원가입 결과화면
	private void resultSignUp(ModelUser modelUser) {
		if (modelUser == null) {
			System.out.println("회원가입을 중단했습니다.");
		} else {
			System.out.println("회원가입을 완료했습니다.");
		}
	}
}
