package cn.edu.swjtu.mapper;

import cn.edu.swjtu.pojo.WorkerNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface WorkerNodeMapper {

    int addWorkerNode(WorkerNode workerNodeEntity);


    WorkerNode getWorkerNodeByHostPort(@Param("host") String host, @Param("port") String port);

}