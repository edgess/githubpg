package com.edge.dao.server;

import com.edge.dao.mapper.Name2FastDFSMapper;
import com.edge.entity.Name2FastDFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class Name2FastDFSService {
    @Autowired
    Name2FastDFSMapper name2FastDFSMapper;

    public Set<Name2FastDFS> findall() {
        return name2FastDFSMapper.findall();
    }

    public int insert(Name2FastDFS name2FastDFS) {
        return name2FastDFSMapper.insert(name2FastDFS);
    }

    public Name2FastDFS findNamebyFdfs(Name2FastDFS name2FastDFS) {
        return name2FastDFSMapper.findNamebyFdfs(name2FastDFS);
    }

    public Name2FastDFS findFdfsbyName(Name2FastDFS name2FastDFS) {
        return name2FastDFSMapper.findFdfsbyName(name2FastDFS);
    }
}
