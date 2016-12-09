package jp.co.topgate.sekiguchi.kai.web.webServer;

import jp.co.topgate.sekiguchi.kai.web.http.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.http.HTTPResponse;

import java.io.IOException;

/**
 * Templateクラスの継承元のインターフェース
 * Created by sekiguchikai on 2016/11/26.
 */
public interface Template {

    void writeHTML(HTTPRequest httpRequest, HTTPResponse httpResponse) throws IOException;

}
