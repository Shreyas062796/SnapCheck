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
				PaymentObject obj = new PaymentObject(i,r.nextInt(1000)+1,new Date().getTime());
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
	 *  add one payment object to database
	 */
	public Boolean addOnePayment(PaymentObject payment) throws InterruptedException{
		//get random number between 1 and 1000
		try {
			Document doc = new Document();
			doc.put("paymentNumber", payment.getPaymentNumber());
			doc.put("amount", payment.getAmount());
			doc.put("date", payment.getDate().toString());
			collection.insertOne(doc);
			System.out.println("Document inserted successfully");
		}catch(Exception e) {
			System.out.println("The error that you recieved is " + e);
			return(false);
		}
		return(true);
	}
	/*
	 * Sorting Algorithm 1 based off of Bubble Sort
	 * 
	 */
	public Document[] SortOne(int n){
		Document[] res = new Document[n];
		FindIterable<Document> cursor = collection.find().limit(n);
		int i = 0;
		Document temp = null;
		boolean swap = false;
		for(Document doc:cursor) {
			res[i] = doc;
			i++;
		}
		for(int j = 0;j < res.length-1;j++) {
			for(int x = 0;x < res.length-j-1;x++) {
				if(Long.parseLong(res[x].get("date").toString()) > Long.parseLong(res[x+1].get("date").toString())) {
					temp = res[x];
					res[x] = res[x+1];
					res[x+1] = temp;
					swap = true;
				}
			}
			if(swap == false) {
				break;
			}
		}
		for(int a = 0;a < res.length;a++) {
			System.out.println(res[a].get("date"));
		}
		return(res);
	}
	/*
	 * Sorting Algorithm 2 based off of Selection Sort
	 */
	public Document[] SortTwo(int n){
		Document[] res = new Document[n];
		FindIterable<Document> cursor = collection.find().limit(n);
		int i = 0;
		Document min = null;
		int minidx = 0;
		Document temp = null;
		for(Document doc:cursor) {
			res[i] = doc;
			i++;
		}
		for(int j = 0;j < res.length;j++) {
			for(int x = j+1;x < res.length;x++) {
//				System.out.println(Long.parseLong(min.get("date").toString()));
				if(min == null || (Long.parseLong(res[x].get("date").toString()) < Long.parseLong(min.get("date").toString()))) {
					minidx = x;
				}
			}
			temp = res[minidx];
			res[minidx] = res[j];
			res[minidx] = temp;
		}
		for(int a = 0;a < res.length;a++) {
			System.out.println(res[a].get("date"));
		}
		return(res);
	}
}
