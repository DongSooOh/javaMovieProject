package movie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// ��ȭ ���� ����, ��ȭ�� ���� ������� ���
public class Movie {
    private static final String FILE_PATH = "C:/web/javaMovieProject/src/movie/movies.txt";
    
    private List<String> movies = new ArrayList<>();
    private List<Long> movieStamps = new ArrayList<>();
    private List<String> genres = new ArrayList<>();
    
    public void defaultMovies() {
        long currentTimeInSeconds = System.currentTimeMillis() / 1000;
        movies.clear(); 
        
        movies.add("��");
        movieStamps.add(currentTimeInSeconds); 
        genres.add("��Ÿ��");
        
        movies.add("�Ĺ�");
        movieStamps.add(currentTimeInSeconds + 1);
        genres.add("������");
        
        movies.add("��Ǫ�Ҵ�");
        movieStamps.add(currentTimeInSeconds + 2);
        genres.add("�ִϸ��̼�");
        
        movies.add("���˵���");
        movieStamps.add(currentTimeInSeconds + 3);
        genres.add("�׼�");
    }
    
    public void addMovie(String movieName, String movieGenre) {
        movies.add(movieName);
        genres.add(movieGenre);
    }
    
    public void removeMovie(String movieName) {
        int index = movies.indexOf(movieName);
        if (index != -1) {
            movies.remove(index);
            movieStamps.remove(index);
            genres.remove(index);
        }
    }
    
    public List<String> getAllMovies(){
        return movies;
    }
    
    public boolean containsMovie(String movieName) {
        return movies.contains(movieName);
    }
    
    public void saveMoviesToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0; i < movies.size(); i++) {
                writer.println(movieStamps.get(i) + "," + movies.get(i) + "," + genres.get(i));
            }
            System.out.println("��ȭ ������ ���Ϸ� �����߽��ϴ�.");
        } catch (IOException e) {
            System.out.println("��ȭ ������ ���Ϸ� �����ϴ� ���� ������ �߻��߽��ϴ�." + e.getMessage());
        }
    }

    public void loadMoviesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    movieStamps.add(Long.parseLong(parts[0]));
                    movies.add(parts[1]);
                    genres.add(parts[2]);
                }
            }
            System.out.println("���Ͽ��� ��ȭ ������ �ҷ��Խ��ϴ�.");
        } catch (FileNotFoundException e) {
            System.out.println("��ȭ ������ �ҷ����� ���� ������ ã�� �� �����ϴ�." + e.getMessage());
        } catch (IOException e) {
            System.out.println("���Ͽ��� ��ȭ ������ �ҷ����� ���� ������ �߻��߽��ϴ�." + e.getMessage());
        }
    }
}