package dev.marekvoe.marekservermanagement.config;

import dev.marekvoe.marekservermanagement.services.SetupService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SetupFilter extends OncePerRequestFilter {

    private final SetupService setupService;

    public SetupFilter(SetupService setupService) {
        this.setupService = setupService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String uri = request.getRequestURI();

        boolean isSetupUri = uri.startsWith("/api/setup");
        boolean isSwaggerUi = uri.startsWith("/swagger-ui");
        boolean isApiDocs   = uri.startsWith("/v3/api-docs");
        boolean isWebjars   = uri.startsWith("/webjars/");
        boolean isRootOrError = uri.equals("/") || uri.equals("/swagger-ui.html") || uri.equals("/error");

        if (!setupService.isSetupComplete()
                && !isSetupUri
                && !isSwaggerUi
                && !isApiDocs
                && !isWebjars
                && !isRootOrError) {

            response.sendError(HttpStatus.FORBIDDEN.value(),
                    "Setup is not complete. Please complete the setup process.");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
