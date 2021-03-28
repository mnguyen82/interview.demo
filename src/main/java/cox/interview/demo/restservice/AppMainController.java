/**
 * 
 */
package cox.interview.demo.restservice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller that renders the index page of react App.js main component
 * @author minhnguyen
 * 
 */
@Controller
public class AppMainController {
	@RequestMapping(value = "/")
    public String index() {
        return "index";
    }
}
