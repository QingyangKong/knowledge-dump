Confusion of Target Framework in the VS 2015 and 2017

When create a new project in Visual Studio, there is a drop down list provided to choose a target frameowrk version. In VS 2015 and 2017, .net core projects are supported but version of .net core never exists in the dropdown list.  
.net core versions are not listed there because your selection in the dropdown list only effective when you are creating a project based on .net framework. If you are creating a .net core project, this drop down list does not make sense.
Don't worry about it when you are creating a new project based on .net core no matter 1.0 or 2.0. Eg, it does not make sense to choose a framework version when creates a `Console App(.Net core)`.
