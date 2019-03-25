package kwany.bmm.view.user;

import java.util.ArrayList;
import java.util.Scanner;

import kwany.bmm.dao.DaoBook;
import kwany.bmm.model.ModelBook;

public class ViewSearchBook {
	// TODO 통합검색이 기본. 리스트의 순서대로 상세검색 가능. 키워드별로 필터링
	private final String SEARCH_NEW = "new";	//신간도서검색 검색어
	private final String SEARCH_BEST = "best";	//인기도서검색 검색어
	private ArrayList<ModelBook> bookList;		//검색결과 출력용 리스트
	private DaoBook daoBook;
	private Scanner scan;
	private StringBuilder sb;
	private String search;
	private boolean flagSearchBook;
	
	public ViewSearchBook() {
		bookList = new ArrayList<ModelBook>();
		daoBook = new DaoBook();
		scan = new Scanner(System.in);
		sb = new StringBuilder();
		search = null;
	}
	// TODO 검색어를 어레이리스트에 담아서, 사이즈만큼 select문을 반복 시켜 어레이 리스트에 저장, 결과 출력
	public void viewSearchBook() {
		flagSearchBook = true;
		while (flagSearchBook) {
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t도서검색 화면");
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t베스트 셀러 1");
			System.out.println("\t베스트 셀러 2");
			System.out.println("\t베스트 셀러 3");
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t1. 도서검색");
			System.out.println("\t0. 이전화면");
			System.out.println("─────────────────────────────────────────");
			System.out.println("메뉴선택 : ");
			switch (scan.next()) {
			case "1": // 신간도서검색
				searchBook();
				break;
			case "0": // 로그아웃
				flagSearchBook = false;
				break;
			default:
				System.out.println("메뉴에 해당된 번호만 입력하세요.");
				continue;
			}
		}
	}

	// 신간도서검색
	private void searchBook() {
		bookList = daoBook.searchBook();
		System.out.println("─────────────────────────────────────────");
		System.out.println("\t신간도서 검색 화면");
		System.out.println("─────────────────────────────────────────");
		
	}
	
}
