package com.youLoveLife.repository;

import com.youLoveLife.domain.Problem;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProblemRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public void sendProblem(Problem problem) {
        entityManager.persist(problem);
    }

    public List<Problem> getAllProblems() {
        return entityManager.createQuery("select p from Problem p", Problem.class).getResultList();
    }

    public void readProblem(Long problemID) {
        List<Problem> list = getAllProblems();
        Iterator<Problem> iterator = list.iterator();

        while (iterator.hasNext()) {
            Problem problem = iterator.next();

            if(problem.getProblemID().equals(problemID)) {
                problem.setReaded(true);
                entityManager.merge(problem);
            }
        }
    }
}
