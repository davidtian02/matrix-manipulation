package com.matrix.servlets;

import com.matrix.utils.MatrixUtils;
import com.google.gson.annotations.SerializedName;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RotateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final Gson gson = new Gson();
        String input = request.getParameter("matrix");

        // TODO implement

        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        response.getWriter().write(gson.toJson(new Result("", "")));
    }

    class Result {
        @SerializedName("result")
        String mResult;

        @SerializedName("error_message")
        String mErrorMessage;

        Result(String result, String errorMessage) {
            mResult = result;
            mErrorMessage = errorMessage;
        }
    }
}
