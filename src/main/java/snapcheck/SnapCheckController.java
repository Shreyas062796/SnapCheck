package snapcheck;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SnapCheckController {
	
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	public int addPayment(@RequestParam Integer paymentNumber,@RequestParam Integer amount,@RequestParam Long date) throws InterruptedException {
		PaymentObject obj = new PaymentObject(paymentNumber,amount,date);
		Boolean didAdd = new SnapCheck().addOnePayment(obj);
		if(didAdd) {
			return HttpStatus.CREATED.value();
		}
		return HttpStatus.NO_CONTENT.value();
	}
}
