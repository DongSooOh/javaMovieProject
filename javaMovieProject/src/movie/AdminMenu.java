package movie;

import java.util.*;

// ������ �޴��� ��°� �Է¿� ���� ó���� ���
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
			System.out.println("1. ��ȭ ����ϱ�");
			System.out.println("2. ��ȭ ��Ϻ���");
			System.out.println("3. ��ȭ �����ϱ�");
			System.out.println("4. ���� �޴��� �̵�");
			
			int select;
			while (true) {
				try {
					System.out.println("����: ");
					select = scan.nextInt();
					scan.nextLine();
					break;
				} catch (Exception e) {
					System.out.println("�߸��� �Է��Դϴ�. ������ �Է����ּ���.");
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
					System.out.println("�߸��� �����Դϴ�. 1 ~ 4�� �� �������ּ���.");
			}
		}
	}
	
	private void addMovie() {
	    System.out.println("��ȭ ������ �Է��ϼ���.: ");
	    String movieName = scan.nextLine();
	    System.out.println("��ȭ �帣�� �Է��ϼ���.: ");
	    String movieGenre = scan.nextLine();
	    movie.addMovie(movieName, movieGenre);
	    System.out.println("��ȭ�� ���������� ��ϵǾ����ϴ�.");
	}
	
	private void showMovieList() {
	    System.out.println("��ϵ� ��ȭ ���: ");
	    List<String> allMovies = movie.getAllMovies(); 
	    if (allMovies.isEmpty()) {
	        System.out.println("��ϵ� ��ȭ�� �����ϴ�.");
	    } else {
	        for (String movieName : allMovies) {
	            System.out.println(movieName);
	        }
	    }
	}
	
	private void deleteMovie() {
		System.out.println("������ ��ȭ ������ �Է��ϼ���.: ");
		String movieName = scan.nextLine();
		if (movie.containsMovie(movieName)) {
			movie.removeMovie(movieName);
			System.out.println("��ȭ�� ���������� �����Ǿ����ϴ�.");
		} else {
			System.out.println("�ش� ��ȭ�� ��ϵǾ� ���� �ʽ��ϴ�.");
		}
	}
}