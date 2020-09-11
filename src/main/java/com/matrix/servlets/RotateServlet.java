package com.matrix.servlets;

import com.google.gson.annotations.SerializedName;
import com.google.gson.Gson;
import com.matrix.utils.MatrixUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RotateServlet extends HttpServlet {

    // we are using specifically GET (as opposed to POST) for this because we're not actually modifying any data on server
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final Gson gson = new Gson();
        String input = request.getParameter("matrix");

        String result = null, errorMsg = null;
        try {
            int[][] matrix = MatrixUtils.parseMatrix(input);
            MatrixUtils.rotate(matrix);
            result = MatrixUtils.toString(matrix);
        } catch (Exception e) {
            errorMsg = e.toString(); // this is actually the most helpful
        }

        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        response.getWriter().write(gson.toJson(new Result(result, errorMsg)));
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
