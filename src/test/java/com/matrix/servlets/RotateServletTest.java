package com.matrix.servlets;

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

public class RotateServletTest {

    private final LocalServiceTestHelper mHelper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig())
                    .setEnvIsLoggedIn(true)
                    .setEnvAuthDomain("localhost")
                    .setEnvEmail("test@localhost");
    private RotateServlet mSubject;

    @Before
    public void setUp() {
        mHelper.setUp();
        mSubject = new RotateServlet();
    }

    @After
    public void tearDown() {
        mHelper.tearDown();
    }

    @Test
    public void doGet_withInputMatrix_shouldReturnResponseWithRotatedMatrixResponse() throws IOException {
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final StringWriter stringWriter = new StringWriter();
        when(request.getParameter("matrix")).thenReturn("[[1,2,3],[4,5,6],[7,8,9]]");
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));

        mSubject.doGet(request, response);

        assertEquals("{\"result\":\"[[7, 4, 1],[8, 5, 2],[9, 6, 3]]\"}", stringWriter.toString());
    }

    @Test
    public void doGet_withEmptyMatrix_shouldReturnHelpfulErrorMessage() throws IOException {
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final StringWriter stringWriter = new StringWriter();
        when(request.getParameter("matrix")).thenReturn("[[]]");
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));

        mSubject.doGet(request, response);

        assertEquals("{\"error_message\":\"java.lang.IllegalArgumentException: Please provide valid matrix with at least 1 point in matrix\"}", stringWriter.toString());
    }

    @Test
    public void doGet_withInvalidChars_shouldReturnSpecificErrorMessage() throws IOException {
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final StringWriter stringWriter = new StringWriter();
        when(request.getParameter("matrix")).thenReturn("[asdfasefaxcddwf");
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));

        mSubject.doGet(request, response);

        assertTrue(stringWriter.toString().contains("{\"error_message\":\"java.lang.NumberFormatException: For input string:"));
    }

    @Test
    public void doGet_withRectangleMatrix_shouldReturnHelpfulErrorMessage() throws IOException {
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final StringWriter stringWriter = new StringWriter();
        when(request.getParameter("matrix")).thenReturn("[[1],[4],[222]]");
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));

        mSubject.doGet(request, response);

        assertEquals("{\"error_message\":\"java.lang.IllegalArgumentException: Matrix is not a square. Please provide square matrix\"}", stringWriter.toString());
    }

    @Test
    public void doGet_withoutParameter_shouldReturnHelpfulErrorMessage() throws IOException {
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final StringWriter stringWriter = new StringWriter();
        when(request.getParameter("matrix")).thenReturn(null);
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));

        mSubject.doGet(request, response);

        assertEquals("{\"error_message\":\"java.lang.IllegalArgumentException: Please provide non-null input\"}", stringWriter.toString());
    }
}