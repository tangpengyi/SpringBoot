package com.tpy.springboot_jdbc_druid.controller;

import com.tpy.springboot_jdbc_druid.api.ResponseResult;
import com.tpy.springboot_jdbc_druid.utils.JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @Autowired
    private JDBCUtils jdbcUtils;

    @GetMapping("test")
    public ResponseResult test(){
        String sql = " declare @file_id varchar(100) = ?\n" +
                "\t, @type varchar(30) = ?\n" +
                "\t, @description varchar(200) = ?\n" +
                "\t, @store_id int = ?\n" +
                "\t, @user_id int = ?\n" +
                "\n" +
                "-- 出仓，资料要存在于库存表里\n" +
                "-- 如果某个色号不在仓库里，则出仓失败\n" +
                "\n" +
                "declare @message varchar(400)\n" +
                "if not exists(select 1 from [warehouse].[td_file] with(nolock) WHERE id = @file_id)\n" +
                "begin\n" +
                "\tset @message = '：系统没有这份资料。'\n" +
                "\traiserror(@message, 16, 1)\n" +
                "\treturn\n" +
                "end\n" +
                "\n" +
                "if not exists(select 1 from [warehouse].[td_file_store] with(nolock) where file_id = @file_id)\n" +
                "begin\n" +
                "\tset @message = 2 + ' - ' + 2 + '：资料已经被xxx在xxx(时间)借出。'\n" +
                "\traiserror(@message, 16, 1)\n" +
                "\treturn\n" +
                "end\n" +
                "\n" +
                "begin tran t1\n" +
                "begin try\n" +
                "\tinsert [warehouse].[td_file_check_out] ([file_id],[type], [description], [store_id]\n" +
                "\t\t, [check_out_user] ,[create_user], [create_date], [modify_user], [modify_date])\n" +
                "\t\tvalues (@file_id,@type, @description, @store_id\n" +
                "\t\t\t,'出仓人', @user_id, getdate(), @user_id, getdate())\n" +
                "\n" +
                "\tdelete from [warehouse].[td_file_store] where [file_id] = @file_id\n" +
                "\t\n" +
                "\tif @@error = 0\n" +
                "\t\tcommit tran [t1]\n" +
                "end try\n" +
                "begin catch\n" +
                "\tselect @message = error_message()\n" +
                "\trollback tran [t1]\n" +
                "\traiserror(@message, 16, 1)\n" +
                "end catch\n";
        ResponseResult responseResult = jdbcUtils.executeSql(sql,"26","类型","备注",1,1);
        return responseResult;
    }


    @GetMapping("query")
    public ResponseResult query(){
//        String sql = "select top 2 color_no,color_name from warehouse.td_file";
        String sql = "select top 2 color_no,color_name from warehouse.td_file where status in(?,?)";
        Map map = new HashMap();
        map.put(1,"报废");
        map.put(2,"正常");

        return jdbcUtils.executeQueryMap(sql,map);

    }


    public static String params(String ... params){
        return params[0];
    }


    public static void main(String[] args) {
        Map map = new HashMap<String,String>();
        map.put("1","张三");
        System.out.println(map.size());
    }
}

