package cn.edu.swust.qd.system.listener.excel;

import com.alibaba.excel.event.AnalysisEventListener;

/**
 * 自定义解析结果监听器
 *
 * @author <a href="https://github.com/yngcy">YounGCY</a>
 */
public abstract class MyAnalysisEventListener<T> extends AnalysisEventListener<T> {

    private String msg;

    public abstract String getMsg();
}
