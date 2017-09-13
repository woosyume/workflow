package ut.net.atlassian.woohyeok.plugins.jira.workflow;

import net.atlassian.woohyeok.plugins.jira.workflow.ParentIssueBlockingCondition;

import com.atlassian.jira.issue.MutableIssue;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParentIssueBlockingConditionTest
{
    public static final String FIELD_WORD = "word";

    protected ParentIssueBlockingCondition condition;
    protected MutableIssue issue;

    @Before
    public void setup() {
        issue = mock(MutableIssue.class);
        condition = new ParentIssueBlockingCondition() {
            protected MutableIssue getIssue(Map transientVars) {
                return issue;
            }
        };
    }

    @Test
    public void testPassesCondition() throws Exception
    {
        Map transientVars = new HashMap();
        transientVars.put(FIELD_WORD, "test");
        when(issue.getDescription()).thenReturn("This description has test in it.");

        boolean result = condition.passesCondition(transientVars, null, null);

        assertTrue("condition should pass", result);
    }

    @Test
    public void testFailsCondition() throws Exception
    {
        Map transientVars = new HashMap();
        transientVars.put(FIELD_WORD, "test");
        when(issue.getDescription()).thenReturn("This description does not have the magic word in it.");

        boolean result = condition.passesCondition(transientVars, null, null);

        assertFalse("condition should fail", result);
    }

}
