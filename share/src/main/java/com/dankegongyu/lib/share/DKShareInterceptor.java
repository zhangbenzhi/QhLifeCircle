package com.dankegongyu.lib.share;

/**
 * @author wupuquan
 * @version 1.0
 * @since 2018/3/22 19:09
 */
public interface DKShareInterceptor<T> {

    void interceptor(T t, DK_SHARE_MEDIA share_media);
}
