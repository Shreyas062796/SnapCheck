package snapcheck;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/*
 * Class to have function to populate mongodb,
 * and run two sorting functions
 */
public class SnapCheck implements SnapCheckDatabase{
	String connectionURL = "";
	MongoCollection<Document> collection = null;
	ServerAddress address;
	MongoCredential credentials;
	public SnapCheck() {
		address = new ServerAddress(mongoClient,Integer.parseInt(mongoPort));
		credentials = MongoCredential.createCredential(mongoUsername, mongoDatabase, mongoPassword.toCharArray());
		MongoClient mongoClient = new MongoClient(address,Arrays.asList(credentials));
		MongoDatabase db = mongoClient.getDatabase("snapcheck");
		collection = db.getCollection(("paymentobjects"));
	}
	/*
	 * populates database with 1000 objects of the PaymentObjects class
	 */
	public void populateDatabase() throws InterruptedException{
		//get random number between 1 and 1000
		Random r = new Random();
		for(int i = 1;i <= 1000;i++) {
			TimeUnit.SECONDS.sleep(1);
			try {
				PaymentObject obj = new PaymentObject(i,r.nextInt(1000)+1,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
				Document doc = new Document();
				doc.put("paymentNumber", obj.getPaymentNumber());
				doc.put("amount", obj.getAmount());
				doc.put("date", obj.getDate().toString());
				collection.insertOne(doc);
				System.out.println("Document inserted successfully");
			}catch(Exception e) {
				System.out.println("The error that you recieved is " + e);
			}
		}
	}
	/*
	 * Sorting Algorithm 1
	 */
	public Object[] SortOne(int n){
		Map<SimpleDateFormat,PaymentObject> map = new HashMap<SimpleDateFormat,PaymentObject>();
		Object[] res = new Object[n];
		FindIterable<Document> cursor = collection.find().limit(n);
		int i = 0;
		for(Document doc:cursor) {
			PaymentObject obj = (PaymentObject) doc.get("paymentOBJ");
			map.put(obj.getDate(), obj);
			res[i] = obj.getDate();
			i++;
		}
		return(res);
	}
	/*
	 * Sorting Algorithm 2
	 */
	public Object[] SortTwo(int n){
		Map<SimpleDateFormat,PaymentObject> map = new HashMap<SimpleDateFormat,PaymentObject>();
		Object[] res = new Object[n];
		FindIterable<Document> cursor = collection.find().limit(n);
		int i = 0;
		for(Document doc:cursor) {
			PaymentObject obj = (PaymentObject) doc.get("paymentOBJ");
			map.put(obj.getDate(), obj);
			res[i] = obj.getDate();
			i++;
		}
		return(res);
	}
}
