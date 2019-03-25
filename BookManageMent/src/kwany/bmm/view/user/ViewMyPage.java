package kwany.bmm.view.user;

import java.util.Scanner;
import java.util.regex.Matcher;

import kwany.bmm.common.PatternUserSign;
import kwany.bmm.dao.DaoUser;
import kwany.bmm.model.ModelUser;

public class ViewMyPage extends PatternUserSign{
	public static String USER_ID;
	private DaoUser daoUser;
	private ModelUser modelUser;
	private Scanner scan;
	private boolean flagMyPage;			// 마이페이지 화면 출력 판단
	private boolean flagConfirm;		// 정보변경 비밀번호 확인 화면 출력 판단
	private boolean flagRetryConfirm;	// 정보변경 비밀번호 재시도 화면 출력 판단
	private boolean flagSelect;			// 정보변경 항목 선택 화면 출력 판단
	private boolean flagUpdate;			// 항목별 변경 화면 출력 판단 

	public ViewMyPage(String id) {
		ViewMyPage.USER_ID = id;
		daoUser = new DaoUser();
		modelUser = new ModelUser();
		scan = new Scanner(System.in);
	}

	// 마이페이지 화면
	public void viewMyPage() {
		flagMyPage = true;
		while (flagMyPage) {
			modelUser = daoUser.getMyPage(USER_ID);
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t'" + USER_ID + "'의 정보입니다.");
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t아  이 디 : " + modelUser.getId());
			System.out.println("\t이　　름 : " + modelUser.getName());
			System.out.println("\t생년월일 : " + modelUser.getBirth());
			System.out.println("\t성　　별 : " + modelUser.getGender());
			System.out.println("\t연  락 처 : " + modelUser.getPhone());
			System.out.println("\t이  메 일 : " + modelUser.getEmail());
			System.out.println("\t등　　급 : " + modelUser.getGrade());
			System.out.println("\t포  인 트 : " + modelUser.getPoint());
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t0. 이전화면 \t1. 정보변경");
			System.out.println("─────────────────────────────────────────");
			System.out.print("메뉴 선택 : ");
			switch (scan.next()) {
			case "0":
				flagMyPage = false;
				break;

			case "1":
				viewConfirmPwd();
				break;

			default:
				System.out.println("메뉴에 해당된 번호만 입력하세요.");
				continue;
			}
		}
	}

	// 정보변경 비밀번호 확인 화면
	public void viewConfirmPwd() {
		flagConfirm = true;
		String pwd = null;
		while (flagConfirm) {
			System.out.println("─────────────────────────────────────────");
			System.out.println("'" + USER_ID + "'님의 정보변경을 위한 비밀번호 확인");
			System.out.println("─────────────────────────────────────────");
			System.out.print("\t비밀번호 : ");
			pwd = scan.next();
			modelUser = daoUser.confirmPwd(USER_ID, pwd);
			if (modelUser != null) {
				selectUpdateMyInfo();
			} else {
				retryConfirmPwd();
				continue;
			}
		}

	}

	// 정보변경 항목 선택 화면
	private void selectUpdateMyInfo() {
		flagSelect = true;
		while (flagSelect) {
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t변경할 항목을 선택하세요.");
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t1. 비밀번호");
			System.out.println("\t2. 이　　름");
			System.out.println("\t3. 생년월일");
			System.out.println("\t4. 성　　별");
			System.out.println("\t5. 연  락 처");
			System.out.println("\t6. 이  메 일");
			System.out.println("\t0. 이전화면");
			System.out.println("─────────────────────────────────────────");
			System.out.print("메뉴 선택 : ");
			switch (scan.next()) {
			case "1":
				updatePwd();
				break;
			case "2":
				updateName();
				break;
			case "3":
				updateBirth();
				break;
			case "4":
				updateGender();
				break;
			case "5":
				updatePhone();
				break;
			case "6":
				updateEmail();
				break;
			case "0":
				flagSelect = false;
				flagConfirm = false;
				break;

			default:
				System.out.println("메뉴에 해당된 번호만 입력하세요.");
				continue;
			}
		}
	}

	// 비밀번호 변경 화면
	private void updatePwd() {
		flagUpdate = true;
		while (flagUpdate) {
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t변경할 비밀번호를 입력하세요.");
			System.out.println("─────────────────────────────────────────");
			System.out.print("\t 비밀번호 : ");
			String pwd = scan.next();
			System.out.print("\t 비밀번호 확인 : ");
			String confirmPwd = scan.next();
			Matcher pwd_matcher = patternPwd.matcher(pwd);
			Matcher confirmPwd_matcher = patternPwd.matcher(confirmPwd);
			if (pwd_matcher.find() && confirmPwd_matcher.find() && pwd.equals(confirmPwd)) {
				daoUser.updateUserPwd(USER_ID, pwd);
				modelUser.setPwd(pwd);
				flagUpdate = false;
				System.out.println("\t비밀번호 변경 완료");
				break;
			} else {
				System.out.println("비밀번호가 일치하지 않거나 5~20글자로 입력하지 않았습니다.");
				continue;
			}
		}

	}

	// 이름 변경 화면
	private void updateName() {
		flagUpdate = true;
		while (flagUpdate) {
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t변경할 이름을 입력하세요.");
			System.out.println("─────────────────────────────────────────");
			System.out.print("\t 이름 : ");
			String name = scan.next();
			Matcher name_matcher = patternName.matcher(name);
			if (name_matcher.find()) {
				daoUser.updateUserName(USER_ID, name);
				modelUser.setName(name);
				flagUpdate = false;
				System.out.println("\t이름 변경 완료");
				break;
			} else {
				System.out.println("정확한 이름을 입력하세요.");
				continue;
			}
		}
	}

	// 생년월일 변경 화면
	private void updateBirth() {
		// TODO Auto-generated method stub

	}

// 성별 변경 화면
	private void updateGender() {
		// TODO Auto-generated method stub

	}

	// 연락처 변경 화면
	private void updatePhone() {
		flagUpdate = true;
		while (flagUpdate) {
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t변경할 연락처를 입력하세요.");
			System.out.println("─────────────────────────────────────────");
			System.out.print("\t연  락 처 : ");
			String phone = scan.next();
			Matcher phone_matcher = patternPhone.matcher(phone);
			if (phone_matcher.find()) {
				daoUser.updateUserPhone(USER_ID, phone);
				modelUser.setPhone(phone);
				flagUpdate = false;
				System.out.println("\t연락처 변경 완료");
				break;
			} else {
				System.out.println("연락처 11자리를 정확히 입력하세요.");
				continue;
			}
		}
	}

	// 이메일 변경 화면
	private void updateEmail() {
		// TODO Auto-generated method stub

	}
	
	// 정보변경 재시도 화면
	private void retryConfirmPwd() {
		flagRetryConfirm = true;
		while (flagRetryConfirm) {
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t비밀번호가 일치하지 않습니다.");
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t0. 이전화면 \t1.재시도");
			System.out.println("─────────────────────────────────────────");
			System.out.print("메뉴 입력 : ");
			switch (scan.next()) {
			case "0":
				flagRetryConfirm = false;
				flagConfirm = false;
				break;

			case "1":
				flagRetryConfirm = false;
				break;

			default:
				System.out.println("메뉴에 해당된 번호만 입력하세요.");
				continue;
			}
		}
	}
}
