package io.diagrid.workflows.activities;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import io.dapr.workflows.WorkflowActivity;
import io.dapr.workflows.WorkflowActivityContext;
import io.diagrid.workflows.services.CounterService;
import io.quarkus.logging.Log;

@ApplicationScoped
public class FirstActivity implements WorkflowActivity {

    @Inject
    CounterService counterService;

    @Override
    public Object run(WorkflowActivityContext ctx) {
        Log.info("Executing the First activity.");
        counterService.incrementCounter();
        return null;
    }
}
