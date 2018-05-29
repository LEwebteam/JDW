package com.mmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.mmall.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.schema.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.util.ElementScanner6;
import java.util.List;

/**
 * Created by geely
 */

@Slf4j
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = null;
        try {
            user = userMapper.selectLogin(username, md5Password);
        } catch (Exception e) {
            log.error("用户和密码检验:{}",e);
            return ServerResponse.createByErrorMessage("密码错误");
        }
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }

        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功", user);
    }


    public ServerResponse<String> register(User user) {
        ServerResponse validResponse = this.checkValid(user.getUsername(), Const.USERNAME);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
 /*       validResponse = this.checkValid(user.getEmail(), Const.EMAIL);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }*/
        user.setRole(Const.Role.ROLE_CUSTOMER);
        //MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("新增失败");
        }
        return ServerResponse.createBySuccessMessage("新增成功");
    }

    public ServerResponse<String> checkValid(String str, String type) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(type)) {
            //开始校验
            if (Const.USERNAME.equals(type)) {
                int resultCount = userMapper.checkUsername(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            /*if(Const.EMAIL.equals(type)){
                int resultCount = userMapper.checkEmail(str);
                if(resultCount > 0 ){
                    return ServerResponse.createByErrorMessage("email已存在");
                }
            }*/
        } else {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    /* public ServerResponse selectQuestion(String username){

         ServerResponse validResponse = this.checkValid(username,Const.USERNAME);
         if(validResponse.isSuccess()){
             //用户不存在
             return ServerResponse.createByErrorMessage("用户不存在");
         }
         String question = userMapper.selectQuestionByUsername(username);
         if(org.apache.commons.lang3.StringUtils.isNotBlank(question)){
             return ServerResponse.createBySuccess(question);
         }
         return ServerResponse.createByErrorMessage("找回密码的问题是空的");
     }

     public ServerResponse<String> checkAnswer(String username,String question,String answer){
         int resultCount = userMapper.checkAnswer(username,question,answer);
         if(resultCount>0){
             //说明问题及问题答案是这个用户的,并且是正确的
             String forgetToken = UUID.randomUUID().toString();
             TokenCache.setKey(TokenCache.TOKEN_PREFIX+username,forgetToken);
             return ServerResponse.createBySuccess(forgetToken);
         }
         return ServerResponse.createByErrorMessage("问题的答案错误");
     }



     public ServerResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken){
         if(org.apache.commons.lang3.StringUtils.isBlank(forgetToken)){
             return ServerResponse.createByErrorMessage("参数错误,token需要传递");
         }
         ServerResponse validResponse = this.checkValid(username,Const.USERNAME);
         if(validResponse.isSuccess()){
             //用户不存在
             return ServerResponse.createByErrorMessage("用户不存在");
         }
         String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX+username);
         if(org.apache.commons.lang3.StringUtils.isBlank(token)){
             return ServerResponse.createByErrorMessage("token无效或者过期");
         }

         if(org.apache.commons.lang3.StringUtils.equals(forgetToken,token)){
             String md5Password  = MD5Util.MD5EncodeUtf8(passwordNew);
             int rowCount = userMapper.updatePasswordByUsername(username,md5Password);

             if(rowCount > 0){
                 return ServerResponse.createBySuccessMessage("修改密码成功");
             }
         }else{
             return ServerResponse.createByErrorMessage("token错误,请重新获取重置密码的token");
         }
         return ServerResponse.createByErrorMessage("修改密码失败");
     }



 */
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user) {
        //防止横向越权,要校验一下这个用户的旧密码,一定要指定是这个用户.因为我们会查询一个count(1),如果不指定id,那么结果就是true啦count>0;
        int resultCount = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld), user.getId());
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("旧密码错误");
        }

        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMessage("密码更新成功");
        }
        return ServerResponse.createByErrorMessage("密码更新失败");
    }

    @Override
    public ServerResponse<PageInfo> getUserList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.selectAllUser();
//        List<OrderVo> orderVoList = this.assembleOrderVoList(orderList,null);
        PageInfo pageResult = new PageInfo(userList);
        pageResult.setList(userList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<String> resetPassword(String passwordNew, User user) {
        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMessage("密码更新成功");
        }
        return ServerResponse.createByErrorMessage("密码更新失败");
    }

    @Override
    public ServerResponse<String> deleteUser(Integer userId) {
        int count = userMapper.deleteByPrimaryKey(userId);
        if(count>=1)
        return ServerResponse.createBySuccessMessage("删除成功");
        else
            return ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse<String> setadmin(Integer userid) {
        if(userid==null){
            return ServerResponse.createByErrorMessage("请输入userid");
        }
        User user =userMapper.selectByPrimaryKey(userid);
        if(user!=null){
            user.setRole(Const.Role.ROLE_ADMIN);
            userMapper.updateByPrimaryKey(user);
            return ServerResponse.createBySuccessMessage("升级成为管理员成功");
        }
        return ServerResponse.createByErrorMessage("升级失败-user为空");

    }

    @Override
    public ServerResponse<String> canceladmin(Integer userid) {
        if(userid==null){
            return ServerResponse.createByErrorMessage("请输入userid");
        }
        User user =userMapper.selectByPrimaryKey(userid);
        if(user!=null){
            user.setRole(Const.Role.ROLE_CUSTOMER);
            userMapper.updateByPrimaryKey(user);
            return ServerResponse.createBySuccessMessage("设置成普通用户成功");
        }
        return ServerResponse.createByErrorMessage("设置失败-user为空");

    }

    @Override
    public ServerResponse<PageInfo> getUserListByusername(int pageNum, int pageSize, String username) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.selectByUserName(username);
//        List<OrderVo> orderVoList = this.assembleOrderVoList(orderList,null);
        PageInfo pageResult = new PageInfo(userList);
        pageResult.setList(userList);
        return ServerResponse.createBySuccess(pageResult);
    }

    public ServerResponse<User> updateInformation(User user) {
        //username是不能被更新的
        //email也要进行一个校验,校验新的email是不是已经存在,并且存在的email如果相同的话,不能是我们当前的这个用户的.
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setOfficeId(user.getOfficeId());
        updateUser.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
     /*   updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());*/

        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        updateUser = userMapper.selectByPrimaryKey(updateUser.getId());
        if (updateCount > 0) {
            return ServerResponse.createBySuccess("更新个人信息成功", updateUser);
        }
        return ServerResponse.createByErrorMessage("更新个人信息失败");
    }


    public ServerResponse<User> getInformation(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return ServerResponse.createByErrorMessage("找不到当前用户");
        }
        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);

    }


    //backend

    /**
     * 校验是否是管理员
     *
     * @param user
     * @return
     */
    public ServerResponse checkAdminRole(User user) {
        if (user != null && user.getRole().intValue() == Const.Role.ROLE_ADMIN) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }


}
