package movie;

// Menu 인터페이스를 구현하는 추상 클래스로 MainMenu와 AdminMenu의 부모 클래스
abstract class AbstractMenu implements Menu {
	@Override
	public abstract void displayMenu();
}
