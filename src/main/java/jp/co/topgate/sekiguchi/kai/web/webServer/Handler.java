package jp.co.topgate.sekiguchi.kai.web.webServer;

import java.io.IOException;

/**
 * Handlerの継承元クラス
 * Created by sekiguchikai on 2016/12/03.
 */
public class Handler {
    /**
     * リクエストGETの際のHandler
     *
     * @param httpRequest  httpRequestのインスタンス
     * @param httpResponse httpResponseのインスタンス
     */
    public void handleGET(HTTPRequest httpRequest, HTTPResponse httpResponse) throws Exception {
        // オーバーライドしない場合は、404を返す
        httpResponse.addStatusLine(HTTPResponse.SC_NOT_FOUND);
        Template template = new ErrorTemplate();
        template.writeHTML(httpRequest, httpResponse);
    }

    /**
     * リクエストPOSTの際のHandler
     *
     * @param httpRequest  httpRequestのインスタンス
     * @param httpResponse httpResponseのインスタンス
     */
    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) throws Exception {
        // オーバーライドしない場合は、404を返す
        httpResponse.addStatusLine(HTTPResponse.SC_NOT_FOUND);
        Template template = new ErrorTemplate();
        template.writeHTML(httpRequest, httpResponse);
    }
}
