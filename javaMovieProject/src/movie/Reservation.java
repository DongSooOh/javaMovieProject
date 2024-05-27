package movie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// 예매 정보를 관리하는 클래스, 예매의 파일 입출력 담당
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
        if (!isSeatReserved(seat)) { // 이미 예약된 좌석인지 확인
            reservedSeats.add(seat);
            reservedMovies.add(movieName);
            long timeStamp = System.currentTimeMillis();
            reservationTimestamps.add(timeStamp);
            System.out.println("예매가 성공적으로 완료되었습니다.");
            System.out.println("발급번호: " + timeStamp + ", 영화: " + movieName + ", 좌석: " + seat);
        } else {
            System.out.println("이미 예약된 좌석입니다.");
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
        System.out.print("예매 정보: ");
        for (int i = 0; i < reservedSeats.size(); i++) {
            System.out.println(reservedSeats.get(i) + " " + reservedMovies.get(i));
        }
    }
    
    public void saveReservationsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0; i < reservedSeats.size(); i++) {
                writer.println(reservationTimestamps.get(i) + "," + reservedSeats.get(i) + "," + reservedMovies.get(i));
            }
            System.out.println("예매 정보를 파일로 저장했습니다.");
        } catch (IOException e) {
            System.out.println("예매 정보를 파일로 저장하는 도중 오류가 발생했습니다." + e.getMessage());
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
            System.out.println("파일에서 예매 정보를 불러왔습니다.");
        } catch (FileNotFoundException e) {
            System.out.println("예매 정보를 불러오는 도중 파일을 찾을 수 없습니다." + e.getMessage());
        } catch (IOException e) {
            System.out.println("파일에서 예매 정보를 불러오는 도중 오류가 발생했습니다." + e.getMessage());
        }
    }
}
