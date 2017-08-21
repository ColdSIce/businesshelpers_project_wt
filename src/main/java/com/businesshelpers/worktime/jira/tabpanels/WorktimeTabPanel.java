package com.businesshelpers.worktime.jira.tabpanels;

import com.atlassian.jira.avatar.AvatarService;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.issuetabpanel.AbstractIssueTabPanel;
import com.atlassian.jira.plugin.issuetabpanel.IssueAction;
import com.atlassian.jira.plugin.issuetabpanel.IssueTabPanel;
import com.businesshelpers.worktime.service.WorktimeService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class WorktimeTabPanel extends AbstractIssueTabPanel implements IssueTabPanel
{
    private static final Logger log = LoggerFactory.getLogger(WorktimeTabPanel.class);

    private final WorktimeService worktimeService;
    private final AvatarService avatarService;

    public WorktimeTabPanel(WorktimeService worktimeService,
                            AvatarService avatarService) {
        this.worktimeService = worktimeService;
        this.avatarService = avatarService;
    }

    public List getActions(Issue issue, com.atlassian.crowd.embedded.api.User remoteUser) {
        List<IssueAction> issueActions = Lists.newArrayList();
        issueActions.add(new WorktimeAction(descriptor, issue, remoteUser, worktimeService, avatarService));
        return issueActions;
    }

    public boolean showPanel(Issue issue, com.atlassian.crowd.embedded.api.User remoteUser)
    {
        return true;
    }
}
