package com.youLoveLife.web;

import com.youLoveLife.domain.Problem;
import com.youLoveLife.repository.ProblemRepository;
import com.youLoveLife.repository.ProblemRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ProblemRestController {

    @Autowired
    private ProblemRepositoryImpl problemRepositoryImpl;
    @Autowired
    private ProblemRepository problemRepository;

    @RequestMapping(value = "/sendProblem/", method = RequestMethod.POST)
    public void sendProblem(@RequestBody Problem problem) {
        problemRepositoryImpl.sendProblem(problem);
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
    public void readProblem(@PathVariable Long problemID) {
        problemRepositoryImpl.readProblem(problemID);
    }

    @RequestMapping(value = "/deleteProblem/{id}", method = RequestMethod.DELETE)
    public void deleteProblem(@PathVariable Long id) {
        Problem problem = problemRepository.findOne(id);
        problemRepository.delete(problem);
    }
}
