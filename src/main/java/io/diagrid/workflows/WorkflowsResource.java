package io.diagrid.workflows;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import io.dapr.workflows.client.DaprWorkflowClient;
import io.dapr.workflows.client.WorkflowRuntimeStatus;
import io.dapr.workflows.client.WorkflowState;
import io.quarkus.logging.Log;

@Path("/workflows")
public class WorkflowsResource {

    @Inject
    DaprWorkflowClient daprWorkflowClient;

    @POST
    @Path("/start")
    @Produces(MediaType.TEXT_PLAIN)
    public Response startWorkflow() {
        String instanceId = daprWorkflowClient.scheduleNewWorkflow(SimpleWorkflow.class);
        Log.info("Starting SimpleWorkflow with instance ID: " + instanceId);
        return Response.accepted()
                .header("Workflow-Instance-Id", instanceId)
                .entity(instanceId)
                .build();
    }

    @GET
    @Path("/{workflowId}/status")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getWorkflowStatus(@PathParam("workflowId") String workflowId) {
        WorkflowState state = daprWorkflowClient.getWorkflowState(workflowId, true);
        assert state != null;

        if (state.getRuntimeStatus().equals(WorkflowRuntimeStatus.COMPLETED)) {
            Log.info("Workflow completed successfully");
            return Response.ok(state.getSerializedOutput()).build();
        }

        Log.info("Current status: " + state.getRuntimeStatus());
        return Response.ok(state.getRuntimeStatus().toString()).build();
    }
}
