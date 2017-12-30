RPC(Remote Process call) and REST(Representational state trnasfer) are basically 2 structure design patterns.  
Both of them are used to require service from a remote server(sometimes in the same server). In my understanding, RPC is older than REST so people think in simple, that is, I tell what to do and I tell all information and server will do it,, while when the application is larger and larger, people find that it is a very difficult to remember everything for RPC call, so they make it simple by only providing limited verbs for CURD.   

Examples:  

Add a user  
in RPC style:  
(POST) `http://domain/request/addItem`  
in REST style:  
(PUT) `http://domain/request/Item`
```
<person>
  <userName>userName1</userName>
  <isMember>false</isMember>
</person>
```

Modify a user
in RPC style:  
(POST) `http://domain/request/updateItem`  
in REST style:  
(POST) `http://domain/request/Item`
```
<person>
  <username>userName1</username>
  <isMember>true</isMember>
</person>
```

Delete a user
in RPC:  
(GET) `http://domain/request/deleteItem/1`  
in REST:  
(DELETE) `http://domain/request/Item/1`  

Read a user  
in RPC:  
(GET)`http://domain/request/getItem/1`  
in REST:  
(GET)`http://domain/request/Item/1`  

REST is resource-oriented and RPC is operation-oriente. REST use different verbs to indicate CRUD and RPC only uses verbs GET(if less parameters) and POST(if more parameters) to use different urls to do specific manipulations.  
IMO, RPC can do everything that REST can do, the only benefit for REST is that consistence is easy to preserve. Because there are only limited verbs to use for CURD, it is easy for developer to use API when the api is design for public. If it is just a small app to be used, it really dose not matter which style to use.  
