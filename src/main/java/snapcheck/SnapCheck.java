package snapcheck;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
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
	DBCollection collection = null;
	public SnapCheck() {
		connectionURL = "mongodb+srv://+ " + mongoUsername + ":" + mongoPassword + "@" + mongoClient;
		MongoClientURI uri = new MongoClientURI(connectionURL);
		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase db = mongoClient.getDatabase("snapcheck");
		db.createCollection("paymentobjects");
		collection = (DBCollection) db.getCollection(("paymentobjects"));
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
				BasicDBObject doc = new BasicDBObject();
				doc.put("paymentOBJ", obj);
				collection.insert(doc);
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
		DBCursor cursor = collection.find().limit(n);
		int i = 0;
		while(cursor.hasNext()) {
			PaymentObject obj = (PaymentObject) cursor.next().get("paymentOBJ");
			map.put(obj.getDate(), obj);
			res[i] = obj.getDate();
		}
		return(res);
	}
	/*
	 * Sorting Algorithm 2
	 */
	public Object[] SortTwo(int n){
		Map<SimpleDateFormat,PaymentObject> map = new HashMap<SimpleDateFormat,PaymentObject>();
		Object[] res = new Object[n];
		DBCursor cursor = collection.find().limit(n);
		int i = 0;
		while(cursor.hasNext()) {
			PaymentObject obj = (PaymentObject) cursor.next().get("paymentOBJ");
			map.put(obj.getDate(), obj);
			res[i] = obj.getDate();
		}
		return(res);
	}
}
