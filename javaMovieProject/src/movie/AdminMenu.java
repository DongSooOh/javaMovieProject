package movie;

import java.util.*;

// 관리자 메뉴의 출력과 입력에 따른 처리를 담당
public class AdminMenu extends AbstractMenu {
	private static Movie movie = new Movie();
	
	private Scanner scan = new Scanner(System.in);
	
	public AdminMenu() {
		movie.defaultMovies();
	}
	
	@Override
	public void displayMenu() {
		boolean run = true;
		while (run) {
			System.out.println("1. 영화 등록하기");
			System.out.println("2. 영화 목록보기");
			System.out.println("3. 영화 삭제하기");
			System.out.println("4. 메인 메뉴로 이동");
			
			int select;
			while (true) {
				try {
					System.out.println("선택: ");
					select = scan.nextInt();
					scan.nextLine();
					break;
				} catch (Exception e) {
					System.out.println("잘못된 입력입니다. 정수를 입력해주세요.");
					scan.nextLine();
				}
			}			
			
			switch (select) {
				case 1:
					addMovie();
					break;
				case 2:
					showMovieList();
					break;
				case 3:
					deleteMovie();
					break;
				case 4:
					run = false;
					break;
				default:
					System.out.println("잘못된 선택입니다. 1 ~ 4번 중 선택해주세요.");
			}
		}
	}
	
	private void addMovie() {
	    System.out.println("영화 제목을 입력하세요.: ");
	    String movieName = scan.nextLine();
	    System.out.println("영화 장르를 입력하세요.: ");
	    String movieGenre = scan.nextLine();
	    movie.addMovie(movieName, movieGenre);
	    System.out.println("영화가 성공적으로 등록되었습니다.");
	}
	
	private void showMovieList() {
	    System.out.println("등록된 영화 목록: ");
	    List<String> allMovies = movie.getAllMovies(); 
	    if (allMovies.isEmpty()) {
	        System.out.println("등록된 영화가 없습니다.");
	    } else {
	        for (String movieName : allMovies) {
	            System.out.println(movieName);
	        }
	    }
	}
	
	private void deleteMovie() {
		System.out.println("삭제할 영화 제목을 입력하세요.: ");
		String movieName = scan.nextLine();
		if (movie.containsMovie(movieName)) {
			movie.removeMovie(movieName);
			System.out.println("영화가 성공적으로 삭제되었습니다.");
		} else {
			System.out.println("해당 영화가 등록되어 있지 않습니다.");
		}
	}
}