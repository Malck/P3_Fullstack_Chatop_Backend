/* V1 construit selon le cours */

package com.chatop.api.controller;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//import com.chatop.api.model.Rental;
//import com.chatop.api.service.RentalService;


/* @RestController
public class RentalController {

    @Autowired
    private RentalService rentalService;

    
    @GetMapping("/rentals")
    public Iterable<Rental> getRentals() {
        return rentalService.getRentals();
    }

}
*/

@RestController
public class RentalController {

	@GetMapping("/user")
	public String getUser() {
		return "Welcome, User";
	}
	
	@GetMapping("/admin")
	public String getAdmin() {
		return "Welcome, Admin";
	}
	
}