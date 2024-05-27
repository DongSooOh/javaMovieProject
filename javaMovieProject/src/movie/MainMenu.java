package movie;

import java.util.List;
import java.util.Scanner;

// ���� �޴��� ����¿� ���� ó���� ���
public class MainMenu extends AbstractMenu {
	private Reservation reservation;
	private Seats seats;
	private Movie movies;
	
	private Scanner scan;
	
	public MainMenu(Reservation reservation, Seats seats, Movie movies) {
		this.reservation = reservation;
		this.seats = seats;
		this.movies = movies;
		this.scan = new Scanner(System.in);
	}

	@Override
	public void displayMenu() {
		boolean run = true;
		while (run) {
			System.out.println("1. �¼� �����ϱ�");
			System.out.println("2. �¼� ��������");
			System.out.println("3. ���� ����ϱ�");
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
					reserveSeat();
					break;
				case 2:
					showSeatInfo();
					break;
				case 3:
					cancelReservation();
					break;
				case 4:
					run = false;
					break;
				default:
					System.out.println("�߸��� �����Դϴ�. 1 ~ 4�� �� �������ּ���.");
			}
		}
	}
	
	public void reserveSeat() {
	    System.out.println("��ȭ�� �������ּ���.: ");
	    List<String> movieList = movies.getAllMovies();
	    for (int i = 0; i < movieList.size(); i++) {
	        System.out.println((i + 1) + ". " + movieList.get(i));
	    }
	    
	    int movieIndex;
	    
	    while (true) {
	        try {
	            System.out.println("����: ");
	            movieIndex = scan.nextInt();
	            scan.nextLine();
	            break;
	        } catch (Exception e) {
	            System.out.println("�߸��� �Է��Դϴ�. ������ �Է����ּ���.");
	            scan.nextLine();
	        }
	    }
	    if (movieIndex < 1 || movieIndex > movieList.size()) {
	        System.out.println("�߸��� �����Դϴ�. �ٽ� �������ּ���.");
	        return;
	    }
	    
	    String selectedMovie = movieList.get(movieIndex - 1);
	    long currentTimeInMilliseconds = System.currentTimeMillis();
	    System.out.println("�����Ͻ� ��ȭ: " + selectedMovie);
	    System.out.println("�߱޹�ȣ: " + currentTimeInMilliseconds);
	    
	    addReservation(selectedMovie);
	}
	
	public void addReservation(String movieName) {
        System.out.println("�¼��� �������ּ���.: ");
        String seat = scan.nextLine();
        
        if (!reservation.isSeatReserved(seat)) { // �̹� ����� �¼����� Ȯ��
            reservation.addReservation(seat, movieName);
        } else {
            System.out.println("�̹� ����� �¼��Դϴ�.");
        }
    }

	
	public void showSeatInfo() {
		System.out.println("�¼� ������ ǥ���մϴ�.");
		seats.displaySeats();
	}
	
	public void showReservationInfo() {
		System.out.println("���� ������ ǥ���մϴ�.");
		reservation.displayReservation();
	}
	
	public void cancelReservation() {
		System.out.println("������ ����մϴ�. �¼� ��ȣ�� �Է����ּ���.: ");
		String seatString = scan.nextLine();
		
		String[] seat = seatString.split("-");
		if (seat.length != 2) {
			System.out.println("�߸��� �¼� �����Դϴ�. �ٽ� �Է����ּ���.");
			return;
		}
		
		String row = seat[0];
		int cancelSeatNumber;
		try {
			cancelSeatNumber = Integer.parseInt(seat[1]);
		} catch (NumberFormatException e) {
			System.out.println("�߸��� �¼� ��ȣ�Դϴ�. �ٽ� �Է����ּ���.");
			return;
		}
		
		String cancelSeatString = row + "-" + cancelSeatNumber;
		if (reservation.removeReservation(cancelSeatString)) {
			System.out.println("������ ��ҵǾ����ϴ�.");
		} else {
			System.out.println("�ش� �¼��� ������ �����ϴ�.");
		}
	}
	
	public void reserveMovie() {
		System.out.println("��ȭ�� �������ּ���.: ");
		List<String> movieList = movies.getAllMovies();
		for (int i = 0; i < movieList.size(); i++) {
			System.out.println((i + 1) + ". " + movieList.get(i));
		}
		
		int movieIndex;
		
		while (true) {
			try {
				System.out.println("����: ");
				movieIndex = scan.nextInt();
				scan.nextLine();
				break;
			} catch (Exception e) {
				System.out.println("�߸��� �Է��Դϴ�. ������ �Է����ּ���.");
				scan.nextLine();
			}
		}
		if (movieIndex < 1 || movieIndex > movieList.size()) {
			System.out.println("�߸��� �����Դϴ�. �ٽ� �������ּ���.");
			return;
		}
		
		String selectedMovie = movieList.get(movieIndex - 1);
		long currentTimeInMilliseconds = System.currentTimeMillis();
		System.out.println("�����Ͻ� ��ȭ: " + selectedMovie);
		System.out.println("�߱޹�ȣ: " + currentTimeInMilliseconds);
		
		addReservation(selectedMovie);
	}
}
