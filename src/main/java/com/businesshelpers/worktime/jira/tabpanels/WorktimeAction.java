package com.businesshelpers.worktime.jira.tabpanels;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.avatar.Avatar;
import com.atlassian.jira.avatar.AvatarService;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.issuetabpanel.AbstractIssueAction;
import com.atlassian.jira.plugin.issuetabpanel.IssueTabPanelModuleDescriptor;
import com.atlassian.jira.user.ApplicationUser;
import com.businesshelpers.worktime.service.WorktimeService;

import java.util.Date;
import java.util.Map;

public class WorktimeAction extends AbstractIssueAction {

    private final Issue issue;
    private final User remoteUser;
    private final WorktimeService worktimeService;
    private final AvatarService avatarService;

    public WorktimeAction(IssueTabPanelModuleDescriptor descriptor,
                          Issue issue, User remoteUser,
                          WorktimeService worktimeService,
                          AvatarService avatarService) {
        super(descriptor);
        this.issue = issue;
        this.remoteUser = remoteUser;
        this.worktimeService = worktimeService;
        this.avatarService = avatarService;
    }

    public Date getTimePerformed() {
        return issue.getCreated();
    }

    protected void populateVelocityParams(Map map) {
        Map<ApplicationUser, Long> worktimes = worktimeService.getWorkTime(issue);
        map.put("size", Avatar.Size.SMALL);
        map.put("user", remoteUser);
        map.put("worktimes", worktimes);
        map.put("total", getTotalTime(worktimes));
        map.put("avatar", avatarService);
    }

    private long getTotalTime(Map<ApplicationUser, Long> worktimes){
        long sum = 0L;
        for(Map.Entry<ApplicationUser, Long> entry : worktimes.entrySet()){
            sum += entry.getValue();
        }
        return sum;
    }
}
