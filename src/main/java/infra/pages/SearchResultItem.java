package infra.pages;

public class SearchResultItem {

    private String title;
    private String date;
    private String prize;
    private String ranking;


    public SearchResultItem(String title, String date, String prize, String ranking) {
        this.title = title;
        this.date = date;
        this.prize = prize;
        this.ranking = ranking;
    }

    public SearchResultItem(String title, String date, String prize) {
        this.title = title;
        this.date = date;
        this.prize = prize;
    }


//    @Override
//    public String toString() {
//        return "infra.pages.SearchResultItem{" +
//                "title='" + title + '\'' +
//                ", date='" + date + '\'' +
//                ", prize='" + prize + '\'' +
//                ", ranking='" + ranking + '\'' +
//                '}';
//    }

    @Override
    public String toString() {
        return
                "title ='" + title + '\'' +
                ", date ='" + date + '\'' +
                ", prize ='" + prize + '\'' +
                '}';

    }
}
