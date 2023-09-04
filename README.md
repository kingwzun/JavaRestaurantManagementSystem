# JavaRestaurantManagementSystem
酒家管理系统
# 系统结构
![image](https://github.com/kingwzun/JavaRestaurantManagementSystem/assets/75526768/5c88bc18-e01d-4fb3-8283-c2b0eb2c0281)
## 系统体系结构
分三层架构为了符合“高内聚、低耦合”思想，把各个功能模块划分为表示层(UI).业务逻辑层（BLL)和数据访问层(DAL）三层架构，各层之间采用接口相互访问，并通过对象模型的实体类(Entity)作为数据传递的载体，不同的对象模型的实体(entity)类一般对应于数据库的不同表。
使用了数据库连接池: c3p0，让程序和易变的文本分开，便于后期修改数据库密码等顺序。 
- ![image](https://github.com/kingwzun/JavaRestaurantManagementSystem/assets/75526768/4d77dbbd-e345-4925-8d86-7b968f966864)

# 页面展示
- 主页面
![image](https://github.com/kingwzun/JavaRestaurantManagementSystem/assets/75526768/cfe74c4e-93ba-4b3a-9f85-38edd080a32f)

- 添加餐桌信息
![image](https://github.com/kingwzun/JavaRestaurantManagementSystem/assets/75526768/ba11840b-5951-47f0-92c1-068d1601f39d)

- 修改餐桌信息
![image](https://github.com/kingwzun/JavaRestaurantManagementSystem/assets/75526768/0e3c663a-13c7-4ea8-842f-5909629a46b3)
- 查询餐桌信息
![image](https://github.com/kingwzun/JavaRestaurantManagementSystem/assets/75526768/27a65182-d00b-4ed0-b4c2-b8c11d8b7031)

- 结账页面
- ![image](https://github.com/kingwzun/JavaRestaurantManagementSystem/assets/75526768/fbcbadca-df98-45b2-9ea6-a58807699fa2)

-登录
![image](https://github.com/kingwzun/JavaRestaurantManagementSystem/assets/75526768/03e26baf-3a31-4766-8343-617874639336)
- 注册
![image](https://github.com/kingwzun/JavaRestaurantManagementSystem/assets/75526768/99317917-85e2-45f7-8086-a068098d88b2)
