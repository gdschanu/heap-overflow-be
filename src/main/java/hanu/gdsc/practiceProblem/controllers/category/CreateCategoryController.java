package hanu.gdsc.practiceProblem.controllers.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hanu.gdsc.practiceProblem.services.category.CreateCategoryService;
import hanu.gdsc.share.controller.ResponseBody;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.error.BusinessLogicError;

@RestController
public class CreateCategoryController {
    @Autowired
    private CreateCategoryService createCategoryService;
    
    @PostMapping("/practiceProblem/createCategory")
    public ResponseEntity<?> create(@RequestBody CreateCategoryService.Input input) {
        try {
            Id category = createCategoryService.create(input);
            return new ResponseEntity<>(new ResponseBody("Create successfully category", category.toString()), HttpStatus.OK);
        } catch (Throwable e) {
            if (e.getClass().equals(BusinessLogicError.class)) {
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseBody(e.getMessage(), ((BusinessLogicError) e).getCode()), HttpStatus.BAD_REQUEST);
            }   
            return new ResponseEntity<>(new ResponseBody(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
