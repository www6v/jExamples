<html>

<head>

    <meta charset="utf-8">

    <title>Freemarker入门小DEMO </title>

</head>

<body>

${name},你好。${message}


 <table border="1">
   <tr> <th>序号</th> <th>学号</th> <th>姓名</th> <th>年龄</th> <th>家庭住址</th> </tr>

    <#list stuList as stu>
       <tr>
          <td>${stu_index}</td>
          <td>${stu.id}</td>
          <td>${stu.name}</td>
          <td>${stu.age}</td>
          <td>${stu.address}</td>
        </tr>
     </#list>
  </table>



    <#list rtcVarList as rtcVar>

       {"appId":"${rtcVar.appId}", "roomId":"${rtcVar.roomId}", "streamId":"${rtcVar.streamId}", "userId":"${rtcVar.userId}", "audio":1,"bitrate":29131,"byteCount":130267,"delay":-1,"fractionLost":0,"interval":3917,"jitter":1,"level":"info","msg":"close","mstag":"STAT","profile":"none","rtt":-1,"server":"log","session":"","ts":"2019-04-03T15:41:41.919+0800","type":"PULL","video":1}

     </#list>


</body>

</html>
