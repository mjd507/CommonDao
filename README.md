# CommonDao

可能是最简单的数据库封装,算上注解总共只有 8 个类,使用方法:

### 配置
在 Application onCreate() 时初始化 DbManager
```
    DbManager.DbParams params = new DbManager.DbParams();
    params.dbName = "xx.db";
    params.dbVersion = 1;
    DbManager.getInstance().init(this, params);
```

### 建库 建表
- 创建数据库
    ```
        DbDao dao = dbManager.getDao(null); //param  --> dbUpdateListener -- see DemoDbActivity how to use
    ```

- 创建表
    ```
        TablesManager tablesManager = TablesManager.getInstance();
        tablesManager.register(Person.class);  // Person -- javabeen
        tablesManager.createTables(dao);
    ```

### CRUD
- 增删改查
    ```
        Person person = new Person();
        person.setName("张三");
        person.setAge(20);

        //insert
        long result = dao.insert(person);

        //delete
        Boolean delete = dao.delete(Person.class, "name=?", new String[]{"张三"});

        //update
        Person person = new Person("李四", 18);
        long update = dao.update(Person.class, person, "name = ?", new String[]{"张三"});

        //query
        ArrayList<Person> personList = dao.query(false, Person.class, null, null, null, null, null, null, null);

    ```
### 权限
使用 CommonDao 需要在 AndroidManifest.xml 中 配置以下权限:
```
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```