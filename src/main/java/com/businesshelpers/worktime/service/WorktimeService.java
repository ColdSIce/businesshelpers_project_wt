package com.businesshelpers.worktime.service;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.user.ApplicationUser;

import java.util.Map;

public interface WorktimeService {
    Map<ApplicationUser, Long> getWorkTime(Issue issue);
}
