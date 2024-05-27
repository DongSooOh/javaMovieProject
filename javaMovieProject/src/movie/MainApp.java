package movie;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws Exception {
        boolean movie = true;
        int keyCode;

        Scanner scan = new Scanner(System.in);

        System.out.println("��ȭ ���� ���α׷��� ����Ǿ����ϴ�.");

        Movie movies = new Movie();
        Seats seats = new Seats();
        Reservation reservation = new Reservation();
        
        // ���Ͽ��� ������ ��ȭ �� ���� �����͸� �ε��մϴ�.
        movies.loadMoviesFromFile();
        reservation.loadReservationsFromFile();

        MainMenu mainMenu = new MainMenu(reservation, seats, movies);

        movies.defaultMovies();

        while (movie) {

            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("1. ��ȭ �����ϱ� | 2. ���� Ȯ���ϱ� | 3. ���� ����ϱ� | 4. ������ �޴��� �̵� | 5. ����");
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("����: ");

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
                        System.out.println("������ �޴��� �̵��մϴ�.");
                        AdminMenu adminMenu = new AdminMenu();
                        adminMenu.displayMenu();
                    } else {
                        System.out.println("������ ������ �����ϼ̽��ϴ�.");
                    }
                    break;
                case 5:
                    movie = false;
                    break;
                default:
                    System.out.println("�߸��� �����Դϴ�. 1 ~ 5�� �� �ٽ� �������ֽʽÿ�.");
            }
        }
        
        // ���α׷� ���� ���� ��ȭ �� ���� �����͸� ���Ͽ� �����մϴ�.
        movies.saveMoviesToFile();
        reservation.saveReservationsToFile();

        System.out.println("���α׷��� ����Ǿ����ϴ�.");
    }

    private static boolean loginAdmin(Scanner scan) {
        System.out.println("������ ���̵� �Է��ϼ���: ");
        String adminId = scan.nextLine();
        System.out.println("������ ��й�ȣ�� �Է��ϼ���: ");
        String adminPw = scan.nextLine();

        return adminId.equals("dhehdtn") && adminPw.equals("1q2w3e!@#");
    }
}
