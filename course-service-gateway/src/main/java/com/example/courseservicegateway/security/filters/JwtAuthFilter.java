package com.example.courseservicegateway.security.filters;

import com.example.courseservicegateway.security.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (request.getRequestURI().equals("/error")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        try {
            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.extractUsername(token);

                // Inject the X-Username header
                HttpServletRequest wrappedRequest = wrapRequestWithUserHeaders(request, username);

                // Optionally set security context
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(username, null,
                                Collections.singletonList(new SimpleGrantedAuthority("USER")));
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);

                // Continue with wrapped request
                filterChain.doFilter(wrappedRequest, response);
                return;
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private HttpServletRequest wrapRequestWithUserHeaders(HttpServletRequest request, String username) {

        return new HttpServletRequestWrapper(request) {

            @Override
            public String getHeader(String name) {
                if ("X-Username".equalsIgnoreCase(name)) {
                    return username;
                }
                return super.getHeader(name);
            }

            @Override
            public Enumeration<String> getHeaderNames() {
                // Get original header names, filter out any "X-Username"
                List<String> headerNames = Collections.list(super.getHeaderNames())
                        .stream()
                        .filter(h -> !h.equalsIgnoreCase("X-Username"))
                        .collect(Collectors.toList());

                // Add your single X-Username header
                headerNames.add("X-Username");

                return Collections.enumeration(headerNames);
            }

            @Override
            public Enumeration<String> getHeaders(String name) {
                if ("X-Username".equalsIgnoreCase(name)) {
                    // Return only your username, ignoring originals
                    return Collections.enumeration(Collections.singletonList(username));
                }
                // For other headers, just return as-is
                return super.getHeaders(name);
            }
        };

    }

}



