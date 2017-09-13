package net.atlassian.woohyeok.plugins.jira.workflow;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.workflow.condition.AbstractJiraCondition;
import com.opensymphony.module.propertyset.PropertySet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ParentIssueBlockingCondition extends AbstractJiraCondition
{
    private static final Logger log = LoggerFactory.getLogger(ParentIssueBlockingCondition.class);

    public static final String FIELD_WORD = "word";

    public boolean passesCondition(Map transientVars, Map args, PropertySet ps)
    {
        String word = (String)transientVars.get(FIELD_WORD);
        Issue issue = getIssue(transientVars);
        String description = issue.getDescription();

        return description != null && description.contains(word);
    }
}
