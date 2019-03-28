package kwany.bmm.view.admin;

import java.util.Date;
import java.util.Scanner;

import kwany.bmm.common.ValidateBookInfo;
import kwany.bmm.dao.DaoBook;
import kwany.bmm.model.ModelBook;

public class ViewBookManagement extends ValidateBookInfo {
	private Scanner scan;
	private DaoBook daoBook;
	private ModelBook modelBook;
	private boolean flagBookManagement;
	private boolean flagAddBook;
	private boolean flagConfirm;
	private int flagCase;

	public ViewBookManagement() {
		scan = new Scanner(System.in);
		daoBook = new DaoBook();
	}

	// 도서관리 화면
	public void viewBookManagement() {
		flagBookManagement = true;
		while (flagBookManagement) {
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t도서관리 화면");
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t1. 대여목록");
			System.out.println("\t2. 도서검색");
			System.out.println("\t3. 도서추가");
			System.out.println("\t4. 도서수정");
			System.out.println("\t5. 도서삭제");
			System.out.println("\t0. 이전화면");
			System.out.println("─────────────────────────────────────────");
			System.out.print("메뉴선택 : ");
			switch (scan.next()) {
			case "1": // 대여목록
				break;
			case "2": // 도서검색
				break;
			case "3": // 도서추가
				addBoook();
				break;
			case "4": // 도서수정
				break;
			case "5": // 도서삭제
				break;
			case "0":
				flagBookManagement = false;
				break;
			default:
				System.out.println("메뉴에 해당된 번호만 입력하세요.");
				continue;
			}
		}
	}

	// 도서추가 화면
	private void addBoook() {
		flagCase = 1;
		flagAddBook = true;
		modelBook = new ModelBook();
		System.out.println("─────────────────────────────────────────");
		System.out.println("\t도서추가 화면");
		System.out.println("─────────────────────────────────────────");
		while (flagAddBook) {
			switch (flagCase) {
			case 1:
				System.out.print("\t도서번호 : ");
				number = scan.next();
				if (validate(FIELD_NUMBER, number)) {
					modelBook.setNumber(number);
					System.out.println("\t\t\t✅");
					flagCase = 2;
					continue;
				} else {
					System.out.println("정확한 도서번호를 입력하세요.");
					flagCase = 1;
					continue;
				}

			case 2:
				System.out.print("\t제　　목 : ");
				title = scan.next();
				if (validate(FIELD_TITLE, title)) {
					modelBook.setTitle(title);
					System.out.println("\t\t\t✅");
					flagCase = 3;
					continue;
				} else {
					System.out.println("정확한 제목을 입력하세요.");
					flagCase = 2;
					continue;
				}

			case 3:
				System.out.print("\t작　　가 : ");
				author = scan.next();
				if (validate(FIELD_AUTHOR, author)) {
					modelBook.setAuthor(author);
					System.out.println("\t\t\t✅");
					flagCase = 4;
					continue;
				} else {
					System.out.println("정확한 작가명을 입력하세요.");
					flagCase = 3;
					continue;
				}

			case 4:
				System.out.print("\t출  판 사 : ");
				publisher = scan.next();
				if (validate(FIELD_PUBLISHER, publisher)) {
					modelBook.setPublisher(publisher);
					System.out.println("\t\t\t✅");
					flagCase = 5;
					continue;
				} else {
					System.out.println("정확한 출판사를 입력하세요.");
					flagCase = 4;
					continue;
				}

			case 5:
				System.out.print("\t출  판 일 : ");
				pubDate = scan.next();
				if (validate(FIELD_PUBDATE, pubDate)) {
					if (inputDate.getTime() - new Date().getTime() > 0) {
						System.out.println("정확한 출판일을 입력하세요.");
						flagCase = 5;
						continue;
					} else {
						modelBook.setPubDate(pubDate);
						System.out.println("\t\t\t✅");
						flagCase = 6;
						continue;
					}
				} else {
					System.out.println("정확한 출판일을 입력하세요.");
					flagCase = 5;
					continue;
				}

			case 6:
				System.out.print("\t장　　르 : ");
				genre = scan.next();
				if (genreList.contains(genre)) {
					modelBook.setGenre(genre);
					System.out.println("\t\t\t✅");
					confirmAddBook(modelBook);
					break;
				} else {
					System.out.println("정확한 도서번호를 입력하세요.");
					flagCase = 6;
					continue;
				}
			}
		}
	}

	//도서추가 확인화면
	private void confirmAddBook(ModelBook modelBook) {
		flagConfirm = true;
		while (flagConfirm) {
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t입력된 내용을 확인해주세요.");
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t도서번호 : " + modelBook.getNumber());
			System.out.println("\t도서제목 : " + modelBook.getTitle());
			System.out.println("\t작　　가 : " + modelBook.getAuthor());
			System.out.println("\t출  판 사 : " + modelBook.getPublisher());
			System.out.println("\t출  판 일 : " + modelBook.getPubDate());
			System.out.println("\t장　　르 : " + modelBook.getGenre());
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t입력된 내용으로 도서추가를 완료하시겠습니까?");
			System.out.println("─────────────────────────────────────────");
			System.out.println("\t0. 아니오\t1. 예");
			System.out.println("─────────────────────────────────────────");
			System.out.print("메뉴 선택 : ");
			switch (scan.next()) {
			case "0":
				resultAddBook(null);
				flagConfirm = false;
				flagAddBook = false;
				break;

			case "1":
				resultAddBook(daoBook.doAddBook(modelBook));
				flagConfirm = false;
				flagAddBook = false;
				break;

			default:
				System.out.println("메뉴에 해당된 번호만 입력하세요.");
				continue;
			}
		}
	}

	// 도서추가 결과화면
	private void resultAddBook(ModelBook modelBook) {
		if (modelBook == null) {
			System.out.println("도서추가를 중단했습니다.");
		} else {
			System.out.println("도서추가를 완료했습니다.");
		}
	}
}
