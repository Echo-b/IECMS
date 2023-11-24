package cn.edu.swjtu.service;

import cn.edu.swjtu.pojo.CommandInfo;
import cn.edu.swjtu.result.ResponseData;

public interface RecordService {
    int InsertCommandRecord(CommandInfo c);

    ResponseData getAllCommandRecord(String operator);
}
