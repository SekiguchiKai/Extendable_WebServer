package jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

/**
 * Messageクラスをテストするためのメソッド
 * Created by sekiguchikai on 2016/11/24.
 */
public class MessageTest {
    private Message message = new Message();

    /**
     * setNameメソッドをテストするメソッド
     * ついでにgetNameメソッドもテストする
     */
    @Test
    public void setName() {
        message.setName("sekky");
        assertThat(message.getName(), is("sekky"));
    }

    /**
     * setTextメソッドをテストするメソッド
     * ついでにgetTextメソッドも実装する
     */
    @Test
    public void setComment() {
        message.setComment("こんにちわ");
        assertThat(message.getComment(), is("こんにちわ"));
    }

    /**
     * setCreatedAtメソッドをテストするメソッド
     * ついでにgetCreatedAtメソッドを実装する
     */
    @Test
    public void setCreatedAt() {
        LocalDateTime localTimeTest = LocalDateTime.of(2016, 11, 29, 15, 0, 00);

        message.setCreatedAt(localTimeTest);
        assertThat(message.getCreatedAt(), is(localTimeTest));
    }


}