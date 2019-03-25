package kwany.bmm.view.user;

import java.util.Scanner;

public class ViewHomeUser {
	public static String USER_ID;
	private ViewSearchBook viewSearchBook;
	private ViewMyPage viewMyPage;
	private Scanner scan;
	private boolean flagHomeUser;

	public ViewHomeUser(String USER_ID) {
		ViewHomeUser.USER_ID = USER_ID;
		viewSearchBook = new ViewSearchBook();
		viewMyPage = new ViewMyPage(USER_ID);
		scan = new Scanner(System.in);
	}

	public void viewHomeUser() {
		flagHomeUser = true;
		while (flagHomeUser) {
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t'" + USER_ID + "'님이 사용중입니다.");
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t1. 도서　검색");
			System.out.println("\t2. 도서　대여");
			System.out.println("\t3. 도서　반납");
			System.out.println("\t4. 대여　현황");
			System.out.println("\t5. 마이페이지");
			System.out.println("\t0. 로그　아웃");
			System.out.println("─────────────────────────────────────────");
			System.out.println("메뉴선택 : ");
			switch (scan.next()) {
			case "1": // 도서검색
				viewSearchBook.viewSearchBook();
				break;
			case "2": // 도서대여
				break;
			case "3": // 도서반납
				break;
			case "4": // 대여현황
				break;
			case "5": // 마이페이지
				viewMyPage.viewMyPage();
				break;
			case "0": // 로그아웃
				flagHomeUser = false;
				System.out.println("정상적으로 로그아웃 했습니다.");
				break;
			default:
				System.out.println("메뉴에 해당된 번호만 입력하세요.");
				continue;
			}
		}
	}
}
