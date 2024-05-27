package movie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// 영화 정보 관리, 영화의 파일 입출력을 담당
public class Movie {
    private static final String FILE_PATH = "C:/web/javaMovieProject/src/movie/movies.txt";
    
    private List<String> movies = new ArrayList<>();
    private List<Long> movieStamps = new ArrayList<>();
    private List<String> genres = new ArrayList<>();
    
    public void defaultMovies() {
        long currentTimeInSeconds = System.currentTimeMillis() / 1000;
        movies.clear(); 
        
        movies.add("듄");
        movieStamps.add(currentTimeInSeconds); 
        genres.add("판타지");
        
        movies.add("파묘");
        movieStamps.add(currentTimeInSeconds + 1);
        genres.add("스릴러");
        
        movies.add("쿵푸팬더");
        movieStamps.add(currentTimeInSeconds + 2);
        genres.add("애니메이션");
        
        movies.add("범죄도시");
        movieStamps.add(currentTimeInSeconds + 3);
        genres.add("액션");
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
            System.out.println("영화 정보를 파일로 저장했습니다.");
        } catch (IOException e) {
            System.out.println("영화 정보를 파일로 저장하는 도중 오류가 발생했습니다." + e.getMessage());
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
            System.out.println("파일에서 영화 정보를 불러왔습니다.");
        } catch (FileNotFoundException e) {
            System.out.println("영화 정보를 불러오는 도중 파일을 찾을 수 없습니다." + e.getMessage());
        } catch (IOException e) {
            System.out.println("파일에서 영화 정보를 불러오는 도중 오류가 발생했습니다." + e.getMessage());
        }
    }
}