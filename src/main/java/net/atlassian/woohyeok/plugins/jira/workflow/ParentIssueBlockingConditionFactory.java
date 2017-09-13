package net.atlassian.woohyeok.plugins.jira.workflow;

import com.atlassian.jira.plugin.workflow.AbstractWorkflowPluginFactory;
import com.atlassian.jira.plugin.workflow.WorkflowPluginConditionFactory;
import com.opensymphony.workflow.loader.AbstractDescriptor;
import com.opensymphony.workflow.loader.ConditionDescriptor;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the factory class responsible for dealing with the UI for the post-function.
 * This is typically where you put default values into the velocity context and where you store user input.
 */

public class ParentIssueBlockingConditionFactory extends AbstractWorkflowPluginFactory implements WorkflowPluginConditionFactory
{
    public static final String FIELD_WORD = "word";

    protected void getVelocityParamsForInput(Map velocityParams)
    {
        //the default message
        velocityParams.put(FIELD_WORD, "test");
    }

    protected void getVelocityParamsForEdit(Map velocityParams, AbstractDescriptor descriptor)
    {
        getVelocityParamsForInput(velocityParams);
        getVelocityParamsForView(velocityParams, descriptor);
    }

    protected void getVelocityParamsForView(Map velocityParams, AbstractDescriptor descriptor)
    {
        if (!(descriptor instanceof ConditionDescriptor))
        {
            throw new IllegalArgumentException("Descriptor must be a ConditionDescriptor.");
        }

        ConditionDescriptor conditionDescriptor = (ConditionDescriptor)descriptor;
        velocityParams.put(FIELD_WORD, conditionDescriptor.getArgs().get(FIELD_WORD));
    }

    public Map getDescriptorParams(Map conditionParams)
    {
        // Process The map
        Map params = new HashMap();
        params.put(FIELD_WORD, extractSingleParam(conditionParams, FIELD_WORD));
        return params;
    }
}
