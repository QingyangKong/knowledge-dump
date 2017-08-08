## Namespace Control and function modularization:

In typescript and javascript, functions can be passed into another function as a parameter:  
One practice of this is to control the access of functions and the other one I think is to modularize functions.  
Talk is cheap and see codes:  

    ...
    private delay = (function(){
        let timer = 0;
        return function(callback: any, ms: number){
            clearTimeout(timer);
            timer = setTimeout(callback, ms);
        };
    })();
    
    private searchClient(event = undefined){
        this.delay(() => {
            if(this.clientsBeforeSearch == undefined){
                this.clientsBeforeSearch = this.uniqueClients;
            }
            this.uniqueClients = this.clientsBeforeSearch.filter(client => client.indexOf(this.clientToSearch) != -1);
            return;
        }, 1000);
    }
    ...

In this example, there is a text input in front-end where user is supposed to input some information, and in this process, user may want 1 sec to finish typing in html page.  

So I can write a method called delay, and what the function delay does is to receive a function, delay for a second and then execute received function. In this case, logics that delays 1 sec can be applied to any function passed in. By this way, logics to delay one sec can be modularized and developers do not need to write it once and once again.  

In function `delay`, it returns a function. The returned function take one function and one number to execute. The reason a pair of brackets in the end is force function `delay` to execute and return a new anonymous function.  

In the function `searchClient`, 2 parameters are passed to the returned function of delay, first one is a function: `()=>{xxx}` and the second one is a number. After 2 parameters passed, delay will wait 1000 ms and then execute the function passed.  

Logics to delay 1000 ms is not accessiable form outside, and this is called <b>namespace control</b>. Logics to delay 1000ms can be reused as a module in by using function `delay`, and this is called <b>function modularization</b>.  
