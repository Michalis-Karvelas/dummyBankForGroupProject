package com.dummyBank.controllers;

import com.dummyBank.models.CardDetails;
import com.dummyBank.requests.CardDetailsRequest;
import com.dummyBank.responses.CardDetailsResponse;
import com.dummyBank.responses.Response;
import com.dummyBank.services.CardDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@Slf4j
@CrossOrigin(origins = " * ", allowedHeaders = " * ")
@RequestMapping(value="/api/card")
@RestController
public class CardDetailsController {

    @Autowired
    private CardDetailsServiceImpl cardDetailsServiceImpl;


    // list of all the CardDetails
    @GetMapping(value="/getall")
    public CardDetailsResponse getAll(){
        log.info("Ready to find all the CardDetails");
        return new CardDetailsResponse("Found all the CardDetails",cardDetailsServiceImpl.getAll());
    }
//
    // get CardDetails by id
    @GetMapping(value="/getbyid/{id}")
    public CardDetailsResponse getById(@PathVariable Long id){
        log.info("Ready to find CardDetails by id");
        return new CardDetailsResponse("Found the CardDetails",cardDetailsServiceImpl.getCardDetailsById(id));
    }
    //get by card number
    @GetMapping(value="/getbycardnumber/{number}")
    public CardDetailsResponse get(@PathVariable Long number){
        log.info("Ready to find all the CardDetails by card number");
        return new CardDetailsResponse("Found all the CardDetails",cardDetailsServiceImpl.getCardDetailsByCardNumber(number));
    }

    //create new account
    @PostMapping(value="/new",consumes = "application/json",
            produces = "application/json")
    public CardDetailsResponse createNewCardDetails(@RequestBody CardDetailsRequest request){
        log.info("Ready to create a new CardDetails information");
        Long cardId=cardDetailsServiceImpl.createCardDetails(request);
        return new CardDetailsResponse("The CardDetails has been saved",cardId);
    }

    // update CardDetails with id
    @PutMapping(value="/update/{id}",consumes = "application/json", produces = "application/json")
    public Response updateExistingCardDetails(@PathVariable(value = "id") Long id,
                                          @RequestBody CardDetailsRequest request){
        log.info("ready to update a CardDetails");
        CardDetails cardDetails = cardDetailsServiceImpl.updateCardDetails(id, request);
        if (isNull(cardDetails)) {
            return new Response("There is no such CardDetails");
        }
        return new Response("The CardDetails have been updated");
    }

    // delete CardDetails by id
    @DeleteMapping("/delete/{id}")
    public Response deleteCardDetails(@PathVariable Long id){
        log.info("Ready to delete a CardDetails info");
        cardDetailsServiceImpl.deleteById(id);
        return new Response("The CardDetails have been deleted");
    }

    @PostMapping(value="/checkcardvalidity",consumes = "application/json",
            produces = "application/json")
    public Response yolo(@RequestBody CardDetailsRequest request){
        log.info("ready to check the validity");
        Long result=cardDetailsServiceImpl.checkCardDetails(request);
        if(result==0L){
            return new Response("Failed Transaction");
        }
        return new Response("Successful Transaction");
    }

}
