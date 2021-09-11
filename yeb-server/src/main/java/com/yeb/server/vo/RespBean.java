package com.yeb.server.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBean {

        private long code;
        private String message;
        private Object obj;

        /**
         * 成功返回
         * @return
         */
        public static RespBean success(String message){
            return new RespBean(200,message,null);
        }


        //重载
        public static RespBean success(String message,Object object){
            return new RespBean(200,message,object);
        }

        /**
         * 失败返回
         * @return
         */
        public static RespBean error(String message){
            return new RespBean(500,message,null);
        }


        //重载
        public static RespBean error(String message,Object object){
            return new RespBean(500,message,object);
        }
}
