> 说明:  ip: http://127.0.0.1
        request: 请求数据: 
## user :
- 注册用户: 
    url: ip/user/reg 
    request:  User (前端提供表单(user表对应下),username,password必填)
    response:  result.setData(1);
- 登录用户: 
    url:  ip/user/login
    request: User{username:'',password:''}
    response: 
             result.setData(user1);
             request.getSession().setAttribute("uid", user1.getUid());

- 修改用户: 
     url: ip/user/update  
     request: User(表单)
     response:  result.data = null (直接返回提示信息)

- 用户权限(后台):
     url: ip/user/role   
     request: User(当前用户){uid:''}  uid required 
     response: [menu,menu,menu]

- 激活用户: (TODO)
     url:   
     request:
     response:
 

## product : 

## category : 

## 