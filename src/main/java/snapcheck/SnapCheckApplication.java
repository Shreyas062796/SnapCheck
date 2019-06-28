package snapcheck;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;abstract

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class SnapCheckApplication {
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(SnapCheckApplication.class,args);
//		SnapCheck snap = new SnapCheck();
//		snap.populateDatabase();
//		snap.SortOne(20);
//		snap.SortTwo(5);
	}
}
