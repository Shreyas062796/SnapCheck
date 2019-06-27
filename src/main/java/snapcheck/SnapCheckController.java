package snapcheck;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SnapCheckController {
	
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addPayment(@RequestBody JSONObject body) throws InterruptedException {
		PaymentObject obj = new PaymentObject(Integer.parseInt(body.get("paymentNumber").toString()),Integer.parseInt(body.get("amounts").toString()),Long.parseLong(body.get("date").toString()));
		Boolean didAdd = new SnapCheck().addOnePayment(obj);
		if(didAdd) {
			return (ResponseEntity) (ResponseEntity.status(HttpStatus.CREATED));
		}
		return (ResponseEntity) (ResponseEntity.status(HttpStatus.NO_CONTENT));
	}
}
