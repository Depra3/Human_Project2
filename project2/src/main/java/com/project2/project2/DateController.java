package com.project2.project2;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@RestController
public class DateController {
    
    @Autowired
    private CommRepository commRe;

    @RequestMapping("/boardUp")
    public Community boardUp(@RequestBody Community comm){
        // commRepository.save(comm);Community comm
        // ses
        // comm.setAuthor();
        comm.setReq_date(new Date());
        return commRe.save(comm);
    }
}
