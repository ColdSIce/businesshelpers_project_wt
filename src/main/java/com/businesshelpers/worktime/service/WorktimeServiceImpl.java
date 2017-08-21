package com.businesshelpers.worktime.service;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.worklog.Worklog;
import com.atlassian.jira.issue.worklog.WorklogManager;
import com.atlassian.jira.user.ApplicationUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorktimeServiceImpl implements WorktimeService {

    public Map<ApplicationUser, Long> getWorkTime(Issue issue) {
        Map<ApplicationUser, Long> result = new HashMap<ApplicationUser, Long>();
        WorklogManager worklogManager = ComponentAccessor.getWorklogManager();
        List<Worklog> worklogs = worklogManager.getByIssue(issue);
        for(Worklog worklog:worklogs){
            if(result.containsKey(worklog.getAuthorObject()))
                result.put(
                    worklog.getAuthorObject(),
                    (result.get(worklog.getAuthorObject()) + worklog.getTimeSpent())
                );
            else result.put(worklog.getAuthorObject(), worklog.getTimeSpent());
        }
        return result;
    }
}
