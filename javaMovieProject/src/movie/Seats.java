package movie;

import java.util.ArrayList;
import java.util.List;

// ���� �¼��� �����ϴ� Ŭ����
public class Seats {
	
	private List<String> seatList;
	
	public Seats() {
		seatList = new ArrayList<>();
		initializeSeats();
	}
	
	private void initializeSeats() {
		for (char row = 'A'; row <= 'Z'; row++) {
			for (int seatNumber = 1; seatNumber <= 10; seatNumber++) {
				String seat = String.valueOf(row) + "-" + seatNumber;
				seatList.add(seat.toUpperCase());
			}
		}
	}
	
	public boolean isSeatAvailable(String seat) {
		return !seatList.contains(seat);
	}
	
	public void reserveSeat(String seat) {
		seatList.add(seat);
	}
	
	public void displaySeats() {
		System.out.println("�¼� ����: ");
		for (String seat : seatList) {
			System.out.print(seat + ": ");
			if (isSeatAvailable(seat)) {
				System.out.println("O ");
			} else {
				System.out.println("X ");
			}
		}
	}
}
