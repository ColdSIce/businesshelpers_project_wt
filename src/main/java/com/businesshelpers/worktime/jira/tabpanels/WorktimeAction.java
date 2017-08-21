package com.businesshelpers.worktime.jira.tabpanels;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.avatar.Avatar;
import com.atlassian.jira.avatar.AvatarService;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.issuetabpanel.AbstractIssueAction;
import com.atlassian.jira.plugin.issuetabpanel.IssueTabPanelModuleDescriptor;
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
        map.put("size", Avatar.Size.SMALL);
        map.put("user", remoteUser);
        map.put("worktimes", worktimeService.getWorkTime(issue));
        map.put("avatar", avatarService);
    }
}
