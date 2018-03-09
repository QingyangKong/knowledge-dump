### Why use it?  
Unknown type can be picked up and specific method can be called if the type following a interface.  
Reflection is a feature that allows unknown types detected in run time. In my work experience, the feature is usually used to add types sharing the existing interfaces while the project is too big to be redeployed. For a project, if it is supposed to be upgraded without break the existing source code, reflection is used to add new types and microservices are used to add new logics.   

Check this project https://github.com/QingyangKong/UserBadgerSystem to see an example.  
