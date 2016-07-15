package app;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by mmak on 14.07.16.
 */
@RestController
public class MyController {

    @RequestMapping(value = "/resources", method = GET)
    public String getResources(){

        return "SampleResources";

    }

}
