package jp.co.topgate.sekiguchi.kai.web;

import java.io.File;

/**
 * 全体のプログラムを制御するクラス
 *
 * @author sekiguchikai
 */
public class StaticFileHandler implements Handler {


    /**
     * リクエストメソッドがGETメソッドの場合の処理
     *
     * @param httpRequest  HTTPRequestクラスのインスタンス
     * @param httpResponse HTTPResponseクラスのインスタンス
     */
    public void handleGET(HTTPRequest httpRequest, HTTPResponse httpResponse) {

        String requestURI = httpRequest.getRequestURI(httpRequest.getSecondSentence(httpRequest.getRequestLine()));

        Files files = new Files();

        File file;
        String requestResource;

        if ((requestURI.endsWith("/")) || !(requestURI.substring(requestURI.lastIndexOf("/"), requestURI.length()).contains("."))) {
            requestResource = "src/main/resources" + requestURI + "index.html";
            file = new File(requestResource);
        } else {
            requestResource = "src/main/resources" + requestURI;
            file = new File(requestResource);
        }

        String extension = requestResource.substring(requestResource.lastIndexOf(".") + 1,
                requestResource.lastIndexOf(""));

        if ((file.exists()) && (extension.equals("html") || extension.equals("css") || extension.equals("js") || (extension.equals("png") || extension.equals("jpeg") || extension.equals("gif")))) {
            httpResponse.setStatusLine("HTTP/1.1 200 OK");
            httpResponse.setResponseHeader(extension);
            httpResponse.setResponseBody(files.readFile(file));
        } else {
            httpResponse.setStatusLine("HTTP/1.1 404 Not Found");
            httpResponse.setResponseHeader("html");
            httpResponse.setResponseBody("404 Not Found".getBytes());
        }

        httpResponse.sendResponse();

    }

    /**
     * リクエストメソッドがPOSTメソッドの場合の処理
     *
     * @param httpRequest  HTTPRequestクラスのインスタンス
     * @param httpResponse HTTPResponseクラスのインスタンス
     */

    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) {
    }

}

