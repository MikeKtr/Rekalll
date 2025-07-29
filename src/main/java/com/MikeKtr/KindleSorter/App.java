    package com.MikeKtr.KindleSorter;

    import java.sql.SQLException;

    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.web.bind.annotation.RestController;



    @SpringBootApplication
    @RestController
    public class App
    {

        // @GetMapping("/")
        // public String Home(){
        //     return "witam siemanko tiasdfaserqwerpoiafdskljqwerididkddkdkdkdkkdkdeiei";
        // }
        public static void main( String[] args ) throws SQLException
        {
            SpringApplication.run(App.class, args);
//
//            String login = "michal";
//            String password = "kotra123";
//
//            connectToDataBase(login,password);
//            readClippingFromFolder("C:\\Users\\micha\\Desktop\\Kindle Sorter\\kindle-sorter-app\\My_Clippings.txt");
//            openWindow();

        }

        // Will open window in web app

        // private static void openWindow()
        // {
        //     System.out.println("Window Opened");
        // }

        //Connecting to DataBase and returns is connection was succesfull

        // private static boolean connectToDataBase(String login,String password) throws SQLException{
        //     String CONN_URL = "jdbc:mysql://localhost:3306/kindle_sorter_db?serverTimezone=UTC";
        //     String PREPARED_STATEMENT = "SELECT * FROM users";
        //     try(Connection conn = DriverManager.getConnection(CONN_URL,"root","12345678");
        //     PreparedStatement ps = conn.prepareStatement(PREPARED_STATEMENT);
        //     ResultSet rs = ps.executeQuery();
        //     ){
        //         while(rs.next()){
        //             int id = rs.getInt("userID");
        //             String name = rs.getString("login");
        //             System.out.println(id + " " + name);
        //         }
        //     }
        //     return true;
        // }

        //Automatically check if there is my_clippings.txt file somewhere on computer

        // private static String searchForClippings(){
        //     String path = "C:\\Users\\micha\\Desktop\\Kindle Sorter\\kindle-sorter-app\\My_Clippings.txt";
        //     return path;
        // }

        //Scans and sorts my_clipping.txt file

    //     private static void readClippingFromFolder(String path){
    //         Map<Book,List<Quote>> books = new HashMap<>();
    //         Map<Author,List<Book>> authors = new HashMap<>();
    //         try (BufferedReader reader = new BufferedReader(
    //                 new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
    //             String line;
    //             while ((line = reader.readLine()) != null) {

    //                 String titleLine = "";
    //                 String dateLine = "";
    //                 List<String> quoteList = new ArrayList<String>();


    //                 while (!line.equals("==========")) {


    //                     if (titleLine.isEmpty()) {
    //                         titleLine = line;

    //                     } else if (dateLine.isEmpty()) {
    //                         dateLine = line;

    //                     } else {
    //                         quoteList.add(line);
    //                     }
    //                     line = reader.readLine();
    //                 }


    //                 Author author = new Author(titleLine);
    //                 Book book = new Book(titleLine, author);
    //                 Quote quote = new Quote(quoteList, book);


    //                 if (books.keySet().stream().noneMatch(key -> key.getTitle().equals(book.getTitle()))) {
    //                     books.put(book, new ArrayList<>());
    //                 }
    //                 //Adds quote to book in map
    //                 Book bookKey = books.keySet().stream().filter(key -> key.getTitle().equals(book.getTitle())).findFirst().orElse(null);
    //                 books.get(bookKey).add(quote);

    //                 if (authors.keySet().stream().noneMatch(key -> key.getName().equals(author.getName()))) {
    //                     authors.put(author, new ArrayList<>());
    //                 }
    //                 Author authorKey = authors.keySet().stream().filter(key -> key.getName().equals(author.getName())).findFirst().orElse(null);
    //                 if(!authors.get(authorKey).contains(bookKey)){
    //                     authors.get(authorKey).add(book);
    //                 }
    //             }

               


    // }
    //         catch (IOException e) {
    //             e.printStackTrace();
    //         }
    //     }
    }