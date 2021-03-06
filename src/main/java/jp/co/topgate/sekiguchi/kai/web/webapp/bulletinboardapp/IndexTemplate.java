package jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp;


import jp.co.topgate.sekiguchi.kai.web.util.Token;
import jp.co.topgate.sekiguchi.kai.web.util.XSSMeasure;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp.model.Message;
import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.webserver.Template;

import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

/**
 * 正常な時のTemplateを表すクラス
 * Created by sekiguchikai on 2016/11/22.
 */
public class IndexTemplate implements Template {


    /**
     * Message型のStreamインタンス
     */
    Stream<Message> messageStream;

    /**
     * コンストラクタ
     * Message型のStreamインタンスを受け取り、messageStreamに設定する
     *
     * @param messageStream Message型のStreamインタンス
     */
    public IndexTemplate(Stream<Message> messageStream) {
        this.messageStream = messageStream;
    }


    /**
     * HTMLのテンプレートを作成するメソッド
     *
     * @param httpRequest  httpRequestのインスタンス
     * @param httpResponse httpResponseのインスタンス
     */
    public void writeHTML(HTTPRequest httpRequest, HTTPResponse httpResponse) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<!DOCTYPE html>")
                .append("<html lang=\"ja\">")
                .append("<head>")
                .append("<meta charset=\"UTF-8\">")
                .append("<title>掲示板</title>")
                .append("<style>")
                .append("table {")
                .append("border-collapse: collapse;")
                .append(" }")
                .append("td,")
                .append("th {")
                .append("border: solid 1px;")
                .append("padding: 0.5em;")
                .append("</style>")
                .append("</head>")
                .append("<body>");


        this.messageStream.forEach(message -> this.writeRepetition(stringBuilder, message));


        stringBuilder.append("<form action=\"/program/board/\" method=\"post\" accept-charset=\"UTF-8\">")
                .append("<table>")
                .append("<tr>")
                .append("<th>ユーザーネーム(20文字まで):</th>")
                .append("<td><input type=\"text\" name=\"name\" maxlength=\"20\" size=\"40\"></td>")
                .append("</tr>")
                .append("<tr>")
                .append("<th>コメント(1000文字まで)</th>")
                .append("<td><textarea name=\"comment\" rows=\"4\" cols=\"40\" maxlength=\"1000\" placeholder=\"ここにコメントを記入してください\"></textarea></td>")
                .append("</tr>")
                .append("</table>")
                .append("<input type=\"hidden\" name=\"token\" value=\"" + Token.getToken() + "\">")
                .append("<p><input type=\"submit\" value=\"送信する\"></p>")
                .append("</form>")
                .append("</body>")
                .append("</html>")


                .append("指定したユーザーの書き込みのみ表示させることができます。<br>")
                .append("下記で、検索したいユーザー名を指定してください<br>")

                .append("<form action=\"/program/board/message/\" method=\"get\" accept-charset=\"UTF-8\">")
                .append("<table>")
                .append("<tr>")
                .append("<th>ユーザーネーム:</th>")
                .append("<td><input type=\"text\" name=\"searchName\" maxlength=\"20\"></td>")
                .append("</tr>")
                .append("</table>")
                .append("<input type=\"hidden\" name=\"token\" value=\"" + Token.getToken() + "\">")
                .append("<input type=\"hidden\" name\"process\" value=\"search\">")
                .append("<input type=\"submit\" value=\"検索する\">")
                .append("</form>")

                .append("<a href= \"/program/board/\">全件表示</a>")


                .append("</body>")
                .append("</html>");

        httpResponse.setDynamicResponseBody(new String(stringBuilder).getBytes());

    }

    /**
     * 指定された回数、繰り返し部分のHTMLを作成し、返すメソッド
     *
     * @param stringBuilder StringBuilderクラスのインスタンス
     */

    private void writeRepetition(StringBuilder stringBuilder, Message message) {
        if (message.getName().equals("") || message.getComment().equals("")) {
            return;
        }
        StringBuilder append = stringBuilder.append("<table>")
                .append("<tr>")
                .append("<th>投稿日時:</th>")
                .append("<td>" + DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(message.getCreatedAt()) + "</td>")
                .append("</tr>")

                .append("<tr>")
                .append("<th>ユーザーネーム:</th>")
                .append("<td>" + XSSMeasure.sanitize(message.getName()) + "</td>")
                .append("</tr>")

                .append("<tr>")
                .append("<th>コメント</th>")
                .append("<td>" + XSSMeasure.sanitize(message.getComment()) + "</td>")
                .append("</tr>")
                .append("</table>")
                .append("<form action=\"/program/board/message/\" method=\"post\" accept-charset=\"UTF-8\">")
                .append("<input type=\"hidden\" name=\"token\" value=\"" + Token.getToken() + "\">")
                .append("<p>")
                .append("<input type=\"hidden\" name =\"delete\" value=" + message.getId() + ">")
                .append("<input type=\"hidden\" name\"process\" value=\"delete\">")
                .append("<input type=\"submit\"  value=\" 削除 \"")
                .append("</p>")
                .append("</form>");

    }
}
