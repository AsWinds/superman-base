# Superman Workflow

## Acitivti

- 5.22.0


## 注意事项

- `List<ProcessDefinition>` 和FastJson不兼容，主要是由于`ProcessDefinition`的实现类`ProcessDefinitionEntity`中的get方法都会被fastjson序列化从而导致报错
````
<property name="features">
    <list>
        <!-- <value>WriteMapNullValue</value> -->
        <value>QuoteFieldNames</value>
        <!-- <value>WriteDateUseDateFormat</value> -->
        <!--吃掉get异常-->
        <value>IgnoreErrorGetter</value>
    </list>
</property>
````
