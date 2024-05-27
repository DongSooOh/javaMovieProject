package movie;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws Exception {
        boolean movie = true;
        int keyCode;

        Scanner scan = new Scanner(System.in);

        System.out.println("영화 예매 프로그램이 실행되었습니다.");

        Movie movies = new Movie();
        Seats seats = new Seats();
        Reservation reservation = new Reservation();
        
        // 파일에서 기존의 영화 및 예약 데이터를 로드합니다.
        movies.loadMoviesFromFile();
        reservation.loadReservationsFromFile();

        MainMenu mainMenu = new MainMenu(reservation, seats, movies);

        movies.defaultMovies();

        while (movie) {

            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("1. 영화 예매하기 | 2. 예매 확인하기 | 3. 예매 취소하기 | 4. 관리자 메뉴로 이동 | 5. 종료");
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("선택: ");

            keyCode = scan.nextInt();
            scan.nextLine();

            switch (keyCode) {
                case 1:
                    mainMenu.reserveMovie();
                    break;
                case 2:
                    mainMenu.showReservationInfo();
                    break;
                case 3:
                    mainMenu.cancelReservation();
                    break;
                case 4:
                    if (loginAdmin(scan)) {
                        System.out.println("관리자 메뉴로 이동합니다.");
                        AdminMenu adminMenu = new AdminMenu();
                        adminMenu.displayMenu();
                    } else {
                        System.out.println("관리자 인증에 실패하셨습니다.");
                    }
                    break;
                case 5:
                    movie = false;
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 1 ~ 5번 중 다시 선택해주십시오.");
            }
        }
        
        // 프로그램 종료 전에 영화 및 예약 데이터를 파일에 저장합니다.
        movies.saveMoviesToFile();
        reservation.saveReservationsToFile();

        System.out.println("프로그램이 종료되었습니다.");
    }

    private static boolean loginAdmin(Scanner scan) {
        System.out.println("관리자 아이디를 입력하세요: ");
        String adminId = scan.nextLine();
        System.out.println("관리자 비밀번호를 입력하세요: ");
        String adminPw = scan.nextLine();

        return adminId.equals("dhehdtn") && adminPw.equals("1q2w3e!@#");
    }
}
