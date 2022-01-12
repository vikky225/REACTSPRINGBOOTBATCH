package com.example.ipldashboard.data;
import com.example.ipldashboard.model.Team;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {



    private final EntityManager entityManager;

    @Autowired
    public JobCompletionNotificationListener(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
           log.info("!!! JOB FINISHED! Time to verify the results");

           Map<String, Team> teamData = new HashMap<>();

           entityManager.createQuery("select   m.team1, count(m.team2) from Match m group by m.team1",Object[].class)
                    .getResultList()
                    .stream()
               .map(e->new Team((String)e[0],(long)e[1]))
               .forEach(team->teamData.put(team.getTeamName(),team));

            entityManager.createQuery("select   m.team2, count(m.team2) from Match m group by m.team2",Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(e->{
                        Team team =teamData.get((String) e[0]);
                        team.setTotalMatches(team.getTotalMatches()+(long) e[1]);
                    });
            entityManager.createQuery("select m.matchwinner,count(m.matchwinner) from Match m group by m.matchwinner",Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(e->{
                        Team team = teamData.get((String) e[0]);
                        if(team !=null) team.setTotalWin((long)e[1]);
                    });
            
            teamData.values().forEach(team->entityManager.persist(team));

           /* jdbcTemplate.query("SELECT team1, team2,date FROM match",
                    (rs, row) ->
                            rs.getString(1) +""+
                            rs.getString(2)+""+
                            rs.getDate(3)
            ).forEach(person -> log.info("Found <" + person + "> in the database."));*/
        }
    }
}
