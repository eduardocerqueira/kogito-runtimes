package org.kie.kogito.addon.source.files;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.kie.kogito.resource.exceptions.ExceptionsHandler;

@ApplicationScoped
@Path("/management/processes/")
public final class SourceFilesResource {

    private static final ExceptionsHandler EXCEPTIONS_HANDLER = new ExceptionsHandler();

    SourceFilesProvider sourceFilesProvider;

    @GET
    @Path("sources")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getSourceFileByUri(@QueryParam("uri") String uri) {
        return sourceFilesProvider.getSourceFilesByUri(uri)
                .map(sourceFile -> {
                    try (InputStream file = new ByteArrayInputStream(sourceFile.readContents())) {
                        return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
                                .header("Content-Disposition", "inline; filename=\"" + java.nio.file.Path.of(sourceFile.getUri()).getFileName() + "\"")
                                .build();
                    } catch (Exception e) {
                        return EXCEPTIONS_HANDLER.mapException(e);
                    }
                }).orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("{processId}/sources")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<SourceFile> getSourceFilesByProcessId(@PathParam("processId") String processId) {
        return sourceFilesProvider.getProcessSourceFiles(processId);
    }

    @GET
    @Path("{processId}/source")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getSourceFileByProcessId(@PathParam("processId") String processId) {
        return sourceFilesProvider.getProcessSourceFile(processId)
                .map(sourceFile -> {
                    try {
                        return Response.ok(sourceFile.readContents()).build();
                    } catch (IOException e) {
                        return EXCEPTIONS_HANDLER.mapException(e);
                    }
                }).orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @Inject
    void setSourceFilesProvider(SourceFilesProvider sourceFilesProvider) {
        this.sourceFilesProvider = sourceFilesProvider;
    }
}
