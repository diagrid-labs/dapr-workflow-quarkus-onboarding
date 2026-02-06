package io.diagrid.workflows;

import jakarta.enterprise.context.ApplicationScoped;

import io.dapr.workflows.Workflow;
import io.dapr.workflows.WorkflowStub;
import io.diagrid.workflows.activities.FirstActivity;

@ApplicationScoped
public class SimpleWorkflow implements Workflow {

    @Override
    public WorkflowStub create() {
        return ctx -> {
            String instanceId = ctx.getInstanceId();

            ctx.getLogger().info("Starting SimpleWorkflow instance: {}", instanceId);

            ctx.callActivity(FirstActivity.class.getName()).await();

            ctx.complete("Workflow completed");
        };
    }
}
