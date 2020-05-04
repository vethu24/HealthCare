package Services;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
import com.payment;
@Path("/paymentController")
public class paymentService {
	
payment p=new payment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String getPaymentDetails() {
		return p.readPayment();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String AddCard(@FormParam("holderName") String holderName,
						  @FormParam("ctype") String ctype,
						  @FormParam("cardNo") String cardNo,
						  @FormParam("cvv") String cvv,
						  @FormParam("expMonth") String expMonth,
						  @FormParam("expYear") String expYear,
						  @FormParam("total") String total) {
		
		String output = p.addCard(holderName, ctype, cardNo, cvv,expMonth ,expYear,total);
		return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePaymentDetails(String paymentData)
	{
	//Convert the input string to a JSON object
	JsonObject patientObject = new JsonParser().parse(paymentData).getAsJsonObject();
	//Read the values from the JSON object
	String cardpayID=patientObject.get("cardpayID").getAsString();
	String holderName = patientObject.get("holderName").getAsString();
	String ctype = patientObject.get("ctype").getAsString();
	String cardNo = patientObject.get("cardNo").getAsString();
	String cvv = patientObject.get("cvv").getAsString();
	String expMonth = patientObject.get("expMonth").getAsString();
	String expYear = patientObject.get("expYear").getAsString();
	String total = patientObject.get("total").getAsString();
	
	
	String output = p.updatePayment(cardpayID,holderName, ctype, cardNo, cvv,expMonth ,expYear,total);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
	//Read the value from the element <cardpayID>
	String cardpayID = doc.select("cardpayID").text();
	String output = p.deletePayment(cardpayID);
	return output;
	}
	
}
