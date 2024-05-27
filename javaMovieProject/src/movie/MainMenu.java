package movie;

import java.util.List;
import java.util.Scanner;

// 메인 메뉴의 입출력에 따른 처리를 담당
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
			System.out.println("1. 좌석 예약하기");
			System.out.println("2. 좌석 정보보기");
			System.out.println("3. 예매 취소하기");
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
					System.out.println("잘못된 선택입니다. 1 ~ 4번 중 선택해주세요.");
			}
		}
	}
	
	public void reserveSeat() {
	    System.out.println("영화를 선택해주세요.: ");
	    List<String> movieList = movies.getAllMovies();
	    for (int i = 0; i < movieList.size(); i++) {
	        System.out.println((i + 1) + ". " + movieList.get(i));
	    }
	    
	    int movieIndex;
	    
	    while (true) {
	        try {
	            System.out.println("선택: ");
	            movieIndex = scan.nextInt();
	            scan.nextLine();
	            break;
	        } catch (Exception e) {
	            System.out.println("잘못된 입력입니다. 정수를 입력해주세요.");
	            scan.nextLine();
	        }
	    }
	    if (movieIndex < 1 || movieIndex > movieList.size()) {
	        System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
	        return;
	    }
	    
	    String selectedMovie = movieList.get(movieIndex - 1);
	    long currentTimeInMilliseconds = System.currentTimeMillis();
	    System.out.println("선택하신 영화: " + selectedMovie);
	    System.out.println("발급번호: " + currentTimeInMilliseconds);
	    
	    addReservation(selectedMovie);
	}
	
	public void addReservation(String movieName) {
        System.out.println("좌석을 선택해주세요.: ");
        String seat = scan.nextLine();
        
        if (!reservation.isSeatReserved(seat)) { // 이미 예약된 좌석인지 확인
            reservation.addReservation(seat, movieName);
        } else {
            System.out.println("이미 예약된 좌석입니다.");
        }
    }

	
	public void showSeatInfo() {
		System.out.println("좌석 정보를 표시합니다.");
		seats.displaySeats();
	}
	
	public void showReservationInfo() {
		System.out.println("예매 정보를 표시합니다.");
		reservation.displayReservation();
	}
	
	public void cancelReservation() {
		System.out.println("예약을 취소합니다. 좌석 번호를 입력해주세요.: ");
		String seatString = scan.nextLine();
		
		String[] seat = seatString.split("-");
		if (seat.length != 2) {
			System.out.println("잘못된 좌석 형식입니다. 다시 입력해주세요.");
			return;
		}
		
		String row = seat[0];
		int cancelSeatNumber;
		try {
			cancelSeatNumber = Integer.parseInt(seat[1]);
		} catch (NumberFormatException e) {
			System.out.println("잘못된 좌석 번호입니다. 다시 입력해주세요.");
			return;
		}
		
		String cancelSeatString = row + "-" + cancelSeatNumber;
		if (reservation.removeReservation(cancelSeatString)) {
			System.out.println("예약이 취소되었습니다.");
		} else {
			System.out.println("해당 좌석에 예약이 없습니다.");
		}
	}
	
	public void reserveMovie() {
		System.out.println("영화를 선택해주세요.: ");
		List<String> movieList = movies.getAllMovies();
		for (int i = 0; i < movieList.size(); i++) {
			System.out.println((i + 1) + ". " + movieList.get(i));
		}
		
		int movieIndex;
		
		while (true) {
			try {
				System.out.println("선택: ");
				movieIndex = scan.nextInt();
				scan.nextLine();
				break;
			} catch (Exception e) {
				System.out.println("잘못된 입력입니다. 정수를 입력해주세요.");
				scan.nextLine();
			}
		}
		if (movieIndex < 1 || movieIndex > movieList.size()) {
			System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
			return;
		}
		
		String selectedMovie = movieList.get(movieIndex - 1);
		long currentTimeInMilliseconds = System.currentTimeMillis();
		System.out.println("선택하신 영화: " + selectedMovie);
		System.out.println("발급번호: " + currentTimeInMilliseconds);
		
		addReservation(selectedMovie);
	}
}
