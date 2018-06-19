package com.youLoveLife.web;

import com.youLoveLife.domain.Problem;
import com.youLoveLife.repository.ProblemRepository;
import com.youLoveLife.repository.ProblemRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProblemRestController {

    @Autowired
    private ProblemRepositoryImpl problemRepositoryImpl;
    @Autowired
    private ProblemRepository problemRepository;

    @RequestMapping(value = "/sendProblem/", method = RequestMethod.POST)
    public ResponseEntity sendProblem(@RequestParam String topic, @RequestParam String message) {
        try {
            Problem problem = new Problem(topic, message);
            problemRepositoryImpl.sendProblem(problem);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/getAllProblems", method = RequestMethod.GET)
    public ResponseEntity getAllProblems() {
        List<Problem> list = problemRepositoryImpl.getAllProblems();

        if(list != null)
            return new ResponseEntity(list, HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/readProblem/{problemID}", method = RequestMethod.GET)
    public ResponseEntity readProblem(@PathVariable Long problemID) {
        try {
            problemRepositoryImpl.readProblem(problemID);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/deleteProblem/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProblem(@PathVariable Long id) {
        try {
            Problem problem = problemRepository.findOne(id);
            problemRepository.delete(problem);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
