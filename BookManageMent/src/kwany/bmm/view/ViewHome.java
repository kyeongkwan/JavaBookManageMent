package kwany.bmm.view;

import java.util.Scanner;

import kwany.bmm.dao.DaoUser;

public class ViewHome {
	private ViewSignIn viewSignIn;
	private ViewSignUp viewSignUp;
	private DaoUser daoUser;
	private Scanner scan;
	private boolean flagHome;

	public ViewHome() {
		viewSignIn = new ViewSignIn();
		viewSignUp = new ViewSignUp();
		daoUser = new DaoUser();
		scan = new Scanner(System.in);
		flagHome = true;
	}

	// 첫 화면 보여주는 메소드
	public void viewHome() {
		while (flagHome) {
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t도서관리 프로그램");
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t1.로그인");
			System.out.println("\t2.회원가입");
			System.out.println("\t0.프로그램 종료");
			System.out.println("─────────────────────────────────────────");
			System.out.print("메뉴선택 : ");
			switch (scan.next()) {
			case "1":
				viewSignIn.viewSignIn(); // 로그인 화면
				break;
			case "2":
				viewSignUp.viewSignUp(); // 회원가입 화면
				break;
			case "0":
				flagHome = false;
				daoUser.closeProgram();
				System.out.println("프로그램 종료");
				break;
			default:
				System.out.println("메뉴에 해당된 번호만 입력하세요.");
				continue;
			}
		}
	}
}