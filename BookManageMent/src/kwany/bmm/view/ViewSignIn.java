package kwany.bmm.view;

import java.util.Scanner;

import kwany.bmm.dao.DaoUser;
import kwany.bmm.view.user.ViewHomeUser;

public class ViewSignIn {
	private DaoUser daoUser;
	private ViewHomeUser viewHomeUser;
	private Scanner scan;
	private boolean flagSignIn;
	private boolean flagRetry;

	public ViewSignIn() {
		daoUser = new DaoUser();
		scan = new Scanner(System.in);
	}

	// 로그인 화면
	public void viewSignIn() {
		flagSignIn = true;
		while (flagSignIn) {
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t로그인 화면");
			System.out.println("─────────────────────────────────────────");
			System.out.print("\t아 이 디: ");
			String id = scan.next();
			System.out.print("\t비밀번호: ");
			String pwd = scan.next();
			if (daoUser.doSignIn(id, pwd)) {
				flagSignIn = false;
				viewHomeUser = new ViewHomeUser(id);
				viewHomeUser.viewHomeUser();
				break;
			} else {
				retrySignIn();
			}
		}
	}

	// 로그인 재시도
	private void retrySignIn() {
		flagRetry = true;
		while (flagRetry) {
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t아이디 또는 비밀번호가 일치하지 않습니다.");
			System.out.println("\t다시 로그인하시겠습니까?");
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t0. 아니오\t1. 예");
			System.out.println("─────────────────────────────────────────");
			System.out.print("메뉴 선택 : ");
			switch (scan.next()) {
			case "0":
				flagRetry = false;
				flagSignIn = false;
				break;
				
			case "1":
				flagRetry = false;
				break;

			default:
				System.out.println("메뉴에 해당된 번호만 입력하세요.");
				continue;
			}
		}

	}
}
