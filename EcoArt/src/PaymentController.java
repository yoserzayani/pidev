import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

public class PaymentController {

    @FXML
    private TextField yourname;
    @FXML
    private TextField numCard;
    @FXML
    private TextField CVC;
    @FXML
    private ComboBox<?> months;
    @FXML
    private ComboBox<?> years;
    @FXML
    private Button confirm;
    @FXML
    private WebView webview;
    @FXML
    private TextField email;

    public void initialize() {
        Stripe.apiKey = "sk_test_YourStripeApiKey";
    }
}
    