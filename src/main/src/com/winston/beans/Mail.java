package com.winston.beans;

import lombok.*;

import java.util.Set;


/**
 * @Author: 于新泽
 * @Date: Created in 23:33 2018/10/8.
 * @site :
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    // 邮件的主题
    private String subject;

    // 邮件的内容
    private String message;

    // 收件人的邮箱 （可以是多个）
    private Set<String> receivers;
}
