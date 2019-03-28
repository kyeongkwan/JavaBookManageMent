package kwany.bmm.view.admin;

import java.util.Scanner;

public class ViewHomeAdmin {
	private Scanner scan;
	private boolean flagHomeAdmin;
	private ViewBookManagement viewBookManagement;
	
	public ViewHomeAdmin() {
		scan = new Scanner(System.in);
		viewBookManagement = new ViewBookManagement();
	}
	
	// 관리자 홈 화면
	public void viewHomeAdmin() {
		flagHomeAdmin = true;
		while(flagHomeAdmin) {
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t도서관리 프로그램 관리자 모드");
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t1. 도서관리");
			System.out.println("\t2. 회원관리");
			System.out.println("\t0. 로그아웃");
			System.out.println("─────────────────────────────────────────");
			System.out.print("메뉴선택 : ");
			switch (scan.next()) {
			case "1": // 도서관리
				viewBookManagement.viewBookManagement();
				break;
			case "2": // 회원관리
				break;
			case "0":
				flagHomeAdmin = false;
				break;
			default:
				System.out.println("메뉴에 해당된 번호만 입력하세요.");
				continue;
			}
		}
	}
}
