package service;

import entity.ExpRec;
import entity.Record;

import java.util.List;
import java.util.Map;

/**
 * Created by Bruce-Jiang on 2016/10/9.
 */
public abstract class RecordService {
    /**
     * 插入一条答题记录
     * @param record 答题记录Model
     * @return
     */
    public abstract int insertOneRecord(Record record);

    /**
     * 查询用户是第几次做题
     * @param u_id 用户编号
     * @return
     */
    public abstract int queryForFrequency(int u_id);

    /**
     * 查询用户第几次做题的做题结果
     * @param num 第几次
     * @param u_id  用户编号
     * @return
     */
    public abstract List<ExpRec> queryForManyRecords(int num, int u_id);
}
