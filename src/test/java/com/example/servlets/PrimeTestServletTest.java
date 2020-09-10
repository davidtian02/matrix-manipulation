package com.example.servlets;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

public class PrimeTestServletTest {

    private final LocalServiceTestHelper mHelper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig())
                    .setEnvIsLoggedIn(true)
                    .setEnvAuthDomain("localhost")
                    .setEnvEmail("test@localhost");
    private PrimeTestServlet mSubject;

    @Before
    public void setUp() {
        mHelper.setUp();
        mSubject = new PrimeTestServlet();
    }

    @After
    public void tearDown() {
        mHelper.tearDown();
    }

    @Test
    public void doGet_withPrimeNumber_shouldReturnResponseWithResultTrue() throws IOException {
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final StringWriter stringWriter = new StringWriter();
        when(request.getParameter("number")).thenReturn("7");
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));

        mSubject.doGet(request, response);

        assertEquals("{\"result\":true}", stringWriter.toString());
    }

    @Test
    public void doGet_withCompositeNumber_shouldReturnResponseWithResultFalse() throws IOException {
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final StringWriter stringWriter = new StringWriter();
        when(request.getParameter("number")).thenReturn("10");
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));

        mSubject.doGet(request, response);

        assertEquals("{\"result\":false}", stringWriter.toString());
    }

    @Test
    public void doGet_withMissingParam_shouldReturnFalseWithErrorMessage() throws IOException {
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final StringWriter stringWriter = new StringWriter();
        when(request.getParameter("number")).thenReturn(null);
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));

        mSubject.doGet(request, response);

        assertEquals("{\"result\":false,\"error_message\":\"Invalid input, please set parameter to be valid integer.\"}", stringWriter.toString());
    }

    @Test
    public void doGet_withInvalidParamType_shouldReturnFalseWithErrorMessage() throws IOException {
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final StringWriter stringWriter = new StringWriter();
        when(request.getParameter("number")).thenReturn("STRING");
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));

        mSubject.doGet(request, response);

        assertEquals("{\"result\":false,\"error_message\":\"Invalid input, please set parameter to be valid integer.\"}", stringWriter.toString());
    }
}