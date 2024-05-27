package movie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// ���� ������ �����ϴ� Ŭ����, ������ ���� ����� ���
public class Reservation {

    private static final String FILE_PATH = "C:/web/javaMovieProject/src/movie/reservation.txt";
    
    private List<String> reservedSeats;
    private List<String> reservedMovies;
    private List<Long> reservationTimestamps;
    
    public Reservation() {
        reservedSeats = new ArrayList<>();
        reservedMovies = new ArrayList<>();
        reservationTimestamps = new ArrayList<>();
    }
    
    public boolean isSeatReserved(String seat) {
        return reservedSeats.contains(seat);
    }
    
    public void addReservation(String seat, String movieName) {
        if (!isSeatReserved(seat)) { // �̹� ����� �¼����� Ȯ��
            reservedSeats.add(seat);
            reservedMovies.add(movieName);
            long timeStamp = System.currentTimeMillis();
            reservationTimestamps.add(timeStamp);
            System.out.println("���Ű� ���������� �Ϸ�Ǿ����ϴ�.");
            System.out.println("�߱޹�ȣ: " + timeStamp + ", ��ȭ: " + movieName + ", �¼�: " + seat);
        } else {
            System.out.println("�̹� ����� �¼��Դϴ�.");
        }
    }
    
    public boolean removeReservation(String seat) {
        int index = reservedSeats.indexOf(seat);
        if (index != -1) {
            reservedSeats.remove(index);
            reservedMovies.remove(index);
            return true;
        }
        return false;
    }
    
    public void displayReservation() {
        System.out.print("���� ����: ");
        for (int i = 0; i < reservedSeats.size(); i++) {
            System.out.println(reservedSeats.get(i) + " " + reservedMovies.get(i));
        }
    }
    
    public void saveReservationsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0; i < reservedSeats.size(); i++) {
                writer.println(reservationTimestamps.get(i) + "," + reservedSeats.get(i) + "," + reservedMovies.get(i));
            }
            System.out.println("���� ������ ���Ϸ� �����߽��ϴ�.");
        } catch (IOException e) {
            System.out.println("���� ������ ���Ϸ� �����ϴ� ���� ������ �߻��߽��ϴ�." + e.getMessage());
        }
    }

    public void loadReservationsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    reservationTimestamps.add(Long.parseLong(parts[0]));
                    reservedSeats.add(parts[1]);
                    reservedMovies.add(parts[2]);
                }
            }
            System.out.println("���Ͽ��� ���� ������ �ҷ��Խ��ϴ�.");
        } catch (FileNotFoundException e) {
            System.out.println("���� ������ �ҷ����� ���� ������ ã�� �� �����ϴ�." + e.getMessage());
        } catch (IOException e) {
            System.out.println("���Ͽ��� ���� ������ �ҷ����� ���� ������ �߻��߽��ϴ�." + e.getMessage());
        }
    }
}
