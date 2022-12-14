import lombok.Getter;

@Getter

public class Purchase {
    private String title;
    private String date;
    private int sum;

    public Purchase(String title, String date, int sum) {
        this.title = title;
        this.date = date;
        this.sum = sum;
    }
}





