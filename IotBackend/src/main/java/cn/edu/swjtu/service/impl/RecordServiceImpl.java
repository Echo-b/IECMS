package cn.edu.swjtu.service.impl;

import cn.edu.swjtu.mapper.RecordMapper;
import cn.edu.swjtu.pojo.CommandInfo;
import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordMapper mapper;


    @Override
    public int InsertCommandRecord(CommandInfo c){
        int res = 0;
        try {
            res = mapper.InsertCommandRecord(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  res;
    }

    @Override
    public ResponseData getAllCommandRecord(String operator) {
        ArrayList<CommandInfo> commandInfos = mapper.getAllCommandRecord(operator);
        return ResponseData.success("success").data("records",commandInfos);
    }
}
