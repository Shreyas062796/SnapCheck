package snapcheck;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;abstract

@SpringBootApplication
public class SnapCheckApplication {
	public static void main(String[] args) throws InterruptedException {
//		SpringApplication.run(SnapCheckApplication.class,args);
		SnapCheck snap = new SnapCheck();
//		snap.populateDatabase();
		snap.SortOne(20);
		snap.SortTwo(5);
	}
}
